package com.gzzhwl.api.agent.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.api.agent.vo.AgentInfoV2VO;
import com.gzzhwl.core.data.model.AgentInfo;

public interface AgentInfoService {

	public String saveAgentInfoV2(AgentInfo agentInfo, String accountId);

	/**
	 * 添加/修改经纪人信息
	 * 
	 * @param agentInfo
	 * @param accountId
	 * @return
	 */
	public String saveOrUpdateAgentInfo(AgentInfo agentInfo, String accountId);

	/**
	 * 获取经纪人详情
	 * 
	 * @param accountId
	 * @return
	 */
	public Map<String, Object> getAgentInfoByCondition(String accountId);

	/**
	 * 上传经纪人信息图片
	 * 
	 * @param file
	 * @param accountId
	 * @return
	 */
	public String updateImageInfo(MultipartFile image, String accountId);

	public boolean saveOrUpdateAgentInfoV2(AgentInfoV2VO agentInfoVo, String accountId);

	public Map<String, Object> getAgentByIdno(String idno);

	String AutoSaveAgentInfo(String idno,String realName, String mobile, String idFImageRefId, String idBImageRefId, String agentInfoId,
			String accountId);

	public boolean isPassed(String accountId); 
}
