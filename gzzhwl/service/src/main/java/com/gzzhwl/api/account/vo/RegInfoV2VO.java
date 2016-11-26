package com.gzzhwl.api.account.vo;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.gzzhwl.api.driver.vo.DriverInfoVo;
import com.gzzhwl.api.vehicle.vo.VehicleInfoVo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegInfoV2VO {
	private String telphone;// 手机号
	private String password;// 密码
	private String barCode;// 短信验证码
	private String deviceType;// 设备类型
	private String channel;
	private String token;
	private List<Line> lineList;// 常跑线路
	private AccountInfoVO agentInfo;// 账号信息
	private List<VehicleInfoVo> vehicleList;// 车辆列表

	public static void main(String[] args) {
		RegInfoV2VO regInfo = new RegInfoV2VO();
		regInfo.setTelphone("13800138000");
		regInfo.setPassword("123456");
		regInfo.setBarCode("123456");
		regInfo.setDeviceType("01");
		List<Line> lineList = Lists.newArrayList();
		Line line1 = new Line();
		line1.setDepartureCode("110000");
		line1.setDestinationCode("310000");
		lineList.add(line1);
		AccountInfoVO agentInfo = new AccountInfoVO();
		agentInfo.setAgentType("01");
		agentInfo.setBusImageRefId("11111");
		agentInfo.setIdBImageRefId("1112125");
		agentInfo.setIdFImageRefId("125125");
		agentInfo.setIdno("3102240303030302305");
		agentInfo.setRealName("阿三");
		regInfo.setAgentInfo(agentInfo);
		regInfo.setLineList(lineList);
		List<VehicleInfoVo> vehicleList = Lists.newArrayList();
		VehicleInfoVo v1 = new VehicleInfoVo();
		v1.setCiImageRefId("125125");
		v1.setLicenseImageRefId("12125");
		v1.setOcImageRefId("236136");
		v1.setPlateNumber("沪A12345");
		v1.setViImageRefId("1251251216");
		List<DriverInfoVo> v1driverList = Lists.newArrayList();
		DriverInfoVo v1d1 = new DriverInfoVo();
		v1d1.setDrivingFImageRefId("125125");
		v1d1.setIdBImageRefId("31325sdgas");
		v1d1.setIdFImageRefId("sdfasdf235235");
		v1d1.setIdno("13125893270872136");
		v1d1.setQualifiImageRefId("sdfa325235");
		v1d1.setRealName("张三");
		v1d1.setTelphone("123121251");
		v1driverList.add(v1d1);
		v1.setDriverList(v1driverList);
		vehicleList.add(v1);
		regInfo.setVehicleList(vehicleList);
		System.out.println(JSON.toJSONString(regInfo));
	}

}
