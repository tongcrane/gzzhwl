package com.gzzhwl.admin.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.account.service.LineManageService;
import com.gzzhwl.admin.account.service.UserService;
import com.gzzhwl.admin.vehicle.service.VehicleManageService;
import com.gzzhwl.api.message.service.MessageTipsService;
import com.gzzhwl.common.model.VerifyType;
import com.gzzhwl.common.service.AccountVerifyService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.dao.AccountOperationLogDao;
import com.gzzhwl.core.data.dao.AgentInfoDao;
import com.gzzhwl.core.data.extdao.AgentInfoExtDao;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.AccountOperationLog;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.message.TipsCategory;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.model.PageParameter;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private AccountOperationLogDao accountOperationLogDao;
	@Autowired
	private AgentInfoExtDao agentInfoExtDao;
	@Autowired
	private AgentInfoDao agentInfoDao;
	@Autowired
	private AccountVerifyService accountVerityService;
	@Autowired
	private MessageTipsService messageTipsService;
	@Autowired
	private LineManageService lineService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private VehicleManageService vehicleManageService;
	
	@Value("${account.check}")
	private String accountCheck;
	@Value("${account.uncheck}")
	private String accountUncheck;
	
	private static final String QUERYTYPE_NORMAL = "0";// 搜索

	private static final String QUERYTYPE_ADVANCE = "1";// 高级搜索

	@Override
	public Page<Map<String, Object>> getCheckList(String status, String telphone, String realName,
			String companyName,String queryType,String queryContent, PageParameter page) throws RestException {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("targetType", VerifyType.AGENT.getCode());
		if(queryType.equals(QUERYTYPE_NORMAL)) {
			if(StringUtils.isNotEmpty(queryContent)) {
				params.put("queryContent", "%"+queryContent+"%");
			}
		} else if(queryType.equals(QUERYTYPE_ADVANCE)) {
			if(StringUtils.isNotEmpty(queryContent)) {
				params.put("queryContent", "%"+queryContent+"%");
			}
			if(StringUtils.isNotEmpty(telphone)) {
				params.put("telphone", telphone);
			}
			if(StringUtils.isNotEmpty(realName)) {
				params.put("realName", "%"+realName+"%");
			}
			if(StringUtils.isNotEmpty(companyName)) {
				params.put("companyName", "%"+companyName+"%");
			}
			if(StringUtils.isNotEmpty(status)) {
				params.put("status", status);
			}
		}
		Page<Map<String,Object>> pageList=agentInfoExtDao.pageCheckList(params, page.getPageIndex(), page.getPageSize());
		return pageList;
	}
	
	@Override
	public Map<String, Object> getUserDetail(String accountId) throws RestException {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("accountId", accountId);
		params.put("targetType", VerifyType.AGENT.getCode());
		Map<String,Object> result=agentInfoExtDao.getUserDetail(params);
		
		if(ValidateUtils.isEmpty(result)){
			throw new RestException(ErrorCode.ERROR_100001);
		}

		return result;
	}

	@Override
	public void checkPass(String accountId,String createdBy) throws RestException {
		
		if(ValidateUtils.isEmpty(getUserDetail(accountId))){
			throw new RestException(ErrorCode.ERROR_100001);
		}
		
		AgentInfo info=new AgentInfo();
		info.setAccountId(accountId);
		info.setStatus(Global.STATUS_PASSED.toString());
		agentInfoDao.updateStatus(info);
		
		accountVerityService.saveVerifyLog(VerifyType.AGENT, accountId, Global.STATUS_WAIT.toString(), Global.STATUS_PASSED.toString(), createdBy);
		
		messageTipsService.addMessage(TipsCategory.TIPS_C02.getCode(), accountId, null, null, accountCheck, createdBy);
	}

	@Override
	public void checkUnpass(String accountId,String createdBy) throws RestException {
		
		if(ValidateUtils.isEmpty(getUserDetail(accountId))){
			throw new RestException(ErrorCode.ERROR_100001);
		}
		
		AgentInfo info=new AgentInfo();
		info.setAccountId(accountId);
		info.setStatus(Global.STATUS_NOT_PASSED.toString());
		agentInfoDao.updateStatus(info);
		
		accountVerityService.saveVerifyLog(VerifyType.AGENT, accountId, Global.STATUS_WAIT.toString(), Global.STATUS_NOT_PASSED.toString(), createdBy);
		
		messageTipsService.addMessage(TipsCategory.TIPS_C03.getCode(), accountId, null, null, accountUncheck, createdBy);
	}

	@Override
	public Page<Map<String, Object>> getAccountList(String telphone,String realName,String status,String queryType,String queryContent,PageParameter page) throws RestException {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if(queryType.equals(QUERYTYPE_NORMAL)) {
			if(StringUtils.isNotEmpty(queryContent)) {
				params.put("queryContent", "%"+queryContent+"%");
			}
		} else if(queryType.equals(QUERYTYPE_ADVANCE)) {
			if(StringUtils.isNotEmpty(telphone)) {
				params.put("telphone", telphone);
			}
			if(StringUtils.isNotEmpty(realName)) {
				params.put("realName", "%"+realName+"%");
			}
			if(StringUtils.isNotEmpty(status)) {
				params.put("status", status);
			}
		}
		Page<Map<String,Object>> pageList=accountInfoDao.pageAccountList(params, page.getPageIndex(), page.getPageSize());
		return pageList;
	}

	@Override
	public void freeze(String accountId,String createdBy) throws RestException {
		
		if(ValidateUtils.isEmpty(accountInfoDao.get(accountId))){
			throw new RestException(ErrorCode.ERROR_100001);
		}
		
		AccountInfo info=new AccountInfo();
		info.setAccountId(accountId);
		info.setStatus(Global.STATUS_FROZEN.toString());
		accountInfoDao.updateSelective(info);
		
		AccountOperationLog log=new AccountOperationLog();
		log.setLogId(IdentifierUtils.getId().generate().toString());
		log.setAccountId(accountId);
		log.setSrcStatus(Global.STATUS_NORMAL.toString());
		log.setDestStatus(Global.STATUS_FROZEN.toString());
		log.setCreatedBy(createdBy);
		accountOperationLogDao.insert(log);
	}

	@Override
	public void thaw(String accountId,String createdBy) throws RestException {
		
		if(ValidateUtils.isEmpty(accountInfoDao.get(accountId))){
			throw new RestException(ErrorCode.ERROR_100001);
		}
		
		AccountInfo info=new AccountInfo();
		info.setAccountId(accountId);
		info.setStatus(Global.STATUS_NORMAL.toString());
		accountInfoDao.updateSelective(info);
		
		AccountOperationLog log=new AccountOperationLog();
		log.setLogId(IdentifierUtils.getId().generate().toString());
		log.setAccountId(accountId);
		log.setSrcStatus(Global.STATUS_FROZEN.toString());
		log.setDestStatus(Global.STATUS_NORMAL.toString());
		log.setCreatedBy(createdBy);
		accountOperationLogDao.insert(log);
	}

	@Override
	public List<Map<String, Object>> getLineList(String accountId) {
		//验证账户是否存在
//		getUserDetail(accountId);
		
		//获取线路列表
		List<Map<String, Object>> lines = lineService.getLineList(accountId);
		
		if(CollectionUtils.isNotEmpty(lines)) {
			for(Map<String, Object> line: lines) {
				String departureCode = (String) line.get("departureCode");
				String departureCodeCn = getCodeCn(departureCode);
				line.put("departureCodeCn", departureCodeCn);

				String destinationCode = (String) line.get("destinationCode");
				String destinationCodeCn = getCodeCn(destinationCode);
				line.put("destinationCodeCn", destinationCodeCn);
			}
		}
		
		return lines;
	}
	
	public String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = regionService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public Map<String, Object> getUserDetail2(String accountId) throws RestException {
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("accountId", accountId);
		params.put("targetType",VerifyType.AGENT.getCode());
		Map<String,Object> userInfo=agentInfoExtDao.getUserDetail(params);
		
		if(ValidateUtils.isEmpty(userInfo)){
			throw new RestException(ErrorCode.ERROR_100001);
		}
		List<Map<String,Object>> vehicleInfo=vehicleManageService.getRealDriverandVehicleList(accountId);
		result.put("userInfo", userInfo);
		result.put("vehicleInfo", vehicleInfo);
		return result;
	}

	@Override
	public Map<String, Object> getAccountDetail2(String accountId) throws RestException {
		Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("accountId", accountId);
		params.put("targetType",VerifyType.AGENT.getCode());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		Map<String,Object> accountInfo=accountInfoDao.getAccountInfo(params);
		List<Map<String,Object>> lineInfo=this.getLineList(accountId);
		List<Map<String,Object>> vehicleInfo=vehicleManageService.getRealDriverandVehicleList(accountId);
		result.put("accountInfo", accountInfo);
		result.put("lineInfo", lineInfo);
		result.put("vehicleInfo", vehicleInfo);
		return result;
	}

}
