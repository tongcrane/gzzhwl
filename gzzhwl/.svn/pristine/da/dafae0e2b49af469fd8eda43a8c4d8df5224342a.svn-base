package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.CustomerBankInfo;
import com.gzzhwl.core.data.dao.CustomerBankInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class CustomerBankInfoDaoImpl implements CustomerBankInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public CustomerBankInfo get(java.lang.String customerId) {
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
	public int insert(CustomerBankInfo customerBankInfo) {
		return dao.insert(PREFIX + ".insert", customerBankInfo);
	}

	@Override
	public int update(CustomerBankInfo customerBankInfo) {
		return dao.update(PREFIX + ".update", customerBankInfo);
	}
	
	@Override
	public int updateSelective(CustomerBankInfo customerBankInfo) {
		return dao.update(PREFIX + ".updateSelective", customerBankInfo);
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


