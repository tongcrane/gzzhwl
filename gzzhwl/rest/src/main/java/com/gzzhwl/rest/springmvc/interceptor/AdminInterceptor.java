package com.gzzhwl.rest.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.rest.security.AdminService;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;

/**
 * session拦截器
 *
 * @author maenwei
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AdminService adminService;

	private static final String NAME = "Authorization";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authorization = request.getHeader(NAME);
		if (StringUtils.isEmpty(authorization)) {
			authorization = request.getParameter(NAME);
		}
		if (authorization != null) {
			StaffInfo staffInfo = adminService.getCurrentAdmin(authorization);
			Subject subject = new Subject(staffInfo);
			SecurityUtils.setSubject(subject);
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		SecurityUtils.logout();
	}

}
