package com.gzzhwl.admin.load.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.consignment.service.ConsignmentInfoService;
import com.gzzhwl.admin.contract.service.ContractService;
import com.gzzhwl.admin.load.service.ArrivedManageService;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadFeedbackService;
import com.gzzhwl.admin.load.service.LoadHistoryService;
import com.gzzhwl.admin.load.service.VADUsedService;
import com.gzzhwl.admin.load.validator.ArrivedValidate;
import com.gzzhwl.admin.load.vo.ArriveQueryVo;
import com.gzzhwl.admin.load.vo.LoadPrintreceiptVo;
import com.gzzhwl.admin.load.vo.ReceiptQueryVo;
import com.gzzhwl.admin.order.service.OrderLinkStatusService;
import com.gzzhwl.admin.quoted.service.QuotedLinkStatusService;
import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.service.ImageServiceFactory;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.dao.LoadArriveInfoDao;
import com.gzzhwl.core.data.dao.LoadElecreceiptDao;
import com.gzzhwl.core.data.dao.LoadExceptionDao;
import com.gzzhwl.core.data.dao.LoadPrintreceiptDao;
import com.gzzhwl.core.data.dao.OrderLoadInfoDao;
import com.gzzhwl.core.data.dao.StaffInfoDao;
import com.gzzhwl.core.data.extdao.LoadElecreceiptExtDao;
import com.gzzhwl.core.data.extdao.LoadPrintreceiptExtDao;
import com.gzzhwl.core.data.extdao.OrderLoadInfoExtDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.LoadArriveInfo;
import com.gzzhwl.core.data.model.LoadElecreceipt;
import com.gzzhwl.core.data.model.LoadException;
import com.gzzhwl.core.data.model.LoadPrintreceipt;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.push.model.PushEvent;
import com.gzzhwl.push.model.PushItem;
import com.gzzhwl.push.service.PushService;
import com.gzzhwl.rest.exception.RestException;

@Service
public class ArrivedManageServiceImpl implements ArrivedManageService {
	@Autowired
	private LoadArriveInfoDao arriveDao;
	@Autowired
	private FlowsService flowsService;
	@Autowired
	private OrderLoadInfoDao loadDao;
	@Autowired
	private OrderLoadInfoExtDao orderLoadInfoExtDao;
	@Autowired
	private LoadHistoryService loadHistoryService;
	@Autowired
	private OrderLinkStatusService orderLinkStatusService;
	@Autowired
	private LoadElecreceiptDao elecReceiptDao;
	@Autowired
	private LoadElecreceiptExtDao elecReceiptExtDao;
	@Autowired
	private LoadPrintreceiptDao printReceiptDao;
	@Autowired
	private LoadPrintreceiptExtDao printReceiptExtDao;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private LoadFeedbackService loadFeedbackService;
	@Autowired
	private VADUsedService vadUsedService;
	@Autowired
	private OrderLoadInfoExtDao loadExtDao;
	@Autowired
	private RegionService regionService;
	@Autowired
	private LoadBillService loadBillService;
	@Autowired
	private StaffInfoDao staffDao;
	@Autowired
	private LoadExceptionDao exceptionDao;
	@Autowired
	private ImageServiceFactory imageServiceFactory;
	@Autowired
	private ConsignmentInfoService consignService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private QuotedLinkStatusService quotedLinkStatusService;
	@Autowired
	private PushService pushService;
	@Autowired
	private AccountInfoDao accountInfoDao;

	private static final String VERIFY_PASSED = "01";
	private static final String VERIFY_NOT_PASSED = "02";

	private static final String TYPE_EXPRESS = "01";// 快递寄回
	private static final String TYPE_HAND_IN = "02";// 直接上交

	private OrderLoadInfo executeFlow(String loadId, String actionCode, String actionTime, String staffId)
			throws RestException {
		// 验证提货单是否存在
		OrderLoadInfo orderLoadInfo = validOrderLoadInfo(loadId);

		String currentStatus = orderLoadInfo.getStatus();
		FlowDef flowDef = flowsService.executeFlow(ZHFlow.LOAD_BILL, actionCode, currentStatus, FlowActionCategory.CBS);
		orderLoadInfo.setStatus(flowDef.getTransitionStatus());
		orderLoadInfo.setUpdatedBy(staffId);
		loadDao.updateSelective(orderLoadInfo);
		loadHistoryService.saveLoadHistory(loadId, currentStatus, actionTime, flowDef, staffId);
		return orderLoadInfo;
	}

