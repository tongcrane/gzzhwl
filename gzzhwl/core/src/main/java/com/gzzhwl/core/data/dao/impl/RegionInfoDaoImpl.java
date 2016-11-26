package com.gzzhwl.core.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.RegionInfoDao;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class RegionInfoDaoImpl implements RegionInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public <T, K, V> List<T> find() {
		return dao.find(PREFIX + ".find");
	}

	@Override
	public <K, V> RegionInfo findByParam(Map<K, V> params) {
		return dao.get(PREFIX + ".findByParam", params);
	}

	@Override
	public <K, V> List<RegionInfo> findRegion(Map<K, V> params) {
		return dao.find(PREFIX + ".findRegion", params);
	}

}
