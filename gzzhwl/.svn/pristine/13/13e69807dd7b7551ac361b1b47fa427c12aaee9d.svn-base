package com.gzzhwl.core.data.extdao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadFeedBackType;
import com.gzzhwl.core.data.extdao.LoadFeedbackLogExtDao;
import com.gzzhwl.core.data.model.LoadFeedbackLog;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.utils.ValidateUtils;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class LoadFeedbackLogExtDaoImpl implements LoadFeedbackLogExtDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public <T, K, V> List<T> getFeedBackList(String loadId, DataSource source, String[] loadFeedBackTypes,
			String isException, String isEnd, String[] loadBillTypes) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		if (!ValidateUtils.isEmpty(loadBillTypes)) {
			params.put("statusArray", loadBillTypes);
		}
		if (!ValidateUtils.isEmpty(loadFeedBackTypes)) {
			params.put("types", loadFeedBackTypes);
		}
		if (!ValidateUtils.isEmpty(isException)) {
			params.put("isException", isException);
		}
		if (!ValidateUtils.isEmpty(isEnd)) {
			params.put("isEnd", isEnd);
		}
		if (!ValidateUtils.isEmpty(source)) {
			params.put("source", source.getCode());
		}

		params.put("isDeleted", Global.ISDEL_NORMAL.toString());

		return dao.find(PREFIX + ".getFeedBackList", params);
	}

	@Override
	public List<Map<String, Object>> findFeedBackOnTrans(Map<String, Object> params) {
		return dao.find(PREFIX + ".findFeedBackOnTrans", params);
	}

	@Override
	public String getRevFeedBackCharges(String loadId) {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("loadId", loadId);
		params.put("type", LoadFeedBackType.SOURCEADMIN.getCode());
		params.put("isException", LoadFeedbackLog.ISEXCEPTION_YES);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		
		
		return dao.get(PREFIX + ".getFeedBackCharges", params);
	}

	@Override
	public String getPayFeedBackCharges(String loadId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("loadId", loadId);
		params.put("type", LoadFeedBackType.VEHICLE.getCode());
		params.put("isException", LoadFeedbackLog.ISEXCEPTION_YES);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		
		
		return dao.get(PREFIX + ".getFeedBackCharges", params);
	}

	@Override
	public Integer getRevInvalidBackCount(String loadId) {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("loadId", loadId);
		params.put("type", LoadFeedBackType.VEHICLE.getCode());
		params.put("isException", LoadFeedbackLog.ISEXCEPTION_YES);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		
		return dao.get(PREFIX + ".getInvalidBackCount", params);
	}

	@Override
	public Integer getPayInvalidBackCount(String loadId) {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("loadId", loadId);
		params.put("type", LoadFeedBackType.VEHICLE.getCode());
		params.put("isException", LoadFeedbackLog.ISEXCEPTION_YES);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		
		return dao.get(PREFIX + ".getInvalidBackCount", params);
	}
}
