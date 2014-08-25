package com.browniebytes.javafx.skymapfx.exceptions;

/**
 * This exception should be thrown when an unrecoverable error occurs, which
 * causes the application to not be able to perform its actions and should
 * probably terminate.
 */
public class FatalRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new FatalRuntimeException
	 * @param message   Message
	 * @param throwable Cause
	 */
	public FatalRuntimeException(
			final String message,
			final Throwable throwable) {

		super(message, throwable);
	}
}
