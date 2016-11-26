package com.gzzhwl.core.message;

public enum TipsSourceType {
	
	/**
	 * 报价
	 */
	TIPS_S01("01","报价"),
	/**
	 * 提货单
	 */
	TIPS_S02("02","提货单");
	
	
	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private TipsSourceType(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}

}
