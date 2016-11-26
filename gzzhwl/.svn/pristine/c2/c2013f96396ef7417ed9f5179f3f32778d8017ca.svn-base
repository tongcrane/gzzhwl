package com.gzzhwl.admin.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.order.service.ChargeService;
import com.gzzhwl.core.data.dao.ChargeInfoDao;
import com.gzzhwl.core.data.model.ChargeInfo;
import com.gzzhwl.rest.exception.RestException;

@Service
public class ChargeServiceImpl implements ChargeService {
	@Autowired
	private ChargeInfoDao chargeInfoDao;

	@Override
	public List<Map<String, Object>> listCharge() throws RestException {
		return chargeInfoDao.find(null);
	}

	@Override
	public ChargeInfo getByName(String name) throws RestException {
		return chargeInfoDao.getByName(name);
	}
}
