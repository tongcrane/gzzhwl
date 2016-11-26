package com.gzzhwl.rest.springmvc.validate;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.exception.RestException;

public class ParamEmptyValidator {

	public static void VALID_PARAM_EMPTY(final CharSequence... css) throws RestException {
		if (StringUtils.isAnyEmpty(css)) {
			throw new RestException(ExceptionCodeDef.SC_EMPTY_REQUEST, "参数不能为空");
		}
	}

}
