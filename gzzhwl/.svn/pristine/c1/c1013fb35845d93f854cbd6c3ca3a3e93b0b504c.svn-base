package com.gzzhwl.common.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.common.model.VerifyType;
import com.gzzhwl.common.service.AccountVerifyService;
import com.gzzhwl.core.data.dao.AccountVerifyLogDao;
import com.gzzhwl.core.data.model.AccountVerifyLog;
import com.gzzhwl.core.utils.IdentifierUtils;

@Service
public class AccountVerifyServiceImpl implements AccountVerifyService {
	@Autowired
	private AccountVerifyLogDao verifyLogDao;

	@Override
	public AccountVerifyLog saveVerifyLog(VerifyType type, String targetId, String from, String to, String createdBy) {
		AccountVerifyLog accountVerifyLog = new AccountVerifyLog();
		accountVerifyLog.setCreatedBy(createdBy);
		accountVerifyLog.setDestStatus(to);
		accountVerifyLog.setLogId(IdentifierUtils.getId().generate().toString());
		accountVerifyLog.setSrcStatus(from);
		accountVerifyLog.setTargetId(targetId);
		accountVerifyLog.setTargetType(type.getCode());
		verifyLogDao.insert(accountVerifyLog);
		return accountVerifyLog;
	}

	@Override
	public AccountVerifyLog getLastest(VerifyType type, String targetId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("target_id", targetId);
		params.put("target_type", type.getCode());
		return verifyLogDao.getLastest(params);
	}

}
