package com.browniebytes.javafx.skymapfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;
import com.browniebytes.javafx.skymapfx.gui.util.GuiceControllerFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Entry point for SkyMapFX application
 */
public class SkyMapFXMain extends Application {

	private static Logger LOGGER = LoggerFactory.getLogger(SkyMapFXMain.class);

	private static ApplicationModule APP_MODULE;
	private static Injector INJECTOR;

	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(new GuiceControllerFactory(INJECTOR));
		fxmlLoader.setLocation(SkyMapFXMain.class.getResource("/fxml/PrimaryPane.fxml"));

		final Scene scene = new Scene(fxmlLoader.load());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Main entry point
	 *
	 * @param args Arguments
	 */
	public static void main(final String[] args) {

		Runtime.getRuntime().addShutdownHook(
				new Thread(
						() -> {
							APP_MODULE.shutdown();
						}));

		try {
			// Setup database properties
			LOGGER.info("Starting database ...");

			// Create a new application module
			APP_MODULE = new ApplicationModule();

			INJECTOR = Guice.createInjector(APP_MODULE);

			launch(args);
		} catch (FatalRuntimeException ex){ // | SQLException ex) {
			LOGGER.error("Fatal exception encountered, terminating application", ex);
			System.exit(1);
		}
	}

}
