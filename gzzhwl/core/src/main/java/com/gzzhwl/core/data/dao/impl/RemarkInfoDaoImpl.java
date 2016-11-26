package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.RemarkInfo;
import com.gzzhwl.core.data.dao.RemarkInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class RemarkInfoDaoImpl implements RemarkInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public RemarkInfo get(java.lang.Long pid, java.lang.String remarkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("remarkId", remarkId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.Long pid, java.lang.String remarkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("remarkId", remarkId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(RemarkInfo remarkInfo) {
		return dao.insert(PREFIX + ".insert", remarkInfo);
	}

	@Override
	public int update(RemarkInfo remarkInfo) {
		return dao.update(PREFIX + ".update", remarkInfo);
	}
	
	@Override
	public int updateSelective(RemarkInfo remarkInfo) {
		return dao.update(PREFIX + ".updateSelective", remarkInfo);
	}

	@Override
	public int delete(java.lang.Long pid, java.lang.String remarkId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("remarkId", remarkId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


