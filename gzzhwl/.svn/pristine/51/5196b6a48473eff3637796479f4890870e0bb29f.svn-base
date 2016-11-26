package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.RealVehicleDriverInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface RealVehicleDriverInfoDao {    
    public final static String PREFIX = RealVehicleDriverInfoDao.class.getName();
    
	public RealVehicleDriverInfo get();
	
	public <K, V> Map<K, V> findOne();
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(RealVehicleDriverInfo realVehicleDriverInfo);
	
	public int update(RealVehicleDriverInfo realVehicleDriverInfo);
	
	public int updateSelective(RealVehicleDriverInfo realVehicleDriverInfo);
	
	public int delete();

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


