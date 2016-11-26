package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_base_info表
 * @author mew
 *
 */
@Data
@ToString
public class OrderBaseInfo implements Serializable {

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 信息标识

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 数据类型

	@Length(max = 36, message = "belongDepartId超过长度限制")
	private java.lang.String belongDepartId; // 所属部门

	@Length(max = 36, message = "agreementId超过长度限制")
	private java.lang.String agreementId; // 所用合同

	@Length(max = 20, message = "needType超过长度限制")
	private java.lang.String needType; // 车型要求

	@Length(max = 20, message = "needLength超过长度限制")
	private java.lang.String needLength; // 车长要求

	@Length(max = 20, message = "needStartTime超过长度限制")
	private java.lang.String needStartTime; // 要求发车时间

	@Length(max = 20, message = "needArriveTime超过长度限制")
	private java.lang.String needArriveTime; // 要求到达时间

	@Length(max = 10, message = "orderType超过长度限制")
	private java.lang.String orderType; // 订单类型

	@Length(max = 10, message = "lineAttribute超过长度限制")
	private java.lang.String lineAttribute; // 线路属性

	@Length(max = 200, message = "goodsName超过长度限制")
	private java.lang.String goodsName; // 货品名称

	@Length(max = 10, message = "goodsWeight超过长度限制")
	private java.lang.String goodsWeight; // 货物重量

	@Length(max = 10, message = "goodsVolume超过长度限制")
	private java.lang.String goodsVolume; // 货物体积

	@Length(max = 2, message = "needImportedVehicles超过长度限制")
	private java.lang.String needImportedVehicles; // 要求进口车

	@Length(max = 2, message = "needOwnVehicles超过长度限制")
	private java.lang.String needOwnVehicles; // 要求志鸿车

	@Length(max = 36, message = "chargeId超过长度限制")
	private java.lang.String chargeId; // 计费标识

	@Length(max = 10, message = "unitPrice超过长度限制")
	private java.lang.String unitPrice; // 单价

	@Length(max = 10, message = "miles超过长度限制")
	private java.lang.String miles; // 公里数

	@Length(max = 10, message = "paymentType超过长度限制")
	private java.lang.String paymentType; // 结算方式

	@Length(max = 10, message = "orderAdvice超过长度限制")
	private java.lang.String orderAdvice; // 回单要求

	@Length(max = 50, message = "customerBillNo超过长度限制")
	private java.lang.String customerBillNo; // 客户单号

	@Length(max = 400, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

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

	@Length(max = 36, message = "addrId超过长度限制")
	private java.lang.String addrId; // 货场地址标识

	@Length(max = 36, message = "pickUpTime超过长度限制")
	private java.lang.String pickUpTime; // 要求达到场地时间

	@Length(max = 36, message = "isPredict超过长度限制")
	private java.lang.String isPredict; // 是否预估
	
	public static final String ORDER_BILL = "01";// 订单
	public static final String CONSIGNMENT_BILL = "02";// 运单

}