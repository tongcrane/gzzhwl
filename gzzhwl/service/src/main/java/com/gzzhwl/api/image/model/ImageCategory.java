package com.gzzhwl.api.image.model;

/**
 * 图片文件夹类型定义
 * 
 * @author anycrane
 *
 */
public enum ImageCategory {
	/**
	 * 经纪人图片
	 */
	AGENT("01"),
	/**
	 * 司机图片
	 */
	DRIVER("02"),
	/**
	 * 车辆图片
	 */
	VEHICLE("03"),
	/**
	 * 账户图片
	 */
	ACCOUNT("04"),
	
	/**
	 * 
	 * 运势界司机车辆图片
	 */
	DRIVER_VEHICLE("05"),
	
	/**
	 * 
	 * 货主合同图片
	 */
	SOURCEAGREEMENT("06"),
	
	/**
	 * 
	 * 异常反馈图片
	 */
	FEEDBACK("07"),
	
	/**
	 * 
	 * 电子回单
	 */
	ELEC("08"),
	
	/**
	 * 
	 * 快递单
	 */
	EXPRESSBILL("09")
	;
	
	private String code;
	
	private ImageCategory(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}