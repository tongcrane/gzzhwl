package com.gzzhwl.api.common.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.rest.exception.RestServerException;

public interface CommonSourceService {
	public List<Map<String, Object>> getCardInfo(String[] ids, String accountId) throws RestServerException;
}
