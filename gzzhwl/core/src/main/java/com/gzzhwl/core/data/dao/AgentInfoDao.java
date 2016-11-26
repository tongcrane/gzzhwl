package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface AgentInfoDao {    
    public final static String PREFIX = AgentInfoDao.class.getName();
    
	public AgentInfo get(java.lang.String accountId);
	
	public <K, V> Map<K, V> findOne(java.lang.String agentInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(AgentInfo agentInfo);
	
	public int update(AgentInfo agentInfo);
	
	public int updateSelective(AgentInfo agentInfo);
	
	public int delete(java.lang.String agentInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public int updateStatus (AgentInfo agentInfo);
	
	public <K,V>int hasIdno(Map<K,V> params);

}


