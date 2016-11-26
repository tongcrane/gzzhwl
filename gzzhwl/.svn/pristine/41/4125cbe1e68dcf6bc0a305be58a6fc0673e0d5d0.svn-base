package com.gzzhwl.admin.lend.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.admin.lend.vo.LendDriverVo;
import com.gzzhwl.admin.lend.vo.LendInfoVo;
import com.gzzhwl.admin.lend.vo.LendQryVo;
import com.gzzhwl.admin.lend.vo.LendVO;
import com.gzzhwl.admin.lend.vo.LendVehicleVo;
import com.gzzhwl.core.data.model.LendLog;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;

public interface LendService {

	public String addLend(LendVO lendVo, Integer departId, String staffId) throws RestException;

	public boolean offLend(String lendId, String staffId) throws RestException;

	public Map<String, Object> validInfo(String vehicleInfoId, Integer departId) throws RestException;

	public Page<Map<String, Object>> getLendLogPage(LendQryVo lendQryVo, int current, int pagesize);

	public Map<String, Object> getLendLogDetail(String lendId);

	/**
	 * 获取借用信息
	 * 
	 * @param lendId
	 * @return
	 */
	public LendInfoVo getLendLogInfo(String lendId);

	/**
	 * 获取借用车辆信息
	 * 
	 * @param lendId
	 * @return
	 */
	public LendVehicleVo getLendVehicleInfo(String vehicleInfoId);

	/**
	 * 获取借用驾驶员信息
	 * 
	 * @param lendId
	 * @return
	 */
	public List<LendDriverVo> getLendDriverInfo(String driver1InfoId, String driver2InfoId);

	public boolean validDriver(String vehicleInfoId, String driverInfoId, Integer departId);

	/**
	 * 判断车辆是否已外借
	 */
	public boolean isVehicleLendOut(String vehicleInfoId);

	/**
	 * 判断司机是否外借
	 */
	public boolean isDriverLendOut(String driverInfoId);

	public List<LendLog> getLogToReturn();
}
