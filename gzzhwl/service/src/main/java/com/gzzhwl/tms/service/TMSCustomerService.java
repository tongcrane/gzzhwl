package com.gzzhwl.tms.service;

import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.rest.exception.RestException;

public interface TMSCustomerService {

	public String saveOrUpdate(final OrderSenderInfo sender) throws RestException;

}
