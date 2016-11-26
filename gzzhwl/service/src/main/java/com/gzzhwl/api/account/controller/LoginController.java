package com.gzzhwl.api.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.gzzhwl.api.account.service.LoginService;
import com.gzzhwl.api.account.vo.RegInfo;
import com.gzzhwl.api.account.vo.RegInfoV2VO;
import com.gzzhwl.api.notice.model.SmsType;
import com.gzzhwl.api.notice.service.SmsService;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.LoginResultModel;
import com.gzzhwl.rest.springmvc.annotation.Authorization;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

/**
 * 
 * @author mj
 * @date 2015年1月20日
 */
@RestController
@RequestMapping("/api/account")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private SmsService smsService;

	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ResponseModel login(@RequestParam(value = "telphone") String telphone,
			@RequestParam(value = "password") String password, @RequestParam(value = "deviceType") String deviceType,
			@RequestParam(required = false) String token, @RequestParam(required = false) String channel,
			@RequestParam(required = false, defaultValue = "02") String source) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(telphone, password, deviceType, source);
		LoginResultModel result = loginService.doLoginInfo(telphone, password, deviceType, token, channel, source);
		return new ResponseModel(result);
	}

	/**
	 * 登出
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public ResponseModel logout(@RequestParam String deviceType,
			@Authorization(required = true) AccountInfo accountInfo) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(deviceType);
		loginService.doLogout(accountInfo.getAccountId(), deviceType);
		return new ResponseModel(null);
	}

	/**
	 * 注册
	 */
	// @RequestMapping(value = "/register", method = { RequestMethod.POST })
	// public ResponseModel register(@RequestParam String telphone,
	// @RequestParam String password,
	// @RequestParam String barCode, @RequestParam String deviceType,
	// @RequestParam String departureCode,
	// @RequestParam String destinationCode) {
	// ParamEmptyValidator.VALID_PARAM_EMPTY(telphone, barCode, password,
	// deviceType);
	// LoginResultModel result = loginService.saveLoginInfo(telphone, barCode,
	// password, deviceType, departureCode,
	// destinationCode);
	// return new ResponseModel(result);
	// }

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel register(@RequestBody RegInfo regInfo) {
		LoginResultModel result = loginService.saveLoginInfo(regInfo);
		return new ResponseModel(result);
	}

	@RequestMapping(value = "/registerv2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel registerV2(@RequestBody RegInfoV2VO regInfo) {
		LoginResultModel result = loginService.saveLoginInfoV2(regInfo);
		return new ResponseModel(result);
	}

	/**
	 * 发送手机验证码
	 */
	@RequestMapping(value = "/generate", method = { RequestMethod.POST })
	public ResponseModel generate(@RequestParam String telphone, @RequestParam String codeType) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(telphone, codeType);
		smsService.sendCaptchaSmsToPhone(telphone, SmsType.getSmsType(codeType));
		return new ResponseModel(null);
	}

	/**
	 * 验证账户是否存在
	 * 
	 * @param telphone
	 * @return
	 */
	@RequestMapping(value = "/isExist", method = { RequestMethod.GET })
	public ResponseModel isExist(@RequestParam String telphone) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(telphone);
		loginService.isExists(telphone);
		return new ResponseModel(null);
	}

	/**
	 * 验证手机验证码是否正确
	 * 
	 * @param telphone
	 * @param barCode
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/validSmsCode", method = { RequestMethod.POST })
	public ResponseModel validSmsCode(@RequestParam String telphone, @RequestParam String barCode,
			@RequestParam String codeType) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(telphone, barCode, codeType);
		loginService.validSmsCode(telphone, barCode, SmsType.getSmsType(codeType));
		return new ResponseModel(null);
	}

	/**
	 * 重置密码
	 * 
	 * @param cellPhone
	 * @param barCode
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = { RequestMethod.POST })
	public ResponseModel resetPassword(@RequestParam String telphone, @RequestParam String barCode,
			@RequestParam String password) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(telphone, barCode, password);
		loginService.resetPassword(telphone, barCode, password);
		return new ResponseModel(null);
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param loginInfo
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = { RequestMethod.POST })
	public ResponseModel changePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			@Authorization(required = true) AccountInfo accountInfo) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(oldPassword, newPassword);
		loginService.changePassword(oldPassword, newPassword, accountInfo.getAccountId(), accountInfo.getPassword());
		return new ResponseModel(null);
	}

	/**
	 * 添加用户头像
	 */
	@RequestMapping(value = "/userhead", method = { RequestMethod.POST })
	public ResponseModel addUserhead(@RequestParam(required = true) MultipartFile image,
			@Authorization(required = true) AccountInfo accountInfo) {
		String imageId = loginService.addUserImages(image, accountInfo.getAccountId());
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("imageId", imageId);
		return new ResponseModel(resMap);
	}
	
	
	/**
	 * 获取账户类型
	 */
	@RequestMapping(value = "/getAccountType", method = {RequestMethod.GET})
	public ResponseModel getAccountType(@RequestParam String telphone) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(telphone);
		String type = loginService.getAccountType(telphone);
		Map<String, Object> resMap = Maps.newHashMap();
		resMap.put("accoutType", type);
		return new ResponseModel(resMap);
	}
}
