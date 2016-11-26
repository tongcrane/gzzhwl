package com.gzzhwl.api.image.utils;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzzhwl.api.image.model.CaptchaModel;
import com.gzzhwl.api.image.service.CaptchaStore;
import com.gzzhwl.core.utils.IdentifierUtils;

@Component
public class CaptchaStoreUtils {
	@Autowired
	private CaptchaStore captchaService;

	private static CaptchaStoreUtils instance = null;

	public CaptchaStoreUtils() {
		instance = this;
		instance.captchaService = this.captchaService;
	}

	public static String store(String token) {
		String id = IdentifierUtils.getId().generate().toString();
		CaptchaModel captcha = new CaptchaModel();
		captcha.setId(id);
		captcha.setTimestamp(Calendar.getInstance().getTimeInMillis());
		captcha.setToken(token);
		instance.captchaService.storeCaptcha(captcha);
		return id;
	}

	public static boolean check(String id, String token) {
		CaptchaModel captcha = instance.captchaService.getCaptcha(id);
		instance.captchaService.removeCaptcha(id);
		if (captcha != null) {
			String current = captcha.getToken();
			if (StringUtils.equalsIgnoreCase(current, token)) {
				return true;
			}
		}
		return false;
	}
}
