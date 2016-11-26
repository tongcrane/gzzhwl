package com.gzzhwl.rest.springmvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.setHeader("Access-Control-Allow-Origin", "*");
		if ("OPTIONS".equals(request.getMethod())) {
			this.optionsRequestResponse(response);
		} else {
			filterChain.doFilter(request, response);
		}
	}

	private void optionsRequestResponse(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("text/plain; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.getWriter().flush();
		response.getWriter().close();
	}
}
