package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.OrderHis;
import com.gzzhwl.core.data.dao.OrderHisDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class OrderHisDaoImpl implements OrderHisDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public OrderHis get(java.lang.String hisId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hisId", hisId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String hisId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hisId", hisId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(OrderHis orderHis) {
		return dao.insert(PREFIX + ".insert", orderHis);
	}

	@Override
	public int update(OrderHis orderHis) {
		return dao.update(PREFIX + ".update", orderHis);
	}
	
	@Override
	public int updateSelective(OrderHis orderHis) {
		return dao.update(PREFIX + ".updateSelective", orderHis);
	}

	@Override
	public int delete(java.lang.String hisId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hisId", hisId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


