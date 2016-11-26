package com.gzzhwl.admin.source.service;

import com.gzzhwl.core.data.model.FlowDef;

public interface SourceHistoryService {
	public String saveSourceHistory(String sourceId, String current, FlowDef flowDef, String staffId);
}
