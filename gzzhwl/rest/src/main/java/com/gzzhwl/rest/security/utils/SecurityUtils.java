package com.gzzhwl.rest.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gzzhwl.rest.security.model.Subject;

public class SecurityUtils {
	private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

	private static final ThreadLocal<Subject> session = new ThreadLocal<Subject>();

	public static void setSubject(Subject subject) {
		logger.debug("put admin:{} data", subject.getNumber());
		session.set(subject);
	}

	public static Subject getSubject() {
		return session.get();
	}

	public static void logout() {
		logger.debug("clear admin data");
		session.remove();
	}
}
