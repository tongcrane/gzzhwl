package com.gzzhwl.core.data.dao;


import java.util.List;

/**
 * 数据访问接口
 *
 */
public interface MenuInfoDao {    
    public final static String PREFIX = MenuInfoDao.class.getName();
    
	public <T> List<T> findMenu();
	
	public <T> List<T> findStaffFunction(String staffId);

}


