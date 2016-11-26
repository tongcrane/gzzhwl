package com.gzzhwl.admin.load.service.impl;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadHistoryService;
import com.gzzhwl.admin.load.service.LoadLinkStatusService;
import com.gzzhwl.admin.load.service.VADUsedService;
import com.gzzhwl.admin.order.service.OrderLinkStatusService;
import com.gzzhwl.admin.supply.service.SupplyService;
import com.gzzhwl.api.message.service.MessageTipsService;
import com.gzzhwl.api.notice.service.SmsService;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.dao.LoadDriverInfoDao;
import com.gzzhwl.core.data.dao.OrderBaseInfoDao;
import com.gzzhwl.core.data.dao.OrderDetailInfoDao;
import com.gzzhwl.core.data.dao.OrderLoadInfoDao;
import com.gzzhwl.core.data.dao.QuotedInfoDao;
import com.gzzhwl.core.data.dao.QuotedPlusInfoDao;
import com.gzzhwl.core.data.dao.RealDriverInfoDao;
import com.gzzhwl.core.data.extdao.OrderInfoExtDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.LoadDriverInfo;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.data.model.QuoteOrderInfo;
import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.data.model.RealDriverInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.data.model.SupplyInfo;
import com.gzzhwl.core.message.TipsCategory;
import com.gzzhwl.core.message.TipsSourceType;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class LoadLinkStatusServiceImpl implements LoadLinkStatusService {
	@Autowired
	private OrderDetailInfoDao orderDetailInfoDao;
	@Autowired
	private OrderBaseInfoDao orderBaseInfoDao;
	@Autowired
	private LoadHistoryService loadHistoryService;
	@Autowired
	private FlowsService flowService;
	@Autowired
	private OrderLoadInfoDao orderLoadInfoDao;
	@Autowired
	private LoadDriverInfoDao loadDriverInfoDao;
	@Autowired
	private QuotedInfoDao quotedInfoDao;
	@Autowired
	private OrderInfoExtDao orderInfoExtDao;
	@Autowired
	private QuotedPlusInfoDao quotedPlusInfoDao;
	@Value("${superadmin.staffid}")
	private String staffId;
	@Value("${ysjadmin.staffid}")
	private String ysjStaffId;
	@Autowired
	private VADUsedService vadUserdService;
	@Autowired
	private OrderLinkStatusService orderLinkStatusService;

	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private RealDriverInfoDao realDriverInfoDao;
	@Autowired
	private MessageTipsService messageTipsService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private LoadBillService loadBillService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private SupplyService supplyService;

	@Value("${sms.send}")
	private boolean send;
	@Value("${load.trip}")
	private String loadTrip;

	@Override
	public String createLoad(String orderId, Integer departId, String staffId) throws RestException {
		OrderDetailInfo orderDetailInfo = orderDetailInfoDao.get(orderId);
		String orderInfoId = orderDetailInfo.getInfoId();
		OrderBaseInfo orderBaseInfo = orderBaseInfoDao.get(orderInfoId);
		OrderLoadInfo loadInfo = new OrderLoadInfo();
		FlowDef flowDef = flowService.startFlow(ZHFlow.LOAD_BILL, FlowActionCategory.SYSTEM);
		String loadId = IdentifierUtils.getId().generate().toString();
		loadInfo.setOrderId(orderId);
		loadInfo.setBelongDepartId(departId + "");
		loadInfo.setCreatedBy(staffId);
		loadInfo.setUpdatedBy(staffId);
		loadInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		loadInfo.setLoadId(loadId);
		loadInfo.setQuotedId(null);
		loadInfo.setSource(DataSource.PLATFORM.getCode());
		loadInfo.setStatus(flowDef.getTransitionStatus());
		loadInfo.setType(OrderLoadInfo.LOAD_BILL);
		loadInfo.setNeedArriveTime(orderBaseInfo.getPickUpTime());
		orderLoadInfoDao.insert(loadInfo);
		loadHistoryService.saveLoadHistory(loadId, flowDef.getLinkedStatus(), null, flowDef, staffId);

		return loadId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String createLoadFromYSJ(String quotedId, String accountId) throws RestException {
		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);
		Map<String, String> params = Maps.newHashMap();
		params.put("quotedId", quotedId);
		List<Map<String, String>> vdList = quotedPlusInfoDao.find(params);
		List<String> driverList = Lists.newArrayList();
		String vehicleInfoId = null;
		for (Map<String, String> vdInfo : vdList) {
			vehicleInfoId = vdInfo.get("vehicleInfoId");
			String driverInfoId = vdInfo.get("driverInfoId");
			driverList.add(driverInfoId);
		}

		QuoteOrderInfo quoteOrderInfo = orderInfoExtDao.getOrderInfoByQuoted(quotedId);
		String orderId = quoteOrderInfo.getOrderId();
		String belongDepartId = quoteOrderInfo.getBelongDepartId();
		String paymentType = quoteOrderInfo.getPaymentType();
		OrderLoadInfo loadInfo = new OrderLoadInfo();
		String actionCode = "11";// 发车动作
		String currentStatus = "09";// 当前已配载
		FlowDef flowDef = flowService.executeFlow(ZHFlow.LOAD_BILL, actionCode, currentStatus, FlowActionCategory.CBS);
		String loadId = IdentifierUtils.getId().generate().toString();
		String loadNo = IdentifierUtils.getSequence(SeqNoKey.LOAD).generate().toString();
		loadInfo.setFreightPrice(new DecimalFormat("#0.00").format(Double.parseDouble(quotedInfo.getPrice())));
		// loadInfo.setLoadInfoId(loadInfoId);//无车挂

		// loadInfo.setIsPredict(isPredict);//是否预估
		// loadInfo.setOilCardNo(oilCardNo);//油卡卡号
		// loadInfo.setOilPay(oilPay);//预付油费
		// loadInfo.setPrePay(prePay);//预付现金
		SupplyInfo supplyInfo = supplyService.saveSupplyFromYSJ(accountId);
		if (supplyInfo != null) {
			loadInfo.setSupplyId(supplyInfo.getSupplyId());// 结算对象
			loadInfo.setPaymentName(supplyInfo.getFullName());// 结算对象名称
		}

		loadInfo.setNeedArriveTime(quoteOrderInfo.getPickUpTime());// 到达客户现场时间
		loadInfo.setVehicleInfoId(vehicleInfoId);
		loadInfo.setPaymentType(paymentType);
		loadInfo.setLoadNo(loadNo);
		loadInfo.setOrderId(orderId);
		loadInfo.setBelongDepartId(belongDepartId);
		loadInfo.setCreatedBy(ysjStaffId);
		loadInfo.setUpdatedBy(ysjStaffId);
		loadInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		loadInfo.setLoadId(loadId);
		loadInfo.setQuotedId(quotedId);
		loadInfo.setSource(DataSource.VLORRY.getCode());
		loadInfo.setStatus(flowDef.getTransitionStatus());
		loadInfo.setType(OrderLoadInfo.LOAD_BILL);

		orderLoadInfoDao.insert(loadInfo);
		boolean setMajor = true;
		for (String driverId : driverList) {
			LoadDriverInfo loadDriverInfo = new LoadDriverInfo();
			loadDriverInfo.setDriverInfoId(driverId);
			loadDriverInfo.setLoadId(loadId);
			if (setMajor) {
				loadDriverInfo.setIsMajor(LoadDriverInfo.MAJOR_YES);
				setMajor = false;
			} else {
				loadDriverInfo.setIsMajor(LoadDriverInfo.MAJOR_NO);
			}
			loadDriverInfoDao.insert(loadDriverInfo);
		}
		loadHistoryService.saveLoadHistory(loadId, currentStatus, null, flowDef, ysjStaffId);

		vadUserdService.lockVandD(loadId, ysjStaffId);// 锁定司机车辆
		orderLinkStatusService.doTrip(orderId, loadNo);// 创建完提货单开始发车，准备车检

		// todo 消息中心
		String content = "";
		String realName = "";
		String telphone = "";
		if (driverList.size() > 0) {
			String driverInfoId = driverList.get(0).toString();
			RealDriverInfo driver = realDriverInfoDao.get(driverInfoId);
			realName = driver.getRealName();
			telphone = driver.getTelphone();

			Map<String, Object> loadDetail = loadBillService.getLoadDetail(loadId);
			Map<String, Object> orderDetail = (Map<String, Object>) loadDetail.get("orderInfo");
			if (MapUtils.isNotEmpty(orderDetail)) {
				String receiveAddress = "";
				String pickUpTime = (String) orderDetail.get("pickUpTime");
				String dateTime = "";
				if (pickUpTime != null) {
					String date = pickUpTime.toString();
					SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日  E", Locale.CHINA);
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date _date = null;
					try {
						_date = sdf2.parse(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					dateTime = sdf.format(_date);
				}

				String receiveArea = orderDetail.get("sendArea").toString();
				List<RegionInfo> list = regionService.findRootByCode(receiveArea);
				if (list.size() > 0) {
					for (int i = list.size(); i > 0; i--) {
						receiveAddress += list.get(i - 1).getRegionName();
					}
				}
				String address = receiveAddress.concat(orderDetail.get("sendAddress").toString());
				String line = orderDetail.get("startCodeCCn").toString().concat("至")
						.concat(orderDetail.get("endCodeCCn").toString());
				content = MessageFormat.format(loadTrip, realName, dateTime, address, line, loadNo);
			}

			// 发送短信
			if (send) {
				smsService.sendSms(telphone, content);
			}
		}
		List<Map<String, Object>> accountIdMap = accountInfoDao.getAccountIdByLoadId(loadId);
		if (accountIdMap.size() > 1 || ValidateUtils.isEmpty(accountIdMap)) {

		} else {
			String driverAccountId = (String) accountIdMap.get(0).get("accountId");

			// 消息中心
			messageTipsService.addMessage(TipsCategory.TIPS_C05.getCode(), driverAccountId, loadId,
					TipsSourceType.TIPS_S02.getCode(), content, accountId);
		}

		return loadInfo.getLoadId();
	}

	@Override
	public boolean cancelTrip(String loadId, String staffId) throws RestException {
		String actionCode = "15";// 作废报价，取消发车
		OrderLoadInfo orderLoadInfo = orderLoadInfoDao.get(loadId);
		String currentStatus = orderLoadInfo.getStatus();
		FlowDef flowdef = flowService.executeFlow(ZHFlow.LOAD_BILL, actionCode, currentStatus,
				FlowActionCategory.SYSTEM);
		orderLoadInfo.setStatus(flowdef.getTransitionStatus());
		orderLoadInfoDao.updateSelective(orderLoadInfo);
		loadHistoryService.saveLoadHistory(orderLoadInfo.getLoadId(), orderLoadInfo.getStatus(), null, flowdef,
				staffId);
		return true;
	}
}
