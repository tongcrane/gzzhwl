package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.RealDriverUsedInfoDao;
import com.gzzhwl.core.data.model.RealDriverUsedInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class RealDriverUsedInfoDaoImpl implements RealDriverUsedInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public RealDriverUsedInfo get(java.lang.String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(RealDriverUsedInfo realDriverUsedInfo) {
		return dao.insert(PREFIX + ".insert", realDriverUsedInfo);
	}

	@Override
	public int update(RealDriverUsedInfo realDriverUsedInfo) {
		return dao.update(PREFIX + ".update", realDriverUsedInfo);
	}

	@Override
	public int updateSelective(RealDriverUsedInfo realDriverUsedInfo) {
		return dao.update(PREFIX + ".updateSelective", realDriverUsedInfo);
	}

	@Override
	public int delete(java.lang.String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}

	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public long getIsStatus(String driverInfoId, String useStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("useStatus", useStatus);
		return dao.get(PREFIX + ".getIsStatus", params);
	}
}
