package com.gzzhwl.admin.lend.vo;

public class LendInfoVo {

	private java.lang.String lendNo; // 借调单号
	
	private java.lang.String toDepartId; // 借用方
	
	private java.lang.String toDepartName; // 借用方
	
	private java.lang.String startTime; // 开始借用时间

	private java.lang.String endTime; // 计划归还时间
	
	private java.lang.String realReturnedTime; // 实际归还时间
	
	private java.lang.String departureCodeCn; // 线路出发地

	private java.lang.String destinationCodeCn; // 线路目的地
	
	private java.lang.String statusCn; // 线路目的地
	
	private String status;
	
	private String vehicleInfoId;
	
	private String driver1InfoId;
	
	private String driver2InfoId;
	
	

	public java.lang.String getToDepartName() {
		return toDepartName;
	}

	public void setToDepartName(java.lang.String toDepartName) {
		this.toDepartName = toDepartName;
	}

	public String getVehicleInfoId() {
		return vehicleInfoId;
	}

	public void setVehicleInfoId(String vehicleInfoId) {
		this.vehicleInfoId = vehicleInfoId;
	}

	public String getDriver1InfoId() {
		return driver1InfoId;
	}

	public void setDriver1InfoId(String driver1InfoId) {
		this.driver1InfoId = driver1InfoId;
	}

	public String getDriver2InfoId() {
		return driver2InfoId;
	}

	public void setDriver2InfoId(String driver2InfoId) {
		this.driver2InfoId = driver2InfoId;
	}

	public java.lang.String getLendNo() {
		return lendNo;
	}

	public void setLendNo(java.lang.String lendNo) {
		this.lendNo = lendNo;
	}

	public java.lang.String getToDepartId() {
		return toDepartId;
	}

	public void setToDepartId(java.lang.String toDepartId) {
		this.toDepartId = toDepartId;
	}

	public java.lang.String getStartTime() {
		return startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getEndTime() {
		return endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getRealReturnedTime() {
		return realReturnedTime;
	}

	public void setRealReturnedTime(java.lang.String realReturnedTime) {
		this.realReturnedTime = realReturnedTime;
	}

	public java.lang.String getDepartureCodeCn() {
		return departureCodeCn;
	}

	public void setDepartureCodeCn(java.lang.String departureCodeCn) {
		this.departureCodeCn = departureCodeCn;
	}

	public java.lang.String getDestinationCodeCn() {
		return destinationCodeCn;
	}

	public void setDestinationCodeCn(java.lang.String destinationCodeCn) {
		this.destinationCodeCn = destinationCodeCn;
	}

	public java.lang.String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(java.lang.String statusCn) {
		this.statusCn = statusCn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
