package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_load_info表
 * @author mew
 *
 */
@Data
@ToString
public class OrderLoadInfo implements Serializable {

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 数据类型

	@Length(max = 36, message = "belongDepartId超过长度限制")
	private java.lang.String belongDepartId; // 所属部门

	@Length(max = 50, message = "loadNo超过长度限制")
	private java.lang.String loadNo; // 提货单号

	@Length(max = 36, message = "orderId超过长度限制")
	private java.lang.String orderId; // 订单标识

	@Length(max = 36, message = "quotedId超过长度限制")
	private java.lang.String quotedId; // 报价标识

	@Length(max = 36, message = "vehicleInfoId超过长度限制")
	private java.lang.String vehicleInfoId; // 车辆信息标识

	@Length(max = 36, message = "loadInfoId超过长度限制")
	private java.lang.String loadInfoId; // 车挂信息标识

	@Length(max = 20, message = "needArriveTime超过长度限制")
	private java.lang.String needArriveTime; // 要求达到场地时间

	@Length(max = 10, message = "freightPrice超过长度限制")
	private java.lang.String freightPrice; // 运费

	@Length(max = 2, message = "isPredict超过长度限制")
	private java.lang.String isPredict; // 是否预估

	@Length(max = 10, message = "prePay超过长度限制")
	private java.lang.String prePay; // 预付现金

	@Length(max = 10, message = "oilPay超过长度限制")
	private java.lang.String oilPay; // 预付油费

	@Length(max = 20, message = "oilCardNo超过长度限制")
	private java.lang.String oilCardNo; // 油卡卡号

	@Length(max = 10, message = "paymentType超过长度限制")
	private java.lang.String paymentType; // 结算方式

	@Length(max = 50, message = "paymentName超过长度限制")
	private java.lang.String paymentName; // 结算对象名称

	@Length(max = 36, message = "supplyId超过长度限制")
	private java.lang.String supplyId; // 结算对象

	@Length(max = 400, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

	@Length(max = 10, message = "status超过长度限制")
	private java.lang.String status; // 单据状态

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
	
	public static final String LOAD_BILL = "01";// 提货单
	public static final String DRIVER_CONTRACT_BILL = "02";// 司机合同

}