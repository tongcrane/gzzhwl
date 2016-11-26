package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.ResetPasswordLog;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface ResetPasswordLogDao {    
    public final static String PREFIX = ResetPasswordLogDao.class.getName();
    
	public ResetPasswordLog get(java.lang.String ticketId);
	
	public <K, V> Map<K, V> findOne(java.lang.String ticketId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(ResetPasswordLog resetPasswordLog);
	
	public int update(ResetPasswordLog resetPasswordLog);
	
	public int updateSelective(ResetPasswordLog resetPasswordLog);
	
	public int delete(java.lang.String ticketId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public int updateStatus(ResetPasswordLog resetPasswordLog);

}


