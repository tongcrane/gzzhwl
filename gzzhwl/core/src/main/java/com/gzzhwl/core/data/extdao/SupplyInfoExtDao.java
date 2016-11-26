package com.gzzhwl.core.data.extdao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.SupplyInfo;
import com.gzzhwl.core.page.Page;

public interface SupplyInfoExtDao {
	public final static String PREFIX = SupplyInfoExtDao.class.getName();

	// 获取供应商列表
	public <T, K, V> List<T> getSupplyList(Map<K, V> params);

	// 获取供应商分页信息
	public <E, K, V> Page<E> pageSupplyList(Map<K, V> params, int current, int pagesize);

	public <K, V> List<SupplyInfo> find(Map<K, V> params);
}
