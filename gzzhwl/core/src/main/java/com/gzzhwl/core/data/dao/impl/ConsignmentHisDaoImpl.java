package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.ConsignmentHis;
import com.gzzhwl.core.data.dao.ConsignmentHisDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class ConsignmentHisDaoImpl implements ConsignmentHisDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public ConsignmentHis get(java.lang.String hisId) {
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
	public int insert(ConsignmentHis consignmentHis) {
		return dao.insert(PREFIX + ".insert", consignmentHis);
	}

	@Override
	public int update(ConsignmentHis consignmentHis) {
		return dao.update(PREFIX + ".update", consignmentHis);
	}
	
	@Override
	public int updateSelective(ConsignmentHis consignmentHis) {
		return dao.update(PREFIX + ".updateSelective", consignmentHis);
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


