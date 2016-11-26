package com.gzzhwl.admin.contract;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.rest.exception.RestException;

public class ContractValidate {
	public static void valid_contract_exist(boolean exist) {
		if(!exist) {
			throw new RestException(ErrorCode.ERROR_110007);
		}
	}
	
	public static void valid_contractId(String contractId) {
		if(StringUtils.isBlank(contractId)) {
			throw new RestException(ErrorCode.ERROR_110008);
		}
	}
	
	public static void valid_load_contract_ref_exist(boolean exist) {
		if(!exist) {
			throw new RestException(ErrorCode.ERROR_110007);
		}
	}
	
	public static void valid_load_exist(boolean exist) {
		if(!exist) {
			throw new RestException(ErrorCode.ERROR_110002);
		}
	}
}
