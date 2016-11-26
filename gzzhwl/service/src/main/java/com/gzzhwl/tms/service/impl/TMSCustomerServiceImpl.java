package com.gzzhwl.tms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.SeqNoKey;
import com.gzzhwl.core.data.dao.CustomerBankInfoDao;
import com.gzzhwl.core.data.dao.CustomerInfoDao;
import com.gzzhwl.core.data.model.CustomerBankInfo;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.service.TMSCustomerService;

@Service
public class TMSCustomerServiceImpl implements TMSCustomerService {
	@Autowired
	private CustomerInfoDao customerInfoDao;
	@Autowired
	private CustomerBankInfoDao customerBankInfoDao;
	@Value("${tmsadmin.staffid}")
	private String tmsAdminId;
	@Value("${tms.departid}")
	private String tmsDepartid;

	@Override
	public String saveOrUpdate(final OrderSenderInfo sender) throws RestException {
		String customerId = sender.getCustomerId();
		CustomerInfo customerInfo = customerInfoDao.get(customerId);
		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
			customerInfo.setCustomerId(customerId);
			String custno = IdentifierUtils.getSequence(SeqNoKey.CUST).generate().toString();
			customerInfo.setCustomerNo(custno);
			customerInfo.setCreatedBy(tmsAdminId);
			customerInfo.setUpdatedBy(tmsAdminId);
			customerInfo.setBelongDepartId(tmsDepartid);
			customerInfo.setFullName(sender.getCustomerName());
			customerInfo.setContactName(sender.getContactName());
			customerInfo.setAddress(sender.getAddress());
			customerInfo.setAreaCode(sender.getAreaCode());
			customerInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
			customerInfo.setMobile(sender.getMobile());
			customerInfo.setTelphone(sender.getTelphone());
			customerInfo.setStatus(Global.CUST_NORMAL.toString());
			customerInfoDao.insert(customerInfo);
			CustomerBankInfo customerBankInfo = new CustomerBankInfo();
			customerBankInfo.setCustomerId(customerId);
			customerBankInfoDao.insert(customerBankInfo);
		}
		return customerId;
	}
}
