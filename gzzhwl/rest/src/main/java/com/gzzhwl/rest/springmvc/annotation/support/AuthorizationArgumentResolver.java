package com.gzzhwl.rest.springmvc.annotation.support;

import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.security.AuthorizationService;
import com.gzzhwl.rest.springmvc.annotation.Authorization;

public class AuthorizationArgumentResolver extends AbstractNamedValueMethodArgumentResolver {
	@Autowired
	private AuthorizationService authorizationService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Authorization.class)
				&& !Map.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
		Authorization annotation = parameter.getParameterAnnotation(Authorization.class);
		return new AuthorizationValueInfo(annotation);
	}

	@Override
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
		String authorization = request.getHeader(name);
		if (StringUtils.isEmpty(authorization)) {
			authorization = request.getParameter(name);
		}
		if (authorization != null) {
			AccountInfo accountInfo = authorizationService.getCurrentAccount(authorization);
			if (AccountInfo.class.isAssignableFrom(parameter.getParameterType())) {
				return accountInfo;
			} else {
				return accountInfo.getAccountId();
			}
		} else {
			return null;
		}
	}

	@Override
	protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
		Class<?> paramType = parameter.getParameterType();
		if (!paramType.getName().equals("java.util.Optional")) {
			throw new RestException(ExceptionCodeDef.SC_MISSING_FORBIDDEN, "token不存在");// token不存在，不支持匿名访问。
		}
	}

	private class AuthorizationValueInfo extends NamedValueInfo {
		private AuthorizationValueInfo(Authorization annotation) {
			super(annotation.value(), annotation.required(), annotation.defaultValue());
		}
	}
}
