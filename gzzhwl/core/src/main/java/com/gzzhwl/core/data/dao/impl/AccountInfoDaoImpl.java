package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.LoadDriverInfo;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class AccountInfoDaoImpl implements AccountInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public AccountInfo get(java.lang.String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(AccountInfo accountInfo) {
		return dao.insert(PREFIX + ".insert", accountInfo);
	}

	@Override
	public int update(AccountInfo accountInfo) {
		return dao.update(PREFIX + ".update", accountInfo);
	}
	
	@Override
	public int updateSelective(AccountInfo accountInfo) {
		return dao.update(PREFIX + ".updateSelective", accountInfo);
	}

	@Override
	public int delete(java.lang.String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public <T, K, V> List<T> findAccountInfoToLogin(Map<K, V> params) {
		return dao.find(PREFIX + ".findAccountInfoToLogin", params);
	}

	@Override
	public <E, K, V> Page<E> pageAccountList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX+".pageAccountList", params, current, pagesize);
	}

	@Override
	public <K, V> Map<K, V> getAccountInfo(Map<K, V> params) {
		return dao.findOne(PREFIX+".getAccountInfo", params);
	}

	@Override
	public <T, K, V> List<T> getAccountIdByLoadId(String loadId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		params.put("isMajor", LoadDriverInfo.MAJOR_YES);
		params.put("statusArray",  new String[]{LoadBillType.NOTVEHICLE.getCode(), LoadBillType.VEHICLECHECK.getCode(),
				LoadBillType.CLOSETOSURFACE.getCode(), LoadBillType.DEPART.getCode(),
				LoadBillType.ARRIVED.getCode()});
		
		return dao.find(PREFIX+".getAccountIdByLoadId", params);
	}

}


