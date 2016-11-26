package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.DepartmentInfo;
import com.gzzhwl.core.data.dao.DepartmentInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class DepartmentInfoDaoImpl implements DepartmentInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public DepartmentInfo get(java.lang.Integer departId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.Integer departId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(DepartmentInfo departmentInfo) {
		return dao.insert(PREFIX + ".insert", departmentInfo);
	}

	@Override
	public int update(DepartmentInfo departmentInfo) {
		return dao.update(PREFIX + ".update", departmentInfo);
	}
	
	@Override
	public int updateSelective(DepartmentInfo departmentInfo) {
		return dao.update(PREFIX + ".updateSelective", departmentInfo);
	}

	@Override
	public int delete(java.lang.Integer departId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("departId", departId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public String getDepartName(String departId) {
		
		if(StringUtils.isNotBlank(departId)){
			DepartmentInfo departmentInfo = this.get(new Integer(departId));
			if (departmentInfo != null) {
				return departmentInfo.getName();
			}
		}
		
		return "";
	}
}


