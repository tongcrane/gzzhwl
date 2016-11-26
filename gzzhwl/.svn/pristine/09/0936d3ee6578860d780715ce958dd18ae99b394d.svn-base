package com.gzzhwl.core.data.extdao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.QuotedHisExtDao;
import com.gzzhwl.core.data.model.QuotedHis;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class QuotedHisExtDaoImpl implements QuotedHisExtDao {
	@Autowired	
	private DaoSupport dao;
	
	@Override
	public QuotedHis getQuotedHisByDestStatus(String sourceId, String destStatus) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("sourceId", sourceId);
		params.put("destStatus", destStatus);
		
		return  dao.get(PREFIX+".getQuotedHis", params);
	}


	@Override
	public <T, K, V> List<T> getQuotedHisList(String sourceId) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("sourceId", sourceId);
		
		return dao.find(PREFIX + ".getQuotedHisList", params);
	}
}


