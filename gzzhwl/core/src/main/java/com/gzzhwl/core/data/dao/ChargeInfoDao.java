package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.ChargeInfo;

/**
 * 数据访问接口
 *
 */
public interface ChargeInfoDao {
	public final static String PREFIX = ChargeInfoDao.class.getName();

	public ChargeInfo get(java.lang.String chargeId);

	public ChargeInfo getByName(java.lang.String name);

	public <T, K, V> List<T> find(Map<K, V> params);

}
