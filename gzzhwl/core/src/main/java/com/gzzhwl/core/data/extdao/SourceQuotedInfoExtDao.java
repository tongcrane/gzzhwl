package com.gzzhwl.core.data.extdao;


import java.util.Map;

import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SourceQuotedInfoExtDao {    
    public final static String PREFIX = SourceQuotedInfoExtDao.class.getName();
    
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


