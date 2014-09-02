package com.browniebytes.javafx.skymapfx.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;

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
import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;
import com.google.inject.Inject;

public class PrimaryController implements Initializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryController.class);

	// Guice injected dependencies
	private final ApplicationSettings applicationSettings;
	private final CatalogFileReader catalogReader;
	private final SessionFactory sessionFactory;
	private final ScheduledExecutorService executor;

	@FXML private TextField latitudeTextField;
	@FXML private TextField longitudeTextField;
	@FXML private CheckBox useCurrentTimeCheckBox;
	@FXML private Label mapLocalTimeTextField;

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
	public void initialize(URL location, ResourceBundle resources) {
		latitudeTextField.setText(applicationSettings.getSettingAsString(Settings.LATITUDE));
		longitudeTextField.setText(applicationSettings.getSettingAsString(Settings.LONGITUDE));

		LOGGER.debug("Starting data initialization");
		final DataInitializationTask readerTask = new DataInitializationTask();
		executor.submit(readerTask);
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
	 * This task class checks to see if star data is loaded into the database yet.  If data has not been
	 * loaded, then it calls the catalog data reader to read data from the data file into the database.
	 */
	private class DataInitializationTask extends Task<Void> {
		@Override
		protected Void call() throws Exception {
			final long starCount = getStarCount();
			if (starCount == 0) {
				try {
					catalogReader.buildStarDatabase();
					LOGGER.debug("Stars found in database: " + getStarCount());
				} catch (FatalRuntimeException ex) {
					// TODO: handle exception
				}
			}
			return null;
		}

		@Override
		protected void done() {
			
		}
	}
}
