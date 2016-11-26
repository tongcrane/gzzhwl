package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum ConsignType {

	CONSIGNNORMAL("01", "待处理"), 
	
	CONSIGNCANCEL("02", "已取消"),
	
	CONSIGN_WAIT("03", "待审核"),
	
	CONSIGN_VERIFIED("04", "已审核");

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

	private ConsignType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String toString() {
		return this.code;
	}

	public static ConsignType getConsignType(String code) throws NotFoundEnumException {
		for (ConsignType lt : values()) {
			if (code.equals(lt.getCode())) {
				return lt;
			}
		}
		throw new NotFoundEnumException();
	}
}
