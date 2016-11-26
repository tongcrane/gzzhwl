package com.gzzhwl.core.data.extdao;


import java.util.List;
import com.gzzhwl.core.data.model.VehicleDriverInfo;

/**
 * 数据访问接口
 *
 */
public interface VehicleDriverExtInfoDao {    
    public final static String PREFIX = VehicleDriverExtInfoDao.class.getName();
    
	public VehicleDriverInfo get(String accountId, String vehicleInfoId, String driverInfoId);
	
	public <T, K, V> List<T> find(String accountId, String vehicleInfoId, String driverInfoId);
	
	public int insert(String accountId, String vehicleInfoId, String driverInfoId);
	
	public int delete(String accountId, String vehicleInfoId, String driverInfoId);

}


