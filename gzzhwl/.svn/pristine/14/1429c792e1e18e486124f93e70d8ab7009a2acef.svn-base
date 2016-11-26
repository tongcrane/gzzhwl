package com.gzzhwl.core.data.dao;

import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoginHistory;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoginHistoryDao {
	public final static String PREFIX = LoginHistoryDao.class.getName();

	public LoginHistory get(java.lang.String loginHistoryId);

	public <K, V> Map<K, V> findOne(java.lang.String loginHistoryId);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(LoginHistory loginHistory);

	public int update(LoginHistory loginHistory);

	public int updateSelective(LoginHistory loginHistory);

	public int delete(java.lang.String loginHistoryId);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * rest-security-use
	 */
	public List<LoginHistory> findLoginHis(Map<String, String> params);
	
	public int updateStatus(Map<String,Object> map);

}
