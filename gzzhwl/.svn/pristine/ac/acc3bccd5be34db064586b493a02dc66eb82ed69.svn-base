package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_unique_key表
 * @author mew
 *
 */
@Data
@ToString
public class UniqueKey implements Serializable {

	@Length(max = 100, message = "seqName超过长度限制")
	private java.lang.String seqName; // 表名

	private java.lang.Integer nextHi; // 序列值

	@Length(max = 8, message = "dateHi超过长度限制")
	private java.lang.String dateHi; // 日期
	
	private java.lang.String currentDate;// 日期


}