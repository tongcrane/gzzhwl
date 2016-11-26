package com.gzzhwl.admin.contract.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.contract.service.ContractLinkStatusService;
import com.gzzhwl.admin.contract.service.ContractService;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.rest.exception.RestException;

@Service
public class ContractLinkStatusServiceImpl implements ContractLinkStatusService {
	@Autowired
	private LoadBillService loadBillService;
	@Autowired
	private ContractService contractService;

	@Override
	public boolean cancelContractByOrder(String orderId, String staffId) throws RestException {
		String loadId = loadBillService.getCurrentLoadBill(orderId);// 根据订单ID获取配载单
		if (StringUtils.isNoneBlank(loadId)) {
			String contractId = contractService.getCurrentContract(loadId);// 根据配载单获取当前司机合同
			if (StringUtils.isNotBlank(contractId)) {
				return contractService.cancelContract(contractId, staffId);
			}
		}
		return true;
	}

}
