package com.gzzhwl.admin.agent.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.agent.model.AgentInfoVO;
import com.gzzhwl.admin.agent.service.AgentInfoService;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.AgentInfoDao;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;

@Service("adminAgentInfoService")
public class AgentInfoServiceImpl implements AgentInfoService{
	@Autowired
	private AgentInfoDao agentInfoDao;

	@Override
	public String saveAgentInfo(AgentInfo agentInfo) {
		String agentInfoId = IdentifierUtils.getId().generate().toString();
		agentInfo.setAgentInfoId(agentInfoId);
		agentInfo.setSource(DataSource.PLATFORM.getCode());
		agentInfo.setCreatedBy(agentInfo.getAccountId());
		//agentInfo.setCreatedTime(DateUtils.currentStringDate("yyyy-mm-dd HH:mm:ss"));
		//agentInfo.setUpdatedTime(DateUtils.currentStringDate("yyyy-mm-dd HH:mm:ss"));
		agentInfo.setStatus(Global.STATUS_WAIT.toString());
		agentInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		agentInfoDao.insert(agentInfo);
		return agentInfoId;
	}

	@Override
	public boolean update(AgentInfo agentInfo) {
		agentInfo.setStatus(Global.STATUS_WAIT.toString());
		//agentInfo.setUpdatedTime(DateUtils.currentStringDate("yyyy-mm-dd HH:mm:ss"));
		return agentInfoDao.updateSelective(agentInfo) > 0;
	}

	@Override
	public AgentInfo getAgentInfoDetail(String agentInfoId) {
		AgentInfo agentInfo = agentInfoDao.get(agentInfoId);
		return agentInfo;
	}

	@Override
	public Page<Map<String, Object>> getAgentInfoList(AgentInfoVO agentInfoVO, int pageIndex,int pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		//params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if(StringUtils.isNotBlank(agentInfoVO.getRealName())) {
			params.put("realName", agentInfoVO.getRealName());
		}
		if(StringUtils.isNotBlank(agentInfoVO.getIdno())) {
			params.put("idno", agentInfoVO.getIdno());
		}
		if(StringUtils.isNotBlank(agentInfoVO.getSex())) {
			params.put("sex", agentInfoVO.getSex());
		}
		if(StringUtils.isNotBlank(agentInfoVO.getTelphone())) {
			params.put("telphone", agentInfoVO.getTelphone());
		}
		Page<Map<String, Object>> result = agentInfoDao.page(params, pageIndex, pageSize);
		return result;
	}





}
