package com.gzzhwl.admin.agent.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.agent.model.AgentInfoVO;
import com.gzzhwl.admin.agent.service.AgentInfoService;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController("adminAgrentController")
@RequestMapping("/admin/agent")
public class AgentInfoController {
	@Autowired
	@Qualifier("adminAgentInfoService")
	private AgentInfoService agentInfoService;

	@RequestMapping(value = "/addAgent", method = RequestMethod.POST)
	public ResponseModel addAgentInfo(AgentInfo agentInfo) {
		String agentInfoId = agentInfoService.saveAgentInfo(agentInfo);
		return new ResponseModel(agentInfoId);
	}

	@RequestMapping(value = "/queryAgentInfoList", method = RequestMethod.GET)
	public ResponseModel queryAgentInfoDetail(@Pagination PageParameter page, AgentInfoVO agentVO) {
		Page<Map<String, Object>> result = agentInfoService.getAgentInfoList(agentVO, page.getPageIndex(),
				page.getPageSize());
		return new ResponseModel(result);

	}

	@RequestMapping(value = "/queryAgentInfoDetail", method = RequestMethod.GET)
	public ResponseModel queryAgentInfoDetail(@RequestParam String agentInfoId) {
		AgentInfo agent = agentInfoService.getAgentInfoDetail(agentInfoId);
		return new ResponseModel(agent);

	}

	@RequestMapping(value = "/updateAgent", method = RequestMethod.POST)
	public ResponseModel updateAgent(AgentInfo agentInfo) {
		agentInfoService.update(agentInfo);
		return new ResponseModel(null);
	}
}
