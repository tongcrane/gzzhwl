package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.StaffInfoDao;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class StaffInfoDaoImpl implements StaffInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public StaffInfo get(java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <K, V> Map<K, V> findOne(java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(StaffInfo staffInfo) {
		return dao.insert(PREFIX + ".insert", staffInfo);
	}

	@Override
	public int update(StaffInfo staffInfo) {
		return dao.update(PREFIX + ".update", staffInfo);
	}

	@Override
	public int updateSelective(StaffInfo staffInfo) {
		return dao.update(PREFIX + ".updateSelective", staffInfo);
	}

	@Override
	public int delete(java.lang.String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("staffId", staffId);
		return dao.delete(PREFIX + ".delete", params);
	}

	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public List<StaffInfo> findByNumber(String number) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("number", number);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.find(PREFIX + ".findByNumber", params);
	}

	@Override
	public <T, K, V> Page<T> pageStaff(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".pageStaff", params, current, pagesize);
	}
}
