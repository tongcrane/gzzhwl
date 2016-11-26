package com.gzzhwl.core.data.extdao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.LoadElecreceiptExtDao;
import com.gzzhwl.core.data.model.LoadElecreceipt;
import com.gzzhwl.core.mybatis.support.DaoSupport;

@Repository
public class LoadElecreceiptExtDaoImpl implements LoadElecreceiptExtDao{
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadElecreceipt getElecreceipt(Map<String, Object> params) {
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public Map<String,Object> getCurrentElecreceipt(String loadId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		
		return dao.get(PREFIX + ".getCurrentElecreceipt", params);
	}
}
