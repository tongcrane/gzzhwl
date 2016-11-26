package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.SourceQuotedInfo;
import com.gzzhwl.core.data.dao.SourceQuotedInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class SourceQuotedInfoDaoImpl implements SourceQuotedInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public SourceQuotedInfo get(java.lang.String quotedId, java.lang.String sourceId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quotedId", quotedId);
		params.put("sourceId", sourceId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String quotedId, java.lang.String sourceId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quotedId", quotedId);
		params.put("sourceId", sourceId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(SourceQuotedInfo sourceQuotedInfo) {
		return dao.insert(PREFIX + ".insert", sourceQuotedInfo);
	}

	@Override
	public int update(SourceQuotedInfo sourceQuotedInfo) {
		return dao.update(PREFIX + ".update", sourceQuotedInfo);
	}
	
	@Override
	public int updateSelective(SourceQuotedInfo sourceQuotedInfo) {
		return dao.update(PREFIX + ".updateSelective", sourceQuotedInfo);
	}

	@Override
	public int delete(java.lang.String quotedId, java.lang.String sourceId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quotedId", quotedId);
		params.put("sourceId", sourceId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


