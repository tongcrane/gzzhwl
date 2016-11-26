package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_feedback_log表
 * @author mew
 *
 */
@Data
@ToString
public class LoadFeedbackLog implements Serializable {

	@Length(max = 36, message = "feedbackId超过长度限制")
	private java.lang.String feedbackId; // 反馈标识

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 异常类型

	@Length(max = 50, message = "itemName超过长度限制")
	private java.lang.String itemName; // 费用名称

	@Length(max = 20, message = "itemPrice超过长度限制")
	private java.lang.String itemPrice; // 费用金额

	@Length(max = 400, message = "itemDesc超过长度限制")
	private java.lang.String itemDesc; // 费用说明

	@Length(max = 20, message = "feedbackTime超过长度限制")
	private java.lang.String feedbackTime; // 反馈时间

	@Length(max = 20, message = "longitude超过长度限制")
	private java.lang.String longitude; // 经度

	@Length(max = 20, message = "latitude超过长度限制")
	private java.lang.String latitude; // 纬度

	@Length(max = 200, message = "address超过长度限制")
	private java.lang.String address; // 地址

	@Length(max = 20, message = "speed超过长度限制")
	private java.lang.String speed; // 车速

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源

	@Length(max = 2, message = "isException超过长度限制")
	private java.lang.String isException; // 是否异常

	@Length(max = 2, message = "isEnd超过长度限制")
	private java.lang.String isEnd; // 是否结束

	@Length(max = 20, message = "endTime超过长度限制")
	private java.lang.String endTime; // 结束时间

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 36, message = "updatedBy超过长度限制")
	private java.lang.String updatedBy; // 修改人

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除

	@Length(max = 10, message = "distances超过长度限制")
	private java.lang.String distances; // 距终点直线距离(km)

	@Length(max = 2, message = "priceSymbol超过长度限制")
	private java.lang.String priceSymbol; // 费用类型 01加 02减
	
	//不是异常
	public static final String ISEXCEPTION_NO="00";
	//是异常
	public static final String ISEXCEPTION_YES="01";
	//未结束
	public static final String ISEND_NO="00";
	//已结束
	public static final String ISEND_YES="01";
	//补偿
	public static final String PRICE_ADD="01";
	//扣款
	public static final String PRICE_DELETED="00";
}