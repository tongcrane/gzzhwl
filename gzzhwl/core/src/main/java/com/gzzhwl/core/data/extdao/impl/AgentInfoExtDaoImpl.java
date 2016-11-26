package com.gzzhwl.core.data.extdao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.extdao.AgentInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class AgentInfoExtDaoImpl implements AgentInfoExtDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public <K, V> Map<K, V> getAgentInfo(Map<K, V> params) {
		// TODO Auto-generated method stub
		return dao.get(PREFIX + ".getAgentInfo", params);
	}

	@Override
	public <E, K, V> Page<E> pageCheckList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX+".pageCheckList", params, current, pagesize);
	}

	@Override
	public <K, V> Map<K, V> getUserDetail(Map<K,V> params) {
		return dao.get(PREFIX + ".getUserDetail", params);
	}
}


