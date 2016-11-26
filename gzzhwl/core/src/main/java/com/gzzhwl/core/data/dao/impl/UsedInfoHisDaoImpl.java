package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.UsedInfoHis;
import com.gzzhwl.core.data.dao.UsedInfoHisDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class UsedInfoHisDaoImpl implements UsedInfoHisDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public UsedInfoHis get(java.lang.String hisId) {
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
	public int insert(UsedInfoHis usedInfoHis) {
		return dao.insert(PREFIX + ".insert", usedInfoHis);
	}

	@Override
	public int update(UsedInfoHis usedInfoHis) {
		return dao.update(PREFIX + ".update", usedInfoHis);
	}
	
	@Override
	public int updateSelective(UsedInfoHis usedInfoHis) {
		return dao.update(PREFIX + ".updateSelective", usedInfoHis);
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


