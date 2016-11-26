package com.gzzhwl.cbs.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

public class ExceptionHandler implements HandlerExceptionResolver, Ordered {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	private int order = Ordered.LOWEST_PRECEDENCE;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("exception : {}", ex.toString());
		ModelAndView mav = new ModelAndView();
		if (ex instanceof AuthException) {
			mav.setViewName("redirect:/login");
		} else if (ex instanceof NoHandlerFoundException) {
			mav.setViewName("info/exception/404");
		} else {
			mav.setViewName("info/exception/500");
		}
		return mav;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return this.order;
	}
}
