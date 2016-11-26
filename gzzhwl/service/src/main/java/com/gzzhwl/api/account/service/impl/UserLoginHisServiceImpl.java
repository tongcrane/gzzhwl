package com.gzzhwl.api.account.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.api.account.service.UserLoginHisService;
import com.gzzhwl.core.constant.LoginType;
import com.gzzhwl.core.data.dao.LoginHistoryDao;
import com.gzzhwl.core.data.model.LoginHistory;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestServerException;

@Service
public class UserLoginHisServiceImpl implements UserLoginHisService {
	@Autowired
	private LoginHistoryDao loginHistoryDao;

	@Override
	public String saveUserLoginHis(String accountId, LoginType loginType) throws RestServerException {
		this.deleteUserLoginHis(accountId, loginType);
		String token = IdentifierUtils.getId().generate().toString();// 生成最新token
		LoginHistory loginHistory = new LoginHistory();
		loginHistory.setLoginHistoryId(IdentifierUtils.getId().generate().toString());
		loginHistory.setAccountId(accountId);
		loginHistory.setAccessToken(token);
		loginHistory.setLoginType(loginType.getCode());
		loginHistory.setStatus(LoginHistory.STATUS_NORMAL);
		loginHistoryDao.insert(loginHistory);
		return token;
	}

	@Override
	public boolean updateInvalidToken(String accountId, LoginType loginType) throws RestServerException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", accountId);
		map.put("loginType", loginType.getCode());
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
