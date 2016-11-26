package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.ChargeInfoDao;
import com.gzzhwl.core.data.model.ChargeInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class ChargeInfoDaoImpl implements ChargeInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public ChargeInfo get(java.lang.String chargeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("chargeId", chargeId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public ChargeInfo getByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		return dao.get(PREFIX + ".getByName", params);
	}

}
