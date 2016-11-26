package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.StaffMenuInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface StaffMenuInfoDao {    
    public final static String PREFIX = StaffMenuInfoDao.class.getName();
    
	public StaffMenuInfo get(java.lang.Integer menuId, java.lang.String staffId);
	
	public <K, V> Map<K, V> findOne(java.lang.Integer menuId, java.lang.String staffId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(StaffMenuInfo staffMenuInfo);
	
	public int update(StaffMenuInfo staffMenuInfo);
	
	public int updateSelective(StaffMenuInfo staffMenuInfo);
	
	public int delete(java.lang.Integer menuId , java.lang.String staffId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public int delStaffMenu(java.lang.String staffId);

}


