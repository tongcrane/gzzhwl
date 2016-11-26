package com.gzzhwl.api.agent.vo;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.gzzhwl.api.driver.vo.DriverInfoVo;
import com.gzzhwl.api.vehicle.vo.VehicleInfoVo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AgentInfoV2VO {
	private String realName; // 真实姓名
	private String idno; // 身份证号
	private String idFImageRefId; // 身份照片(正)
	private String idBImageRefId; // 身份照片(反)
	private String busImageRefId; // 营业执照照片
//	private String agentType;// 账户类型

	private List<VehicleInfoVo> vehicleList;// 车辆列表

	public static void main(String[] args) {
		AgentInfoV2VO regInfo = new AgentInfoV2VO();
//		regInfo.setAgentType("01");
		regInfo.setBusImageRefId("11111");
		regInfo.setIdBImageRefId("1112125");
		regInfo.setIdFImageRefId("125125");
		regInfo.setIdno("3102240303030302305");
		regInfo.setRealName("阿三");
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
