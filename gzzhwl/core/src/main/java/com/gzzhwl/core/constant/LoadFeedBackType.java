package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum LoadFeedBackType {

	SOURCEADMIN("01", "货主异常反馈"),VEHICLE("02", "车辆异常反馈"),DRIVER("03", "司机异常反馈"),GPS("04", "位置信息");
	
	
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
	
	private LoadFeedBackType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String toString(){
		return this.code;
	}
	
	public static LoadFeedBackType getLoadFeedBackType(String code) throws NotFoundEnumException {
		for (LoadFeedBackType lb : values()) {
			if (code.equals(lb.getCode())) {
				return lb;
			}
		}
		throw new NotFoundEnumException();
	}
	
}
