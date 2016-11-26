package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 *
 */
public interface PushDeviceDao {
	public final static String PREFIX = PushDeviceDao.class.getName();

	public <T, K, V> List<T> find(Map<K, V> params);

}
