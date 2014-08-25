package com.browniebytes.javafx.skymapfx;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * 
 *
 */
public class ApplicationModule extends AbstractModule {

	private final ApplicationSettings applicationSettings;

	public ApplicationModule() {
		this.applicationSettings = new ApplicationSettings();
	}

	@Override
	protected void configure() {

	}

	@Provides
	@Singleton
	protected ApplicationSettings provideApplicationSettings() {
		return applicationSettings;
	}

}
