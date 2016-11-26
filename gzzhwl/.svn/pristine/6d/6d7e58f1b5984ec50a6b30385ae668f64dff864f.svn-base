package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.StaffLoginHistoryDao;
import com.gzzhwl.core.data.model.StaffLoginHistory;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class StaffLoginHistoryDaoImpl implements StaffLoginHistoryDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public StaffLoginHistory get(java.lang.String loginHistoryId) {
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
	public int insert(StaffLoginHistory staffLoginHistory) {
		return dao.insert(PREFIX + ".insert", staffLoginHistory);
	}

	@Override
	public int update(StaffLoginHistory staffLoginHistory) {
		return dao.update(PREFIX + ".update", staffLoginHistory);
	}

	@Override
	public int updateSelective(StaffLoginHistory staffLoginHistory) {
		return dao.update(PREFIX + ".updateSelective", staffLoginHistory);
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
	public List<StaffLoginHistory> findLoginHis(Map<String, String> params) {
		return dao.find(PREFIX + ".findLoginHis", params);
	}

	@Override
	public int updateStatus(Map<String,Object> map) {
		return dao.update(PREFIX + ".updateStatus", map);
	}
}
