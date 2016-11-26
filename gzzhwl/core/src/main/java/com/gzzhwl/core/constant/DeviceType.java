package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum DeviceType {
	WEB("01", "网页", LoginType.WEB), ANDROID("02", "安卓", LoginType.MOBILE), IOS("03", "苹果", LoginType.MOBILE);

	private String code;
	private String deviceName;
	private LoginType loginType;

	public LoginType getLoginType() {
		return loginType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getCode() {
		return code;
	}

	private DeviceType(String code, String deviceName, LoginType loginType) {
		this.code = code;
		this.deviceName = deviceName;
		this.loginType = loginType;
	}

	public static DeviceType getDeviceType(String code) throws NotFoundEnumException {
		for (DeviceType dt : values()) {
			if (code.equals(dt.getCode())) {
				return dt;
			}
		}
		throw new NotFoundEnumException();
	}

}
