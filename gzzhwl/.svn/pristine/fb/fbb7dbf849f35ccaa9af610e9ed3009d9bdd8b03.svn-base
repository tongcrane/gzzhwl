package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.ConsignmentHis;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface ConsignmentHisDao {    
    public final static String PREFIX = ConsignmentHisDao.class.getName();
    
	public ConsignmentHis get(java.lang.String hisId);
	
	public <K, V> Map<K, V> findOne(java.lang.String hisId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(ConsignmentHis consignmentHis);
	
	public int update(ConsignmentHis consignmentHis);
	
	public int updateSelective(ConsignmentHis consignmentHis);
	
	public int delete(java.lang.String hisId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


