package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_arrive_info表
 * @author mew
 *
 */
@Data
@ToString
public class LoadArriveInfo implements Serializable {

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 20, message = "arriveTime超过长度限制")
	private java.lang.String arriveTime; // 实际到达时间

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}