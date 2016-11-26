package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface QuotedInfoDao {
	public final static String PREFIX = QuotedInfoDao.class.getName();

	public QuotedInfo get(java.lang.String quotedId);

	public <K, V> Map<K, V> findOne(java.lang.String quotedId);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(QuotedInfo quotedInfo);

	public int update(QuotedInfo quotedInfo);

	public int updateSelective(QuotedInfo quotedInfo);

	public int delete(java.lang.String quotedId);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public int hasQuoted(Map<String, Object> params);

	public int findLineQuote(Map<String, Object> params);

}
