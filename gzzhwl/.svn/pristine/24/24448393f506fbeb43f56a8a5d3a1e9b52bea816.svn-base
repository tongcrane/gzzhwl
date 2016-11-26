package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoginHistory;
import com.gzzhwl.core.data.dao.LoginHistoryDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoginHistoryDaoImpl implements LoginHistoryDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoginHistory get(java.lang.String loginHistoryId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginHistoryId", loginHistoryId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String loginHistoryId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginHistoryId", loginHistoryId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(LoginHistory loginHistory) {
		return dao.insert(PREFIX + ".insert", loginHistory);
	}

	@Override
	public int update(LoginHistory loginHistory) {
		return dao.update(PREFIX + ".update", loginHistory);
	}
	
	@Override
	public int updateSelective(LoginHistory loginHistory) {
		return dao.update(PREFIX + ".updateSelective", loginHistory);
	}

	@Override
	public int delete(java.lang.String loginHistoryId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginHistoryId", loginHistoryId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public List<LoginHistory> findLoginHis(Map<String, String> params) {
		return dao.find(PREFIX + ".findLoginHis", params);
	}
	
	@Override
	public int updateStatus(Map<String,Object> map) {
		return dao.update(PREFIX + ".updateStatus", map);
	}
	
}


