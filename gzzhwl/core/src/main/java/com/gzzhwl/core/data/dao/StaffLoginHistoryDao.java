package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.StaffLoginHistory;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface StaffLoginHistoryDao {    
    public final static String PREFIX = StaffLoginHistoryDao.class.getName();
    
	public StaffLoginHistory get(java.lang.String loginHistoryId);
	
	public <K, V> Map<K, V> findOne(java.lang.String loginHistoryId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(StaffLoginHistory staffLoginHistory);
	
	public int update(StaffLoginHistory staffLoginHistory);
	
	public int updateSelective(StaffLoginHistory staffLoginHistory);
	
	public int delete(java.lang.String loginHistoryId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public List<StaffLoginHistory> findLoginHis(Map<String, String> params);

	public int updateStatus(Map<String, Object> map);

}


