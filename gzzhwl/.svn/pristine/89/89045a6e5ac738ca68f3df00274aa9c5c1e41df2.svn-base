package com.gzzhwl.api.agent.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.gzzhwl.api.account.validate.LoginInfoValidator;
import com.gzzhwl.api.agent.service.AgentInfoService;
import com.gzzhwl.api.agent.validate.AgentValidate;
import com.gzzhwl.api.agent.vo.AgentInfoV2VO;
import com.gzzhwl.api.driver.service.DriverInfoService;
import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.service.ImageServiceFactory;
import com.gzzhwl.api.vehicle.vo.VehicleInfoVo;
import com.gzzhwl.core.constant.AgentType;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.AgentInfoDao;
import com.gzzhwl.core.data.extdao.AgentInfoExtDao;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.data.model.VehicleInfo;
import com.gzzhwl.core.data.model.VehiclePlusInfo;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;

@Service
public class AgentInfoServiceImpl implements AgentInfoService {
	@Autowired
	private AgentInfoDao agentInfoDao;
	@Autowired
	private AgentInfoExtDao extDao;
	@Autowired
	private ImageServiceFactory imageFactory;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private DriverInfoService driverInfoService;

	@Override
	public String saveAgentInfoV2(AgentInfo agentInfo, String accountId) {

		AgentValidate.valid_Empty_v2(agentInfo);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("idno", agentInfo.getIdno());
		param.put("isDeleted", Global.ISDEL_NORMAL.toString());
		int num = agentInfoDao.hasIdno(param);
		AgentValidate.valid_idno(agentInfo.getIdno(), num);

		String agentInfoId = IdentifierUtils.getId().generate().toString();
		agentInfo.setAgentInfoId(agentInfoId);
		agentInfo.setAccountId(accountId);
		agentInfo.setSource(DataSource.VLORRY.getCode());
		agentInfo.setCreatedBy(accountId);
		agentInfo.setStatus(Global.STATUS_WAIT.toString());
		agentInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		// String busImageRefId = agentInfo.getBusImageRefId();
		// if (StringUtils.isNotBlank(busImageRefId)) {
		// agentInfo.setAgentType(AgentType.COMPANY.getCode());
		// } else {
		// agentInfo.setAgentType(AgentType.PERSONAL.getCode());
		// }
		agentInfoDao.insert(agentInfo);
		return agentInfoId;
	}

	@Override
	public String saveOrUpdateAgentInfo(AgentInfo agentInfo, String accountId) {
		AgentInfo info = agentInfoDao.get(accountId);
		if (null == info) {// do insert
			AgentValidate.valid_Empty(agentInfo);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("idno", agentInfo.getIdno());
			param.put("isDeleted", Global.ISDEL_NORMAL.toString());
			int num = agentInfoDao.hasIdno(param);
			AgentValidate.valid_idno(agentInfo.getIdno(), num);

			String agentInfoId = IdentifierUtils.getId().generate().toString();
			agentInfo.setAgentInfoId(agentInfoId);
			agentInfo.setAccountId(accountId);
			agentInfo.setSource(DataSource.VLORRY.getCode());
			agentInfo.setCreatedBy(agentInfo.getAccountId());
			agentInfo.setStatus(Global.STATUS_WAIT.toString());
			agentInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
			String busImageRefId = agentInfo.getBusImageRefId();
			if (StringUtils.isNotBlank(busImageRefId)) {
				agentInfo.setAgentType(AgentType.COMPANY.getCode());
			} else {
				agentInfo.setAgentType(AgentType.PERSONAL.getCode());
			}
			agentInfoDao.insert(agentInfo);
			return agentInfoId;
		} else {// do update
			AgentValidate.valid_Empty(agentInfo);

			AgentValidate.valid_status(info);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idno", agentInfo.getIdno());
			params.put("isDeleted", Global.ISDEL_NORMAL.toString());
			params.put("_idno", info.getIdno());
			int num = agentInfoDao.hasIdno(params);
			AgentValidate.valid_idno(agentInfo.getIdno(), num);
			agentInfo.setAccountId(accountId);
			agentInfo.setStatus(Global.STATUS_WAIT.toString());
			String busImageRefId = agentInfo.getBusImageRefId();
			if (StringUtils.isNotBlank(busImageRefId)) {
				agentInfo.setAgentType(AgentType.COMPANY.getCode());
			} else {
				agentInfo.setAgentType(AgentType.PERSONAL.getCode());
			}
			agentInfoDao.updateSelective(agentInfo);
			return info.getAgentInfoId();
		}
	}

