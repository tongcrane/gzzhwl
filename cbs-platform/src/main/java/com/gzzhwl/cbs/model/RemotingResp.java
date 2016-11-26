package com.gzzhwl.cbs.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RemotingResp<T> {
	private RemotingStatus status;
	private T data;

	public boolean isSuccess() {
		if (status != null) {
			return StringUtils.equals(status.getStatusCode(), "200");
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
