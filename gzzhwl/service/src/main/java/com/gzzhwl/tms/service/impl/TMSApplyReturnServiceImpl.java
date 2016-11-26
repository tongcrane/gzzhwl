package com.gzzhwl.tms.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.orderReturn.service.OrderReturnService;
import com.gzzhwl.admin.source.service.SourceService;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.service.TMSApplyReturnService;
import com.gzzhwl.tms.vo.SourceVO;

@Service
public class TMSApplyReturnServiceImpl implements TMSApplyReturnService {
	@Autowired
	private OrderReturnService orderReturnService;
	@Autowired
	private SourceService sourceService;
	@Value("${tmsadmin.staffid}")
	private String tmsAdminId;

	@Override
	public boolean applyReturn(SourceVO sourceVo) throws RestException {
		sourceVo.valid();
		String sourceId = sourceVo.getSourceId();
		OrderDetailInfo detailInfo = sourceService.getOrderDetailInfo(sourceId);
		String orderId = detailInfo.getOrderId();
		if (StringUtils.isBlank(orderId)) {
			throw new RestException("90003", "该货源ID不存在");
		}
		boolean success = orderReturnService.applyReturn(orderId, tmsAdminId);
		if (!success) {
			throw new RestException("90004", "该货源当前不能申请退回，或已申请，请勿重复申请。");
		}
		return success;
	}

}
