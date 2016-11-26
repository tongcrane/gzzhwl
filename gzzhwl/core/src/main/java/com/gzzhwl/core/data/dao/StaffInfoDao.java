package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface StaffInfoDao {    
    public final static String PREFIX = StaffInfoDao.class.getName();
    
	public StaffInfo get(java.lang.String staffId);
	
	public <K, V> Map<K, V> findOne(java.lang.String staffId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(StaffInfo staffInfo);
	
	public int update(StaffInfo staffInfo);
	
	public int updateSelective(StaffInfo staffInfo);
	
	public int delete(java.lang.String staffId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public List<StaffInfo> findByNumber(String number);
	
	public <T, K, V> Page<T> pageStaff(Map<K, V> params,int current,int pagesize);

}


