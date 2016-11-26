package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.QuotedInfoDao;
import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class QuotedInfoDaoImpl implements QuotedInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public QuotedInfo get(java.lang.String quotedId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quotedId", quotedId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <K, V> Map<K, V> findOne(java.lang.String quotedId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quotedId", quotedId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(QuotedInfo quotedInfo) {
		return dao.insert(PREFIX + ".insert", quotedInfo);
	}

	@Override
	public int update(QuotedInfo quotedInfo) {
		return dao.update(PREFIX + ".update", quotedInfo);
	}

	@Override
	public int updateSelective(QuotedInfo quotedInfo) {
		return dao.update(PREFIX + ".updateSelective", quotedInfo);
	}

	@Override
	public int delete(java.lang.String quotedId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quotedId", quotedId);
		return dao.delete(PREFIX + ".delete", params);
	}

	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public int hasQuoted(Map<String, Object> params) {
		return dao.get(PREFIX + ".hasQuoted", params);
	}

	@Override
	public int findLineQuote(Map<String, Object> params) {
		return dao.get(PREFIX + ".findLineQuote", params);
	}
}
