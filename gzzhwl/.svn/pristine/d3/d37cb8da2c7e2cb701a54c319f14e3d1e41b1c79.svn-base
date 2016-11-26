package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.StoreAddrInfoDao;
import com.gzzhwl.core.data.model.StoreAddrInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class StoreAddrInfoDaoImpl implements StoreAddrInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public StoreAddrInfo get(java.lang.String addrId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("addrId", addrId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

}
