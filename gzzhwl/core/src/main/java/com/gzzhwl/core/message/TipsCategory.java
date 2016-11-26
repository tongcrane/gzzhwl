package com.gzzhwl.core.message;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum TipsCategory {
	
	/**
	 * 注册成功
	 */
	TIPS_C01("01","注册成功"),
	/**
	 * 认证成功
	 */
	TIPS_C02("02","认证成功"),
	/**
	 * 账号认证失败
	 */
	TIPS_C03("03","认证失败"),
	/**
	 * 中标提醒
	 */
	TIPS_C04("04","中标提醒"),
	/**
	 * 发车提醒
	 */
	TIPS_C05("05","发车提醒");
	
	private String code;
	private String desc;

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	private TipsCategory(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getCategoryDesc(String code) throws NotFoundEnumException {
		for (TipsCategory tc : values()) {
			if (code.equals(tc.getCode())) {
				String desc = tc.getDesc();
				return desc;
			}
		}
		throw new NotFoundEnumException();
	}

}
