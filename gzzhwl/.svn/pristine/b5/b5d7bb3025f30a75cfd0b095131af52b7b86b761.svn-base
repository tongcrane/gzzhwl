package com.gzzhwl.api.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.api.image.service.CaptchaStore;

@Service("captchaTask")
public class CaptchaTask {
	private static Logger logger = LoggerFactory.getLogger(CaptchaTask.class);

	@Autowired
	private CaptchaStore captchaService;

	public void clear() {
		if (logger.isDebugEnabled()) {
			logger.debug("start clear captcha task");
		}
		captchaService.clear();
	}
}
