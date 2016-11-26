package com.gzzhwl.admin.consignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.consignment.service.ConsignmentHistoryService;
import com.gzzhwl.core.data.dao.ConsignmentHisDao;
import com.gzzhwl.core.data.model.ConsignmentHis;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.utils.IdentifierUtils;

@Service
public class ConsignmentHistoryServiceImpl implements ConsignmentHistoryService {
	@Autowired
	private ConsignmentHisDao consignmentHisDao;

	@Override
	public String saveConsignHistory(String consignId, String current, FlowDef flowDef, String staffId) {
		ConsignmentHis consignHis = new ConsignmentHis();
		consignHis.setCreatedBy(staffId);
		consignHis.setHisId(IdentifierUtils.getId().generate().toString());
		consignHis.setConsignId(consignId);
		consignHis.setSrcStatus(current);
		consignHis.setDestStatus(flowDef.getTransitionStatus());
		consignHis.setMsg(flowDef.getMsgTemplate());
		consignmentHisDao.insert(consignHis);
		return consignId;
	}

}
