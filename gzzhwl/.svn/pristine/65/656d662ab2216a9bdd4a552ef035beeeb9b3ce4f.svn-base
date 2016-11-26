package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.QuotedPlusInfo;
import com.gzzhwl.core.data.dao.QuotedPlusInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class QuotedPlusInfoDaoImpl implements QuotedPlusInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public QuotedPlusInfo get(java.lang.String driverInfoId, java.lang.String quotedId, java.lang.String vehicleInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("quotedId", quotedId);
		params.put("vehicleInfoId", vehicleInfoId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId, java.lang.String quotedId, java.lang.String vehicleInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("quotedId", quotedId);
		params.put("vehicleInfoId", vehicleInfoId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(QuotedPlusInfo quotedPlusInfo) {
		return dao.insert(PREFIX + ".insert", quotedPlusInfo);
	}

	@Override
	public int update(QuotedPlusInfo quotedPlusInfo) {
		return dao.update(PREFIX + ".update", quotedPlusInfo);
	}
	
	@Override
	public int updateSelective(QuotedPlusInfo quotedPlusInfo) {
		return dao.update(PREFIX + ".updateSelective", quotedPlusInfo);
	}

	@Override
	public int delete(java.lang.String driverInfoId, java.lang.String quotedId, java.lang.String vehicleInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("quotedId", quotedId);
		params.put("vehicleInfoId", vehicleInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


