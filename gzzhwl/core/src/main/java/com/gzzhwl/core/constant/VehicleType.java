package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum VehicleType{

	CAR("01", "车辆"),HEAD("02", "车头"),HUNG("03","车挂");
	
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
	private VehicleType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String toString(){
		return this.code;
	}
	
	public static VehicleType getVehicleType(String code) throws NotFoundEnumException {
		for (VehicleType lt : values()) {
			if (code.equals(lt.getCode())) {
				return lt;
			}
		}
		throw new NotFoundEnumException();
	}
	
	
	
}
