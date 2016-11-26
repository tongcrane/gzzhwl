package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LineInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LineInfoDao {    
    public final static String PREFIX = LineInfoDao.class.getName();
    
	public LineInfo get(java.lang.String lineInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String lineInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LineInfo lineInfo);
	
	public int update(LineInfo lineInfo);
	
	public int updateSelective(LineInfo lineInfo);
	
	public int delete(java.lang.String lineInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	/**
	 * 获取热门线路
	 * @param accountId
	 * @return
	 */
	public <T, K, V> List<T> getHotLineList(String accountId,Integer lineCount);

}


