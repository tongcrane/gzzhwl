package com.gzzhwl.admin.order.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.ChargeInfo;
import com.gzzhwl.rest.exception.RestException;

public interface ChargeService {

	public List<Map<String, Object>> listCharge() throws RestException;

	public ChargeInfo getByName(String name) throws RestException;

}
