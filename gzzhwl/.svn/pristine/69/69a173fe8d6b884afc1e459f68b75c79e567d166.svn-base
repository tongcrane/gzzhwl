package com.gzzhwl.rest.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.StaffInfoDao;
import com.gzzhwl.core.data.dao.StaffLoginHistoryDao;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.data.model.StaffLoginHistory;
import com.gzzhwl.rest.exception.RestServerException;
import com.gzzhwl.rest.security.AdminService;
import com.gzzhwl.rest.security.validate.AdminValidator;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private StaffLoginHistoryDao loginHistoryDao;
	@Autowired
	private StaffInfoDao staffInfoDao;

	@Override
	public StaffInfo getCurrentAdmin(String authorization) throws RestServerException {
		AdminValidator.validAuthorization(authorization);// 验证参数是否合法
		String[] param = StringUtils.split(authorization, AUTHORIZATION_SPLIT);
		String staffId = param[0];
		String token = param[1];
		StaffLoginHistory loginHistory = this.getAccountHis(staffId, token);
		AdminValidator.validAuthorization(loginHistory, token);// 验证参数中的token是否有效
		StaffInfo staffInfo = this.getCurrentAdminById(staffId);
		AdminValidator.validStaffInfo(staffInfo);// 验证用户状态
		return staffInfo;
	}

	@Override
	public StaffLoginHistory getAccountHis(String staffId, String token) throws RestServerException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountId", staffId);
		params.put("accessToken", token);
		params.put("status", Global.STATUS_NORMAL.toString());
		List<StaffLoginHistory> loginHisList = loginHistoryDao.findLoginHis(params);
		if (loginHisList.isEmpty()) {
			return null;
		} else {
			return loginHisList.get(0);
		}
	}

	@Override
	public StaffInfo getCurrentAdminById(String staffId) throws RestServerException {
		return staffInfoDao.get(staffId);
	}
}
