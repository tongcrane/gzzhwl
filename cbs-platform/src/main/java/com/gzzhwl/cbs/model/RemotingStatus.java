package com.gzzhwl.cbs.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RemotingStatus {
	private String statusCode;
	private String errorMsg;

}