	private OrderLoadInfo validOrderLoadInfo(String loadId) {
		ArrivedValidate.valid_load_id(loadId);
		OrderLoadInfo orderLoadInfo = loadDao.get(loadId);
		// 验证提货单是否存在
		ArrivedValidate.valid_load_exist(!ValidateUtils.isEmpty(orderLoadInfo));
		return orderLoadInfo;
	}

	@Override
	public boolean doArrived(String loadId, String arriveTime, String staffId) throws RestException {
		String actionCode = "17"; // 车辆到达
		// 提货单流程结转
		OrderLoadInfo orderLoadInfo = this.executeFlow(loadId, actionCode, arriveTime, staffId);

		// 订单流程结转
		orderLinkStatusService.doArrive(orderLoadInfo.getOrderId(), staffId);

		LoadArriveInfo arriveInfo = new LoadArriveInfo();
		arriveInfo.setLoadId(loadId);
		arriveInfo.setArriveTime(arriveTime);
		arriveInfo.setCreatedBy(staffId);
		arriveDao.insert(arriveInfo);

		Map<String, Object> content = Maps.newHashMap();
		content.put("loadId", loadId);
		PushItem item = new PushItem(PushEvent.STOP_GPS, content);
		List<Map<String, Object>> accountIdMap = accountInfoDao.getAccountIdByLoadId(loadId);

		if (accountIdMap.size() > 1 || ValidateUtils.isEmpty(accountIdMap)) {
			// throw new RestException(ErrorCode.ERROR_900010.getCode(),
			// ErrorCode.ERROR_900010.getDesc());
		} else {
			String driverAccountId = (String) accountIdMap.get(0).get("accountId");
			pushService.pushToSingleUser(item, driverAccountId);
		}
		return true;
	}

	@Override
	public boolean uploadElecReceipt(String loadId, String imageId, String actionTime, String staffId) {
		String actionCode = "18";// 电子回单上传
		// 提货单流程结转
		OrderLoadInfo orderLoadInfo = this.executeFlow(loadId, actionCode, actionTime, staffId);

		// 运单合同流程流转
		this.executeConsignFlow(orderLoadInfo.getOrderId(), staffId);

		// 保存电子回单信息
		this.saveElecReceipt(loadId, imageId, actionTime, staffId);

		// 释放司机车辆
		vadUsedService.releaseVandD(loadId, staffId);// 解锁司机车辆
		return true;
	}

	private boolean executeConsignFlow(String orderId, String staffId) {
		String consignId = consignService.getCurrentConsign(orderId);
		consignService.waitVerify(consignId, staffId);
		return true;

	}

	private void saveElecReceipt(String loadId, String imageId, String actionTime, String staffId) {
		LoadElecreceipt elecreceipt = new LoadElecreceipt();
		String receiptId = IdentifierUtils.getId().generate().toString();
		elecreceipt.setReceiptId(receiptId);
		elecreceipt.setLoadId(loadId);
		elecreceipt.setReceiptImageRefId(imageId);
		elecreceipt.setCreatedBy(staffId);
		elecreceipt.setUpdatedBy(staffId);
		elecreceipt.setStatus(Global.STATUS_PASSED.toString());
		elecreceipt.setIsDeleted(Global.ISDEL_NORMAL.toString());
		elecreceipt.setOpTime(actionTime);
		elecreceipt.setSource(DataSource.PLATFORM.getCode());
		elecReceiptDao.insert(elecreceipt);
	}

	@Override
	public boolean uploadPrintReceipt(LoadPrintreceiptVo vo, String staffId) {
		String actionCode = "19";// 纸质回单上传

		if (TYPE_EXPRESS.equals(vo.getType())) {
			// 验证提货单是否存在
			OrderLoadInfo orderLoadInfo = validOrderLoadInfo(vo.getLoadId());

			// 记录日志
			FlowDef flowdef = new FlowDef();
			flowdef.setCategory(FlowActionCategory.CBS.getCode());
			flowdef.setTransitionStatus(orderLoadInfo.getStatus());
			flowdef.setMsgTemplate("上传纸质回单");
			loadHistoryService.saveLoadHistory(orderLoadInfo.getLoadId(), orderLoadInfo.getStatus(), null, flowdef,
					staffId);

			// 快递寄回，需确认
			this.savePrintReceipt(vo, staffId, Global.STATUS_WAIT.toString());

		} else if (TYPE_HAND_IN.equals(vo.getType())) {
			// 直接上交
			// 提货单流程结转
			OrderLoadInfo orderLoadInfo = this.executeFlow(vo.getLoadId(), actionCode, null, staffId);

			// 订单流程结转
			orderLinkStatusService.doPrintReceipt(orderLoadInfo.getOrderId(), staffId);

			// 运势界报价流程结束
			if (StringUtils.isNotBlank(orderLoadInfo.getQuotedId())) {
				quotedLinkStatusService.completedQuoted(orderLoadInfo.getQuotedId(), staffId);
			}

			// 司机合同流程结转
			this.executeContractFlow(vo.getLoadId(), staffId);

			this.savePrintReceipt(vo, staffId, Global.STATUS_PASSED.toString());
		}

		return true;
	}

