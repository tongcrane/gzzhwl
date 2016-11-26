package com.gzzhwl.rest.springmvc.model;

public class ResponseStatusModel {
	private String statusCode;
	private String errorMsg;

	public ResponseStatusModel(String statusCode) {
		this(statusCode, null);
	}

	public ResponseStatusModel(String statusCode, String errorMsg) {
		this.statusCode = statusCode;
		this.errorMsg = errorMsg;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
