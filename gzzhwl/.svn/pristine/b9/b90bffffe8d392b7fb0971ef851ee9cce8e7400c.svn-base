package com.gzzhwl.core.constant;

import com.gzzhwl.core.exception.NotFoundEnumException;

/**
 * 报价类型
 * @author jly
 *
 */
public enum QuotedType {

	QUOTED("01", "已报价"), 
	CLOSED("02", "已关闭"), 
	NOTIMPROVE("03", "已中标,待完善信息"), 
	ONLINE("05", "在途中"),
	INVALID("04", "作废"),
	ENDDING("06", "已结束"),
	SYSCLOSE("07", "已关闭（系统）"),
	CANCLETRIP("08", "已关闭（取消发车）"), 
	COMPLETE("09", "已完成"),
	ONLINE_TRIP("10", "在途中"),
	;

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

	private QuotedType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String toString(){
		return this.code;
	}
	
	public static QuotedType getQuotedType(String code) throws NotFoundEnumException {
		for (QuotedType ds : values()) {
			if (code.equals(ds.getCode())) {
				return ds;
			}
		}
		throw new NotFoundEnumException();
	}
}
