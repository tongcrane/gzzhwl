package com.gzzhwl.core.exception;

import java.util.List;

public class ModelValidException extends RuntimeException {
	private List<String> errorMessage;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7215443553253395113L;

	public ModelValidException() {
		super();
	}

	public ModelValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModelValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModelValidException(String message) {
		super(message);
	}

	public ModelValidException(Throwable cause) {
		super(cause);
	}

	public ModelValidException(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

}
