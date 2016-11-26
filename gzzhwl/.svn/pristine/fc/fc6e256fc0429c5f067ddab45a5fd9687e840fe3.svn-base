package com.gzzhwl.rest.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.dao.LoginHistoryDao;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.LoginHistory;
import com.gzzhwl.rest.exception.RestServerException;
import com.gzzhwl.rest.security.AuthorizationService;
import com.gzzhwl.rest.security.validate.AuthorizationValidator;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	@Autowired
	private LoginHistoryDao loginHistoryDao;
	@Autowired
	private AccountInfoDao loginInfoDao;

	@Override
	public AccountInfo getCurrentAccount(String authorization) throws RestServerException {
		AuthorizationValidator.validAuthorization(authorization);// 验证参数是否合法
		String[] param = StringUtils.split(authorization, AUTHORIZATION_SPLIT);
		String userId = param[0];
		String token = param[1];
		LoginHistory loginHistory = this.getAccountHis(userId, token);
		AuthorizationValidator.validAuthorization(loginHistory, token);// 验证参数中的token是否有效
		return this.getCurrentAccountById(userId);
	}

	@Override
	public LoginHistory getAccountHis(String userId, String token) throws RestServerException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountId", userId);
		params.put("accessToken", token);
		params.put("status", Global.STATUS_NORMAL.toString());
		List<LoginHistory> loginHisList = loginHistoryDao.findLoginHis(params);
		if (loginHisList.isEmpty()) {
			return null;
		} else {
			return loginHisList.get(0);
		}
	}

	@Override
	public AccountInfo getCurrentAccountById(String loginId) throws RestServerException {
		return loginInfoDao.get(loginId);
	}

}
