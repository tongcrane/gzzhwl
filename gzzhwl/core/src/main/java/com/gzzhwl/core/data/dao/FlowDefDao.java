package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.FlowDef;

/**
 * 数据访问接口
 *
 */
public interface FlowDefDao {
	public final static String PREFIX = FlowDefDao.class.getName();

	public FlowDef get(Map<String, Object> params);

	public <T, K, V> List<T> find(Map<K, V> params);

}
