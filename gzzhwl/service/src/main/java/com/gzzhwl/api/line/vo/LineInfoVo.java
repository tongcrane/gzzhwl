package com.gzzhwl.api.line.vo;

public class LineInfoVo {
	private String lineInfoId; // 线路标识
	private String departureCode; // 出发地所在区域
	private String destinationCode; // 目的地所在区域
	public String getDepartureCode() {
		return departureCode;
	}
	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}
	public String getDestinationCode() {
		return destinationCode;
	}
	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}
	public String getLineInfoId() {
		return lineInfoId;
	}
	public void setLineInfoId(String lineInfoId) {
		this.lineInfoId = lineInfoId;
	}
	
	
}
