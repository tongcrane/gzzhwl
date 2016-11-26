package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum DataSource {
	PLATFORM("01", "内部"), VLORRY("02", "运势界");

	private String code;
	private String name;

	public String getLoginName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	private DataSource(String code, String name) {
		this.name = name;
		this.code = code;
	}

	public static DataSource getDataSource(String code) throws NotFoundEnumException {
		for (DataSource ds : values()) {
			if (code.equals(ds.getCode())) {
				return ds;
			}
		}
		throw new NotFoundEnumException();
	}
}
