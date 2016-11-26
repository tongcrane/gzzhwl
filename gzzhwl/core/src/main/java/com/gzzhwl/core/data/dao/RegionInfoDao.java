package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.RegionInfo;

/**
 * 数据访问接口
 *
 */
public interface RegionInfoDao {
	public final static String PREFIX = RegionInfoDao.class.getName();

	public <T, K, V> List<T> find();

	public <K, V> RegionInfo findByParam(Map<K, V> params);

	public <K, V> List<RegionInfo> findRegion(Map<K, V> params);

}
