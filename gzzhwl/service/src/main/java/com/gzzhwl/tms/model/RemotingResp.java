package com.gzzhwl.tms.model;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.rest.exception.ExceptionCodeDef;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RemotingResp<T> {
	private RemotingStatus status;
	private T data;

	public boolean isSuccess() {
		if (status != null) {
			return StringUtils.equals(status.getStatusCode(), ExceptionCodeDef.SC_OK);
		} else {
			return false;
		}
	}

	public String getInfo() {
		if (isSuccess()) {
			return StringUtils.EMPTY;
		} else {
			if (status != null) {
				return status.getErrorMsg();
			} else {
				return StringUtils.EMPTY;
			}

		}
	}
}
