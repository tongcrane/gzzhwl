package com.gzzhwl.core.data.extdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.SupplyInfoExtDao;
import com.gzzhwl.core.data.model.SupplyInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

@Repository
public class SupplyInfoExtDapImpl implements SupplyInfoExtDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public <T, K, V> List<T> getSupplyList(Map<K, V> params) {
		return dao.find(PREFIX + ".getSupplyList", params);
	}

	@Override
	public <E, K, V> Page<E> pageSupplyList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public <K, V> List<SupplyInfo> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

}
