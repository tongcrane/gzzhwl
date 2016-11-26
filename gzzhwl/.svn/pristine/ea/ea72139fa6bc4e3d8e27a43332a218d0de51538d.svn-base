package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.StaffOrgRela;
import com.gzzhwl.core.data.dao.StaffOrgRelaDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class StaffOrgRelaDaoImpl implements StaffOrgRelaDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public StaffOrgRela get(java.lang.Integer departId, java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		params.put("staffId", staffId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.Integer departId, java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		params.put("staffId", staffId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(StaffOrgRela staffOrgRela) {
		return dao.insert(PREFIX + ".insert", staffOrgRela);
	}

	@Override
	public int update(StaffOrgRela staffOrgRela) {
		return dao.update(PREFIX + ".update", staffOrgRela);
	}
	
	@Override
	public int updateSelective(StaffOrgRela staffOrgRela) {
		return dao.update(PREFIX + ".updateSelective", staffOrgRela);
	}

	@Override
	public int delete(java.lang.Integer departId, java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		params.put("staffId", staffId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public int delStaffDep(String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		return dao.delete(PREFIX+".delStaffDep", params);
	}
}


