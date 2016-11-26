package com.gzzhwl.admin.source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.source.service.SourceHistoryService;
import com.gzzhwl.core.data.dao.SourceHisDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.SourceHis;
import com.gzzhwl.core.utils.IdentifierUtils;

@Service
public class SourceHistoryServiceImpl implements SourceHistoryService {
	@Autowired
	private SourceHisDao sourceHisDao;

	@Override
	public String saveSourceHistory(String sourceId, String current, FlowDef flowDef, String staffId) {
		SourceHis sourceHis = new SourceHis();
		sourceHis.setCreatedBy(staffId);
		sourceHis.setHisId(IdentifierUtils.getId().generate().toString());
		sourceHis.setSourceId(sourceId);
		sourceHis.setSrcStatus(current);
		sourceHis.setDestStatus(flowDef.getTransitionStatus());
		sourceHis.setMsgInfo(flowDef.getMsgTemplate());
		sourceHisDao.insert(sourceHis);
		return sourceId;
	}

}
