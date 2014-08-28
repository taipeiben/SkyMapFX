package com.browniebytes.javafx.skymapfx;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;

/**
 * Entry point for SkyMapFX application
 */
public class SkyMapFXMain {

	private static Logger LOGGER = LoggerFactory.getLogger(SkyMapFXMain.class);
	private static Server DB_SERVER;

	/**
	 * Main entry point
	 *
	 * @param args Arguments
	 */
	public static void main(final String[] args) {

		Runtime.getRuntime().addShutdownHook(
				new Thread(
						() -> {
							if (DB_SERVER != null) {
								LOGGER.info("Shutting down database ...");
								DB_SERVER.stop();
							}
						}));

		try {
			// Create a new application module
			final ApplicationModule module = new ApplicationModule();

			// Setup database properties
			LOGGER.info("Starting database ...");
			DB_SERVER = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon", "-baseDir", "data");
			DB_SERVER.start();
		} catch (FatalRuntimeException | SQLException ex) {
			LOGGER.error("Fatal exception encountered, terminating application", ex);
			System.exit(1);
		}
	}

}
