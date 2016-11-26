package com.gzzhwl.tms.vo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderReceiverInfoVO {
	private String address; // 收货人详细地址
	private String areaCodeC; // 收货人地址（市）
	private String areaCodeD; // 收货人地址（区）
	private String areaCodeP; // 收货人地址（省）
	private String contactName; // 收货联系人姓名
	private String customerName; // 收货人公司
	private String mobile; // 收货人手机号
	private String telphone; // 收货人联系电话
	private String longitude; // 经度
	private String latitude; // 纬度

	public void valid() {
		validEmpty();
		validData();
		validFormat();
		validBusEmpty();
	}

	private void validEmpty() {
		if (StringUtils.isAnyEmpty(address, areaCodeC, areaCodeD, areaCodeP, contactName)) {
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
		if (StringUtils.isNotBlank(longitude)) {
			if (!NumberUtils.isNumber(longitude)) {
				throw new RestException("90001", "经度格式不正确");
			}
		}
		if (StringUtils.isNotBlank(latitude)) {
			if (!NumberUtils.isNumber(latitude)) {
				throw new RestException("90001", "纬度格式不正确");
			}
		}
	}

	private void validBusEmpty() {
		if (StringUtils.isBlank(mobile) && StringUtils.isBlank(telphone)) {
			throw new RestException("90001", "请填写收货手机号或联系电话");
		}
		if (StringUtils.isNotBlank(longitude) || StringUtils.isNotBlank(latitude)) {
			if (StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)) {
				throw new RestException("90001", "请填写完整的收货人经纬度信息");
			}
		}

	}
}