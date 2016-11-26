package com.gzzhwl.api.source.vo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuerySourceVO {
	private String lineId;
	private String startCodeC;
	private String endCodeC;
	private String needType;
	private String needLength;
	private String returned;
	private String sort;
	
	public Map<String, Object> getParam() {
		ParamEmptyValidator.VALID_PARAM_EMPTY(this.lineId);
		Map<String, Object> params = Maps.newHashMap();
		if (StringUtils.equals(this.returned, "01")) {
			params.put("returned", true);
		} else {
			params.put("returned", false);
		}
		
		params.put("lineId", this.lineId);
		
		if (StringUtils.equals(this.sort, "01")) {
			params.put("sort", "desc");
		} else if (StringUtils.equals(this.sort, "02")) {
			params.put("sort", "asc");
		}
		if (StringUtils.isNotBlank(startCodeC)) {
			params.put("startCodeCList", this.startCodeC.split(","));
		}
		if (StringUtils.isNotBlank(endCodeC)) {
			params.put("endCodeCList", this.endCodeC.split(","));
		}
		if (StringUtils.isNotBlank(this.needType)) {
			params.put("needTypeList", this.needType.split(","));
		}
		if (StringUtils.isNotBlank(this.needLength)) {
			params.put("needLengthList", this.needLength.split(","));
		}
		
		return params;
	}
	
	
	public static void main(String[] args) {
		String test = "a,b,c,22,ff";
		System.out.println(test.split(",")[0]);
	}

}
