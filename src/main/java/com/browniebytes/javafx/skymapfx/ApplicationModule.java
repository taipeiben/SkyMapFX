package com.browniebytes.javafx.skymapfx;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Application module used to instruct Guice on how to inject dependencies 
 */
public class ApplicationModule extends AbstractModule {

	/**
	 * Application settings
	 */
	private final ApplicationSettings applicationSettings;

	/**
	 * Initialize resources
	 */
	public ApplicationModule() {
		this.applicationSettings = new ApplicationSettings();
	}

	/**
	 * Configure bindings for Guice
	 */
	@Override
	protected void configure() {
	}

	/**
	 * Provides the ApplicationSettings instance
	 * @return The ApplicationSettings instance
	 */
	@Provides
	@Singleton
	protected ApplicationSettings provideApplicationSettings() {
		return applicationSettings;
	}

}
