package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoadElecreceipt;
import com.gzzhwl.core.data.dao.LoadElecreceiptDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoadElecreceiptDaoImpl implements LoadElecreceiptDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadElecreceipt get(java.lang.Long pid, java.lang.String receiptId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("receiptId", receiptId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.Long pid, java.lang.String receiptId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("receiptId", receiptId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(LoadElecreceipt loadElecreceipt) {
		return dao.insert(PREFIX + ".insert", loadElecreceipt);
	}

	@Override
	public int update(LoadElecreceipt loadElecreceipt) {
		return dao.update(PREFIX + ".update", loadElecreceipt);
	}
	
	@Override
	public int updateSelective(LoadElecreceipt loadElecreceipt) {
		return dao.update(PREFIX + ".updateSelective", loadElecreceipt);
	}

	@Override
	public int delete(java.lang.Long pid, java.lang.String receiptId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("receiptId", receiptId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


