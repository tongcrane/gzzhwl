package com.gzzhwl.admin.contract.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzzhwl.admin.contract.ContractValidate;
import com.gzzhwl.admin.contract.service.ContractService;
import com.gzzhwl.admin.contract.vo.ContractQueryVo;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadHistoryService;
import com.gzzhwl.admin.payables.vo.PayContractQueryVo;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.ContractType;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.data.dao.DriverContractInfoDao;
import com.gzzhwl.core.data.dao.LoadDriverInfoDao;
import com.gzzhwl.core.data.dao.OrderLoadInfoDao;
import com.gzzhwl.core.data.extdao.DriverContractInfoExtDao;
import com.gzzhwl.core.data.extdao.OrderInfoExtDao;
import com.gzzhwl.core.data.model.DriverContractInfo;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.LoadDriverInfo;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class ContractServiceImpl implements ContractService {
	private static final Logger logger = LoggerFactory.getLogger(ContractService.class);
	@Autowired
	private LoadHistoryService loadHistoryService;
	@Autowired
	private FlowsService flowService;
	@Autowired
	private OrderLoadInfoDao orderLoadInfoDao;
	@Autowired
	private LoadDriverInfoDao loadDriverInfoDao;
	@Autowired
	private DriverContractInfoDao driverContractInfoDao;
	@Autowired
	private DriverContractInfoExtDao contractExtDao;
	@Autowired
	private RegionService regionService;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private OrderInfoExtDao orderInfoExtDao;
	@Autowired
	private LoadBillService loadService;
	@Autowired
	private DriverContractInfoDao driverContractDao;

	@Override
	public String createContract(String loadId, Integer departId, String staffId) throws RestException {
		boolean allow = this.allowCreate(loadId);
		if (!allow) {
			throw new RestException("220202", "不能生成司机合同(或已生成)");
		} else {
			DriverContractInfo driverContractInfo = this.create(loadId, staffId);
			return driverContractInfo.getContractId();
		}
	}

	@Override
	public String autoCreateContract(String loadId, Integer departId, String staffId) throws RestException {
		// 判断是否有司机合同，没有则重新创建
		String contractId = this.getCurrentContract(loadId);
		if (ValidateUtils.isEmpty(contractId)) {
			this.createContract(loadId, departId, staffId);
		}
		return contractId;
	}

	private DriverContractInfo create(String loadId, String staffId) {
		OrderLoadInfo orderLoadInfo = orderLoadInfoDao.get(loadId);

		OrderLoadInfo newOrderLoadInfo = new OrderLoadInfo();
		beanMapper.map(orderLoadInfo, newOrderLoadInfo);

		Map<String, Object> params = Maps.newHashMap();
		params.put("loadId", loadId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<Map<String, Object>> driverList = loadDriverInfoDao.find(params);
		String contractId = IdentifierUtils.getId().generate().toString();
		FlowDef flowDef = flowService.startFlow(ZHFlow.DRIVER_CONTRACT_BILL, FlowActionCategory.CBS);
		String loadNo = IdentifierUtils.getSequence(SeqNoKey.CONTRACT).generate().toString();
		DriverContractInfo driverContractInfo = new DriverContractInfo();
		driverContractInfo.setContractId(contractId);
		driverContractInfo.setLoadId(loadId);

		newOrderLoadInfo.setLoadId(contractId);
		newOrderLoadInfo.setLoadNo(loadNo);
		newOrderLoadInfo.setStatus(flowDef.getTransitionStatus());
		newOrderLoadInfo.setCreatedBy(staffId);
		newOrderLoadInfo.setUpdatedBy(staffId);
		newOrderLoadInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		newOrderLoadInfo.setType(OrderLoadInfo.DRIVER_CONTRACT_BILL);

		driverContractInfoDao.insert(driverContractInfo);
		orderLoadInfoDao.insert(newOrderLoadInfo);

		List<LoadDriverInfo> loadDriverList = Lists.newArrayList();
		for (Map<String, Object> driver : driverList) {
			LoadDriverInfo driverInfo = new LoadDriverInfo();
			String driverId = (String) driver.get("driverInfoId");
			driverInfo.setDriverInfoId(driverId);
			driverInfo.setLoadId(contractId);
			driverInfo.setIsMajor((String) driver.get("isMajor"));
			loadDriverList.add(driverInfo);
		}

		if (CollectionUtils.isNotEmpty(loadDriverList)) {
			for (LoadDriverInfo loadDriverInfo : loadDriverList) {
				loadDriverInfoDao.insert(loadDriverInfo);
			}
		}

		loadHistoryService.saveLoadHistory(contractId, flowDef.getLinkedStatus(), null, flowDef, staffId);

		return driverContractInfo;
	}

	@Override
	public String getCurrentContract(String loadId) throws RestException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("flowName", ZHFlow.DRIVER_CONTRACT_BILL.getName());
		params.put("loadId", loadId);
		params.put("dataType", OrderLoadInfo.DRIVER_CONTRACT_BILL);
		params.put("cancelStatus", ContractType.CONTRACTCANCEL.getCode());
		return driverContractInfoDao.getContractByLoad(params);
	}

	/**
	 * true 允许 false 不允许
	 */
	@Override
	public boolean allowCreate(String loadId) throws RestException {
		OrderLoadInfo loadInfo = orderLoadInfoDao.get(loadId);
		String currentStatus = loadInfo.getStatus();
		if (StringUtils.equals(currentStatus, LoadBillType.UNLOAD.getCode())) {
			return false;
		} else {
			String contractId = this.getCurrentContract(loadId);
			return StringUtils.isBlank(contractId);
		}
	}

	@Override
	public boolean cancelContract(String contractId, String staffId) throws RestException {
		String actionCode = "02";// 作废司机合同
		OrderLoadInfo loadInfo = orderLoadInfoDao.get(contractId);
		String currentStatus = loadInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.DRIVER_CONTRACT_BILL, actionCode, currentStatus,
				FlowActionCategory.CBS);
		loadInfo.setStatus(flowDef.getTransitionStatus());
		orderLoadInfoDao.updateSelective(loadInfo);
		loadHistoryService.saveLoadHistory(contractId, currentStatus, null, flowDef, staffId);
		return true;
	}

	@Override
	public String autoCreateContract(String loadId, String staffId) throws RestException {
		String contractId = this.getCurrentContract(loadId);
		if (StringUtils.isBlank(contractId)) {
			DriverContractInfo driverContractInfo = this.create(loadId, staffId);
			return driverContractInfo.getContractId();
		} else {
			logger.debug("提货单已生成司机合同，自动跳过。");
			return contractId;
		}
	}

	@Override
	public Page<Map<String, Object>> pageContractList(ContractQueryVo vo, int currentPage, int pageSize) {
		Map<String, Object> params = vo.getParam();
		Page<Map<String, Object>> result = contractExtDao.pageContractList(params, currentPage, pageSize);
		List<Map<String, Object>> list = result.getRows();
		if (!ValidateUtils.isEmpty(list)) {
			for (Map<String, Object> map : list) {
				String status = (String) map.get("status");
				FlowStatus flowStatus = flowService.getStatus(ZHFlow.DRIVER_CONTRACT_BILL, status);
				if (ValidateUtils.isEmpty(flowStatus)) {
					map.put("statusCn", "");
				} else {
					map.put("statusCn", flowStatus.getName());
				}

				String startCodeP = (String) map.get("startCodeP");
				String startCodePCn = this.getCodeCn(startCodeP);
				map.put("startCodePCn", startCodePCn);

				String startCodeC = (String) map.get("startCodeC");
				String startCodeCCn = this.getCodeCn(startCodeC);
				map.put("startCodeCCn", startCodeCCn);

				String endCodeP = (String) map.get("endCodeP");
				String endCodePCn = this.getCodeCn(endCodeP);
				map.put("endCodePCn", endCodePCn);

				String endCodeC = (String) map.get("endCodeC");
				String endCodeCCn = this.getCodeCn(endCodeC);
				map.put("endCodeCCn", endCodeCCn);
			}
		}
		return result;
	}

	private String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = regionService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public Map<String, Object> queryContractDetail(String contractId) {
		Map<String, Object> contractDetail = Maps.newHashMap();
		ContractValidate.valid_contractId(contractId);
		
		OrderLoadInfo contract = orderLoadInfoDao.get(contractId);
		//验证司机合同是否存在
		ContractValidate.valid_contract_exist(!ValidateUtils.isEmpty(contract));
		String orderId = contract.getOrderId();
		
		Map<String, Object> contractInfo = this.getContractInfo(contractId);
		Map<String, Object> orderInfo = this.getLoadOrderInfo(orderId);
		
		contractDetail.put("actionList", Lists.newArrayList());
		contractDetail.put("orderInfo", orderInfo);
		contractDetail.put("contractInfo", contractInfo);
		return contractDetail;
	}
	
	private Map<String, Object> getLoadOrderInfo(String orderId) {
		Map<String, Object> orderInfo = orderInfoExtDao.getLoadOrderInfo(orderId);
		if (orderInfo != null) {
			String startCodeP = (String) orderInfo.get("startCodeP");
			String startCodePCn = this.getCodeCn(startCodeP);
			orderInfo.put("startCodePCn", startCodePCn);

			String startCodeC = (String) orderInfo.get("startCodeC");
			String startCodeCCn = this.getCodeCn(startCodeC);
			orderInfo.put("startCodeCCn", startCodeCCn);

			String endCodeP = (String) orderInfo.get("endCodeP");
			String endCodePCn = this.getCodeCn(endCodeP);
			orderInfo.put("endCodePCn", endCodePCn);

			String endCodeC = (String) orderInfo.get("endCodeC");
			String endCodeCCn = this.getCodeCn(endCodeC);
			orderInfo.put("endCodeCCn", endCodeCCn);
			String sendArea = (String) orderInfo.get("sendArea");
			String receiveArea = (String) orderInfo.get("receiveArea");
			if (sendArea != null) {
				List<RegionInfo> sendRegion = regionService.findRootByCode(sendArea);
				orderInfo.put("sendRegion", sendRegion);
			}
			if (receiveArea != null) {
				List<RegionInfo> receiveRegion = regionService.findRootByCode(receiveArea);
				orderInfo.put("receiveRegion", receiveRegion);
			}
		}
		return orderInfo;
	}
	
	private Map<String, Object> getContractInfo(String contractId) {		
		Map<String, Object> params = Maps.newHashMap();
		params.put("loadId", contractId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		Map<String, Object> contractMap = orderLoadInfoDao.getLoadInfo(params);
		
		contractMap.put("contractId", contractId);
		contractMap.put("contractNo", (String)contractMap.get("loadNo"));
		
		//获取提货单关联关系
		DriverContractInfo driverContractInfo = driverContractDao.get(contractId);
		ContractValidate.valid_load_contract_ref_exist(!ValidateUtils.isEmpty(driverContractInfo));
		//查询提货单信息
		OrderLoadInfo loadInfo = orderLoadInfoDao.get(driverContractInfo.getLoadId());
		ContractValidate.valid_load_exist(!ValidateUtils.isEmpty(loadInfo));
		contractMap.put("loadId", loadInfo.getLoadId());
		contractMap.put("loadNo", loadInfo.getLoadNo());
		
		List<Map<String, Object>> driverList = loadService.getLoadDriverList(contractId);

		contractMap.put("driverInfo", driverList);
			
		return contractMap;
	}

	@Override
	public Page<Map<String, Object>> pagePayContractList(PayContractQueryVo vo, int currentPage, int pageSize) {
		
		Page<Map<String, Object>> resPage = contractExtDao.pagePayContractList(vo.getParam(), currentPage, pageSize);
		
		List<Map<String, Object>> resList = resPage.getRows();
		if(CollectionUtils.isNotEmpty(resList)) {
			for(Map<String, Object> map : resList) {
				
				String startCodeP = (String) map.get("startCodeP");
				map.put("startCodePCn", this.getCodeCn(startCodeP));

				String startCodeC = (String) map.get("startCodeC");
				map.put("startCodeCCn", this.getCodeCn(startCodeC));

				String endCodeP = (String) map.get("endCodeP");
				map.put("endCodePCn", this.getCodeCn(endCodeP));

				String endCodeC = (String) map.get("endCodeC");
				map.put("endCodeCCn", this.getCodeCn(endCodeC));
				
				String status = (String) map.get("status");
				FlowStatus flowStatus = flowService.getStatus(ZHFlow.DRIVER_CONTRACT_BILL, (String) map.get("status"));
				map.put("statusCn", flowStatus.getName());
				
			}
		}
				
		return resPage;
	}
	
	public boolean executeFlow(String contractId, String actionCode, String staffId) throws RestException {
		OrderLoadInfo loadInfo = orderLoadInfoDao.get(contractId);
		ContractValidate.valid_contract_exist(!ValidateUtils.isEmpty(loadInfo));
		String currentStatus = loadInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.DRIVER_CONTRACT_BILL, actionCode, currentStatus,
				FlowActionCategory.CBS);
		loadInfo.setStatus(flowDef.getTransitionStatus());
		orderLoadInfoDao.updateSelective(loadInfo);
		loadHistoryService.saveLoadHistory(contractId, currentStatus, null, flowDef, staffId);
		return true;
	}

	@Override
	public boolean waitVerify(String contractId, String staffId) {
		String actionCode = "03";
		this.executeFlow(contractId, actionCode, staffId);
		return true;
	}

	@Override
	public boolean doVerified(String contractId, String staffId) {
		String actionCode = "04";
		this.executeFlow(contractId, actionCode, staffId);
		return true;
	}
}
