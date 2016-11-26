package com.gzzhwl.admin.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.security.service.StaffLoginHisService;
import com.gzzhwl.core.constant.LoginType;
import com.gzzhwl.core.data.dao.StaffLoginHistoryDao;
import com.gzzhwl.core.data.model.LoginHistory;
import com.gzzhwl.core.data.model.StaffLoginHistory;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestServerException;

@Service
public class StaffLoginHisServiceImpl implements StaffLoginHisService {
	@Autowired
	private StaffLoginHistoryDao loginHistoryDao;

	@Override
	public String saveUserLoginHis(String accountId) throws RestServerException {
		this.deleteUserLoginHis(accountId, DEFAULT_LOGIN_TYPE);
		String token = IdentifierUtils.getId().generate().toString();// 生成最新token
		StaffLoginHistory loginHistory = new StaffLoginHistory();
		loginHistory.setLoginHistoryId(IdentifierUtils.getId().generate().toString());
		loginHistory.setAccountId(accountId);
		loginHistory.setAccessToken(token);
		loginHistory.setLoginType(DEFAULT_LOGIN_TYPE.getCode());
		loginHistory.setStatus(LoginHistory.STATUS_NORMAL);
		loginHistoryDao.insert(loginHistory);
		return token;
	}

	@Override
	public boolean updateInvalidToken(String accountId) throws RestServerException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("loginType", DEFAULT_LOGIN_TYPE.getCode());
		map.put("loginStatus", LoginHistory.STATUS_NORMAL);
		map.put("logoutStatus", LoginHistory.STATUS_OVERDUE);
		map.put("logoutTime", "1");// 将使用now替换
		loginHistoryDao.updateStatus(map);
		return true;
	}

	/**
	 * 将该人员，该类型之前的登录历史置为失效
	 * 
	 * @param accountId
	 * @param loginType
	 * @return
	 * @throws RestServerException
	 */
	private boolean deleteUserLoginHis(String accountId, LoginType loginType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("accountId", accountId);
		param.put("loginType", loginType.getCode());
		param.put("loginStatus", LoginHistory.STATUS_NORMAL);
		param.put("logoutStatus", LoginHistory.STATUS_OVERDUE);
		loginHistoryDao.updateStatus(param);
		return true;
	}
}
