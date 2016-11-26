package com.gzzhwl.api.driver.vo;

public class DriverInfoVo  {
	private String driverInfoId;
	private String realName; // 真实姓名
	private String idno; // 身份证号
	private String telphone; // 手机号
	private String idFImageRefId; // 身份照片(正)
	private String idBImageRefId; // 身份照片(反)
	private String drivingFImageRefId; // 驾驶证照片(正)
	private String qualifiImageRefId; // 从业资格证照片
	private String qualifiImage2RefId; // 从业资格证照片 2
	
	
	public String getQualifiImage2RefId() {
		return qualifiImage2RefId;
	}

	public void setQualifiImage2RefId(String qualifiImage2RefId) {
		this.qualifiImage2RefId = qualifiImage2RefId;
	}

	public String getDriverInfoId() {
		return driverInfoId;
	}

	public void setDriverInfoId(String driverInfoId) {
		this.driverInfoId = driverInfoId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getIdFImageRefId() {
		return idFImageRefId;
	}

	public void setIdFImageRefId(String idFImageRefId) {
		this.idFImageRefId = idFImageRefId;
	}

	public String getIdBImageRefId() {
		return idBImageRefId;
	}

	public void setIdBImageRefId(String idBImageRefId) {
		this.idBImageRefId = idBImageRefId;
	}

	public String getDrivingFImageRefId() {
		return drivingFImageRefId;
	}

	public void setDrivingFImageRefId(String drivingFImageRefId) {
		this.drivingFImageRefId = drivingFImageRefId;
	}

	public String getQualifiImageRefId() {
		return qualifiImageRefId;
	}

	public void setQualifiImageRefId(String qualifiImageRefId) {
		this.qualifiImageRefId = qualifiImageRefId;
	}

}
