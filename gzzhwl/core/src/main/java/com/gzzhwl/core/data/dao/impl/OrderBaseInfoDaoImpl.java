package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.data.dao.OrderBaseInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class OrderBaseInfoDaoImpl implements OrderBaseInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public OrderBaseInfo get(java.lang.String infoId) {
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
	public int insert(OrderBaseInfo orderBaseInfo) {
		return dao.insert(PREFIX + ".insert", orderBaseInfo);
	}

	@Override
	public int update(OrderBaseInfo orderBaseInfo) {
		return dao.update(PREFIX + ".update", orderBaseInfo);
	}
	
	@Override
	public int updateSelective(OrderBaseInfo orderBaseInfo) {
		return dao.update(PREFIX + ".updateSelective", orderBaseInfo);
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


