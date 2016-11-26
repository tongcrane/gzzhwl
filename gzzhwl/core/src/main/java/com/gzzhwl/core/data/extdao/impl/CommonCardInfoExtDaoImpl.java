package com.gzzhwl.core.data.extdao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.CommonCardInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;

@Repository
public class CommonCardInfoExtDaoImpl implements CommonCardInfoExtDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public List<Map<String, Object>> findSourceCardInfo(Map<String, Object> params) {
		return dao.find(PREFIX + ".getSourceCardInfo", params);
	}

	@Override
	public List<Map<String, Object>> findQuoteCardInfo(Map<String, Object> params) {
		return dao.find(PREFIX + ".findQuoteCardInfo", params);
	}

}
