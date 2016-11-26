package com.gzzhwl.api.notice.service;

import com.gzzhwl.api.notice.model.SmsType;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.exception.RestServerException;

public interface SmsService {
	/**
	 * 给某手机号发送短信验证码
	 * 
	 * @param phone
	 *            手机号
	 * @param type
	 *            验证码类型
	 * @return
	 * @throws RestServerException
	 */
	public boolean sendCaptchaSmsToPhone(String telphone, SmsType type) throws RestException;

	/**
	 * 验证验证码是否有效
	 * 
	 * @param phone
	 *            手机号
	 * @param captcha
	 *            验证码
	 * @param type
	 *            验证码类型
	 * @return
	 * @throws RestServerException
	 */
	public boolean validateCaptcha(String telphone, String captcha, SmsType type) throws RestException;

	/**
	 * 将该手机号之前该类型的验证码置为失效
	 * 
	 * @param phone
	 * @param type
	 * @return
	 * @throws RestServerException
	 */
	public boolean deleteExpiredCaptcha(String telphone, SmsType type) throws RestException;

	/**
	 * 发送手机短息
	 * 
	 * @param phone
	 * @param content
	 * @return
	 * @throws RestException
	 */
	public boolean sendSms(String phone, String content) throws RestException;

}