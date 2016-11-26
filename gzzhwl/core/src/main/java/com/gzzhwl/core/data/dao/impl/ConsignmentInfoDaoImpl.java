package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.ConsignmentInfo;
import com.gzzhwl.core.data.dao.ConsignmentInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class ConsignmentInfoDaoImpl implements ConsignmentInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public ConsignmentInfo get(java.lang.String consignId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consignId", consignId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String consignId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consignId", consignId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(ConsignmentInfo consignmentInfo) {
		return dao.insert(PREFIX + ".insert", consignmentInfo);
	}

	@Override
	public int update(ConsignmentInfo consignmentInfo) {
		return dao.update(PREFIX + ".update", consignmentInfo);
	}
	
	@Override
	public int updateSelective(ConsignmentInfo consignmentInfo) {
		return dao.update(PREFIX + ".updateSelective", consignmentInfo);
	}

	@Override
	public int delete(java.lang.String consignId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("consignId", consignId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public String getConsignByOrder(Map<String, Object> params) {
		return dao.get(PREFIX + ".getConsignByOrder", params);
	}
}


