package com.gzzhwl.admin.quoted.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.quoted.service.QuotedHisService;
import com.gzzhwl.admin.quoted.service.QuotedLinkStatusService;
import com.gzzhwl.admin.source.service.SourceLinkStatusService;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.core.data.dao.QuotedInfoDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.QuotedInfo;

@Service
public class QuotedLinkStatusServiceImpl implements QuotedLinkStatusService {
	@Autowired
	private QuotedInfoDao quotedInfoDao;
	@Autowired
	private FlowsService flowsService;
	@Autowired
	private QuotedHisService quotedHisService;
	@Autowired
	private SourceLinkStatusService sourceLinkStatusService;

	@Override
	public boolean cancelSetOut(String quotedId, String staffId) {
		String actionCode = "20";// 取消发车
		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);
		String currentStatus = quotedInfo.getStatus();
		FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, actionCode, currentStatus, FlowActionCategory.SYSTEM);
		quotedInfo.setStatus(flowdef.getTransitionStatus());
		quotedInfo.setUpdatedBy(staffId);
		quotedInfoDao.updateSelective(quotedInfo);
		quotedHisService.saveQuotedHis(quotedId, currentStatus, flowdef.getTransitionStatus(),
				FlowActionCategory.SYSTEM, flowdef.getMsgTemplate(), staffId);
		String sourceId = quotedInfo.getSourceId();
		sourceLinkStatusService.cancelSetOut(sourceId, staffId);
		return true;
	}

	@Override
	public boolean completedQuoted(String quotedId, String staffId) {
		String actionCode = "21";// 完成报价
		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);
		String currentStatus = quotedInfo.getStatus();
		FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, actionCode, currentStatus, FlowActionCategory.SYSTEM);
		quotedInfo.setStatus(flowdef.getTransitionStatus());
		quotedInfo.setUpdatedBy(staffId);
		quotedInfoDao.updateSelective(quotedInfo);
		quotedHisService.saveQuotedHis(quotedId, currentStatus, flowdef.getTransitionStatus(),
				FlowActionCategory.SYSTEM, flowdef.getMsgTemplate(), staffId);
		return true;
	}

	@Override
	public boolean setOut(String quotedId, String staffId) {
		String actionCode = "22";// 发车
		QuotedInfo quotedInfo = quotedInfoDao.get(quotedId);
		String currentStatus = quotedInfo.getStatus();
		FlowDef flowdef = flowsService.executeFlow(ZHFlow.QUOTED, actionCode, currentStatus, FlowActionCategory.SYSTEM);
		quotedInfo.setStatus(flowdef.getTransitionStatus());
		quotedInfo.setUpdatedBy(staffId);
		quotedInfoDao.updateSelective(quotedInfo);
		quotedHisService.saveQuotedHis(quotedId, currentStatus, flowdef.getTransitionStatus(),
				FlowActionCategory.SYSTEM, flowdef.getMsgTemplate(), staffId);
		return true;
	}

}
