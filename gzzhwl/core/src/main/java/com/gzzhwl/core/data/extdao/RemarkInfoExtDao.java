package com.gzzhwl.core.data.extdao;


import java.util.List;

/**
 * 数据访问接口
 *
 */
public interface RemarkInfoExtDao {    
    public final static String PREFIX = RemarkInfoExtDao.class.getName();
    
    public <T, K, V> List<T> getSourceRecordList(String target_id);
    
    public <T, K, V> List<T> getOrderRecordList(String target_id);
    
    public <T, K, V> List<T> getLoadRecordList(String target_id);
    
    public <T, K, V> List<T> getLoadRecordList(String target_id,String[] statusArray);

}


