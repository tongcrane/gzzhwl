package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.data.dao.AgentInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class AgentInfoDaoImpl implements AgentInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public AgentInfo get(java.lang.String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String agentInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("agentInfoId", agentInfoId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(AgentInfo agentInfo) {
		return dao.insert(PREFIX + ".insert", agentInfo);
	}

	@Override
	public int update(AgentInfo agentInfo) {
		return dao.update(PREFIX + ".update", agentInfo);
	}
	
	@Override
	public int updateSelective(AgentInfo agentInfo) {
		return dao.update(PREFIX + ".updateSelective", agentInfo);
	}

	@Override
	public int delete(java.lang.String agentInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("agentInfoId", agentInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public int updateStatus (AgentInfo agentInfo) {
		return dao.update(PREFIX+".updateStatus", agentInfo);
	}

	@Override
	public <K, V> int hasIdno(Map<K, V> params) {
		return dao.get(PREFIX+".hasIdno", params);
	}


}


