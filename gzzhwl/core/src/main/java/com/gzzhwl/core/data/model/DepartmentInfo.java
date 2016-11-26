package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_department_info表
 * @author mew
 *
 */
@Data
@ToString
public class DepartmentInfo implements Serializable {

	private java.lang.Integer departId; // 部门标识

	@Length(max = 20, message = "name超过长度限制")
	private java.lang.String name; // 部门名字

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 3, message = "lo超过长度限制")
	private java.lang.String lo; // 
}