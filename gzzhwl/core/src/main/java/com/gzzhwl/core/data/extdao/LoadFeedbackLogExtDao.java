package com.gzzhwl.core.data.extdao;


import java.util.List;
import java.util.Map;

import com.gzzhwl.core.constant.DataSource;

/**
 * 数据访问接口
 *
 */
public interface LoadFeedbackLogExtDao {
    public final static String PREFIX = LoadFeedbackLogExtDao.class.getName();
    
    /**
     * 获取异常反馈
     * @param loadId
     * @param loadFeedBackType
     * @param loadBillType
     * @return
     */
    public <T, K, V> List<T> getFeedBackList(String loadId,DataSource source,String[] loadFeedBackTypes,String isException,String isEnd,String[] loadBillTypes);

	public List<Map<String, Object>> findFeedBackOnTrans(Map<String, Object> params);

	/**
	 * 获取应收异常反馈费用
	 * @param loadId
	 * @return
	 */
	public String getRevFeedBackCharges(String loadId);
	
	/**
	 * 获取应付异常反馈费用
	 * @param loadId
	 * @return
	 */
	public String getPayFeedBackCharges(String loadId);
	
	/**
	 * 获取应收未录入金额数据条数
	 * @param loadId
	 * @return
	 */
	public Integer getRevInvalidBackCount(String loadId);
	
	/**
	 * 获取应付未录入金额数据条数
	 * @param loadId
	 * @return
	 */
	public Integer getPayInvalidBackCount(String loadId);
	
	
}


