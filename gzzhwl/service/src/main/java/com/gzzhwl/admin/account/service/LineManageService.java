package com.gzzhwl.admin.account.service;

import java.util.List;
import java.util.Map;

public interface LineManageService {
	public List<Map<String, Object>> getLineList(String accountId);
}
