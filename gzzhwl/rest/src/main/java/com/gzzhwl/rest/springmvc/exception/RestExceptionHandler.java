package com.gzzhwl.rest.springmvc.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.model.ResponseStatusModel;

public class RestExceptionHandler implements HandlerExceptionResolver, Ordered {
	@Autowired
	private ExceptionResolve exceptionResolve;
	private String suffix;
	private ObjectMapper objectMapper;
	private int order = Ordered.LOWEST_PRECEDENCE;

	public RestExceptionHandler(String suffix, ObjectMapper objectMapper, int order) {
		this.suffix = suffix;
		this.objectMapper = objectMapper;
		this.order = order;
	}

	/**
	 * 确认是否rest服务方法
	 * 
	 * @param request
	 * @return 是否为rest服务
	 */
	public boolean isRestServiceException(HttpServletRequest request) {
		String path = request.getServletPath();
		if (StringUtils.startsWith(path, suffix))
			return true;
		else
			return false;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ResponseStatusModel status = exceptionResolve.resolveException(ex);
		if (isRestServiceException(request)) {
			try {
				if (status.getStatusCode().equals(ExceptionCodeDef.SC_LASTEST_FORBIDDEN)) {
					response.setStatus(HttpStatus.FORBIDDEN.value());
				} else {
					response.setStatus(HttpStatus.OK.value());
				}
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json");
				JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
						JsonEncoding.UTF8);
				ResponseModel result = new ResponseModel(status);
				objectMapper.writeValue(jsonGenerator, result);
			} catch (JsonGenerationException e) {
			} catch (JsonMappingException e) {
			} catch (IOException e) {
			}
			return new ModelAndView();
		} else {
			return null;
		}
	}

	@Override
	public int getOrder() {
		return this.order;
	}
}
