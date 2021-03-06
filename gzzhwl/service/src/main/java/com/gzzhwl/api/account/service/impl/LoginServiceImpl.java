package com.gzzhwl.api.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.api.account.service.LoginService;
import com.gzzhwl.api.account.service.UserLoginHisService;
import com.gzzhwl.api.account.validate.LoginInfoValidator;
import com.gzzhwl.api.account.vo.AccountInfoVO;
import com.gzzhwl.api.account.vo.Line;
import com.gzzhwl.api.account.vo.RegInfo;
import com.gzzhwl.api.account.vo.RegInfoV2VO;
import com.gzzhwl.api.agent.service.AgentInfoService;
import com.gzzhwl.api.driver.service.DriverInfoService;
import com.gzzhwl.api.image.model.ImageCategory;
import com.gzzhwl.api.image.model.ImageItem;
import com.gzzhwl.api.image.service.ImageServiceFactory;
import com.gzzhwl.api.line.service.LineService;
import com.gzzhwl.api.message.service.MessageTipsService;
import com.gzzhwl.api.notice.model.SmsType;
import com.gzzhwl.api.notice.service.SmsService;
import com.gzzhwl.api.notice.validate.SendSmsValidator;
import com.gzzhwl.api.vehicle.vo.VehicleInfoVo;
import com.gzzhwl.common.model.VerifyType;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.DeviceType;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.RegSource;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.data.model.InternalDriver;
import com.gzzhwl.core.data.model.LoginResultModel;
import com.gzzhwl.core.data.model.PushDevice;
import com.gzzhwl.core.data.model.VehicleInfo;
import com.gzzhwl.core.data.model.VehiclePlusInfo;
import com.gzzhwl.core.message.TipsCategory;
import com.gzzhwl.core.support.PushDeviceSupport;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.MD5;
import com.gzzhwl.core.utils.RandomCaptchaUtils;
import com.gzzhwl.push.service.PushNotificationService;
import com.gzzhwl.rest.exception.RestException;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private UserLoginHisService userLoginHisService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private LineService lineService;
	@Autowired
	private AgentInfoService agentInfoService;
	@Autowired
	private DriverInfoService driverInfoService;
	@Autowired
	private Mapper beanMapper;
	@Autowired
	private ImageServiceFactory imageServiceFactory;
	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private MessageTipsService messageTipsService;

	@Value("${account.register}")
	private String accountRegister;
	
	private static final String COMMON_PASSWORD = "4008880906"; 

	@Override
	public LoginResultModel doLoginInfo(String telphone, String password, String deviceType, String token,
			String channel, String logFrom) throws RestException {
		AccountInfo accountInfo = this.getLoginInfoByParam(telphone, true);
		String _p = MD5.md5(password);
		DataSource logSource = DataSource.getDataSource(logFrom);
		LoginInfoValidator.valid_loginInfo(accountInfo, _p, logSource);
		DeviceType dType = DeviceType.getDeviceType(deviceType);
		this.regPushInfo(dType, channel, token, accountInfo.getAccountId());
		return this.getLoginInfo(accountInfo, dType);
	}

	private void regPushInfo(DeviceType deviceType, String channel, String token, String accountId) {
		if (deviceType != null) {
			if (deviceType != DeviceType.WEB) {
				pushNotificationService.delPushInfo(accountId);
			}
			PushDevice pushDevice = PushDeviceSupport.getDevice(deviceType, channel);
			if (pushDevice != null) {
				if (StringUtils.isNotBlank(token)) {
					pushNotificationService.addPushInfo(channel, deviceType.getCode(), token, accountId);
				}
			}
		}
	}

	private AccountInfo getLoginInfoByParam(String telphone, boolean isDel) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (isDel) {
			params.put("isDel", Global.ISDEL_NORMAL.toString());
		}
		params.put("telphone", telphone);
		List<AccountInfo> loginList = accountInfoDao.findAccountInfoToLogin(params);
		if (CollectionUtils.isEmpty(loginList)) {
			return null;
		} else {
			return loginList.get(0);
		}
	}

	@Override
	public void isExists(String telphone) {
		boolean exists = this.isExist(telphone);
		LoginInfoValidator.valid_exists(exists);// 判断账号是否存在
	}

	// @Override
	// public LoginResultModel saveLoginInfo(String telphone, String barCode,
	// String password, String regSource,
	// String departureCode, String destinationCode) throws RestException {
	// RegSource regs = RegSource.getRegSource(regSource);// 注册来源
	//
	// SendSmsValidator.valid_sendSms(telphone); //验证手机号格式
	// LoginInfoValidator.valid_password_length(password); //验证密码长度
	// boolean cellPhoneExists = this.isExist(telphone);// 手机号是否存在
	// LoginInfoValidator.valid_cellphone(cellPhoneExists);
	//
	// smsService.validateCaptcha(telphone, barCode, SmsType.REG);// 验证手机验证码正确性
	//
	// String accountId = IdentifierUtils.getId().generate().toString();
	// AccountInfo accountInfo = new AccountInfo();
	// accountInfo.setAccountId(accountId);
	// accountInfo.setTelphone(telphone);
	// accountInfo.setNickName(telphone);
	// String _p = MD5.md5(password);
	// accountInfo.setPassword(_p);
	// accountInfo.setAccountSource(regs.getCode());
	// accountInfo.setStatus(Global.STATUS_NORMAL.toString());
	// accountInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
	// accountInfoDao.insert(accountInfo);
	// // 常跑线路
	// lineService.saveLine(accountId, departureCode, destinationCode);
	//
	// smsService.deleteExpiredCaptcha(telphone, SmsType.REG);
	// return this.getLoginInfo(accountInfo, regs.getDeviceType());
	// }

	@Override
	public LoginResultModel saveLoginInfo(RegInfo regInfo) throws RestException {
		RegSource regs = RegSource.getRegSource(regInfo.getDeviceType());// 注册来源

		SendSmsValidator.valid_sendSms(regInfo.getTelphone()); // 验证手机号格式
//		LoginInfoValidator.valid_password_length(regInfo.getPassword()); // 验证密码长度
		boolean cellPhoneExists = this.isExist(regInfo.getTelphone());// 手机号是否存在
		LoginInfoValidator.valid_cellphone(cellPhoneExists);

		smsService.validateCaptcha(regInfo.getTelphone(), regInfo.getBarCode(), SmsType.REG);// 验证手机验证码正确性

		String accountId = IdentifierUtils.getId().generate().toString();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountId(accountId);
		accountInfo.setTelphone(regInfo.getTelphone());
		accountInfo.setNickName(regInfo.getTelphone());
		String _p = MD5.md5(regInfo.getPassword());
		accountInfo.setPassword(_p);
		accountInfo.setAccountSource(regs.getCode());
		accountInfo.setStatus(Global.STATUS_NORMAL.toString());
		accountInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		accountInfoDao.insert(accountInfo);
		// 常跑线路
		List<Line> lineList = regInfo.getLineList();
		if (CollectionUtils.isNotEmpty(lineList)) {
			for (Line line : lineList) {
				lineService.saveLine(accountId, line.getDepartureCode(), line.getDestinationCode());
			}
		}

		smsService.deleteExpiredCaptcha(regInfo.getTelphone(), SmsType.REG);
		this.regPushInfo(regs.getDeviceType(), regInfo.getChannel(), regInfo.getToken(), accountId);
		messageTipsService.addMessage(TipsCategory.TIPS_C01.getCode(), accountId, null, null, accountRegister,
				accountId);
		return this.getLoginInfo(accountInfo, regs.getDeviceType());
	}

	@Override
	public LoginResultModel getLoginInfo(AccountInfo accountInfo, DeviceType deviceType) {
		String accountId = accountInfo.getAccountId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("accountId", accountId);
		params.put("targetType",VerifyType.AGENT.getCode());
		Map<String, Object> result = accountInfoDao.getAccountInfo(params);
		LoginResultModel loginResult = beanMapper.map(result, LoginResultModel.class);
		if (deviceType != null) {// 如果设备类型为null，则不执行登录操作
			LoginInfoValidator.valid_login(loginResult, deviceType);// 验证账号是否可以登录
			String token = userLoginHisService.saveUserLoginHis(accountId, deviceType.getLoginType());// 获取登录token
			loginResult.setToken(token);
		}
		return loginResult;
	}

	@Override
	public boolean isExist(String telphone) {
		AccountInfo loginInfo = this.getLoginInfoByParam(telphone, false);
		return loginInfo != null;
	}

	@Override
	public boolean doLogout(String userId, String deviceType) throws RestException {
		DeviceType dType = DeviceType.getDeviceType(deviceType);
		if (dType != DeviceType.WEB) {
			pushNotificationService.delPushInfo(userId);
		}
		return userLoginHisService.updateInvalidToken(userId, dType.getLoginType());
	}

	@Override
	public void validSmsCode(String telphone, String barCode, SmsType smsType) throws RestException {
		SendSmsValidator.valid_sendSms(telphone);// 验证手机号
		smsService.validateCaptcha(telphone, barCode, smsType);
	}

	@Override
	public void resetPassword(String telphone, String barCode, String password) throws RestException {
		SendSmsValidator.valid_sendSms(telphone);// 验证手机号
//		LoginInfoValidator.valid_password_length(password);
		smsService.validateCaptcha(telphone, barCode, SmsType.FORGET);// 验证手机号与验证码
		AccountInfo loginInfo = this.getLoginInfoByParam(telphone, true);
		LoginInfoValidator.valid_not_exists(loginInfo);
		AccountInfo updatePassword = new AccountInfo();
		updatePassword.setAccountId(loginInfo.getAccountId());
		updatePassword.setPassword(MD5.md5(password));
		accountInfoDao.updateSelective(updatePassword);
		smsService.deleteExpiredCaptcha(telphone, SmsType.FORGET);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword, String accountId, String loginPassword)
			throws RestException {
//		LoginInfoValidator.valid_password_length(newPassword);
		LoginInfoValidator.valid_changePassword(MD5.md5(oldPassword), MD5.md5(newPassword), loginPassword);
		AccountInfo updatePassword = new AccountInfo();
		updatePassword.setAccountId(accountId);
		updatePassword.setPassword(MD5.md5(newPassword));
		accountInfoDao.updateSelective(updatePassword);
	}

	@Override
	public String addUserImages(MultipartFile image, String accountId) throws RestException {
		if (image == null) {
			throw new RestException(ErrorCode.ERROR_900001);
		}
		ImageItem imageItem = imageServiceFactory.saveImage(ImageCategory.ACCOUNT, image, accountId);
		String imageId = imageItem.getFileId();
		AccountInfo updateUserHead = new AccountInfo();
		updateUserHead.setAccountId(accountId);
		updateUserHead.setUserHead(imageId);
		accountInfoDao.updateSelective(updateUserHead);
		return imageId;
	}

	@Override
	public LoginResultModel saveLoginInfoV2(RegInfoV2VO regInfo) throws RestException {
		RegSource regs = RegSource.getRegSource(regInfo.getDeviceType());// 注册来源

		SendSmsValidator.valid_sendSms(regInfo.getTelphone()); // 验证手机号格式
//		LoginInfoValidator.valid_password_length(regInfo.getPassword()); // 验证密码长度
		boolean cellPhoneExists = this.isExist(regInfo.getTelphone());// 手机号是否存在
		LoginInfoValidator.valid_cellphone(cellPhoneExists);
		List<Line> lineList = regInfo.getLineList();
		LoginInfoValidator.valid_line(lineList);

		AccountInfoVO agentInfoVO = regInfo.getAgentInfo();
		String agentType = agentInfoVO.getAgentType();
		List<VehicleInfoVo> vehicleList = regInfo.getVehicleList();
		LoginInfoValidator.valid_vehicle(vehicleList, agentType);

		smsService.validateCaptcha(regInfo.getTelphone(), regInfo.getBarCode(), SmsType.REG);// 验证手机验证码正确性

		String accountId = IdentifierUtils.getId().generate().toString();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountId(accountId);
		accountInfo.setTelphone(regInfo.getTelphone());
		accountInfo.setNickName(regInfo.getTelphone());
		String _p = MD5.md5(regInfo.getPassword());
		accountInfo.setPassword(_p);
		accountInfo.setAccountSource(regs.getCode());
		accountInfo.setStatus(Global.STATUS_NORMAL.toString());
		accountInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		accountInfoDao.insert(accountInfo);
		// 常跑线路

		if (CollectionUtils.isNotEmpty(lineList)) {
			for (Line line : lineList) {
				lineService.saveLine(accountId, line.getDepartureCode(), line.getDestinationCode());
			}
		}

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

		AgentInfo agentInfo = beanMapper.map(agentInfoVO, AgentInfo.class);
		agentInfoService.saveAgentInfoV2(agentInfo, accountId);

		smsService.deleteExpiredCaptcha(regInfo.getTelphone(), SmsType.REG);

		this.regPushInfo(regs.getDeviceType(), regInfo.getChannel(), regInfo.getToken(), accountId);
		messageTipsService.addMessage(TipsCategory.TIPS_C01.getCode(), accountId, null, null, accountRegister,
				accountId);
		return this.getLoginInfo(accountInfo, regs.getDeviceType());
	}

	@Override
	public LoginResultModel autoRegister(String telphone, String deviceType) throws RestException {
		if (telphone == null) {
			telphone = RandomCaptchaUtils.getRandomString(11);
		}
		RegSource regs = RegSource.getRegSource(deviceType);// 注册来源
		String password = MD5.md5(COMMON_PASSWORD);

		String accountId = IdentifierUtils.getId().generate().toString();
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountId(accountId);
		accountInfo.setTelphone(telphone);
		accountInfo.setNickName(telphone);
		String _p = MD5.md5(password);
		accountInfo.setPassword(_p);
		accountInfo.setAccountSource(regs.getCode());
		accountInfo.setStatus(Global.STATUS_NORMAL.toString());
		accountInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		accountInfoDao.insert(accountInfo);

		return this.getLoginInfo(accountInfo, regs.getDeviceType());
	}

	@Override
	public String getAccountType(String telphone) {
		AccountInfo accountInfo = this.getLoginInfoByParam(telphone, true);
		LoginInfoValidator.valid_not_exists(accountInfo);
		return InternalDriver.YES.equals(accountInfo.getIsStaff()) ? InternalDriver.YES : InternalDriver.NO;
	}

	public static void main(String[] args) {
		String _p = MD5.md5(MD5.md5(COMMON_PASSWORD));
		System.out.println(_p);
	}
}
