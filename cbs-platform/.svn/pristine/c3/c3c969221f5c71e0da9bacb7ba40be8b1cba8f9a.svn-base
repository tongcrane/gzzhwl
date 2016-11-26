package com.gzzhwl.cbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gzzhwl.cbs.support.PropertiesSupport;

public class CommonInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private PropertiesSupport properties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sessionId = request.getSession().getId();
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("server", properties.getValue("api.url"));
		return true;
	}

}
