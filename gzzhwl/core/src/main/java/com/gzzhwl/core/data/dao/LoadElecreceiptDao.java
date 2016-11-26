package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadElecreceipt;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadElecreceiptDao {    
    public final static String PREFIX = LoadElecreceiptDao.class.getName();
    
	public LoadElecreceipt get(java.lang.Long pid, java.lang.String receiptId);
	
	public <K, V> Map<K, V> findOne(java.lang.Long pid, java.lang.String receiptId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadElecreceipt loadElecreceipt);
	
	public int update(LoadElecreceipt loadElecreceipt);
	
	public int updateSelective(LoadElecreceipt loadElecreceipt);
	
	public int delete(java.lang.Long pid , java.lang.String receiptId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


