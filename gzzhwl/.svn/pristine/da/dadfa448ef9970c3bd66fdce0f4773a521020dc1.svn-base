package com.gzzhwl.admin.agent.service;

import java.util.Map;

import com.gzzhwl.admin.agent.model.AgentInfoVO;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.page.Page;

public interface AgentInfoService {
	public String saveAgentInfo(AgentInfo agentInfo);

	public boolean update(AgentInfo agentInfo);

	public AgentInfo getAgentInfoDetail(String agentInfoId);

	public Page<Map<String, Object>> getAgentInfoList(AgentInfoVO agentInfoVO, int pageIndex, int pageSize);
}
