package com.gzzhwl.cbs.exception;

import com.gzzhwl.cbs.model.RemotingStatus;

public class RestException extends RuntimeException {
	private static final long serialVersionUID = 8397226659388376389L;

	private String errorCode;
	private String errorMsg;

	public RestException(RemotingStatus status) {
		this(status.getStatusCode(), status.getErrorMsg());
	}

	public RestException(String errorCode, String errorMsg) {
		this(errorCode, errorMsg, null);
	}

	public RestException(String errorCode, String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getStatus() {
		return this.errorCode;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

}
