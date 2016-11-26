package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_flow_def表
 * @author mew
 *
 */
@Data
@ToString
public class FlowDef implements Serializable {

	@Length(max = 36, message = "flowDefId超过长度限制")
	private java.lang.String flowDefId; // 标识

	@Length(max = 36, message = "flowId超过长度限制")
	private java.lang.String flowId; // 所属流程状态

	@Length(max = 2, message = "category超过长度限制")
	private java.lang.String category; // 分类

	@Length(max = 10, message = "actionCode超过长度限制")
	private java.lang.String actionCode; // 动作编码

	@Length(max = 50, message = "actionName超过长度限制")
	private java.lang.String actionName; // 动作名称

	@Length(max = 50, message = "linkedStatus超过长度限制")
	private java.lang.String linkedStatus; // 前置状态

	@Length(max = 50, message = "transitionStatus超过长度限制")
	private java.lang.String transitionStatus; // 执行后状态

	private java.lang.Integer isStarted; // 起始步骤

	@Length(max = 20, message = "autoRun超过长度限制")
	private java.lang.String autoRun; // 自动执行

	@Length(max = 400, message = "msgTemplate超过长度限制")
	private java.lang.String msgTemplate; // 消息模板

	private java.lang.Integer notice; // 通知
	
	/** 是开始节点 */
	public static final Integer IS_STARTED = 1;
	/** 不是开始节点 */
	public static final Integer NOT_STARTED = 0;
}