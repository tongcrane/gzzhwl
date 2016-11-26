package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_flows表
 * @author mew
 *
 */
@Data
@ToString
public class Flows implements Serializable {

	@Length(max = 36, message = "flowId超过长度限制")
	private java.lang.String flowId; // 流程标识

	@Length(max = 50, message = "name超过长度限制")
	private java.lang.String name; // 流程名称

	@Length(max = 10, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 50, message = "remark超过长度限制")
	private java.lang.String remark; // 描述
}