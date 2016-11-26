package com.gzzhwl.common.model;

import com.gzzhwl.core.exception.NotFoundEnumException;

public enum ZHFlow {
	/**
	 * 订单流程
	 */

	ORDER("order_flow", "01"),
	/**
	 * 竞标流程
	 */
	QUOTED("quoted_flow", "02"),
	/**
	 * 货主合同流程
	 */
	CONSIGN("consign_flow", "03"),
	/**
	 * 订单退回申请
	 */
	APPLY_RETURN("apply_return_flow", "04"),
	/**
	 * 提货单流程
	 */
	LOAD_BILL("load_flow", "06"),
	/**
	 * 司机合同
	 */
	DRIVER_CONTRACT_BILL("dirver_contract_flow", "07"),
	/**
	 * 货源流程
	 */
	SOURCE("source_flow", "05");

	private String name;

	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private ZHFlow(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public static ZHFlow getZHFlow(String code) throws NotFoundEnumException {
		for (ZHFlow zh : values()) {
			if (code.equals(zh.getCode())) {
				return zh;
			}
		}
		throw new NotFoundEnumException();
	}

}
