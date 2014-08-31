package com.browniebytes.javafx.skymapfx.gui.util;

import com.google.inject.Injector;

import javafx.util.Callback;

public class GuiceControllerFactory implements Callback<Class<?>, Object> {

	private final Injector injector;

	public GuiceControllerFactory(final Injector injector) {
		this.injector = injector;
	}

	/**
	 * The <code>call</code> method is called when required, and is given a
	 * single argument of type P, with a requirement that an object of type R is
	 * returned.
	 *
	 * @param param The single argument upon which the returned value should be
	 *              determined.
	 * @return An object of type R that may be determined based on the provided
	 *         parameter value.
	 */
	@Override
	public Object call(Class<?> param) {
		return injector.getInstance(param);
	}
}
