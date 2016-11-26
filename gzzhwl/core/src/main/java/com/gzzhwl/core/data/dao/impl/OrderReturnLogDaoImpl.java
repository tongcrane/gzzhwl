package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.OrderReturnLogDao;
import com.gzzhwl.core.data.model.OrderReturnLog;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class OrderReturnLogDaoImpl implements OrderReturnLogDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(OrderReturnLog orderReturnLog) {
		return dao.insert(PREFIX + ".insert", orderReturnLog);
	}

	@Override
	public int updateSelective(OrderReturnLog orderReturnLog) {
		return dao.update(PREFIX + ".updateSelective", orderReturnLog);
	}

	@Override
	public <K, V> String getApplyLog(Map<K, V> params) {
		return dao.get(PREFIX + ".getApplyLog", params);
	}

	@Override
	public OrderReturnLog get(String logId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("logId", logId);
		return dao.get(PREFIX + ".get", params);
	}

}
