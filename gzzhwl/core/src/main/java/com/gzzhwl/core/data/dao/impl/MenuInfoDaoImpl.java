package com.gzzhwl.core.data.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.MenuInfoDao;
import com.gzzhwl.core.data.model.MenuInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class MenuInfoDaoImpl implements MenuInfoDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public <T> List<T> findMenu() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Global.STATUS_NORMAL.toString());
		return dao.find(PREFIX + ".findMenu", params);
	}

	@Override
	public <T> List<T> findStaffFunction(String staffId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isLeaf", MenuInfo.LEAF_YES);
		params.put("staffId", staffId);
		return dao.find(PREFIX + ".findStaffFunction", params);
	}

}
