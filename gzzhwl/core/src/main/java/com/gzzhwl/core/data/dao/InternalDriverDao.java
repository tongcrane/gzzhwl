package com.gzzhwl.core.data.dao;

import com.gzzhwl.core.data.model.InternalDriver;

/**
 * 数据访问接口
 *
 */
public interface InternalDriverDao {
	public final static String PREFIX = InternalDriverDao.class.getName();

	public int insert(InternalDriver internalDriver);

}
