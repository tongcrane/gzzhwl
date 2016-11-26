package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoadTripInfo;
import com.gzzhwl.core.data.dao.LoadTripInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoadTripInfoDaoImpl implements LoadTripInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadTripInfo get(java.lang.String loadId) {
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
	public int insert(LoadTripInfo loadTripInfo) {
		return dao.insert(PREFIX + ".insert", loadTripInfo);
	}

	@Override
	public int update(LoadTripInfo loadTripInfo) {
		return dao.update(PREFIX + ".update", loadTripInfo);
	}
	
	@Override
	public int updateSelective(LoadTripInfo loadTripInfo) {
		return dao.update(PREFIX + ".updateSelective", loadTripInfo);
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
}


