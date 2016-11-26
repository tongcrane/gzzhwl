package com.gzzhwl.admin.load.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.load.service.VADUsedService;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.LoadDriverInfoDao;
import com.gzzhwl.core.data.dao.OrderLoadInfoDao;
import com.gzzhwl.core.data.dao.RealDriverUsedInfoDao;
import com.gzzhwl.core.data.dao.RealVehicleUsedInfoDao;
import com.gzzhwl.core.data.dao.UsedInfoHisDao;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.data.model.RealDriverUsedInfo;
import com.gzzhwl.core.data.model.RealVehicleUsedInfo;
import com.gzzhwl.core.data.model.UsedInfoHis;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class VADUsedServiceImpl implements VADUsedService {
	@Autowired
	private OrderLoadInfoDao orderLoadInfoDao;
	@Autowired
	private LoadDriverInfoDao loadDriverInfoDao;
	@Autowired
	private RealDriverUsedInfoDao driverUsedDao;
	@Autowired
	private RealVehicleUsedInfoDao vehicleUsedDao;
	@Autowired
	private UsedInfoHisDao userdHisDao;

	@Override
	public boolean lockVandD(String loadId, String staffId) {
		OrderLoadInfo loadInfo = orderLoadInfoDao.get(loadId);
		String vehicleInfoId = loadInfo.getVehicleInfoId();// 车ID
		this.lockVehicle(vehicleInfoId, loadId, staffId);// 锁定车辆
		String loadInfoId = loadInfo.getLoadInfoId();// 挂ID
		this.lockVehicle(loadInfoId, loadId, staffId);// 锁定车挂
		Map<String, Object> params = Maps.newHashMap();
		params.put("loadId", loadId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, String>> driverList = loadDriverInfoDao.find(params);
		for (Map<String, String> driverInfo : driverList) {
			String driverId = driverInfo.get("driverInfoId");// 司机ID
			this.lockDriver(driverId, loadId, staffId);// 锁定司机
		}
		return true;
	}

	private void lockVehicle(String vehicleInfoId, String loadId, String staffId) {
		if (StringUtils.isNotBlank(vehicleInfoId)) {
			boolean inUse = this.vehicleIsInUse(vehicleInfoId);
			if (inUse) {
				throw new RestException(ErrorCode.ERROR_22002);
			}
			RealVehicleUsedInfo realVehicleUsedInfo = new RealVehicleUsedInfo();
			realVehicleUsedInfo.setVehicleInfoId(vehicleInfoId);
			realVehicleUsedInfo.setUseStatus(UsedInfoHis.INUSED);
			vehicleUsedDao.updateSelective(realVehicleUsedInfo);
			this.saveUseHis(loadId, staffId, vehicleInfoId, UsedInfoHis.VEHICLE_TYPE);
		}
	}

	private boolean vehicleIsInUse(String vehicleInfoId) {
		long count = vehicleUsedDao.getIsStatus(vehicleInfoId, UsedInfoHis.INUSED);
		return count > 0;
	}

	private void lockDriver(String driverId, String loadId, String staffId) {
		if (StringUtils.isNotBlank(driverId)) {
			boolean inUse = this.driverIsInUse(driverId);
			if (inUse) {
				throw new RestException(ErrorCode.ERROR_22003);
			}
			RealDriverUsedInfo realDriverUsedInfo = new RealDriverUsedInfo();
			realDriverUsedInfo.setDriverInfoId(driverId);
			realDriverUsedInfo.setUseStatus(UsedInfoHis.INUSED);
			driverUsedDao.updateSelective(realDriverUsedInfo);
			this.saveUseHis(loadId, staffId, driverId, UsedInfoHis.DRIVER_TYPE);
		}
	}

	private boolean driverIsInUse(String driverId) {
		long count = driverUsedDao.getIsStatus(driverId, UsedInfoHis.INUSED);
		return count > 0;
	}

	private boolean saveUseHis(String loadId, String staffId, String targetId, String type) {
		UsedInfoHis usedInfoHis = new UsedInfoHis();
		usedInfoHis.setCreatedBy(staffId);
		usedInfoHis.setSrcStatus(UsedInfoHis.UNUSED);
		usedInfoHis.setDestStatus(UsedInfoHis.INUSED);
		usedInfoHis.setHisId(IdentifierUtils.getId().generate().toString());
		usedInfoHis.setLoadId(loadId);
		usedInfoHis.setTargetId(targetId);
		usedInfoHis.setTargetType(type);
		userdHisDao.insert(usedInfoHis);
		return true;
	}

	private boolean saveReleaseHis(String loadId, String staffId, String targetId, String type) {
		UsedInfoHis usedInfoHis = new UsedInfoHis();
		usedInfoHis.setCreatedBy(staffId);
		usedInfoHis.setSrcStatus(UsedInfoHis.INUSED);
		usedInfoHis.setDestStatus(UsedInfoHis.UNUSED);
		usedInfoHis.setHisId(IdentifierUtils.getId().generate().toString());
		usedInfoHis.setLoadId(loadId);
		usedInfoHis.setTargetId(targetId);
		usedInfoHis.setTargetType(type);
		userdHisDao.insert(usedInfoHis);
		return true;
	}

	@Override
	public boolean releaseVandD(String loadId, String staffId) {
		OrderLoadInfo loadInfo = orderLoadInfoDao.get(loadId);
		String vehicleInfoId = loadInfo.getVehicleInfoId();// 车ID
		this.releaseVehicle(vehicleInfoId, loadId, staffId);// 锁定车辆
		String loadInfoId = loadInfo.getLoadInfoId();// 挂ID
		this.releaseVehicle(loadInfoId, loadId, staffId);// 锁定车挂
		Map<String, Object> params = Maps.newHashMap();
		params.put("loadId", loadId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, String>> driverList = loadDriverInfoDao.find(params);
		for (Map<String, String> driverInfo : driverList) {
			String driverId = driverInfo.get("driverInfoId");// 司机ID
			this.releaseDriver(driverId, loadId, staffId);// 解锁司机
		}
		return true;
	}

	private void releaseDriver(String driverId, String loadId, String staffId) {
		if (StringUtils.isNotBlank(driverId)) {
			boolean inUse = this.driverIsInUse(driverId);
			if (inUse) {
				RealDriverUsedInfo realDriverUsedInfo = new RealDriverUsedInfo();
				realDriverUsedInfo.setDriverInfoId(driverId);
				realDriverUsedInfo.setUseStatus(UsedInfoHis.UNUSED);
				driverUsedDao.updateSelective(realDriverUsedInfo);
			}
			this.saveReleaseHis(loadId, staffId, driverId, UsedInfoHis.DRIVER_TYPE);
		}
	}

	private void releaseVehicle(String vehicleInfoId, String loadId, String staffId) {
		if (StringUtils.isNotBlank(vehicleInfoId)) {
			boolean inUse = this.vehicleIsInUse(vehicleInfoId);
			if (inUse) {
				RealVehicleUsedInfo realVehicleUsedInfo = new RealVehicleUsedInfo();
				realVehicleUsedInfo.setVehicleInfoId(vehicleInfoId);
				realVehicleUsedInfo.setUseStatus(UsedInfoHis.UNUSED);
				vehicleUsedDao.updateSelective(realVehicleUsedInfo);
			}
			this.saveReleaseHis(loadId, staffId, vehicleInfoId, UsedInfoHis.VEHICLE_TYPE);
		}

	}

}
