package com.gzzhwl.core.data.extdao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.extdao.AgreementInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
@Repository
public class AgreementInfoExtDaoImpl implements AgreementInfoExtDao{
	@Autowired	
	private DaoSupport dao;

	@Override
	public <T, K, V> List<T> getAgreementsByCustId(String custId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("custId", custId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.find(PREFIX + ".getAgreementsByCustId", params);
	}

}
