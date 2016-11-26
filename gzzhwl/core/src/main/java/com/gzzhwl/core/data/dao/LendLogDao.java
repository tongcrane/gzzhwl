package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.LendLog;

/**
 * 数据访问接口
 *
 */
public interface LendLogDao {
	public final static String PREFIX = LendLogDao.class.getName();

	public int insert(LendLog lendLog);

	public LendLog getLendLog(Map<String, Object> params);

	public LendLog get(String lendId);

	public int updateSelective(LendLog lendLog);

	public LendLog getDriverLendLog(Map<String, Object> params);

	public long isLendOut(Map<String, Object> params);

	public List<LendLog> getLogToReturn(Map<String, Object> params);

}
