package com.gzzhwl.core.data.extdao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;

public interface CustomerInfoExtDao {
	public final static String PREFIX = CustomerInfoExtDao.class.getName();
	
	public <E, K, V> Page<E> pageCustList(Map<K,V> params, int current, int pagesize);
	
	public <T, K, V> List<T> find(Map<K, V> params);
}
