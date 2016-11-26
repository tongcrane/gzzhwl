package com.gzzhwl.core.data.dao;

import com.gzzhwl.core.data.model.UniqueKey;

/**
 * 数据访问接口
 *
 */
public interface UniqueKeyDao {
	public final static String PREFIX = UniqueKeyDao.class.getName();

	public UniqueKey get(java.lang.String seqName);

	public int update(UniqueKey uniqueKey);

}
