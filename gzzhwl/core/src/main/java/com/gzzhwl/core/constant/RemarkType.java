package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum RemarkType {

	QUOTED("01", "报价备注"),SOURCE("02", "货源备注"),ORDER("03", "订单备注"),LOAD("04", "提货单备注"),ONLINE("05", "在途记录");
	
	
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
	
	private RemarkType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String toString(){
		return this.code;
	}
	
	public static RemarkType getRemarkType(String code) throws NotFoundEnumException {
		for (RemarkType rt : values()) {
			if (code.equals(rt.getCode())) {
				return rt;
			}
		}
		throw new NotFoundEnumException();
	}
	
}
