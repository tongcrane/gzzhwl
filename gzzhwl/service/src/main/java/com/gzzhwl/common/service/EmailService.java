package com.gzzhwl.common.service;

import java.util.Map;

import com.gzzhwl.common.model.EmailSender;
import com.gzzhwl.rest.exception.RestServerException;

public interface EmailService {
	/**
	 * 发送邮件
	 * 
	 * @param receiver
	 * @param subject
	 * @param templateId
	 * @param formData
	 * @return
	 * @throws RestServerException
	 */
	public boolean sendEmail(EmailSender sender, String receiver, String subject, String templateId,
			Map<String, Object> formData) throws RestServerException;
}
