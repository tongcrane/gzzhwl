package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.data.dao.CustomerInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class CustomerInfoDaoImpl implements CustomerInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public CustomerInfo get(java.lang.String customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(CustomerInfo customerInfo) {
		return dao.insert(PREFIX + ".insert", customerInfo);
	}

	@Override
	public int update(CustomerInfo customerInfo) {
		return dao.update(PREFIX + ".update", customerInfo);
	}
	
	@Override
	public int updateSelective(CustomerInfo customerInfo) {
		return dao.update(PREFIX + ".updateSelective", customerInfo);
	}

	@Override
	public int delete(java.lang.String customerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


