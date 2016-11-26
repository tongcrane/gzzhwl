package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.MessageTips;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface MessageTipsDao {    
    public final static String PREFIX = MessageTipsDao.class.getName();
    
	public MessageTips get(java.lang.String messageId);
	
	public <K, V> Map<K, V> findOne(java.lang.String messageId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(MessageTips messageTips);
	
	public int update(MessageTips messageTips);
	
	public int updateSelective(MessageTips messageTips);
	
	public int delete(java.lang.String messageId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public <K,V> int getUnreadMessageCount(Map<K,V> params);
	
	public <K,V> int updateMessageStatus(Map<K,V> params);

}


