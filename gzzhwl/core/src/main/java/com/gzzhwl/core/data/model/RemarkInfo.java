package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_remark_info表
 * @author mew
 *
 */
@Data
@ToString
public class RemarkInfo implements Serializable {

	private java.lang.Long pid; // 自增标识

	@Length(max = 36, message = "remarkId超过长度限制")
	private java.lang.String remarkId; // 备注标识

	@Length(max = 36, message = "targetId超过长度限制")
	private java.lang.String targetId; // 对象标识

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 对象类型

	@Length(max = 400, message = "content超过长度限制")
	private java.lang.String content; // 备注内容

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}