package com.gzzhwl.api.driver.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.api.driver.vo.DriverInfoQueryVo;
import com.gzzhwl.api.driver.vo.DriverInfoVo;
import com.gzzhwl.api.vehicle.vo.VehicleInfoVo;
import com.gzzhwl.core.data.model.DriverInfo;
import com.gzzhwl.core.data.model.VehicleInfo;
import com.gzzhwl.core.data.model.VehiclePlusInfo;
import com.gzzhwl.core.page.Page;

public interface DriverInfoService {

	public boolean insertOrUpdate(DriverInfo driverInfo);

	public Page<Map<String, Object>> page(String accountId, int pageIndex, int pageSize);

	public String updateImageInfo(MultipartFile file, String accountId);

	public VehicleInfoVo getDriverAndVehicleInfo(String driverInfoId, String accountId);

	public String saveDriverAndVehicle(String accountId, List<DriverInfoVo> drivers, VehicleInfo vehicleInfo,
			VehiclePlusInfo vehiclePlusInfo);

	public Page<Map<String, Object>> pageDriverAndVehicleList(String accountId, DriverInfoQueryVo queryVo,
			int pageIndex, int pageSize);

	public String saveDriverAndVehicleTemp(String accountId, List<DriverInfoVo> drivers, VehicleInfo vehicleInfo,
			VehiclePlusInfo vehiclePlusInfo);

	public String updateDriverAndVehicle(List<DriverInfoVo> drivers, VehicleInfo vehicleInfo, VehiclePlusInfo plusInfo,
			String accountId);

	// public boolean driverExistValidator(String idno, String accountId, String
	// driverInfoId);
	
	

	public Map<String, Object> getOtherDriverInfo(String accountId, String driverInfoId, String idno);

	public boolean remove(String driverInfoId, String accountId);

	public boolean isPendingAudit(String plateNumber, String accountId);
	
	public List<Map<String,Object>> getDriverAndVehicleListByAccountId(String accountId);

	public DriverInfoVo getCBSDriverInfo(String accountId);
	
	/**
	 * 查询可用的司机车辆分页信息
	 */
	public Page<Map<String, Object>> pageUsableDriverAndVehicleList(String accountId, int pageIndex, int pageSize);

	/**
	 * 查询司机信息
	 */
	public DriverInfoVo getDriverInfo(String driverInfoId);

}
