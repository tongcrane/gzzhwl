package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.AccountOperationLog;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface AccountOperationLogDao {    
    public final static String PREFIX = AccountOperationLogDao.class.getName();
    
	public AccountOperationLog get(java.lang.String logId);
	
	public <K, V> Map<K, V> findOne(java.lang.String logId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(AccountOperationLog accountOperationLog);
	
	public int update(AccountOperationLog accountOperationLog);
	
	public int updateSelective(AccountOperationLog accountOperationLog);
	
	public int delete(java.lang.String logId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


