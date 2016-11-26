package com.gzzhwl.tms.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.rest.exception.RestException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderLineInfoVO {
	private String endCodeC; // 订单线路目的地（市）
	private String endCodeP; // 订单线路目的地（省）
	private String startCodeC; // 订单线路出发地（市）
	private String startCodeP; // 订单线路出发地（省）

	public void valid() {
		validEmpty();
		validData();
		validFormat();
		validBusEmpty();
	}

	private void validEmpty() {
		if (StringUtils.isAnyEmpty(endCodeC, endCodeP, startCodeC, startCodeP)) {
			throw new RestException("90001", "参数不能为空");
		}
	}

	private void validData() {
	}

	private void validFormat() {
	}

	private void validBusEmpty() {
	}
}