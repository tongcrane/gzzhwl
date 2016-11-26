package com.gzzhwl.excel.exception;

public class ExcelParsingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8617300189487478264L;

	public ExcelParsingException(String message) {
		super(message);
	}

	public ExcelParsingException(String message, Exception exception) {
		super(message, exception);
	}

}
