package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.model.DepartmentInfo;
import com.gzzhwl.core.data.model.SupplyInfo;
import com.gzzhwl.core.data.dao.SupplyInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class SupplyInfoDaoImpl implements SupplyInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public SupplyInfo get(java.lang.String supplyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplyId", supplyId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String supplyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplyId", supplyId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(SupplyInfo supplyInfo) {
		return dao.insert(PREFIX + ".insert", supplyInfo);
	}

	@Override
	public int update(SupplyInfo supplyInfo) {
		return dao.update(PREFIX + ".update", supplyInfo);
	}
	
	@Override
	public int updateSelective(SupplyInfo supplyInfo) {
		return dao.update(PREFIX + ".updateSelective", supplyInfo);
	}

	@Override
	public int delete(java.lang.String supplyId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplyId", supplyId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public String getSupplyName(String supplyId) {
	
		if(StringUtils.isNotBlank(supplyId)){
			SupplyInfo supplyInfo = this.get(supplyId);
			if (supplyInfo != null) {
				return supplyInfo.getFullName();
			}
		}
		return "";
	}
}


