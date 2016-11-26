package com.gzzhwl.rest.exception;

import com.gzzhwl.rest.springmvc.exception.InternalException;

/**
 * 内部错误类定义
 * 
 * @author anycrane
 */
public class RestServerException extends RuntimeException implements InternalException {

	private static final long serialVersionUID = 7786257477612659782L;

	public RestServerException() {
		super();
	}

	public RestServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RestServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestServerException(String message) {
		super(message);
	}

	public RestServerException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorMsg() {
		return this.getMessage();
	}

}
