package com.gzzhwl.core.support.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.PushDeviceDao;
import com.gzzhwl.core.data.model.PushDevice;
import com.gzzhwl.core.support.service.PushDeviceService;

@Service
public class PushDeviceServiceImpl implements PushDeviceService {
	@Autowired
	private PushDeviceDao pushDeviceDao;

	@Override
	public List<PushDevice> findPushDevice() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("status", Global.STATUS_NORMAL.toString());
		return pushDeviceDao.find(params);
	}

}
