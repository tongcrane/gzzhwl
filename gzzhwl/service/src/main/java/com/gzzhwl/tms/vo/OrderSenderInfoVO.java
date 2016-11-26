package com.gzzhwl.tms.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderSenderInfoVO {
	private String address; // 发货人详细地址
	private String areaCodeC; // 发货人地址（市）
	private String areaCodeD; // 发货人地址（区）
	private String areaCodeP; // 发货人地址（省）
	private String contactName; // 发货联系人姓名
	private String customerName; // 发货人公司
	private String customerId; // 发货人ID
	private String mobile; // 发货人手机号
	private String telphone; // 发货人联系电话

	public void valid() {
		validEmpty();
		validData();
		validFormat();
		validBusEmpty();
	}

	private void validEmpty() {
		if (StringUtils.isAnyEmpty(customerId, address, areaCodeC, areaCodeD, areaCodeP, contactName, customerName)) {
			throw new RestException("90001", "参数不能为空");
		}
	}

	private void validData() {
	}

	private void validFormat() {
		if (StringUtils.isNotBlank(telphone)) {
			String pattern = "^(0[0-9]{2,3}-)?[0-9]{7,8}(-[0-9]{1,6})?$";
			boolean matcher = ValidateUtils.matcher(pattern, telphone);
			if (!matcher) {
				throw new RestException("90001", "收货人联系电话格式不正确");
			}
		}
		if (StringUtils.isNotBlank(mobile)) {
			boolean matcher = ValidateUtils.isMobileNo(mobile);
			if (!matcher) {
				throw new RestException("90001", "收货人手机号格式不正确");
			}
		}
	}

	private void validBusEmpty() {
		if (StringUtils.isBlank(mobile) && StringUtils.isBlank(telphone)) {
			throw new RestException("90001", "请填写收货手机号或联系电话");
		}
	}
}