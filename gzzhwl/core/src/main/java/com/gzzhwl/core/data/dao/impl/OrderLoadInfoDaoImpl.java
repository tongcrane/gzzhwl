package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.OrderLoadInfoDao;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class OrderLoadInfoDaoImpl implements OrderLoadInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public OrderLoadInfo get(java.lang.String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <K, V> Map<K, V> findOne(java.lang.String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(OrderLoadInfo orderLoadInfo) {
		return dao.insert(PREFIX + ".insert", orderLoadInfo);
	}

	@Override
	public int update(OrderLoadInfo orderLoadInfo) {
		return dao.update(PREFIX + ".update", orderLoadInfo);
	}

	@Override
	public int updateSelective(OrderLoadInfo orderLoadInfo) {
		return dao.update(PREFIX + ".updateSelective", orderLoadInfo);
	}

	@Override
	public int delete(java.lang.String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		return dao.delete(PREFIX + ".delete", params);
	}

	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public <E, K, V> Page<E> pageLoads(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".pageLoads", params, current, pagesize);
	}

	@Override
	public String getLoadByOrder(Map<String, Object> params) {
		return dao.get(PREFIX + ".getLoadByOrder", params);
	}

	@Override
	public <K, V> Map<K, V> getLoadInfo(Map<K, V> params) {
		return dao.get(PREFIX + ".getLoadInfo", params);
	}

	@Override
	public String getLoadByVehicle(Map<String, Object> params) {
		return dao.get(PREFIX + ".getLoadByVehicle", params);
	}

	@Override
	public Map<String, Object> findBaseInfo(Map<String, Object> params) {
		return dao.findOne(PREFIX + ".findBaseInfo", params);
	}
}
