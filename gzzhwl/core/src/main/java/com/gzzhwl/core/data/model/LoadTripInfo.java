package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_trip_info表
 * @author mew
 *
 */
@Data
@ToString
public class LoadTripInfo implements Serializable {

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 20, message = "tripTime超过长度限制")
	private java.lang.String tripTime; // 发车时间

	@Length(max = 10, message = "goodsWeight超过长度限制")
	private java.lang.String goodsWeight; // 货物重量

	@Length(max = 10, message = "goodsVolume超过长度限制")
	private java.lang.String goodsVolume; // 货物体积

	@Length(max = 50, message = "customerBillNo超过长度限制")
	private java.lang.String customerBillNo; // 客户单号

	@Length(max = 36, message = "contractImageRefId超过长度限制")
	private java.lang.String contractImageRefId; // 货主托运合同照片

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}