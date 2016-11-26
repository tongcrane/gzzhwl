package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

/**
 * 货源状态类型
 */
public enum OrderType {

	TODO("01", "待处理"), CANCLED("02", "已取消"), PUSHED_YSJ("03", "已推送YSJ"), WAIT_LOAD("04", "待配载"), YSJ_BACK("05",
			"被YSJ退回"), CLOSED("06", "已关闭"), PUBLISH_YSJ("07", "YSJ已发布"), BID("08", "YSJ已中标"), YSJ_COMPLETED("09",
					"YSJ已完成"), YSJ_CLOSED("10", "YSJ关闭"), YSJ_INVALID("11", "YSJ作废"), QUTOED_COMPLTE("12",
							"YSJ竞价结束"), LOADED("13", "已配载"), WAIT("14", "待处理"), TRIPED("15", "已发车"), ON_PASSAGE("16",
									"在途"), CANCLE_TRIP("17", "取消发车"), ARRIVED("18",
											"到达"), PRINT_RECEIPT("19", "收到纸质回单"), ARRIVED_COLSED("20", "关闭");

	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private OrderType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String toString() {
		return this.code;
	}

	public static OrderType getSourceType(String code) throws NotFoundEnumException {
		for (OrderType ds : values()) {
			if (code.equals(ds.getCode())) {
				return ds;
			}
		}
		throw new NotFoundEnumException();
	}
}
