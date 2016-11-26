package com.gzzhwl.core.data.dao;

import java.util.Map;

import com.gzzhwl.core.data.model.AccountVerifyLog;

/**
 * 数据访问接口
 *
 */
public interface AccountVerifyLogDao {
	public final static String PREFIX = AccountVerifyLogDao.class.getName();

	public int insert(AccountVerifyLog accountVerifyLog);

	public AccountVerifyLog getLastest(Map<String, Object> params);

}
