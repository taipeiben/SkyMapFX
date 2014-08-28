package com.browniebytes.javafx.skymapfx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;


public class ApplicationSettings {

	/**
	 * Enumeration of available application settings
	 */
	public enum Settings {
		/**
		 * User's desired latitude
		 */
		LATITUDE("latitude", new DefaultValue<Double>(33.902729)),

		/**
		 * User's desired longitude
		 */
		LONGITUDE("longitude", new DefaultValue<Double>(-117.897096)),

		/**
		 * Minimum magnitude to view
		 */
		MIN_MAGNITUDE("min_magnitude", new DefaultValue<Double>(7.0));

		/**
		 * Key to use in the config file
		 */
		private final String key;

		/**
		 * Default value
		 */
		private final DefaultValue<?> defaultValue;

		/**
		 * Constructs a new Setting
		 * @param key The key for the configurable parameter
		 */
		private Settings(final String key, final DefaultValue<?> defaultValue) {
			this.key = key;
			this.defaultValue = defaultValue;
		}

		/**
		 * Gets the key for the configurable parameter
		 * @return The key for the configurable parameter
		 */
		public String getKey() {
			return key;
		}
	}

	/**
	 * Relative path to directory containing configuration files
	 */
	private static final String CONFIG_DIR = "config";

	/**
	 * Application settings properties file name
	 */
	private static final String CONFIG_FILE_NAME = "settings.properties";

	/**
	 * Relative path to configuration file
	 */
	private static final String CONFIG_FILE_LOCATION = CONFIG_DIR + "/" + CONFIG_FILE_NAME;

	/**
	 * Error message from Apache Commons Configuration when it cannot locate the file with the given file name.
	 */
	private static final String CANNOT_LOCATE_CONFIG_FILE_ERROR_MSG =
			"Cannot locate configuration source config/settings.properties";

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationSettings.class);

	/**
	 * Properties file configuration
	 */
	private PropertiesConfiguration config;

	/**
	 * Checks to see if an existing config file exists.  If the file is found, then the application settings
	 * are loaded from this file.  If the file is not found, then a new default one is generated.
	 */
	public ApplicationSettings() {

		try {
			LOGGER.debug(
					String.format(
							"Attempting to loading application settings file: %s",
							CONFIG_FILE_LOCATION));
			config = new PropertiesConfiguration(CONFIG_FILE_LOCATION);

			// Turn on auto save so changes get persisted
			config.setAutoSave(true);

			LOGGER.info("Application settings loaded");
		} catch (ConfigurationException ex) {
			if (ex.getMessage().equals(CANNOT_LOCATE_CONFIG_FILE_ERROR_MSG)) {
				// Generate a new default
				LOGGER.info(
						"Application settings file not found, generating a new file using defaults");
				generateDefault();
			} else {
				throw new FatalRuntimeException(
						"Unknown error occurred when reading configuration file",
						ex);
			}
		}
	}

	/**
	 * Returns a setting as a double precision floating point number
	 * @param setting The setting to retrieve
	 * @return The setting value as a double precision floating point number
	 */
	public Double getSettingAsDouble(final Settings setting) {
		return config.getDouble(setting.key);
	}

	private void generateDefault() {
		// Check if directory exists, and create it if it doesn't
		generateConfigDirectory();

		// Generate default settings
		generateDefaultSettings();
	}

	/**
	 * Creates new configuration directory if it doesn't exist already
	 */
	private void generateConfigDirectory() {
		// Check if configuration directory exists
		final Path configDirPath = Paths.get(CONFIG_DIR);

		// If it doesn't, create the directory
		if (Files.notExists(configDirPath)) {
			try {
				LOGGER.info("Config directory does not exist, creating ...");
				Files.createDirectory(configDirPath);
			} catch (IOException ex) {

				// Unrecoverable if IOException occurs, application cannot
				// function as intended
				throw new FatalRuntimeException(
						"IOException encountered when attempting to create directory",
						ex);
			}
		}
	}

	/**
	 * Creates a new configuration file
	 */
	private void generateDefaultSettings() {
		// Create new configuration from scratch
		config = new PropertiesConfiguration();

		// Populate configuration with default values from enumeration
		for (Settings setting : Settings.values()) {
			config.addProperty(
					setting.key,
					String.valueOf(setting.defaultValue.value));
		}

		// Set filename and save
		config.setFileName(CONFIG_FILE_LOCATION);
		try {
			LOGGER.info("Creating new config file ...");
			config.save();
		} catch (ConfigurationException ex) {
			// Unrecoverable if we get an error during the save operation
			throw new FatalRuntimeException(
					"Error occurred when creating new configuration file",
					ex);
		}

		// Turn on auto save so when user changes settings, they'll get persisted
		config.setAutoSave(true);
	}

	/**
	 * Class that holds a default value
	 *
	 * @param <T> Value type (Double, String, etc.)
	 */
	private static class DefaultValue<T> {
		private T value;

		public DefaultValue(T value) {
			this.value = value;
		}
	}
}