	private void executeContractFlow(String loadId, String staffId) {
		String contractId = contractService.getCurrentContract(loadId);
		contractService.waitVerify(contractId, staffId);
	}

	private void savePrintReceipt(LoadPrintreceiptVo vo, String staffId, String status) {
		LoadPrintreceipt loadPrintreceipt = beanMapper.map(vo, LoadPrintreceipt.class);
		String receiptId = IdentifierUtils.getId().generate().toString();
		loadPrintreceipt.setReceiptId(receiptId);
		loadPrintreceipt.setCreatedBy(staffId);
		loadPrintreceipt.setUpdatedBy(staffId);
		loadPrintreceipt.setStatus(status);
		loadPrintreceipt.setIsDeleted(Global.ISDEL_NORMAL.toString());
		printReceiptDao.insert(loadPrintreceipt);
	}

	@Override
	public boolean verifyElecReceipt(String loadId, String receiptId, String verifyResult, String staffId) {
		String actionCode = "18";// 电子回单上传

		if (VERIFY_PASSED.equals(verifyResult)) {
			// 审核通过，流程结转，记日志，更新状态
			OrderLoadInfo orderLoadInfo = this.executeFlow(loadId, actionCode, null, staffId);
			// 更新回单状态
			this.updateElecReceipt(receiptId, staffId, Global.STATUS_PASSED.toString());

			// 运单合同流程流转
			this.executeConsignFlow(orderLoadInfo.getOrderId(), staffId);

			// 释放司机车辆
			vadUsedService.releaseVandD(loadId, staffId);// 解锁司机车辆
		} else if (VERIFY_NOT_PASSED.equals(verifyResult)) {
			// 验证提货单是否存在
			OrderLoadInfo orderLoadInfo = validOrderLoadInfo(loadId);

			// 审核不通过，记日志，更新状态
			FlowDef flowdef = new FlowDef();
			flowdef.setCategory(FlowActionCategory.CBS.getCode());
			flowdef.setTransitionStatus(LoadBillType.ARRIVED.getCode());
			flowdef.setMsgTemplate("电子回单审核不通过");

			Map<String, Object> params = Maps.newHashMap();
			params.put("receiptId", receiptId);
			LoadElecreceipt elecreceipt = elecReceiptExtDao.getElecreceipt(params);

			loadHistoryService.saveElecReceiptHis(orderLoadInfo.getLoadId(), LoadBillType.ARRIVED.getCode(), null,
					flowdef, staffId, elecreceipt.getReceiptImageRefId());

			this.updateElecReceipt(receiptId, staffId, Global.STATUS_NOT_PASSED.toString());
		}

		return true;

	}

	private boolean updateElecReceipt(String receiptId, String staffId, String status) {
		ArrivedValidate.valid_receipt_id(receiptId);

		Map<String, Object> params = Maps.newHashMap();
		params.put("receiptId", receiptId);
		params.put("source", DataSource.VLORRY.getCode());
		params.put("status", Global.STATUS_WAIT.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());

		LoadElecreceipt elecreceipt = elecReceiptExtDao.getElecreceipt(params);

		ArrivedValidate.valid_receipt_exist(!ValidateUtils.isEmpty(elecreceipt));
		elecreceipt.setStatus(status);
		elecreceipt.setUpdatedBy(staffId);
		elecReceiptDao.updateSelective(elecreceipt);
		return true;

	}

