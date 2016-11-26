package com.gzzhwl.admin.source.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.order.service.OrderLinkStatusService;
import com.gzzhwl.admin.orderReturn.service.OrderReturnService;
import com.gzzhwl.admin.quoted.service.QuotedManageService;
import com.gzzhwl.admin.source.service.SourceHistoryService;
import com.gzzhwl.admin.source.service.SourceService;
import com.gzzhwl.admin.source.vo.PushSourceVO;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.dao.OrderBaseInfoDao;
import com.gzzhwl.core.data.dao.OrderSourceInfoDao;
import com.gzzhwl.core.data.dao.SourceBaseInfoDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderSourceInfo;
import com.gzzhwl.core.data.model.SourceBaseInfo;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.model.Operate;
import com.gzzhwl.tms.service.TMSApiService;

@Service
public class SourceServiceImpl implements SourceService {
	@Autowired
	private FlowsService flowService;
	@Autowired
	private SourceHistoryService sourceHistoryService;
	@Autowired
	private OrderLinkStatusService orderService;
	@Autowired
	private OrderSourceInfoDao orderSourceInfoDao;
	@Autowired
	private SourceBaseInfoDao sourceBaseInfoDao;
	@Autowired
	private OrderReturnService orderReturnService;
	@Autowired
	private QuotedManageService quotedManageService;
	@Autowired
	private OrderBaseInfoDao baseInfoDao;
	@Autowired
	private TMSApiService tmsApiService;

	@Override
	public boolean rejectSource(String sourceId, String reason, String staffId, boolean notice) throws RestException {
		String actionCode = "03";// 拒绝动作
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		String currentStatus = orderSourceInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.SOURCE, actionCode, currentStatus, FlowActionCategory.CBS_YSJ);
		orderSourceInfo.setStatus(flowDef.getTransitionStatus());
		orderSourceInfo.setRejectReason(reason);
		orderSourceInfo.setUpdatedBy(staffId);
		orderSourceInfoDao.updateSelective(orderSourceInfo);
		String infoId = orderSourceInfo.getInfoId();
		SourceBaseInfo sourceBaseInfo = sourceBaseInfoDao.get(infoId);
		String orderId = sourceBaseInfo.getOrderId();
		sourceHistoryService.saveSourceHistory(sourceId, currentStatus, flowDef, staffId);
		orderService.rejectOrder(orderId, reason, staffId);
		orderReturnService.autoAgreeReturn(sourceId, staffId);
		if (notice) {
			tmsApiService.doOperateOrder(sourceId, Operate.REJECT, reason);
		}
		return true;
	}

	@Override
	public boolean publicSource(PushSourceVO pushSource, String staffId) throws RestException {
		String actionCode = "02";// 发布动作
		String sourceId = pushSource.getSourceId();
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		OrderDetailInfo orderDetailInfo = this.getOrderDetailInfo(sourceId);

		String currentStatus = orderSourceInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.SOURCE, actionCode, currentStatus, FlowActionCategory.CBS_YSJ);
		orderSourceInfo.setStatus(flowDef.getTransitionStatus());

		orderSourceInfo.setNeedType(pushSource.getNeedType());
		orderSourceInfo.setNeedLength(pushSource.getNeedLength());
		orderSourceInfo.setNeedDriverNum(pushSource.getNeedDriverNum());
		orderSourceInfo.setStartTime(pushSource.getStartTime());
		orderSourceInfo.setEndTime(pushSource.getEndTime());
		orderSourceInfo.setTransitRequire(pushSource.getTransitRequire());
		orderSourceInfo.setEstimatedPrice(pushSource.getEstimatedPrice());
		String miles = pushSource.getMiles();
		if (StringUtils.isBlank(miles)) {
			String orderInfoId = orderDetailInfo.getInfoId();
			OrderBaseInfo orderBaseInfo = baseInfoDao.get(orderInfoId);
			miles = orderBaseInfo.getMiles();
		}
		orderSourceInfo.setMiles(miles);
		orderSourceInfo.setLevel(pushSource.getLevel());
		orderSourceInfo.setRemark(pushSource.getRemark());
		orderSourceInfo.setUpdatedBy(staffId);
		orderSourceInfoDao.updateSelective(orderSourceInfo);
		sourceHistoryService.saveSourceHistory(sourceId, currentStatus, flowDef, staffId);
		String orderId = orderDetailInfo.getOrderId();
		orderService.publicOrder(orderId, staffId);

		tmsApiService.doOperateOrder(sourceId, Operate.CONFIRM, "同意发布");
		return true;
	}

	@Override
	public boolean closeSource(String sourceId, String reason, String staffId, boolean notice) throws RestException {
		String actionCode = "07";// 关闭货源动作
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		String currentStatus = orderSourceInfo.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.SOURCE, actionCode, currentStatus, FlowActionCategory.CBS_YSJ);
		orderSourceInfo.setStatus(flowDef.getTransitionStatus());
		orderSourceInfo.setRejectReason(reason);
		orderSourceInfo.setUpdatedBy(staffId);
		orderSourceInfoDao.updateSelective(orderSourceInfo);
		String infoId = orderSourceInfo.getInfoId();
		SourceBaseInfo sourceBaseInfo = sourceBaseInfoDao.get(infoId);
		String orderId = sourceBaseInfo.getOrderId();
		sourceHistoryService.saveSourceHistory(sourceId, currentStatus, flowDef, staffId);
		orderService.closeSourceOrder(orderId, reason, staffId);
		orderReturnService.autoAgreeReturn(sourceId, staffId);
		quotedManageService.closeSourceLinkageHandler(sourceId, staffId);
		if (notice) {
			tmsApiService.doOperateOrder(sourceId, Operate.CLOSE, "关闭货源");
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> whatToDo(String sourceId) throws RestException {
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		String currentStatus = orderSourceInfo.getStatus();
		return flowService.whatToDo(ZHFlow.SOURCE, currentStatus, FlowActionCategory.CBS_YSJ);
	}

	@Override
	public String getCurrentSource(String orderId) throws RestException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("flowName", ZHFlow.SOURCE.getName());
		params.put("orderId", orderId);
		params.put("statusType", FlowStatus.NORMAL_TYPE);
		return orderSourceInfoDao.getSourceByOrder(params);
	}

	@Override
	public List<String> findSourceClose() throws RestException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("status", SourceType.PUBLISH.getCode());
		return orderSourceInfoDao.findSourceClose(params);
	}

	@Override
	public OrderDetailInfo getOrderDetailInfo(String sourceId) throws RestException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("sourceId", sourceId);
		return orderSourceInfoDao.getOrderBySource(params);
	}

}
