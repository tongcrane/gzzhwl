package com.gzzhwl.core.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.InternalDriverDao;
import com.gzzhwl.core.data.model.InternalDriver;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class InternalDriverDaoImpl implements InternalDriverDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(InternalDriver internalDriver) {
		return dao.insert(PREFIX + ".insert", internalDriver);
	}
}
