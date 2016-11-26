package com.gzzhwl.common.model;

public enum FlowActionCategory {
	/**
	 * 系统
	 */
	SYSTEM("00"),
	/**
	 * CBS
	 */
	CBS("01"),
	/**
	 * CBS_YSJ
	 */
	CBS_YSJ("02"),
	/**
	 * YSJ
	 */
	YSJ("03");

	private String code;

	private FlowActionCategory(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
