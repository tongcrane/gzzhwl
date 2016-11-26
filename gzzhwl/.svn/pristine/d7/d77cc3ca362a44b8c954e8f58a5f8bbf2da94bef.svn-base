package com.gzzhwl.tms.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.rest.exception.RestException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SourceVO {
	private String sourceId;// 货源ID

	public void valid() {
		validEmpty();
		validData();
		validFormat();
		validBusEmpty();
	}

	private void validEmpty() {
		if (StringUtils.isAnyEmpty(sourceId)) {
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
