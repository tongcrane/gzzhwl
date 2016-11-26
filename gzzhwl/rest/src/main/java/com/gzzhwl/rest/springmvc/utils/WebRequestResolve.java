package com.gzzhwl.rest.springmvc.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class WebRequestResolve {

	public static boolean isMultipartRequest(NativeWebRequest webRequest) {
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		String contentType = servletRequest.getContentType();
		if (contentType == null || !contentType.toLowerCase().startsWith("multipart/")) {
			return false;
		}
		return true;
	}

	public static MultipartHttpServletRequest resolveRequest(NativeWebRequest webRequest) {
		if (isMultipartRequest(webRequest)) {
			HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
			return WebUtils.getNativeRequest(servletRequest, MultipartHttpServletRequest.class);
		} else {
			return null;
		}
	}

	public static boolean isIE(WebRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (StringUtils.isNotBlank(userAgent)) {
			UserAgent ua = UserAgent.parseUserAgentString(userAgent);
			Browser browser = ua.getBrowser();
			return browser.getGroup() == Browser.IE;
		}
		return false;
	}
}
