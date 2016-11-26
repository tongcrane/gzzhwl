package com.gzzhwl.cbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gzzhwl.cbs.constants.SessionConstant;
import com.gzzhwl.cbs.exception.AuthException;
import com.gzzhwl.cbs.model.Subject;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Subject subject = (Subject) session.getAttribute(SessionConstant.CURRENT_USER);
		if (subject != null) {
			String context = request.getContextPath();
			String url = request.getRequestURI();
			if (StringUtils.isNotBlank(context)) {
				url = StringUtils.replaceOnce(url, context, "");
			}
			return true;
		} else {
			throw new AuthException();
		}
	}
}
