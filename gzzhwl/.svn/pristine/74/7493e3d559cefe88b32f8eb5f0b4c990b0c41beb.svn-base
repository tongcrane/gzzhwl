package com.gzzhwl.core.data.extdao;


import java.util.List;

import com.gzzhwl.core.data.model.QuotedHis;

/**
 * 数据访问接口
 *
 */
public interface QuotedHisExtDao {    
    public final static String PREFIX = QuotedHisExtDao.class.getName();
	
	/**
	 * 获取货源某一状态的报价变更历史
	 * @param sourceId
	 * @param destStatus
	 * @return
	 */
	public QuotedHis getQuotedHisByDestStatus(String sourceId,String destStatus);
	 
	/**
	 * 获取货源的报价变更历史
	 * @param sourceId
	 * @return
	 */
	public <T, K, V> List<T> getQuotedHisList(String sourceId);

}


