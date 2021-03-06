package com.gzzhwl.admin.quoted.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.quoted.service.QuotedHisService;
import com.gzzhwl.admin.quoted.service.QuotedManageService;
import com.gzzhwl.admin.remark.service.RemarkInfoService;
import com.gzzhwl.admin.source.service.SourceLinkStatusService;
import com.gzzhwl.api.message.service.MessageTipsService;
import com.gzzhwl.api.notice.service.SmsService;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.QuotedType;
import com.gzzhwl.core.constant.RemarkType;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.dao.QuotedInfoDao;
import com.gzzhwl.core.data.extdao.OrderSourceInfoExtDao;
import com.gzzhwl.core.data.extdao.QuotedInfoExtDao;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.message.TipsCategory;
import com.gzzhwl.core.message.TipsSourceType;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.service.TMSApiService;

@Service
public class QuotedManageServiceImpl implements QuotedManageService {

	@Autowired
	private QuotedInfoDao quotedInfoDao;
	@Autowired
	private FlowsService flowsService;
	@Autowired
	private OrderSourceInfoExtDao orderSourceInfoExtDao;
	@Autowired
	private QuotedInfoExtDao quotedInfoExtDao;
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private RemarkInfoService remarkInfoService;
	@Autowired
	private QuotedHisService quotedHisService;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private SourceLinkStatusService sourceLinkStatusService;
	@Autowired
	private MessageTipsService messageTipsService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private TMSApiService tmsApiService;

	@Value("${sms.send}")
	private boolean send;
	@Value("${quoted.win}")
	private String quotedWin;

	@Override
	public String winTheBid(String quotedId, String staffId) {

		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);

		if (quotedInfo == null) {
			throw new RestException(ErrorCode.ERROR_900013);
		}

		FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, "13", quotedInfo.getStatus(),
				FlowActionCategory.CBS_YSJ);

		quotedHisService.saveQuotedHis(quotedId, quotedInfo.getStatus(), flowdef.getTransitionStatus(),
				FlowActionCategory.CBS_YSJ, flowdef.getMsgTemplate(), staffId);

		quotedInfo.setStatus(flowdef.getTransitionStatus());
		quotedInfo.setUpdatedBy(staffId);
		quotedInfoDao.updateSelective(quotedInfo);

		sourceLinkStatusService.bidSource(quotedInfo.getSourceId(), staffId);

		winTheBidLinkageHandler(quotedInfo.getSourceId(), quotedInfo.getQuotedId(), staffId);

		// 中标后扣除押金

		// 消息中心
		String accountId = quotedInfo.getAccountId();
		messageTipsService.addMessage(TipsCategory.TIPS_C04.getCode(), accountId, quotedId,
				TipsSourceType.TIPS_S01.getCode(), quotedWin, staffId);
		tmsApiService.doBidOrder(quotedId);
		// 发送短信
		if (send) {
			AccountInfo accountInfo = accountInfoDao.get(accountId);
			String telphone = accountInfo.getTelphone();
			if (StringUtils.isNotEmpty(telphone)) {
				try {
					smsService.sendSms(telphone, quotedWin);
				} catch (RestException e) {
					e.printStackTrace();
				}
			}
		}

		return quotedInfo.getQuotedId();
	}

	@Override
	public Page<Map<String, Object>> queryQuotedManagerList(String sourceId, int pageIndex, int pageSize) {

		if (ValidateUtils.isEmpty(sourceId)) {
			throw new RestException(ErrorCode.ERROR_500002);
		}

		Map<String, Object> objMap = orderSourceInfoExtDao.getQuotedSourceDetail(sourceId);

		if (ValidateUtils.isEmpty(objMap)) {
			throw new RestException(ErrorCode.ERROR_500003);
		}
		// 货源状态
		String sourceStatus = objMap.get("status").toString();

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sourceId", sourceId);

		String[] strArray = null;

		if (sourceStatus.equals(SourceType.PUBLISH.getCode())) {
			strArray = new String[] { QuotedType.QUOTED.getCode(), QuotedType.CLOSED.getCode(),
					QuotedType.INVALID.getCode(), QuotedType.NOTIMPROVE.getCode(), QuotedType.ONLINE.getCode(),
					QuotedType.ONLINE_TRIP.getCode(), QuotedType.COMPLETE.getCode() };
		} else if (sourceStatus.equals(SourceType.HASBID.getCode())) {
			strArray = new String[] { QuotedType.NOTIMPROVE.getCode() };
		} else if (sourceStatus.equals(SourceType.FINISH.getCode())) {
			strArray = new String[] { QuotedType.ONLINE.getCode(), QuotedType.ONLINE_TRIP.getCode(),
					QuotedType.COMPLETE.getCode() };
		} else {
			throw new RestException(ErrorCode.ERROR_900006);
		}

		paraMap.put("quotedStatusList", strArray);

		Page<Map<String, Object>> quotedInfoList = quotedInfoExtDao.queryQuotedManagerList(paraMap, pageIndex,
				pageSize);

		// 更新状态描述
		CollectionUtils.transform(quotedInfoList.getRows(),
				new Transformer<Map<String, Object>, Map<String, Object>>() {
					@Override
					public Map<String, Object> transform(Map<String, Object> input) {
						String status = (String) input.get("status");
						if (StringUtils.isNotBlank(status)) {
							FlowStatus statusCn = flowsService.getStatus(ZHFlow.QUOTED, status);
							input.put("statusCn", statusCn.getName());
						} else {
							input.put("statusCn", StringUtils.EMPTY);
						}

						input.put("actionList",
								flowsService.whatToDo(ZHFlow.QUOTED, status, FlowActionCategory.CBS_YSJ));

						return input;
					}
				});

		return quotedInfoList;
	}

	@Override
	public Page<Map<String, Object>> queryAllQuotedList(String sourceId, int pageIndex, int pageSize) {
		if (ValidateUtils.isEmpty(sourceId)) {
			throw new RestException(ErrorCode.ERROR_500002);
		}

		Map<String, Object> objMap = orderSourceInfoExtDao.getQuotedSourceDetail(sourceId);

		if (ValidateUtils.isEmpty(objMap)) {
			throw new RestException(ErrorCode.ERROR_500003);
		}

		// 货源状态
		String sourceStatus = objMap.get("status").toString();
		String sourceStatusCn = "";
		if (StringUtils.isNotBlank(sourceStatus)) {
			FlowStatus flowStatus = flowsService.getStatus(ZHFlow.SOURCE, sourceStatus);
			if (sourceStatusCn != null) {
				sourceStatusCn = flowStatus.getName();
			}
		}

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sourceId", sourceId);
		paraMap.put("destStatus", QuotedType.NOTIMPROVE.getCode());

		Page<Map<String, Object>> quotedInfoPage = quotedInfoExtDao.queryQuotedManagerList(paraMap, pageIndex,
				pageSize);

		// 更新状态描述
		List<Map<String, Object>> quotedInfoList = quotedInfoPage.getRows();

		if (CollectionUtils.isNotEmpty(quotedInfoList)) {
			for (Map<String, Object> qutoedInfo : quotedInfoList) {
				String status = (String) qutoedInfo.get("status");
				if (StringUtils.isNotBlank(status)) {
					FlowStatus statusCn = flowsService.getStatus(ZHFlow.QUOTED, status);
					qutoedInfo.put("statusCn", statusCn.getName());
				} else {
					qutoedInfo.put("statusCn", StringUtils.EMPTY);
				}

				qutoedInfo.put("sourceStatus", sourceStatus);
				qutoedInfo.put("sourceStatusCn", sourceStatusCn);

				qutoedInfo.put("actionList", flowsService.whatToDo(ZHFlow.QUOTED, status, FlowActionCategory.CBS_YSJ));
			}
		}

		return quotedInfoPage;
	}

	@Override
	public String invalidTheBid(String quotedId, String remark, String staffId) {

		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);

		if (quotedInfo == null) {
			throw new RestException(ErrorCode.ERROR_900013);
		}
		// 作废报价

		invalidQuoted(quotedInfo.getSourceId(), remark, staffId);
		// 作废货源
		sourceLinkStatusService.breakPromiseSourceByAdmin(quotedInfo.getSourceId(), staffId, true);

		return quotedInfo.getQuotedId();
	}

	@Override
	public String invalidQuoted(String sourceId, String content, String staffId) {

		QuotedInfo quotedInfo = getBidQuoted(sourceId);

		if (quotedInfo == null) {
			throw new RestException(ErrorCode.ERROR_900013);
		}

		FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, "15", quotedInfo.getStatus(),
				FlowActionCategory.CBS_YSJ);

		quotedInfo.setStatus(flowdef.getTransitionStatus());
		quotedInfo.setUpdatedBy(staffId);

		quotedInfoDao.updateSelective(quotedInfo);

		remarkInfoService.saveRemark(quotedInfo.getQuotedId(), RemarkType.QUOTED, content, staffId);

		quotedHisService.saveQuotedHis(quotedInfo.getQuotedId(), quotedInfo.getStatus(), flowdef.getTransitionStatus(),
				FlowActionCategory.CBS_YSJ, flowdef.getMsgTemplate(), staffId);

		return quotedInfo.getQuotedId();
	}

	@Override
	public String closeQuotedInfo(String quotedId, String remark, String staffId) {

		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);

		if (quotedInfo == null) {
			throw new RestException(ErrorCode.ERROR_900013);
		}

		FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, "17", quotedInfo.getStatus(),
				FlowActionCategory.CBS_YSJ);

		quotedInfo.setStatus(flowdef.getTransitionStatus());
		quotedInfo.setUpdatedBy(staffId);

		quotedInfoDao.updateSelective(quotedInfo);

		remarkInfoService.saveRemark(quotedId, RemarkType.QUOTED, remark, staffId);

		quotedHisService.saveQuotedHis(quotedId, flowdef.getLinkedStatus(), flowdef.getTransitionStatus(),
				FlowActionCategory.CBS_YSJ, flowdef.getMsgTemplate(), staffId);

		return quotedInfo.getQuotedId();
	}

	@Override
	public Map<String, Object> getBidVehicleInfo(String sourceId) {

		List<Map<String, Object>> listMap = quotedInfoExtDao.getBidVehicleInfo(sourceId);

		Map<String, Object> resMap = new HashMap<>();

		if (!ValidateUtils.isEmpty(listMap)) {

			for (int i = 0; i < listMap.size(); i++) {
				resMap.put("vehicleInfoId", listMap.get(0).get("vehicleInfoId"));
				resMap.put("plateNumber", listMap.get(0).get("plateNumber"));
				resMap.put("accountId", listMap.get(0).get("accountId"));
				resMap.put("type", listMap.get(0).get("type"));
				resMap.put("length", listMap.get(0).get("length"));
			}

			// 更新省市区描述 状态描述
			CollectionUtils.transform(listMap, new Transformer<Map<String, Object>, Map<String, Object>>() {
				@Override
				public Map<String, Object> transform(Map<String, Object> input) {

					String status = (String) input.get("status");

					if (status.equals(Global.STATUS_PASSED.toString())) {
						input.put("statusCn", Global.STATUS_PASSED.getName());
					} else if (status.equals(Global.STATUS_WAIT.toString())) {
						input.put("statusCn", Global.STATUS_WAIT.getName());
					} else if (status.equals(Global.STATUS_ENTRY_NOT_FINISHED.toString())) {
						input.put("statusCn", Global.STATUS_ENTRY_NOT_FINISHED.getName());
					} else if (status.equals(Global.STATUS_NOT_PASSED.toString())) {
						input.put("statusCn", Global.STATUS_NOT_PASSED.getName());
					}

					return input;
				}
			});

			resMap.put("driverList", listMap);
		}

		return resMap;
	}

	@Override
	public void winTheBidLinkageHandler(String sourceId, String bidQuotedId, String staffId) {

		Map<String, Object> params = new HashMap<>();

		params.put("sourceId", sourceId);
		params.put("bidQuotedId", bidQuotedId);
		params.put("status", QuotedType.QUOTED.getCode());
		List<Map<String, Object>> listMap = quotedInfoExtDao.find(params);

		for (int i = 0; i < listMap.size(); i++) {

			QuotedInfo quotedInfo = beanMapper.map(listMap.get(i), QuotedInfo.class);

			FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, "18", quotedInfo.getStatus(),
					FlowActionCategory.SYSTEM);

			quotedHisService.saveQuotedHis(quotedInfo.getQuotedId(), quotedInfo.getStatus(),
					flowdef.getTransitionStatus(), FlowActionCategory.SYSTEM, flowdef.getMsgTemplate(), staffId);

			quotedInfo.setStatus(flowdef.getTransitionStatus());

			quotedInfoDao.updateSelective(quotedInfo);

		}

	}

	@Override
	public void closeSourceLinkageHandler(String sourceId, String staffId) {

		Map<String, Object> params = new HashMap<>();

		params.put("sourceId", sourceId);
		params.put("status", QuotedType.QUOTED.getCode());
		List<Map<String, Object>> listMap = quotedInfoExtDao.find(params);

		for (int i = 0; i < listMap.size(); i++) {

			QuotedInfo quotedInfo = beanMapper.map(listMap.get(i), QuotedInfo.class);

			FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, "19", quotedInfo.getStatus(),
					FlowActionCategory.SYSTEM);

			quotedHisService.saveQuotedHis(quotedInfo.getQuotedId(), quotedInfo.getStatus(),
					flowdef.getTransitionStatus(), FlowActionCategory.SYSTEM, flowdef.getMsgTemplate(), staffId);

			quotedInfo.setStatus(flowdef.getTransitionStatus());

			quotedInfoDao.updateSelective(quotedInfo);

		}

	}

	@Override
	public Integer getSourceQuotedCount(String sourceId) {

		Map<String, Object> params = new HashMap<>();

		params.put("sourceId", sourceId);

		List<Map<String, Object>> listMap = quotedInfoExtDao.find(params);

		return listMap.size();
	}

	@Override
	public QuotedInfo getBidQuoted(String sourceId) {

		Map<String, Object> params = new HashMap<>();

		params.put("sourceId", sourceId);
		params.put("quotedStatusList", new String[] { QuotedType.NOTIMPROVE.getCode(), QuotedType.ONLINE.getCode(),
				QuotedType.ONLINE_TRIP.getCode(), QuotedType.COMPLETE.getCode() });

		List<QuotedInfo> listMap = quotedInfoExtDao.find(params);

		if (ValidateUtils.isEmpty(listMap)) {
			return null;
		}

		return beanMapper.map(listMap.get(0), QuotedInfo.class);
	}

	@Override
	public Map<String, Object> getBidQuotedInfo(String sourceId) {
		if (ValidateUtils.isEmpty(sourceId)) {
			throw new RestException(ErrorCode.ERROR_500002);
		}

		Map<String, Object> objMap = orderSourceInfoExtDao.getQuotedSourceDetail(sourceId);

		if (ValidateUtils.isEmpty(objMap)) {
			throw new RestException(ErrorCode.ERROR_500003);
		}

		// 货源状态
		String sourceStatus = objMap.get("status").toString();
		String sourceStatusCn = "";
		if (StringUtils.isNotBlank(sourceStatus)) {
			FlowStatus flowStatus = flowsService.getStatus(ZHFlow.SOURCE, sourceStatus);
			if (sourceStatusCn != null) {
				sourceStatusCn = flowStatus.getName();
			}
		}

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sourceId", sourceId);//

		paraMap.put("destStatus", QuotedType.NOTIMPROVE.getCode());

		paraMap.put("quotedStatusList", new String[] { QuotedType.NOTIMPROVE.getCode(), QuotedType.ONLINE.getCode(),
				QuotedType.COMPLETE.getCode(), QuotedType.ONLINE_TRIP.getCode() });

		Map<String, Object> bidInfo = quotedInfoExtDao.queryBidQuotedInfo(paraMap);

		if (!ValidateUtils.isEmpty(bidInfo)) {

			String status = (String) bidInfo.get("status");
			if (StringUtils.isNotBlank(status)) {
				FlowStatus statusCn = flowsService.getStatus(ZHFlow.QUOTED, status);
				bidInfo.put("statusCn", statusCn.getName());
			} else {
				bidInfo.put("statusCn", StringUtils.EMPTY);
			}

			bidInfo.put("actionList", flowsService.whatToDo(ZHFlow.QUOTED, status, FlowActionCategory.CBS_YSJ));

			bidInfo.put("sourceStatus", sourceStatus);
			bidInfo.put("sourceStatusCn", sourceStatusCn);
		}

		return bidInfo;
	}

}
