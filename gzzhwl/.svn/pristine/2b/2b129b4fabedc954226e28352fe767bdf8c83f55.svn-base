package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.AgreementInfo;
import com.gzzhwl.core.data.dao.AgreementInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class AgreementInfoDaoImpl implements AgreementInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public AgreementInfo get(java.lang.String agreementId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("agreementId", agreementId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String agreementId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("agreementId", agreementId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(AgreementInfo agreementInfo) {
		return dao.insert(PREFIX + ".insert", agreementInfo);
	}

	@Override
	public int update(AgreementInfo agreementInfo) {
		return dao.update(PREFIX + ".update", agreementInfo);
	}
	
	@Override
	public int updateSelective(AgreementInfo agreementInfo) {
		return dao.update(PREFIX + ".updateSelective", agreementInfo);
	}

	@Override
	public int delete(java.lang.String agreementId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("agreementId", agreementId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


