package com.gzzhwl.admin.order.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderLineInfoVO {
	private java.lang.String startCodeP; // 起始区域(省)
	private java.lang.String startCodeC; // 起始区域(市)
	private java.lang.String transferCodeP; // 中转区域(省)
	private java.lang.String transferCodeC; // 中转区域(市)
	private java.lang.String endCodeP; // 终点区域(省)
	private java.lang.String endCodeC; // 终点区域(市)

}