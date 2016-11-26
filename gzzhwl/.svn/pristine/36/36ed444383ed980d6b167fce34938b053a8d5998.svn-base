package com.gzzhwl.api.notice.model;

import com.gzzhwl.rest.exception.RestException;

public enum SmsType {

	REG("00"), FORGET("01"), EXCHANGE("02"),STAFF("03");

	private String code;

	private SmsType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static SmsType getSmsType(String code) throws RestException {
		for (SmsType st : values()) {
			if (code.equals(st.getCode())) {
				return st;
			}
		}
		throw new RestException("91000","no this smsType");
	}

}
