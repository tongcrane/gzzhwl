package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_flow_status表
 * @author mew
 *
 */
@Data
@ToString
public class FlowStatus implements Serializable {

	@Length(max = 36, message = "flowId超过长度限制")
	private java.lang.String flowId; // 流程标识

	@Length(max = 2, message = "code超过长度限制")
	private java.lang.String code; // 状态码

	@Length(max = 50, message = "name超过长度限制")
	private java.lang.String name; // 状态名称

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 类型
	
	/** 开始节点 */
	public static final String START_TYPE = "01";
	/** 普通节点 */
	public static final String NORMAL_TYPE = "02";
	/** 结束借点*/
	public static final String END_TYPE = "03";
}