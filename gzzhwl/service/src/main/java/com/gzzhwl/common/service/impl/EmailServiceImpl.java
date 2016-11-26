package com.gzzhwl.common.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.gzzhwl.common.model.EmailSender;
import com.gzzhwl.common.service.EmailService;
import com.gzzhwl.rest.exception.RestServerException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailServiceImpl implements EmailService {
	private static final String DEFAULT_ENCODING = "utf-8";
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Configuration freemarkerConfiguration;

	@Override
	public boolean sendEmail(EmailSender sender, String receiver, String subject, String templateId,
			Map<String, Object> formData) throws RestServerException {
		try {
			Template template = freemarkerConfiguration.getTemplate(templateId + ".ftl");
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, formData);
			MimeMessage email = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(email, true, DEFAULT_ENCODING);
			helper.setTo(receiver);
			helper.setFrom(sender.getUsername(), sender.getDisplay());
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(email);
			return true;
		} catch (IOException e) {
			throw new RestServerException("邮件发送失败!");
		} catch (TemplateException e) {
			throw new RestServerException("邮件发送失败!");
		} catch (MessagingException e) {
			throw new RestServerException("邮件发送失败!");
		} catch (Exception e) {
			throw new RestServerException("邮件发送失败!");
		}
	}
}
