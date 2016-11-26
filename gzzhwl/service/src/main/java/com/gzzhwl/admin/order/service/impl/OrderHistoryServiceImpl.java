package com.gzzhwl.admin.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.order.service.OrderHistoryService;
import com.gzzhwl.core.data.dao.OrderHisDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.OrderHis;
import com.gzzhwl.core.utils.IdentifierUtils;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	@Autowired
	private OrderHisDao orderHisDao;

	@Override
	public String saveOrderHistory(String orderId, String current, FlowDef flowDef, String staffId) {
		OrderHis orderHis = new OrderHis();
		orderHis.setCreatedBy(staffId);
		orderHis.setHisId(IdentifierUtils.getId().generate().toString());
		orderHis.setOrderId(orderId);
		orderHis.setSrcStatus(current);
		orderHis.setDestStatus(flowDef.getTransitionStatus());
		orderHis.setMsgInfo(flowDef.getMsgTemplate());
		orderHisDao.insert(orderHis);
		return orderId;
	}

}
