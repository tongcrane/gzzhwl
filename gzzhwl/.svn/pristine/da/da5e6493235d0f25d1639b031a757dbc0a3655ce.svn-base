package com.gzzhwl.common.service;

import com.gzzhwl.common.model.VerifyType;
import com.gzzhwl.core.data.model.AccountVerifyLog;

public interface AccountVerifyService {

	public AccountVerifyLog saveVerifyLog(VerifyType type, String targetId, String from, String to, String createdBy);

	public AccountVerifyLog getLastest(VerifyType type, String targetId);
}
