package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.SupplyBankInfo;
import com.gzzhwl.core.data.dao.SupplyBankInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class SupplyBankInfoDaoImpl implements SupplyBankInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public SupplyBankInfo get(java.lang.String supplyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplyId", supplyId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String supplyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplyId", supplyId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(SupplyBankInfo supplyBankInfo) {
		return dao.insert(PREFIX + ".insert", supplyBankInfo);
	}

	@Override
	public int update(SupplyBankInfo supplyBankInfo) {
		return dao.update(PREFIX + ".update", supplyBankInfo);
	}
	
	@Override
	public int updateSelective(SupplyBankInfo supplyBankInfo) {
		return dao.update(PREFIX + ".updateSelective", supplyBankInfo);
	}

	@Override
	public int delete(java.lang.String supplyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplyId", supplyId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