	@Override
	public Map<String, Object> getAgentInfoByCondition(String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("accountId", accountId);
		return extDao.getAgentInfo(params);
	}

	@Override
	public String updateImageInfo(MultipartFile image, String accountId) {
		ImageItem item = new ImageItem();
		if (image != null && !image.isEmpty()) {
			item = imageFactory.saveImage(ImageCategory.AGENT, image, accountId);
		}
		return item.getFileId();
	}

	@Override
	public boolean saveOrUpdateAgentInfoV2(AgentInfoV2VO agentInfoVo, String accountId) {
		List<VehicleInfoVo> vehicleList = agentInfoVo.getVehicleList();
		String agentType = null;
		if (StringUtils.isNotBlank(agentInfoVo.getBusImageRefId())) {
			agentType = AgentType.COMPANY.getCode();
		} else {
			agentType = AgentType.PERSONAL.getCode();
		}

		LoginInfoValidator.valid_vehicle(vehicleList, agentType);

		AgentInfo agentInfo = beanMapper.map(agentInfoVo, AgentInfo.class);
		this.saveOrUpdateAgentInfo(agentInfo, accountId);

		if (CollectionUtils.isNotEmpty(vehicleList)) {
			for (VehicleInfoVo vehicleVo : vehicleList) {
				// 获取车辆信息
				VehicleInfo vehicleInfo = beanMapper.map(vehicleVo, VehicleInfo.class);
				// 获取车辆附加信息
				VehiclePlusInfo vehiclePlusInfo = beanMapper.map(vehicleVo, VehiclePlusInfo.class);
				driverInfoService.saveDriverAndVehicle(accountId, vehicleVo.getDriverList(), vehicleInfo,
						vehiclePlusInfo);
			}
		}

		return true;
	}
	
	@Override
	public Map<String, Object> getAgentByIdno(String idno) {
		Map<String,Object> agentMap = null;
		Map<String,Object> params = Maps.newHashMap();
		params.put("idno", idno);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		//params.put("agentType", AgentType.PERSONAL.toString());
		List<Map<String,Object>> list = agentInfoDao.find(params);
		if(CollectionUtils.isNotEmpty(list)) {
			agentMap = list.get(0);
		}
		return agentMap;
	}
	
	@Override
	public String AutoSaveAgentInfo(String idno, String realName, String mobile, String idFImageRefId, String idBImageRefId,
			String agentInfoId, String accountId) {
		AgentInfo agentInfo = new AgentInfo();
		if(StringUtils.isNotBlank(agentInfoId)) {
			agentInfo.setAgentInfoId(agentInfoId);
			agentInfo.setIdFImageRefId(idFImageRefId);
			agentInfo.setIdBImageRefId(idBImageRefId);
			agentInfoDao.updateSelective(agentInfo);
		}else {
			agentInfoId = IdentifierUtils.getId().generate().toString();			
			agentInfo.setAgentInfoId(agentInfoId);
			agentInfo.setAccountId(accountId);
			agentInfo.setIdno(idno);
			agentInfo.setRealName(realName);
			agentInfo.setIdFImageRefId(idFImageRefId);
			agentInfo.setIdBImageRefId(idBImageRefId);
			agentInfo.setAgentType(AgentType.PERSONAL.getCode());
			agentInfo.setSource(DataSource.VLORRY.getCode());
			agentInfo.setCreatedBy(accountId);
			agentInfo.setStatus(Global.STATUS_PASSED.toString());
			agentInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
			agentInfoDao.insert(agentInfo);
		}

		return agentInfoId;
	}

	@Override
	public boolean isPassed(String accountId) {
		
		Map<String, Object> agentMap = this.getAgentInfoByCondition(accountId);
		
		if(!ValidateUtils.isEmpty(agentMap)){
			
			String status = (String)agentMap.get("status");
			
			if(status.equals(Global.STATUS_PASSED.toString())){
				return true;
			}
		}
		
		return false;
	}
	
}
