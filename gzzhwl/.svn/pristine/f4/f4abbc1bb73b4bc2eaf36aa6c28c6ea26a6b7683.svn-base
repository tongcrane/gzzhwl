package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.StaffOrgRela;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface StaffOrgRelaDao {    
    public final static String PREFIX = StaffOrgRelaDao.class.getName();
    
	public StaffOrgRela get(java.lang.Integer departId, java.lang.String staffId);
	
	public <K, V> Map<K, V> findOne(java.lang.Integer departId, java.lang.String staffId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(StaffOrgRela staffOrgRela);
	
	public int update(StaffOrgRela staffOrgRela);
	
	public int updateSelective(StaffOrgRela staffOrgRela);
	
	public int delete(java.lang.Integer departId , java.lang.String staffId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public int delStaffDep(String staffId);

}


