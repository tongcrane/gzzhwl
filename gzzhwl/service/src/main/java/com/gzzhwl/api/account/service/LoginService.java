package com.gzzhwl.api.account.service;

import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.api.account.vo.RegInfo;
import com.gzzhwl.api.account.vo.RegInfoV2VO;
import com.gzzhwl.api.notice.model.SmsType;
import com.gzzhwl.core.constant.DeviceType;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.LoginResultModel;
import com.gzzhwl.rest.exception.RestException;

public interface LoginService {
	/**
	 * 登录操作
	 * 
	 * @param channel
	 * @param token
	 */
	public LoginResultModel doLoginInfo(String telphone, String password, String deviceType, String token,
			String channel, String logFrom) throws RestException;

	/**
	 * 登出
	 */
	public boolean doLogout(String userId, String deviceType) throws RestException;

	/**
	 * 判断手机号是否已被注册
	 */
	public void isExists(String telphone) throws RestException;

	public boolean isExist(String telphone);

	/**
	 * 用户注册
	 */
	// public LoginResultModel saveLoginInfo(String telphone, String barCode,
	// String password, String regSource,String departureCode,String
	// destinationCode) throws RestException;

	public LoginResultModel saveLoginInfo(RegInfo regInfo) throws RestException;

	/**
	 * 验证手机验证码是否正确
	 * 
	 * @param telphone
	 * @param barCode
	 * @throws RestException
	 */

	public void validSmsCode(String telphone, String barCode, SmsType smsType) throws RestException;

	/**
	 * 忘记密码
	 */
	public void resetPassword(String telphone, String barCode, String password) throws RestException;

	/**
	 * 修改密码
	 */
	public void changePassword(String oldPassword, String newPassword, String accountId, String loginPassword)
			throws RestException;

	public String addUserImages(MultipartFile image, String accountId) throws RestException;

	public LoginResultModel saveLoginInfoV2(RegInfoV2VO regInfo) throws RestException;

	/**
	 * 运势界司机扫码验证，自动注册
	 */
	public LoginResultModel autoRegister(String telphone, String deviceType) throws RestException;

	public LoginResultModel getLoginInfo(AccountInfo accountInfo, DeviceType deviceType);

	/**
	 * 获取账号类型 00-内部司机 01-外部司机
	 */
	public String getAccountType(String telphone);

}
