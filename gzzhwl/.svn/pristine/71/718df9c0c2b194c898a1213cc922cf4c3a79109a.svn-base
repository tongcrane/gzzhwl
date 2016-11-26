package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoadFeedbackAtta;
import com.gzzhwl.core.data.dao.LoadFeedbackAttaDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoadFeedbackAttaDaoImpl implements LoadFeedbackAttaDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadFeedbackAtta get(java.lang.String attaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("attaId", attaId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String attaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("attaId", attaId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(LoadFeedbackAtta loadFeedbackAtta) {
		return dao.insert(PREFIX + ".insert", loadFeedbackAtta);
	}

	@Override
	public int update(LoadFeedbackAtta loadFeedbackAtta) {
		return dao.update(PREFIX + ".update", loadFeedbackAtta);
	}
	
	@Override
	public int updateSelective(LoadFeedbackAtta loadFeedbackAtta) {
		return dao.update(PREFIX + ".updateSelective", loadFeedbackAtta);
	}

	@Override
	public int delete(java.lang.String attaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("attaId", attaId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


