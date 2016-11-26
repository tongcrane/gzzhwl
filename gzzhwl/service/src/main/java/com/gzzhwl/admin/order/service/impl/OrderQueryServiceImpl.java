package com.gzzhwl.admin.order.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.agreement.service.AgreementInfoService;
import com.gzzhwl.admin.consignment.service.ConsignmentInfoService;
import com.gzzhwl.admin.load.service.ArrivedManageService;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadFeedbackService;
import com.gzzhwl.admin.order.service.OrderQueryService;
import com.gzzhwl.admin.order.vo.OrderQueryVo;
import com.gzzhwl.admin.orderReturn.service.OrderReturnService;
import com.gzzhwl.admin.source.controller.SourceValidate;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.ConsignType;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.dao.ChargeInfoDao;
import com.gzzhwl.core.data.dao.CustomerInfoDao;
import com.gzzhwl.core.data.dao.DepartmentInfoDao;
import com.gzzhwl.core.data.dao.LoadArriveInfoDao;
import com.gzzhwl.core.data.dao.LoadTripInfoDao;
import com.gzzhwl.core.data.dao.OrderBaseInfoDao;
import com.gzzhwl.core.data.dao.OrderDetailInfoDao;
import com.gzzhwl.core.data.dao.OrderLineInfoDao;
import com.gzzhwl.core.data.dao.OrderReceiverInfoDao;
import com.gzzhwl.core.data.dao.OrderSenderInfoDao;
import com.gzzhwl.core.data.extdao.OrderInfoExtDao;
import com.gzzhwl.core.data.model.ChargeInfo;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.data.model.DepartmentInfo;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.LoadTripInfo;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderLineInfo;
import com.gzzhwl.core.data.model.OrderReceiverInfo;
import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.ValidateUtils;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {
	@Autowired
	private FlowsService flowService;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private OrderDetailInfoDao detailInfoDao;
	@Autowired
	private OrderBaseInfoDao baseInfoDao;
	@Autowired
	private OrderLineInfoDao lineInfoDao;
	@Autowired
	private OrderReceiverInfoDao receiverInfoDao;
	@Autowired
	private OrderSenderInfoDao senderInfoDao;
	@Autowired
	private OrderInfoExtDao orderExtDao;
	@Autowired
	private ChargeInfoDao chargeDao;
	@Autowired
	private RegionService reginService;
	@Autowired
	private CustomerInfoDao custDao;
	@Autowired
	private OrderReturnService orderReturnService;
	@Autowired
	private DepartmentInfoDao departDao;
	@Autowired
	private ConsignmentInfoService consignService;
	@Autowired
	private AgreementInfoService agreementService;
	@Autowired
	private LoadBillService loadService;
	@Autowired
	private LoadFeedbackService feedBackService;
	@Autowired
	private LoadFeedbackService loadFeedbackService;
	@Autowired
	private LoadTripInfoDao tripDao;
	@Autowired
	private LoadArriveInfoDao arriveDao;
	@Autowired
	private ArrivedManageService arriveManageService;

	/**
	 * 订单列表-总览
	 * 
	 * @param keyWord
	 * @param currentPage
	 * @param pageSizes
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> pageOrderList(String keyWord, String sort, int currentPage, int pageSizes) {
		Page<Map<String, Object>> result = orderExtDao.pageOrderList(keyWord, sort, currentPage, pageSizes);

		List<Map<String, Object>> rows = result.getRows();
		if (!ValidateUtils.isEmpty(rows)) {
			for (Map<String, Object> map : rows) {
				String status = (String) map.get("status");
				FlowStatus statusCn = flowService.getStatus(ZHFlow.ORDER, status);
				if (!ValidateUtils.isEmpty(statusCn)) {
					map.put("statusCn", statusCn.getName());
				} else {
					map.put("statusCn", "");
				}

				map.put("toDoList", flowService.whatToDo(ZHFlow.ORDER, status, FlowActionCategory.CBS));
				// TODO
				map.put("elecOrderTime", "");
				map.put("allocatTime", "");
			}
		}
		return result;
	}

	/**
	 * 获取订单详细信息
	 */
	@Override
	public Map<String, Object> getOrderDetail(String orderId) {
		Map<String, Object> data = getOrderInfo(orderId);

		// 获取运单合同信息
		Map<String, Object> consignMap = consignService.getConsignByOrderId(orderId);
		if (!ValidateUtils.isEmpty(consignMap)) {
			data.put("hasConsign", "是");
		} else {
			data.put("hasConsign", "否");
		}
		data.put("consignmentInfo", consignMap);

		String sourceId = orderReturnService.allowApply(orderId);
		data.put("allowReturn", sourceId != null);

		// 获取提货单信息
		Map<String, Object> loadInfo = loadService.getLoadInfoByOrdrId(orderId);

		data.put("loadInfo", loadInfo);

		if (!ValidateUtils.isEmpty(loadInfo)) {
			String loadId = (String) loadInfo.get("loadId");
			if (StringUtils.isNotBlank(loadId)) {
				// 获取车检反馈信息
				Map<String, Object> VCFeedBackList = feedBackService.getCbsLoadFeedbackList(loadId,
						LoadBillType.NOTVEHICLE);
				// 获取靠台异常反馈信息
				Map<String, Object> CTSFeedBackList = feedBackService.getCbsLoadFeedbackList(loadId,
						LoadBillType.VEHICLECHECK);
				// 获取发车异常反馈信息
				Map<String, Object> TripFeedBackList = feedBackService.getCbsLoadFeedbackList(loadId,
						LoadBillType.CLOSETOSURFACE);
				// 获取发车反馈
				LoadTripInfo tripFeedBackDetail = tripDao.get(loadId);
				// 获取在途详情
				List<Map<String, Object>> transitFeedBackList = loadFeedbackService.getTrackList(loadId);
				// 获取车辆到达信息
				Map<String, Object> arriveMap = arriveManageService.getArriveDetail(loadId);
				// 获取电子回单信息
				Map<String, Object> elecReceiptMap = arriveManageService.getElecReceiptDetail(loadId);
				// 获取纸质回单信息
				Map<String, Object> printReceipt = arriveManageService.getPrintReceiptDetail(loadId);

				data.put("VCFeedBackList", VCFeedBackList);
				data.put("CTSFeedBackList", CTSFeedBackList);
				data.put("TripFeedBackList", TripFeedBackList);
				data.put("tripFeedBackDetail", tripFeedBackDetail);
				data.put("transitFeedBackList", transitFeedBackList);
				data.put("arriveInfo", arriveMap);
				data.put("elecReceipt", elecReceiptMap);
				data.put("printReceipt", printReceipt);
			}
		}

		return data;
	}

	@Override
	public Map<String, Object> getOrderInfo(String orderId) {
		// 查询订单信息
		OrderDetailInfo detailInfo = detailInfoDao.get(orderId);
		SourceValidate.valid_order_exist(!ValidateUtils.isEmpty(detailInfo));
		// data.put("orderDetailInfo", detailInfo);

		String infoId = detailInfo.getInfoId();

		Map<String, Object> data = getOrderInfoByInfoId(infoId);
		data.put("orderDetailInfo", detailInfo);

		return data;
	}

	@Override
	public Page<Map<String, Object>> pageOrders(OrderQueryVo queryVo, int pageIndex, int pageSize)
			throws ParseException {
		Map<String, Object> params = queryVo.getParam();

		Page<Map<String, Object>> data = orderExtDao.pageOrders(params, pageIndex, pageSize);
		List<Map<String, Object>> lists = data.getRows();

		if (!ValidateUtils.isEmpty(lists)) {
			for (Map<String, Object> map : lists) {
				// 订单状态
				String status = (String) map.get("status");
				FlowStatus flowStatus = flowService.getStatus(ZHFlow.ORDER, status);
				if (!ValidateUtils.isEmpty(flowStatus)) {
					map.put("statusCn", flowStatus.getName());
				} else {
					map.put("statusCn", "");
				}

				// 退回申请状态
				String retStatus = (String) map.get("retStatus");
				FlowStatus retFlowStatus = flowService.getStatus(ZHFlow.APPLY_RETURN, retStatus);
				if (!ValidateUtils.isEmpty(retFlowStatus)) {
					map.put("retStatusCn", retFlowStatus.getName());
				} else {
					map.put("retStatusCn", "");
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

				List<Map<String, Object>> toDoList = flowService.whatToDo(ZHFlow.ORDER, status, FlowActionCategory.CBS);
				map.put("toDoList", toDoList);

				// TODO
				map.put("YSJStatus", "");
			}
		}
		return data;
	}

	@Override
	public Map<String, Object> getOrderInfoByInfoId(String infoId) {
		Map<String, Object> data = new HashMap<String, Object>();
		// 查询订单基本信息
		OrderBaseInfo baseInfo = baseInfoDao.get(infoId);
		SourceValidate.valid_order_exist(!ValidateUtils.isEmpty(baseInfo));
		Map<String, Object> baseMap = new HashMap<String, Object>();
		beanMapper.map(baseInfo, baseMap);

		if (StringUtils.isNotBlank(baseInfo.getBelongDepartId())) {
			DepartmentInfo info = departDao.get(Integer.valueOf(baseInfo.getBelongDepartId()).intValue());
			if (!ValidateUtils.isEmpty(info)) {
				baseMap.put("departName", info.getName());
			} else {
				baseMap.put("departName", "");
			}
		} else {
			baseMap.put("departName", "");
		}

		data.put("orderBaseInfo", baseMap);

		if (StringUtils.isNotBlank(baseInfo.getAgreementId())) {
			Map<String, Object> agreementMap = agreementService.getAgreementDetail(baseInfo.getAgreementId());
			data.put("agreementInfo", agreementMap);
		} else {
			data.put("agreementInfo", null);
		}

		// 查询计费方式
		ChargeInfo chargeInfo = chargeDao.get(baseInfo.getChargeId());
		data.put("chargeInfo", chargeInfo);

		// 查询订单发货人信息
		OrderSenderInfo sender = senderInfoDao.get(infoId);
		// 根据发货人ID查询发货人信息
		if (!ValidateUtils.isEmpty(sender)) {
			CustomerInfo cust = custDao.get(sender.getCustomerId());
			if (!ValidateUtils.isEmpty(cust)) {
				sender.setCustomerName(cust.getFullName());
			}

			Map<String, Object> senderMap = new HashMap<String, Object>();
			beanMapper.map(sender, senderMap);
			List<RegionInfo> senderArea = reginService.findRootByCode(sender.getAreaCode());
			senderMap.put("area", senderArea);
			data.put("orderSenderInfo", senderMap);
		} else {
			data.put("orderSenderInfo", null);
		}

		// 查询订单收货人信息
		OrderReceiverInfo receiver = receiverInfoDao.get(infoId);
		if (!ValidateUtils.isEmpty(receiver)) {
			Map<String, Object> receiverMap = new HashMap<String, Object>();
			beanMapper.map(receiver, receiverMap);
			List<RegionInfo> area = reginService.findRootByCode(receiver.getAreaCode());
			receiverMap.put("area", area);

			data.put("orderReceiverInfo", receiverMap);
		} else {
			data.put("orderReceiverInfo", null);
		}

		// 查询订单路线信息
		OrderLineInfo lineInfo = lineInfoDao.get(infoId);
		if (!ValidateUtils.isEmpty(lineInfo)) {
			Map<String, Object> lineMap = new HashMap<String, Object>();
			beanMapper.map(lineInfo, lineMap);

			String StartCodePCn = this.getCodeCn(lineInfo.getStartCodeP());
			lineMap.put("startCodePCn", StartCodePCn);

			String StartCodeCCn = this.getCodeCn(lineInfo.getStartCodeC());
			lineMap.put("startCodeCCn", StartCodeCCn);

			String transferCodePCn = this.getCodeCn(lineInfo.getTransferCodeP());
			lineMap.put("transferCodePCn", transferCodePCn);

			String transferCodeCCn = this.getCodeCn(lineInfo.getTransferCodeC());
			lineMap.put("transferCodeCCn", transferCodeCCn);

			String endCodePCn = this.getCodeCn(lineInfo.getEndCodeP());
			lineMap.put("endCodePCn", endCodePCn);

			String endCodeCCn = this.getCodeCn(lineInfo.getEndCodeC());
			lineMap.put("endCodeCCn", endCodeCCn);

			data.put("orderLineInfo", lineMap);
		} else {
			data.put("orderLineInfo", null);
		}

		return data;
	}

	public String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = reginService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	/**
	 * 获取订单概要信息
	 */
	@Override
	public Map<String, Object> getLoadOrderInfo(String orderId) {
		Map<String, Object> orderInfo = orderExtDao.getLoadOrderInfo(orderId);
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
				List<RegionInfo> sendRegion = reginService.findRootByCode(sendArea);
				orderInfo.put("sendRegion", sendRegion);
			}
			if (receiveArea != null) {
				List<RegionInfo> receiveRegion = reginService.findRootByCode(receiveArea);
				orderInfo.put("receiveRegion", receiveRegion);
			}
		}
		return orderInfo;
	}

	@Override
	public Map<String, Object> getOrderGeneralInfo(String orderId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("orderId", orderId);
		params.put("statusArray",
				new String[] {ConsignType.CONSIGN_WAIT.getCode(), ConsignType.CONSIGN_VERIFIED.getCode() });
		params.put("status", Global.STATUS_PASSED.toString());
		Map<String, Object> orderMap = orderExtDao.getOrderGenernalInfo(params);

		if (!ValidateUtils.isEmpty(orderMap)) {
			String startCodeP = (String) orderMap.get("startCodeP");
			String startCodePCn = this.getCodeCn(startCodeP);
			orderMap.put("startCodePCn", startCodePCn);

			String startCodeC = (String) orderMap.get("startCodeC");
			String startCodeCCn = this.getCodeCn(startCodeC);
			orderMap.put("startCodeCCn", startCodeCCn);

			String endCodeP = (String) orderMap.get("endCodeP");
			String endCodePCn = this.getCodeCn(endCodeP);
			orderMap.put("endCodePCn", endCodePCn);

			String endCodeC = (String) orderMap.get("endCodeC");
			String endCodeCCn = this.getCodeCn(endCodeC);
			orderMap.put("endCodeCCn", endCodeCCn);
		}
		return orderMap;
	}
}
