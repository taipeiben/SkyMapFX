package com.browniebytes.javafx.skymapfx.gui.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.control.DateTimePicker;
import com.browniebytes.javafx.skymapfx.ApplicationSettings;
import com.browniebytes.javafx.skymapfx.ApplicationSettings.Settings;
import com.browniebytes.javafx.skymapfx.data.dao.DeepSkyObjectDao;
import com.browniebytes.javafx.skymapfx.data.dao.StarDao;
import com.browniebytes.javafx.skymapfx.data.domain.TimeComputations;
import com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject;
import com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject.DsoCatalog;
import com.browniebytes.javafx.skymapfx.data.domain.entities.Star;
import com.browniebytes.javafx.skymapfx.data.io.HipparcosCatalogFileReader;
import com.browniebytes.javafx.skymapfx.data.io.NgcCatalogFileReader;
import com.browniebytes.javafx.skymapfx.gui.view.SkyMapCanvas;
import com.google.inject.Inject;

/**
 * 
 */
public class PrimaryController implements Initializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryController.class);
	private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MM/d/yy HH:mm:ss a");
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	// ========================================
	// FXML dependencies
	// ========================================

	// User Context
	@FXML private TextField latitudeTextField;
	@FXML private TextField longitudeTextField;
	@FXML private CheckBox useCurrentTimeCheckBox;
	@FXML private DateTimePicker dateTimePicker;

	// Time Information
	@FXML private Label mapLocalTimeLabel;
	@FXML private Label gmtTimeLabel;
	@FXML private Label jdLabel;
	@FXML private Label mjdLabel;
	@FXML private Label gmstLabel;
	@FXML private Label gastLabel;
	@FXML private Label lmstLabel;

	// Map Controls
	@FXML private CheckBox drawConstellationLinesCheckBox;
	@FXML private CheckBox showConstellationNamesCheckBox;
	@FXML private CheckBox drawAltAziCheckBox;
	@FXML private CheckBox flipHorizontalCheckBox;
	@FXML private Slider dsoMagnitudeSlider;

	// Canvas
	@FXML private StackPane canvasPane;
	@FXML private SkyMapCanvas canvas;

	// ========================================
	// Guice injected dependencies
	// ========================================
	private final ApplicationSettings applicationSettings;
	private final HipparcosCatalogFileReader catalogReader;
	private final NgcCatalogFileReader ngcReader;
	private final StarDao starDao;
	private final DeepSkyObjectDao dsoDao;
	private final SessionFactory sessionFactory;
	private final ScheduledExecutorService executor;

	// ========================================
	// Private instance variables
	// ========================================
	private ScheduledFuture<Void> timeUpdateFuture;

	@Inject
	public PrimaryController(
			final ApplicationSettings applicationSettings,
			final HipparcosCatalogFileReader catalogReader,
			final NgcCatalogFileReader ngcReader,
			final StarDao starDao,
			final DeepSkyObjectDao dsoDao,
			final SessionFactory sessionFactory,
			final ScheduledExecutorService executor) {

		this.applicationSettings = applicationSettings;
		this.sessionFactory = sessionFactory;
		this.catalogReader = catalogReader;
		this.ngcReader = ngcReader;
		this.starDao = starDao;
		this.dsoDao = dsoDao;
		this.executor = executor;
	}

	/**
	 * Invoked after all @FXML dependencies are injected.  This is run on the application thread.
	 */
	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		latitudeTextField.setText(applicationSettings.getSettingAsString(Settings.LATITUDE));
		longitudeTextField.setText(applicationSettings.getSettingAsString(Settings.LONGITUDE));
		useCurrentTimeCheckBox.selectedProperty().set(
				applicationSettings.getSettingAsBoolean(Settings.USE_CURRENT_TIME));

		LOGGER.debug("Starting data initialization");
		final DataInitializationTask readerTask = new DataInitializationTask();
		executor.submit(readerTask);

		useCurrentTimeCheckBox.selectedProperty().addListener(
				(observable, oldValue, newValue) -> {
					if (newValue) {
						startTimer();
					} else {
						stopTimer();
					}
				});

		canvasPane.widthProperty().addListener(
				(observable, oldValue, newValue) -> {
					final double canvasPaneHeight = canvasPane.getHeight();
					final double desired = Math.min(canvasPaneHeight, newValue.doubleValue());
					canvas.widthProperty().set(desired-20.0);
					canvas.heightProperty().set(desired-20.0);
				});
		canvasPane.heightProperty().addListener(
				(observable, oldValue, newValue) -> {
					final double canvasPaneWidth = canvasPane.getWidth();
					final double desired = Math.min(canvasPaneWidth, newValue.doubleValue());
					canvas.heightProperty().set(desired-20.0);
					canvas.widthProperty().set(desired-20.0);
				});
	}

	/**
	 * Retrieves the number of stars in the database
	 * @return Number of stars in the database
	 */
	private long getStarCount() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			final Criteria criteria = session.createCriteria(Star.class);
			return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		} finally {
			session.close();
		}
	}

	private long getDsoCount(final DsoCatalog catalog) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			final Criteria criteria = session.createCriteria(DeepSkyObject.class);
			return (Long) criteria
					.setProjection(Projections.rowCount())
					.add(Restrictions.eq("catalog", catalog))
					.uniqueResult();
		} finally {
			session.close();
		}
	}

	/**
	 * Starts the timer
	 */
	@SuppressWarnings("unchecked")
	private void startTimer() {
		LOGGER.debug("Starting time update thread");
		timeUpdateFuture = (ScheduledFuture<Void>) executor.scheduleAtFixedRate(
				() -> {
					final LocalDateTime localDateTime = LocalDateTime.now();

					LOGGER.debug(
							String.format(
									"Setting date/time to: %s",  
									DATETIME_FORMATTER.format(localDateTime)));

					Platform.runLater(
							() -> {
								// Changing UI value must be done on application thread
								dateTimePicker.dateTimeProperty().set(localDateTime);
								startTimeComputations();
							});
				},
				0L,  // no delay
				1L, // period
				TimeUnit.SECONDS); // in seconds
	}

	/**
	 * Stops the timer
	 */
	private void stopTimer() {
		LOGGER.debug("Stopping time update thread");
		timeUpdateFuture.cancel(false);
	}

	/**
	 * Runs time computations on separate thread
	 */
	private void startTimeComputations() {
		executor.submit(new TimeComputationTask());
	}

	/**
	 * This task class calls the catalog data reader to read data from the data file into the database.
	 */
	private class DataInitializationTask extends Task<Void> {
		/**
		 * Uses catalog reader to build star database from a data file
		 */
		@Override
		protected Void call() throws Exception {
			catalogReader.buildStarDatabase();
			ngcReader.buildDeepSkyObjectDatabase();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Stars found in database: " + getStarCount());

				LOGGER.debug(
						String.format(
								"DSOs from %s catalog found in database: %d",
								DsoCatalog.INDEX.toString(),
								getDsoCount(DsoCatalog.INDEX)));
				LOGGER.debug(
						String.format(
								"DSOs from %s catalog found in database: %d",
								DsoCatalog.NEW_GENERAL.toString(),
								getDsoCount(DsoCatalog.NEW_GENERAL)));
				LOGGER.debug(
						String.format(
								"DSOs from %s catalog found in database: %d",
								DsoCatalog.MESSIER.toString(),
								getDsoCount(DsoCatalog.MESSIER)));
			}

			return null;
		}

		/**
		 * Executed when data load has completed
		 */
		@Override
		protected void done() {
			LOGGER.debug("Database initialization complete");

			if (useCurrentTimeCheckBox.isSelected()) {
				startTimer();
			}
		}
	}

	/**
	 * This task computes time computations and then updates the UI once computations are completed.
	 */
	private class TimeComputationTask extends Task<TimeComputations> {
		/**
		 * Performs time computations on separate thread
		 */
		@Override
		protected TimeComputations call() throws Exception {
			final LocalDateTime localDateTime = dateTimePicker.dateTimeProperty().get();

			final TimeComputations computations = new TimeComputations(
					localDateTime,
					Double.parseDouble(longitudeTextField.textProperty().get())); // TODO: process NumberFormatException

			starDao.updateAltitudeAzimuth(
					Math.toRadians(Double.parseDouble(latitudeTextField.getText())),
					Math.toRadians(computations.getLmstDeg()));
			dsoDao.updateAltitudeAzimuth(
					Math.toRadians(Double.parseDouble(latitudeTextField.getText())),
					Math.toRadians(computations.getLmstDeg()));

			return computations;
		}

		/**
		 * Sets the UI text properties on application thread
		 */
		@Override
		protected void done() {
			try {
				final TimeComputations computations = get();

				final Map<Long, Star> starList = starDao.findAllByPositiveAltitude();
				final List<DeepSkyObject> dsoList = dsoDao.findAllByPositiveAltitude();

				Platform.runLater(
						() -> {
							mapLocalTimeLabel.textProperty().set(
									DATETIME_FORMATTER.format(
											computations.getLocalDateTime()));
							gmtTimeLabel.setText(
									DATETIME_FORMATTER.format(
											computations.getGmtTime()));

							jdLabel.setText(String.format("%.3f", computations.getJd()));
							mjdLabel.setText(String.format("%.3f", computations.getMjd()));

							gmstLabel.setText(TIME_FORMATTER.format(computations.getGmst()));
							gastLabel.setText(TIME_FORMATTER.format(computations.getGast()));
							lmstLabel.setText(TIME_FORMATTER.format(computations.getLmst()));

							canvas.redraw(
									drawConstellationLinesCheckBox.isSelected(),
									showConstellationNamesCheckBox.isSelected(),
									drawAltAziCheckBox.isSelected(),
									flipHorizontalCheckBox.isSelected(),
									starList,
									dsoList,
									dsoMagnitudeSlider.getValue());
						});
			} catch (ExecutionException ex) {
				// TODO: handle exception
				LOGGER.error("Execution exception", ex);
			} catch (InterruptedException ex) {}  // Do nothing if interrupted
		}
	}

}
