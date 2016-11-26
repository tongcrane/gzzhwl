package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum RegSource {
	WEB("01", "网页", DeviceType.WEB), 
	
	ANDROID("02", "安卓", DeviceType.ANDROID), 
	
	IOS("03", "苹果", DeviceType.IOS), 
	
	CBS("04", "CBS", DeviceType.ANDROID);

	private String code;
	private String regName;
	private DeviceType deviceType;

	public static RegSource getRegSource(String code) throws NotFoundEnumException {
		for (RegSource rs : values()) {
			if (code.equals(rs.getCode())) {
				return rs;
			}
		}
		throw new NotFoundEnumException();
	}

	public String getRegName() {
		return regName;
	}

	public String getCode() {
		return code;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	private RegSource(String code, String regName, DeviceType deviceType) {
		this.code = code;
		this.regName = regName;
		this.deviceType = deviceType;
	}
}
