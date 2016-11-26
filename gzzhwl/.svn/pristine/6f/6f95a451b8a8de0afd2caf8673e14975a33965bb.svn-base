package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.core.data.dao.OrderSenderInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class OrderSenderInfoDaoImpl implements OrderSenderInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public OrderSenderInfo get(java.lang.String infoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("infoId", infoId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String infoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("infoId", infoId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(OrderSenderInfo orderSenderInfo) {
		return dao.insert(PREFIX + ".insert", orderSenderInfo);
	}

	@Override
	public int update(OrderSenderInfo orderSenderInfo) {
		return dao.update(PREFIX + ".update", orderSenderInfo);
	}
	
	@Override
	public int updateSelective(OrderSenderInfo orderSenderInfo) {
		return dao.update(PREFIX + ".updateSelective", orderSenderInfo);
	}

	@Override
	public int delete(java.lang.String infoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("infoId", infoId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


