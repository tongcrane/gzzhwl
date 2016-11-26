package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.RealDriverUsedInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface RealDriverUsedInfoDao {
	public final static String PREFIX = RealDriverUsedInfoDao.class.getName();

	public RealDriverUsedInfo get(java.lang.String driverInfoId);

	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(RealDriverUsedInfo realDriverUsedInfo);

	public int update(RealDriverUsedInfo realDriverUsedInfo);

	public int updateSelective(RealDriverUsedInfo realDriverUsedInfo);

	public int delete(java.lang.String driverInfoId);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public long getIsStatus(java.lang.String driverInfoId, java.lang.String useStatus);

}
