package com.gzzhwl.core.data.extdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.ConsignmentInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

@Repository
public class ConsignmentInfoExtDaoImpl implements ConsignmentInfoExtDao{
	@Autowired
	private DaoSupport dao;
	
	@Override
	public <E, K, V> Page<E> pageConsignList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".getConsignList", params, current, pagesize);
	}
	
	@Override
	public <K, V> Map<K, V> getOrderGenernalInfo(Map<K, V> params) {
		return dao.get(PREFIX + ".getOrderGeneralInfo", params);
	}
	
	@Override
	public <E, K, V> Page<E> pageRecieveList(Map<K, V> params, int currentPage, int pageSizes) {
		return dao.page(PREFIX + ".getRecieveList", params, currentPage, pageSizes);
	}

	@Override
	public <E, K, V> List<E> getReceiveSatementList(Map<K, V> params) {
		return dao.find(PREFIX + ".getReceiveStatementList", params);
	}

}
