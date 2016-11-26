package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_staff_menu_info表
 * @author mew
 *
 */
@Data
@ToString
public class StaffMenuInfo implements Serializable {

	@Length(max = 36, message = "staffId超过长度限制")
	private java.lang.String staffId; // 员工标识

	private java.lang.Integer menuId; // 菜单标识
}