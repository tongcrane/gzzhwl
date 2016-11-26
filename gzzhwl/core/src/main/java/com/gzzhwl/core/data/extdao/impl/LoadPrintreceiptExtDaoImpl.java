package com.gzzhwl.core.data.extdao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.LoadPrintreceiptExtDao;
import com.gzzhwl.core.data.model.LoadPrintreceipt;
import com.gzzhwl.core.mybatis.support.DaoSupport;

@Repository
public class LoadPrintreceiptExtDaoImpl implements LoadPrintreceiptExtDao{
	@Autowired	
	private DaoSupport dao;
	
	@Override
	public LoadPrintreceipt getPrintreceipt(Map<String, Object> params) {
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public Map<String,Object> getCurrentPrintreceipt(String loadId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		
		return dao.get(PREFIX + ".getCurrentPrintreceipt", params);
	}

}
