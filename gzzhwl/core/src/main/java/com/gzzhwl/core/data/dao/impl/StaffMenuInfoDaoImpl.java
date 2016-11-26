package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.StaffMenuInfo;
import com.gzzhwl.core.data.dao.StaffMenuInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class StaffMenuInfoDaoImpl implements StaffMenuInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public StaffMenuInfo get(java.lang.Integer menuId, java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menuId);
		params.put("staffId", staffId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.Integer menuId, java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menuId);
		params.put("staffId", staffId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(StaffMenuInfo staffMenuInfo) {
		return dao.insert(PREFIX + ".insert", staffMenuInfo);
	}

	@Override
	public int update(StaffMenuInfo staffMenuInfo) {
		return dao.update(PREFIX + ".update", staffMenuInfo);
	}
	
	@Override
	public int updateSelective(StaffMenuInfo staffMenuInfo) {
		return dao.update(PREFIX + ".updateSelective", staffMenuInfo);
	}

	@Override
	public int delete(java.lang.Integer menuId, java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menuId);
		params.put("staffId", staffId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public int delStaffMenu(String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		return dao.delete(PREFIX+".delStaffMenu", params);
	}
}


