package com.gzzhwl.admin.supply.validate;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.data.model.SupplyInfo;
import com.gzzhwl.core.utils.IdCardUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class SupplyValidate {
	public static final String TYPE_PERSONAL = "01";
	public static final String TYPE_COMPANY = "02";
	
	public static void valid_supplyId(String supplyId) {
		if(StringUtils.isBlank(supplyId)) {
			throw new RestException(ErrorCode.ERROR_200001);
		}
	}
	
	public static void valid_not_exist(boolean exist) {
		if(!exist) {
			throw new RestException(ErrorCode.ERROR_200002);
		}
	}
	
	public static void valid_exist(boolean exist) {
		if(exist) {
			throw new RestException(ErrorCode.ERROR_200003);
		}
	}
	
	public static void valid_supply(SupplyInfo vo) {
		if(StringUtils.isBlank(vo.getType())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "供应商类型不能为空。");
		}
		
		if(StringUtils.isBlank(vo.getFullName())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "供应商全称不能为空。");
		}
		
		boolean fullName = ValidateUtils.validateChinese(vo.getFullName(), 1 , 20);
		if(!fullName) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "供应商全称必须为中文并且不得超过20个汉字。");
		}
		
		if(StringUtils.isBlank(vo.getTaxRegCode())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "身份证号/税务登记号不能为空。");
		}
		
		//个人
		if(TYPE_PERSONAL.equals(vo.getType())) {
			boolean idno = IdCardUtils.validateIdCard18(vo.getTaxRegCode());
			if(!idno) {
				throw new RestException(ErrorCode.ERROR_900004.getCode(), "供应商身份证号码有误。");
			}
		//公司
		}else if(TYPE_COMPANY.equals(vo.getType())){
			boolean taxRegCode = ValidateUtils.validateNumber(vo.getTaxRegCode(), 20);
			if(!taxRegCode) {
				throw new RestException(ErrorCode.ERROR_900004.getCode(), "税务登记号必须为20位数字。");
			}
		}else {
			throw new RestException(ErrorCode.ERROR_900006.getCode(), "供应商类型参数有误。");
		}
		
		boolean contactName = ValidateUtils.validateChinese(vo.getContactName(), 1, 10);
		if(!contactName) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "供应商联系人必须为中文并且不得超过10个汉字。");
		}
		
		if(StringUtils.isBlank(vo.getMobile())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "联系人手机号不能为空。");
		}
		if(!ValidateUtils.isMobileNo(vo.getMobile())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "联系人手机号有误。");
		}
		
		if(StringUtils.isBlank(vo.getAddress())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "供应商联系地址不能为空。");
		}
		if(StringUtils.isBlank(vo.getAreaCode())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "areaCode不能为空。");
		}
		
		if(StringUtils.isBlank(vo.getPaymentType())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "结算方式不能为空。");
		}
	}
}
