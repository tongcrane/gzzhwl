package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.PushDevice;
import com.gzzhwl.core.data.model.PushInfo;

/**
 * 数据访问接口
 *
 */
public interface PushInfoDao {
	public final static String PREFIX = PushInfoDao.class.getName();

	public <T, K, V> List<T> find(Map<String, Object> params);

	public int insert(PushInfo pushInfo);

	public int updateSelective(PushInfo pushInfo);

	public int delInfoByToken(Map<String, Object> params);

}
