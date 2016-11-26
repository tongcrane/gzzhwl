package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoadHis;
import com.gzzhwl.core.data.dao.LoadHisDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoadHisDaoImpl implements LoadHisDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadHis get(java.lang.String hisId) {
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
	public int insert(LoadHis loadHis) {
		return dao.insert(PREFIX + ".insert", loadHis);
	}

	@Override
	public int update(LoadHis loadHis) {
		return dao.update(PREFIX + ".update", loadHis);
	}
	
	@Override
	public int updateSelective(LoadHis loadHis) {
		return dao.update(PREFIX + ".updateSelective", loadHis);
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


