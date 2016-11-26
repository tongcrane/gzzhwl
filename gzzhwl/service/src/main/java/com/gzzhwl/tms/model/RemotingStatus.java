package com.gzzhwl.tms.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RemotingStatus {
	private String statusCode;
	private String errorMsg;

}
