package com.gzzhwl.rest.springmvc.annotation.support;

import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;

public class PaginationArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Pagination.class)
				&& !Map.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
		Pagination annotation = parameter.getParameterAnnotation(Pagination.class);
		return new PaginationValueInfo(annotation);
	}

	@Override
	protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
		String pageIndex = request.getParameter(name);
		String pageSize = request.getParameter("pageSize");
		PageParameter pageParameter = new PageParameter(pageIndex, pageSize);
		if (PageParameter.class.isAssignableFrom(parameter.getParameterType())) {
			return pageParameter;
		} else {
			return pageParameter.getPageIndex();
		}
	}

	@Override
	protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
		if (!parameter.getParameterType().getName().equals("java.util.Optional")) {
			throw new MissingServletRequestParameterException(name, parameter.getParameterType().getSimpleName());
		}
	}

	private class PaginationValueInfo extends NamedValueInfo {
		private PaginationValueInfo(Pagination annotation) {
			super("pageIndex", annotation.required(), annotation.defaultValue());
		}
	}
}
