package com.gzzhwl.core.data.extdao;


import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderSourceInfoExtDao {    
    public final static String PREFIX = OrderSourceInfoExtDao.class.getName();
    
    public <K, V> Map<K, V> getQuotedSourceDetail(String sourceId);
    
    public <E, K, V> Page<E> getOrderSourcePage(Map<K, V> params, int current, int pagesize);
    
    /**
     * 获取热门线路货源分页
     * @return
     */
    public <E, K, V> Page<E> getHotLineSourcePage(String accountId,String startCodeP, int current, int pagesize);
    
    /**
     * 获取推荐货源分页
     * @return
     */
    public <E, K, V> Page<E> getRecommandSourcePage(Map<String, Object> params, int current, int pagesize);
    
    /**
     * 获取临时热门线路货源分页
     * @return
     */
    public <E, K, V> Page<E> getTmpHotLineSourcePage(String startCodeC,String endCodeC, int current, int pagesize);
    
    //获取最新发布的货源
    public <T, K, V> List<T> getNewSource(int count);
    
    //获取今天新发布的货源数量
    public int getNewSourceCnt();
    
    //获取成功签单的经纪人和司机数量
    public int getQutoSuccessCnt();

}


