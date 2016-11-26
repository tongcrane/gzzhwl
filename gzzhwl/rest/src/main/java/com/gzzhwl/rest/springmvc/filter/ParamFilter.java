package com.gzzhwl.rest.springmvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gzzhwl.rest.springmvc.request.HttpHelper;
import com.gzzhwl.rest.springmvc.request.ParamHttpServletRequestWrapper;

public class ParamFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			String contentType = request.getContentType();
			if (StringUtils.equalsIgnoreCase(contentType, "application/json")) {
				ServletRequest requestWrapper = new ParamHttpServletRequestWrapper(request);
				String body = HttpHelper.getBodyString(requestWrapper);
				filterChain.doFilter(requestWrapper, response);
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}

	}

}
