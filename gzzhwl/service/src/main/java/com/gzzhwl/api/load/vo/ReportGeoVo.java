package com.gzzhwl.api.load.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReportGeoVo {
	private String tokenId;
	private String longitude;
	private String latitude;
	private String speed;
}
