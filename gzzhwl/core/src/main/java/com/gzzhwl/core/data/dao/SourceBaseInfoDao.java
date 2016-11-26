package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.SourceBaseInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SourceBaseInfoDao {    
    public final static String PREFIX = SourceBaseInfoDao.class.getName();
    
	public SourceBaseInfo get(java.lang.String infoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String infoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(SourceBaseInfo sourceBaseInfo);
	
	public int update(SourceBaseInfo sourceBaseInfo);
	
	public int updateSelective(SourceBaseInfo sourceBaseInfo);
	
	public int delete(java.lang.String infoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public SourceBaseInfo getByOrderId(String orderId);

}


