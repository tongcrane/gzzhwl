package com.gzzhwl.admin.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.account.service.LineManageService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.LineInfoDao;

@Service
public class LineManageServiceImpl implements LineManageService{
	@Autowired
	private LineInfoDao lineInfoDao;

	@Override
	public List<Map<String, Object>> getLineList(String accountId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("accountId", accountId);
		List<Map<String, Object>> result = lineInfoDao.find(params);
		return result;
	}

}
