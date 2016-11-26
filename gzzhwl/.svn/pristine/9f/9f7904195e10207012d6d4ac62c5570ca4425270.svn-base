package com.gzzhwl.core.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.FlowStatusDao;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class FlowStatusDaoImpl implements FlowStatusDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public FlowStatus get(Map<String, Object> params) {
		return dao.get(PREFIX + ".get", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<String, Object> params) {
		
		return dao.find(PREFIX + ".find", params);
	}
	
}
