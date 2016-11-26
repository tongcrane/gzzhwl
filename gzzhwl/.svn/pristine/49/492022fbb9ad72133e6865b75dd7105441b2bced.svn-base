package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

/**
 * 货源状态类型
 */
public enum SourceType {

	TODO("01", "待确认"), PUBLISH("02", "已发布"), REJECT("03", "已拒绝"), HASBID("04", "已中标"), FINISH("05", "已完成"),INVALID("06", "作废") ,CLOSE("07",
			"已作废"), END("08", "已关闭"),;

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

	private SourceType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String toString() {
		return this.code;
	}

	public static SourceType getSourceType(String code) throws NotFoundEnumException {
		for (SourceType ds : values()) {
			if (code.equals(ds.getCode())) {
				return ds;
			}
		}
		throw new NotFoundEnumException();
	}
}
