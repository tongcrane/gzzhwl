package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoadFeedbackLog;
import com.gzzhwl.core.data.dao.LoadFeedbackLogDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoadFeedbackLogDaoImpl implements LoadFeedbackLogDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadFeedbackLog get(java.lang.String feedbackId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("feedbackId", feedbackId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String feedbackId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("feedbackId", feedbackId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(LoadFeedbackLog loadFeedbackLog) {
		return dao.insert(PREFIX + ".insert", loadFeedbackLog);
	}

	@Override
	public int update(LoadFeedbackLog loadFeedbackLog) {
		return dao.update(PREFIX + ".update", loadFeedbackLog);
	}
	
	@Override
	public int updateSelective(LoadFeedbackLog loadFeedbackLog) {
		return dao.update(PREFIX + ".updateSelective", loadFeedbackLog);
	}

	@Override
	public int delete(java.lang.String feedbackId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("feedbackId", feedbackId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


