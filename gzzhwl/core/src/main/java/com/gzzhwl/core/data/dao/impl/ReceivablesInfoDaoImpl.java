package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.ReceivablesInfo;
import com.gzzhwl.core.data.dao.ReceivablesInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class ReceivablesInfoDaoImpl implements ReceivablesInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public ReceivablesInfo get(java.lang.String consignId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consignId", consignId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String consignId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consignId", consignId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(ReceivablesInfo receivablesInfo) {
		return dao.insert(PREFIX + ".insert", receivablesInfo);
	}

	@Override
	public int update(ReceivablesInfo receivablesInfo) {
		return dao.update(PREFIX + ".update", receivablesInfo);
	}
	
	@Override
	public int updateSelective(ReceivablesInfo receivablesInfo) {
		return dao.update(PREFIX + ".updateSelective", receivablesInfo);
	}

	@Override
	public int delete(java.lang.String consignId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consignId", consignId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