	@Override
	public boolean confirmPrintReceipt(String loadId, String receiptId, String signTime, String staffId) {
		String actionCode = "19";// 纸质回单上传
		// 提货单流程结转
		OrderLoadInfo orderLoadInfo = this.executeFlow(loadId, actionCode, null, staffId);

		// 订单流程结转
		orderLinkStatusService.doPrintReceipt(orderLoadInfo.getOrderId(), staffId);

		// 运势界报价流程结束
		if (StringUtils.isNotBlank(orderLoadInfo.getQuotedId())) {
			quotedLinkStatusService.completedQuoted(orderLoadInfo.getQuotedId(), staffId);
		}
		// 司机合同流程结转
		this.executeContractFlow(loadId, staffId);

		this.updatePrintReceipt(loadId, receiptId, staffId, signTime);
		return true;
	}

	private boolean updatePrintReceipt(String loadId, String receiptId, String staffId, String signTime) {
		ArrivedValidate.valid_receipt_id(receiptId);

		Map<String, Object> params = Maps.newHashMap();
		params.put("receiptId", receiptId);
		params.put("loadId", loadId);
		params.put("status", Global.STATUS_WAIT.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		LoadPrintreceipt loadPrintreceipt = printReceiptExtDao.getPrintreceipt(params);

		ArrivedValidate.valid_receipt_exist(!ValidateUtils.isEmpty(loadPrintreceipt));

		loadPrintreceipt.setStatus(Global.STATUS_PASSED.toString());
		loadPrintreceipt.setUpdatedBy(staffId);
		loadPrintreceipt.setSignTime(signTime);
		printReceiptDao.updateSelective(loadPrintreceipt);
		return true;
	}

	@Override
	public boolean close(String loadId, String reason, String staffId) {
		String actionCode = "20";// 车辆到达关闭
		// 提货单流程结转
		OrderLoadInfo orderLoadInfo = this.executeFlow(loadId, actionCode, null, staffId);

		// 订单流程结转
		orderLinkStatusService.doClose(orderLoadInfo.getOrderId(), staffId);

		// 运势界报价流程结束
		if (StringUtils.isNotBlank(orderLoadInfo.getQuotedId())) {
			quotedLinkStatusService.completedQuoted(orderLoadInfo.getQuotedId(), staffId);
		}

		// 司机合同流程结转
		this.executeContractFlow(loadId, staffId);

		// 保存信息
		LoadException loadException = new LoadException();
		loadException.setLoadId(loadId);
		loadException.setReason(reason);
		loadException.setCreatedBy(staffId);
		exceptionDao.insert(loadException);
		return true;
	}

	@Override
	public Page<Map<String, Object>> pageArriveList(ArriveQueryVo queryVo, int currentPage, int pageSize)
			throws ParseException {
		Map<String, Object> params = queryVo.getParam();
		Page<Map<String, Object>> data = loadExtDao.pageArriveList(params, currentPage, pageSize);
		List<Map<String, Object>> arriveList = data.getRows();
		if (CollectionUtils.isNotEmpty(arriveList)) {
			for (Map<String, Object> map : arriveList) {
				String status = (String) map.get("status");
				FlowStatus flow = flowsService.getStatus(ZHFlow.LOAD_BILL, status);
				if (!ValidateUtils.isEmpty(flow)) {
					map.put("statusCn", flow.getName());
				} else {
					map.put("statusCn", "");
				}

				List<Map<String, Object>> actionList = flowsService.whatToDo(ZHFlow.LOAD_BILL, status,
						FlowActionCategory.CBS);
				map.put("actionList", actionList);

				String startCodeP = (String) map.get("startCodeP");
				map.put("startCodePCn", this.getCodeCn(startCodeP));

				String startCodeC = (String) map.get("startCodeC");
				map.put("startCodeCCn", this.getCodeCn(startCodeC));

				String endCodeP = (String) map.get("endCodeP");
				map.put("endCodePCn", this.getCodeCn(endCodeP));

				String endCodeC = (String) map.get("endCodeC");
				map.put("endCodeCCn", this.getCodeCn(endCodeC));

				// 获取发车司机信息
				String loadId = (String) map.get("loadId");
				List<Map<String, Object>> drivers = loadExtDao.getTripDrivers(loadId);

				map.put("driverList", drivers);
			}
		}
		return data;
	}

	public String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = regionService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public Map<String, Object> getArriveDetail(String loadId) {
		Map<String, Object> arriveDetail = Maps.newHashMap();
		// 获取提货单车辆详情
		Map<String, Object> loadVehicleMap = loadBillService.getLoadVehcleDetail(loadId);
		arriveDetail.put("loadVehicleInfo", loadVehicleMap);
		// 获取到达详情
		Map<String, Object> arriveMap = getArriveInfo(loadId);
		arriveDetail.put("arriveInfo", arriveMap);
		// 获取电子回单详情
		Map<String, Object> elecReceiptMap = getElecReceiptDetail(loadId);
		arriveDetail.put("elecReceipt", elecReceiptMap);
		// 获取纸质回单详情
		Map<String, Object> printReceipt = getPrintReceiptDetail(loadId);
		arriveDetail.put("printReceipt", printReceipt);
		// 获取关闭详情
		Map<String, Object> loadExceptionMap = getLoadExceptionInfo(loadId);
		arriveDetail.put("loadException", loadExceptionMap);
		return arriveDetail;
	}

	@Override
	public Map<String, Object> getArriveInfo(String loadId) {
		LoadArriveInfo arriveInfo = arriveDao.get(loadId);
		Map<String, Object> arriveMap = new HashMap<String, Object>();

		if (arriveInfo != null) {
			beanMapper.map(arriveInfo, arriveMap);
			StaffInfo staffInfo = staffDao.get(arriveInfo.getCreatedBy());
			if (staffInfo != null) {
				arriveMap.put("position", staffInfo.getPosition());
				arriveMap.put("realName", staffInfo.getRealName());
			}
		}
		return arriveMap;
	}

	private Map<String, Object> getLoadExceptionInfo(String loadId) {
		LoadException loadException = exceptionDao.get(loadId);
		Map<String, Object> loadExceptionMap = new HashMap<String, Object>();

		if (loadException != null) {
			beanMapper.map(loadException, loadExceptionMap);
			StaffInfo staffInfo = staffDao.get(loadException.getCreatedBy());
			if (staffInfo != null) {
				loadExceptionMap.put("position", staffInfo.getPosition());
				loadExceptionMap.put("realName", staffInfo.getRealName());
			}
		}
		return loadExceptionMap;
	}

	@Override
	public Map<String, Object> getElecReceiptDetail(String loadId) {

		Map<String, Object> loadElecreceipt = elecReceiptExtDao.getCurrentElecreceipt(loadId);

		if (loadElecreceipt == null) {
			loadElecreceipt = new HashMap<>();
		} else {
			String status = (String) loadElecreceipt.get("status");
			String statusCn = "";
			if (Global.STATUS_NORMAL.toString().equals(status)) {
				statusCn = "通过";
			} else if (Global.STATUS_WAIT.toString().equals(status)) {
				statusCn = "待审核";
			} else if (Global.STATUS_NOT_PASSED.toString().equals(status)) {
				statusCn = "不通过";
			}
			loadElecreceipt.put("statusCn", statusCn);
		}

		List<Map<String, Object>> elecFeedbackList = loadFeedbackService.getElecFeedbackList(loadId);

		if (elecFeedbackList != null) {
			loadElecreceipt.put("elecFeedbackList", elecFeedbackList);
		} else {
			loadElecreceipt.put("elecFeedbackList", null);
		}

		return loadElecreceipt;
	}

	@Override
	public Map<String, Object> getPrintReceiptDetail(String loadId) {

		return printReceiptExtDao.getCurrentPrintreceipt(loadId);
	}

	@Override
	public Page<Map<String, Object>> pageReceiptList(ReceiptQueryVo queryVo, int currentPage, int pageSize)
			throws ParseException {

		Page<Map<String, Object>> resPage = orderLoadInfoExtDao.pageReceiptList(queryVo.getParam(), currentPage,
				pageSize);

		List<Map<String, Object>> resList = resPage.getRows();
		if (CollectionUtils.isNotEmpty(resList)) {
			for (Map<String, Object> map : resList) {

				String startCodeP = (String) map.get("startCodeP");
				map.put("startCodePCn", this.getCodeCn(startCodeP));

				String startCodeC = (String) map.get("startCodeC");
				map.put("startCodeCCn", this.getCodeCn(startCodeC));

				String endCodeP = (String) map.get("endCodeP");
				map.put("endCodePCn", this.getCodeCn(endCodeP));

				String endCodeC = (String) map.get("endCodeC");
				map.put("endCodeCCn", this.getCodeCn(endCodeC));
			}
		}

		return resPage;
	}

	@Override
	public String updateElecImage(MultipartFile image, String staffId) {
		if (image == null) {
			throw new RestException(ErrorCode.ERROR_900001);
		}

		ImageItem imageItem = imageServiceFactory.saveImage(ImageCategory.ELEC, image, staffId);

		return imageItem.getFileId();
	}

}
