package com.gzzhwl.admin.customer.validate;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.constant.CustType;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.utils.IdCardUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class CustomerValidate {
	public static void valid_not_exist(boolean notExist) {
		if (notExist) {
			throw new RestException(ErrorCode.ERROR_600001);
		}
	}

	public static void valid_exist(boolean exist) {
		if (exist) {
			throw new RestException(ErrorCode.ERROR_600005);
		}
	}

	public static void valid_fullName_exist(boolean exist) {
		if (exist) {
			throw new RestException(ErrorCode.ERROR_600005.getCode(), "客户全称已存在!");
		}
	}

	public static void valid_taxCode_exist(boolean exist, String type) {
		if (exist) {
			if (StringUtils.equals(type, CustType.COMPANY.getCode())) {
				throw new RestException(ErrorCode.ERROR_600007);
			} else {
				throw new RestException(ErrorCode.ERROR_600008);
			}

		}
	}

	public static void valid_custId(String custId) {
		if (StringUtils.isBlank(custId)) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "客户ID不能为空!");
		}
	}

	public static void valid_custno(String custno, String comCustno) {
		if (StringUtils.isBlank(custno)) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "客户编号不能为空!");
		}

		if (!StringUtils.equals(custno, comCustno)) {
			throw new RestException(ErrorCode.ERROR_600006);
		}
	}

	public static void valid_custno_(String custno) {
		if (StringUtils.isBlank(custno)) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "客户编号不能为空!");
		}
	}

	public static void valid_customer(CustomerInfo cust) {

		if (StringUtils.isBlank(cust.getType())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "客户类型不能为空!");
		}

		if (StringUtils.isBlank(cust.getFullName())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "客户全称不能为空!");
		}

		if (StringUtils.isBlank(cust.getContactName())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "业务联系人不能为空!");
		}

		if (StringUtils.isBlank(cust.getTelphone()) && StringUtils.isBlank(cust.getMobile())) {
			throw new RestException(ErrorCode.ERROR_600003);
		}

		if (StringUtils.isBlank(cust.getAddress())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "联系地址不能为空!");
		}

		if (StringUtils.isBlank(cust.getIsAgreement())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "是否合同不能为空!");
		}

		if (StringUtils.isBlank(cust.getAreaCode())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "行政区域码不能为空!");
		}

		if (StringUtils.isBlank(cust.getTaxRegCode())) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "税务登记号/个人身份证号不能为空!");
		}

		if (StringUtils.isNotBlank(cust.getTaxRegCode())) {
			if ((CustType.PERSONAL.getCode()).equals(cust.getType())) {
				// 个人 为身份证号，并且必填

				if (!IdCardUtils.validateIdCard18(cust.getTaxRegCode())) {
					throw new RestException(ErrorCode.ERROR_900004.getCode(), "个人身份证号输入有误!");
				}

				// if(!ValidateUtils.validateNumber(cust.getTaxRegCode(), 20)) {
				// throw new RestException(ErrorCode.ERROR_600002.getCode(),
				// ErrorCode.ERROR_600002.getDesc());
				// }
			} else if ((CustType.COMPANY.getCode()).equals(cust.getType())) {
				// 公司 税务登记号为15位数字
				if (!ValidateUtils.validateNumber(cust.getTaxRegCode(), 15)) {
					throw new RestException(ErrorCode.ERROR_600002);
				}
			}
		}

		if (StringUtils.isNotBlank(cust.getEmail())) {
			if (!ValidateUtils.isEmail(cust.getEmail())) {
				throw new RestException(ErrorCode.ERROR_600004);
			}
		}
	}
}
