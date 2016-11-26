package com.gzzhwl.admin.orderReturn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzzhwl.admin.orderReturn.service.OrderReturnService;
import com.gzzhwl.admin.quoted.service.QuotedManageService;
import com.gzzhwl.admin.source.service.SourceLinkStatusService;
import com.gzzhwl.admin.source.service.SourceService;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.dao.OrderDetailInfoDao;
import com.gzzhwl.core.data.dao.OrderReturnLogDao;
import com.gzzhwl.core.data.dao.OrderSourceInfoDao;
import com.gzzhwl.core.data.extdao.OrderInfoExtDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderReturnLog;
import com.gzzhwl.core.data.model.OrderSourceInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.service.TMSApiService;

@Service
public class OrderReturnServiceImpl implements OrderReturnService {
	@Autowired
	private OrderInfoExtDao orderExtDao;
	@Autowired
	private FlowsService flowService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private OrderReturnLogDao orderReturnLogDao;
	@Autowired
	private SourceService sourceService;
	@Autowired
	private SourceLinkStatusService sourceLinkStatusService;
	@Autowired
	private OrderSourceInfoDao orderSourceInfoDao;
	@Autowired
	private OrderDetailInfoDao detailInfoDao;
	@Autowired
	private QuotedManageService quotedManageService;
	@Autowired
	private TMSApiService tmsApiService;

	@Override
	public Page<Map<String, Object>> pageOrderSourceList(String keyWord, String status, int currentPage, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());

		if (!ValidateUtils.isEmpty(keyWord)) {
			params.put("queryContent", keyWord);
		}

		if (ValidateUtils.isEmpty(status)) {
			params.put("status", "01");
		} else {
			params.put("status", status);
		}

		Page<Map<String, Object>> data = orderExtDao.pageOrderReturnList(params, currentPage, pageSize);
		List<Map<String, Object>> rows = data.getRows();
		if (!ValidateUtils.isEmpty(rows)) {
			for (Map<String, Object> map : rows) {
				// 订单状态
				String orderStatus = (String) map.get("orderStatus");
				FlowStatus flowStatus = flowService.getStatus(ZHFlow.ORDER, orderStatus);
				if (!ValidateUtils.isEmpty(flowStatus)) {
					map.put("orderStatusCn", flowStatus.getName());
				} else {
					map.put("orderStatusCn", "");
				}

				// 申请退回状态
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

				List<Map<String, Object>> toDoList = flowService.whatToDo(ZHFlow.APPLY_RETURN, retStatus,
						FlowActionCategory.CBS_YSJ);
				map.put("toDoList", toDoList);
			}
		}
		return data;
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
	public boolean applyReturn(String orderId, String staffId) throws RestException {
		String sourceId = this.allowApply(orderId);
		if (StringUtils.isNotBlank(sourceId)) {
			OrderReturnLog orderReturnLog = new OrderReturnLog();
			orderReturnLog.setCreatedBy(staffId);
			orderReturnLog.setUpdatedBy(staffId);
			orderReturnLog.setIsDeleted(Global.ISDEL_NORMAL.toString());
			orderReturnLog.setLogId(IdentifierUtils.getId().generate().toString());
			orderReturnLog.setSourceId(sourceId);
			orderReturnLog.setOrderId(orderId);
			FlowDef flowDef = flowService.startFlow(ZHFlow.APPLY_RETURN, FlowActionCategory.CBS);
			orderReturnLog.setStatus(flowDef.getTransitionStatus());
			orderReturnLogDao.insert(orderReturnLog);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String allowApply(String orderId) throws RestException {
		String[] YSJ_STATUS = { "03", "07", "08", "09" };// 运势界状态
		OrderDetailInfo orderDetailInfo = detailInfoDao.get(orderId);
		String currentStatus = orderDetailInfo.getStatus();
		if (ArrayUtils.contains(YSJ_STATUS, currentStatus)) {// 订单状态不允许申请退回
			String sourceId = sourceService.getCurrentSource(orderId);
			if (StringUtils.isBlank(sourceId)) {// 该订单尚未推送至运势界
				return null;
			}
			String logId = this.getApplyLog(sourceId);
			if (StringUtils.isNotBlank(logId)) {// 有正在进行中的申请
				return null;
			}
			return sourceId;
		} else {
			return null;
		}
	}

	@Override
	public String getApplyLog(String sourceId) throws RestException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("sourceId", sourceId);
		params.put("statusType", FlowStatus.NORMAL_TYPE);
		params.put("flowName", ZHFlow.APPLY_RETURN.getName());
		return orderReturnLogDao.getApplyLog(params);
	}

	@Override
	public boolean agreeReturn(String sourceId, String staffId) throws RestException {
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		String currentStatus = orderSourceInfo.getStatus();
		// 根据状态判断级联的动作。。略屎
		if (SourceType.TODO.getCode().equals(currentStatus)) {

			sourceService.rejectSource(sourceId, "同意退回申请。", staffId, false);
		} else if (SourceType.PUBLISH.getCode().equals(currentStatus)) {

			sourceService.closeSource(sourceId, "同意退回申请。", staffId, false);
		} else if (SourceType.HASBID.getCode().equals(currentStatus)
				|| SourceType.FINISH.getCode().equals(currentStatus)) {

			quotedManageService.invalidQuoted(sourceId, null, staffId);
			sourceLinkStatusService.breakPromiseSourceByAdmin(sourceId, staffId, false);
		}
		tmsApiService.doReturnResponse(sourceId, true);
		return true;
	}

	@Override
	public boolean disAgreeReturn(String sourceId, String staffId) throws RestException {
		String actionCode = "03";// 不同意退回申请
		String logId = this.getApplyLog(sourceId);
		OrderReturnLog orderReturnLog = orderReturnLogDao.get(logId);
		String currentStatus = orderReturnLog.getStatus();
		FlowDef flowDef = flowService.executeFlow(ZHFlow.APPLY_RETURN, actionCode, currentStatus,
				FlowActionCategory.CBS_YSJ);
		orderReturnLog.setUpdatedBy(staffId);
		orderReturnLog.setStatus(flowDef.getTransitionStatus());
		orderReturnLogDao.updateSelective(orderReturnLog);
		tmsApiService.doReturnResponse(sourceId, false);
		return false;
	}

	@Override
	public boolean autoAgreeReturn(String sourceId, String staffId) throws RestException {
		String actionCode = "02";// 同意退回申请
		String logId = this.getApplyLog(sourceId);
		if (StringUtils.isNotBlank(logId)) {
			OrderReturnLog orderReturnLog = orderReturnLogDao.get(logId);
			String currentStatus = orderReturnLog.getStatus();
			FlowDef flowDef = flowService.executeFlow(ZHFlow.APPLY_RETURN, actionCode, currentStatus,
					FlowActionCategory.CBS_YSJ);
			orderReturnLog.setUpdatedBy(staffId);
			orderReturnLog.setStatus(flowDef.getTransitionStatus());
			orderReturnLogDao.updateSelective(orderReturnLog);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> whatToDo(String sourceId) throws RestException {
		String logId = this.getApplyLog(sourceId);
		if (StringUtils.isNotBlank(logId)) {
			OrderReturnLog orderReturnLog = orderReturnLogDao.get(logId);
			String currentStatus = orderReturnLog.getStatus();
			return flowService.whatToDo(ZHFlow.APPLY_RETURN, currentStatus, FlowActionCategory.CBS_YSJ);
		} else {
			return Lists.newArrayList();
		}

	}
}
