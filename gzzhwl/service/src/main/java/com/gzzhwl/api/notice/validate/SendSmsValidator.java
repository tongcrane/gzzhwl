package com.gzzhwl.api.notice.validate;

import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class SendSmsValidator {
	public static String SEND_SMS_FAILURE = "11009";// 短消息发送失败

	public static void valid_sendSms(String cellPhone) throws RestException {
		if (!ValidateUtils.isMobileNo(cellPhone)) {
			throw new RestException("11004","手机号格式错误"); // 手机号错误
		}
	}

	public static void valid_captcha(boolean valid, int reason) throws RestException {
		if (!valid) {
			switch (reason) {
			case 1:
				throw new RestException("11006","验证码过期"); // 验证码过期
			case 2:
				throw new RestException("11005","验证码错误"); // 验证码错误
			default:
				break;
			}
		}
	}

	public static void valid_captcha_time(int count, Integer countOfValid) throws RestException {
		if (count > countOfValid) {
			throw new RestException("11007","超过次数，请明天再试"); // 一天内已经获取满了N次验证码，请明天再试。
		}
	}

	public static void valid_captcha_interval(int second, Integer interval) throws RestException {
		if (second < interval) {
			throw new RestException("11008","获取频率过快"); // 获取频率过快。
		}
	}
}