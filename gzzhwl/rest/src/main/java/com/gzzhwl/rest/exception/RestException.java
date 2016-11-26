package com.gzzhwl.rest.exception;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.rest.springmvc.exception.StatusException;

public class RestException extends RuntimeException implements StatusException {
	private static final long serialVersionUID = 8397226659388376389L;

	private String errorCode;
	private String errorMsg;

	public RestException(ErrorCode errorCode) {
		this(errorCode.getCode(), errorCode.getDesc());
	}

	public RestException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public RestException(String errorCode, String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getStatus() {
		return this.errorCode;
	}

	@Override
	public String getErrorMsg() {
		return this.errorMsg;
	}

}
