package com.browniebytes.javafx.skymapfx.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.skymapfx.ApplicationSettings;
import com.browniebytes.javafx.skymapfx.ApplicationSettings.Settings;
import com.browniebytes.javafx.skymapfx.data.entities.Star;
import com.browniebytes.javafx.skymapfx.data.io.CatalogFileReader;
import com.google.inject.Inject;

/**
 * 
 */
public class PrimaryController implements Initializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryController.class);

	// FXML dependencies
	@FXML private TextField latitudeTextField;
	@FXML private TextField longitudeTextField;
	@FXML private CheckBox useCurrentTimeCheckBox;
	@FXML private Label mapLocalTimeLabel;
	@FXML private Label gmtTimeTextField;
	@FXML private Label jdLabel;
	@FXML private Label mjdLabel;
	@FXML private Label gmstLabel;
	@FXML private Label gastLabel;
	@FXML private Label lmstLabel;

	// Guice injected dependencies
	private final ApplicationSettings applicationSettings;
	private final CatalogFileReader catalogReader;
	private final SessionFactory sessionFactory;
	private final ScheduledExecutorService executor;

	// Private instance variables
	private ScheduledFuture<Void> timeUpdateFuture;

	@Inject
	public PrimaryController(
			final ApplicationSettings applicationSettings,
			final CatalogFileReader catalogReader,
			final SessionFactory sessionFactory,
			final ScheduledExecutorService executor) {

		this.applicationSettings = applicationSettings;
		this.sessionFactory = sessionFactory;
		this.catalogReader = catalogReader;
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

	/**
	 * Starts the timer
	 */
	private void startTimer() {
		LOGGER.debug("Starting time update thread");
		timeUpdateFuture = (ScheduledFuture<Void>) executor.schedule(
				new Callable<Void>() {
					@Override
					public Void call() throws Exception {
						return null;
					}
				},
				1,
				TimeUnit.SECONDS);
	}

	/**
	 * Stops the timer
	 */
	private void stopTimer() {
		LOGGER.debug("Stopping time update thread");
		timeUpdateFuture.cancel(false);
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

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Stars found in database: " + getStarCount());
			}
			return null;
		}

		/**
		 * Executed when data load has completed
		 */
		@Override
		protected void done() {
			LOGGER.debug("Star database initialization complete");

			if (useCurrentTimeCheckBox.isSelected()) {
				startTimer();
			}
		}
	}

}
