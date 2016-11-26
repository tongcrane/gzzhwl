package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface AccountInfoDao {    
    public final static String PREFIX = AccountInfoDao.class.getName();
    
	public AccountInfo get(java.lang.String accountId);
	
	public <K, V> Map<K, V> findOne(java.lang.String accountId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(AccountInfo accountInfo);
	
	public int update(AccountInfo accountInfo);
	
	public int updateSelective(AccountInfo accountInfo);
	
	public int delete(java.lang.String accountId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public <T, K, V> List<T> findAccountInfoToLogin(Map<K, V> params);
	
	public <E,K,V> Page<E> pageAccountList(Map<K,V> params,int current,int pagesize);
	
	public <K,V>Map<K,V> getAccountInfo(Map<K,V> params);
	
	public <T, K, V> List<T> getAccountIdByLoadId(String loadId);
	
}


