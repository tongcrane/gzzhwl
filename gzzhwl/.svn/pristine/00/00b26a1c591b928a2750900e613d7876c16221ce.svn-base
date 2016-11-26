package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.ResetPasswordLog;
import com.gzzhwl.core.data.dao.ResetPasswordLogDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class ResetPasswordLogDaoImpl implements ResetPasswordLogDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public ResetPasswordLog get(java.lang.String ticketId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ticketId", ticketId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String ticketId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ticketId", ticketId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(ResetPasswordLog resetPasswordLog) {
		return dao.insert(PREFIX + ".insert", resetPasswordLog);
	}

	@Override
	public int update(ResetPasswordLog resetPasswordLog) {
		return dao.update(PREFIX + ".update", resetPasswordLog);
	}
	
	@Override
	public int updateSelective(ResetPasswordLog resetPasswordLog) {
		return dao.update(PREFIX + ".updateSelective", resetPasswordLog);
	}

	@Override
	public int delete(java.lang.String ticketId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ticketId", ticketId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public int updateStatus(ResetPasswordLog resetPasswordLog) {
		return dao.update(PREFIX+".updateStatus", resetPasswordLog);
	}
}


