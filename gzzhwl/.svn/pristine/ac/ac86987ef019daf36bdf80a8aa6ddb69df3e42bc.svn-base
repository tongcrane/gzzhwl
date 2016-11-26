package com.gzzhwl.admin.agreement.validate;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.rest.exception.RestException;

public class AgreementValidate {
	public static void valid_agreement_id(String agreementId) {
		if(StringUtils.isBlank(agreementId)) {
			throw new RestException(ErrorCode.ERROR_400003);
		}
	}
	
	public static void valid_agreement_exist(boolean exist) {
		if(!exist) {
			throw new RestException(ErrorCode.ERROR_400004);
		}
	}
}
