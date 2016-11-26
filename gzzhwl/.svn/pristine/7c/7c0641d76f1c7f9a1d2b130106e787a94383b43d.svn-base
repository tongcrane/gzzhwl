package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.UniqueKeyDao;
import com.gzzhwl.core.data.model.UniqueKey;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class UniqueKeyDaoImpl implements UniqueKeyDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public UniqueKey get(java.lang.String seqName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seqName", seqName);
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public int update(UniqueKey uniqueKey) {
		return dao.update(PREFIX + ".update", uniqueKey);
	}
}
