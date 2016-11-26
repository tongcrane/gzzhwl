package com.gzzhwl.core.data.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.AccountVerifyLogDao;
import com.gzzhwl.core.data.model.AccountVerifyLog;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class AccountVerifyLogDaoImpl implements AccountVerifyLogDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(AccountVerifyLog accountVerifyLog) {
		return dao.insert(PREFIX + ".insert", accountVerifyLog);
	}

	@Override
	public AccountVerifyLog getLastest(Map<String, Object> params) {
		return dao.get(PREFIX + ".getLastest", params);
	}

}
