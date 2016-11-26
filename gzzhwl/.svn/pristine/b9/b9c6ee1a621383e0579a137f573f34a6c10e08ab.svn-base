package com.gzzhwl.api.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gzzhwl.api.account.service.LoginService;
import com.gzzhwl.api.notice.model.SmsConfig;
import com.gzzhwl.api.notice.model.SmsResponse;
import com.gzzhwl.api.notice.model.SmsType;
import com.gzzhwl.api.notice.service.SmsService;
import com.gzzhwl.api.notice.validate.SendSmsValidator;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.SmsCaptchaDao;
import com.gzzhwl.core.data.model.SmsCaptcha;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.RandomCaptchaUtils;
import com.gzzhwl.rest.exception.HttpException;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.exception.RestServerException;
import com.gzzhwl.rest.http.HttpService;
import com.gzzhwl.rest.http.model.HttpConfig;

@Service
public class SmsServiceImpl implements SmsService {
	private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	@Autowired
	private SmsCaptchaDao smsCaptchaDao;
	@Autowired
	private LoginService loginService;

	@Value("${captcha.valid.minute}")
	private Integer minuteOfValid;
	@Value("${captcha.valid.second}")
	private Integer secondOfValid;
	@Value("${captcha.valid.count}")
	private Integer countOfValid;

	@Autowired
	private SmsConfig smsConfig;
	@Autowired
	private HttpService httpService;

	@Value("${sms.send}")
	private boolean send;
	@Value("${sms.check}")
	private boolean check;

	@Override
	public boolean sendCaptchaSmsToPhone(final String telphone, final SmsType type) throws RestException {
		SendSmsValidator.valid_sendSms(telphone);
		if (type == SmsType.REG) {
			loginService.isExists(telphone);
		}
		this.validateTime(telphone, type.getCode());// 验证码次数
		this.validateInterval(telphone, type.getCode());// 验证频率
		this.deleteExpiredCaptcha(telphone, type);// 使之前的验证码失效
		this.sendCaptchaSms(telphone, null, type.getCode());
		return true;
	}

	@Override
	public boolean validateCaptcha(String phone, String captcha, SmsType type) throws RestServerException {
		boolean valid = true;// 默认成功
		if (check) {
			SendSmsValidator.valid_sendSms(phone);
			Map<String, Object> data = this.getCaptcha(phone, captcha, type);
			int reason = 0;// 失败原因：无

			if (null != data) {
				String minuteAgo = String.valueOf(data.get("timeAgo"));
				Integer i_minute = Integer.valueOf(minuteAgo);
				if (i_minute > minuteOfValid) {
					if (logger.isDebugEnabled()) {
						logger.debug("手机号：" + phone + "，验证码：" + captcha + "。结果：验证码过期。");
					}
					reason = 1;// 失败原因：过期
					valid = false;// 失败
				} else {
					reason = 0;// 失败原因：无
					valid = true;
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("手机号：" + phone + "，验证码：" + captcha + "。结果：验证码错误。");
				}
				reason = 2;// 失败原因：验证码错误
				valid = false;// 失败
			}
			SendSmsValidator.valid_captcha(valid, reason);
		}
		return valid;
	}

	private Map<String, Object> getCaptcha(final String telphone, final String captcha, SmsType type)
			throws RestServerException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("captcha", captcha);
		params.put("telphone", telphone);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("type", type.getCode());
		List<Map<String, Object>> smsHistor = smsCaptchaDao.find(params);
		if (CollectionUtils.isNotEmpty(smsHistor)) {
			return smsHistor.get(0);
		}
		return null;
	}

	/**
	 * 验证手机号获取验证码的次数（在规定时间内）
	 * 
	 * @param phone
	 * @return
	 * @throws RestServerException
	 */
	private boolean validateTime(String telphone, String type) throws RestServerException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("telphone", telphone);
		params.put("codeType", type);
		List<Map<String, String>> list = smsCaptchaDao.findCountSameDay(params);
		int count = list.size();
		SendSmsValidator.valid_captcha_time(count, countOfValid);
		return true;
	}

	/**
	 * 验证手机号获取验证码的频率（在规定时间内）
	 * 
	 * @param phone
	 * @return
	 * @throws RestServerException
	 */
	private boolean validateInterval(String telphone, String type) throws RestServerException {
		Map<String, Object> map = smsCaptchaDao.findOneByTel(telphone, type);
		if (map != null) {
			String timeAgo = String.valueOf(map.get("timeAgo"));
			Integer i_second = Integer.valueOf(timeAgo);
			SendSmsValidator.valid_captcha_interval(i_second, secondOfValid);
		}
		return true;
	}

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @param userId
	 * @param type
	 * @return
	 * @throws RestServerException
	 */
	private String sendCaptchaSms(final String telphone, final String accountId, final String type)
			throws RestServerException {
		String code = RandomCaptchaUtils.getRandomNum(6);// 随机6位数字
		SmsCaptcha smsCaptcha = new SmsCaptcha();
		smsCaptcha.setCaptchaId(IdentifierUtils.getId().generate().toString());
		smsCaptcha.setCaptcha(code);
		smsCaptcha.setType(type);
		smsCaptcha.setIsDeleted(Global.ISDEL_NORMAL.toString());
		smsCaptcha.setStatus(Global.STATUS_NORMAL.toString());
		smsCaptcha.setTelphone(telphone);
		smsCaptcha.setAccountId(accountId);
		smsCaptchaDao.insert(smsCaptcha);
		if (logger.isDebugEnabled()) {
			logger.debug("手机号：" + telphone + "，获取的验证码：" + code + "。");
		}
		if (send) {
			String content = "尊敬的用户，您的验证码是：" + code + "，有效时间是" + minuteOfValid + "分钟。";
			this.sendSms(telphone, content);
		}
		return code;
	}

	/**
	 * 将该手机号之前该类型的验证码置为失效
	 * 
	 * @param phone
	 * @param type
	 * @return
	 * @throws RestServerException
	 */
	@Override
	public boolean deleteExpiredCaptcha(final String telphone, final SmsType type) throws RestServerException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("type", type.getCode());
		params.put("telphone", telphone);
		params.put("status", SmsCaptcha.STATUS_NORMAL);
		params.put("overdue", SmsCaptcha.STATUS_OVERDUE);
		smsCaptchaDao.deleteExpiredCaptcha(params);
		return true;
	}

	@Override
	public boolean sendSms(String phone, String content) throws RestException {
		try {
			Map<String, String> param = smsConfig.getBaseParam();
			param.put("phones", phone);// 手机号码（必填,多条以英文逗号隔开）
			param.put("content", content);// 短信内容（必填）
			String body = JSON.toJSONString(param);
			String json = httpService.postAsBody(smsConfig.getUrl(), body, HttpConfig.createDefault());
			SmsResponse response = JSON.parseObject(json, new TypeReference<SmsResponse>() {
			});
			if (StringUtils.equals(response.getResult(), SmsResponse.SUCCESS)) {
				return true;
			} else {
				throw new RestException(ErrorCode.ERROR_10005);
			}
		} catch (HttpException e) {
			throw new RestException(ErrorCode.ERROR_10005.getCode(), e.getMessage());
		}

	}

}