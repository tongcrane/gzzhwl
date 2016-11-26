package com.gzzhwl.rest.springmvc.model;

import java.io.Serializable;

public class ResponseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8831045043570812654L;
	private Object data;
	private ResponseStatusModel statusModel;

	public ResponseModel(Object data) {
		this.data = data;
	}

	public ResponseModel(ResponseStatusModel statusCode) {
		this.data = null;
		this.statusModel = statusCode;
	}

	public Object getData() {
		return data;
	}

	public ResponseStatusModel getStatusModel() {
		return statusModel;
	}

}
