package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_store_addr_info表
 * @author mew
 *
 */
@Data
@ToString
public class StoreAddrInfo implements Serializable {

	@Length(max = 36, message = "addrId超过长度限制")
	private java.lang.String addrId; // 地址标识

	@Length(max = 50, message = "addrName超过长度限制")
	private java.lang.String addrName; // 地址名字

	@Length(max = 10, message = "provinceCode超过长度限制")
	private java.lang.String provinceCode; // 省编码

	@Length(max = 10, message = "cityCode超过长度限制")
	private java.lang.String cityCode; // 市编码

	@Length(max = 10, message = "districtCode超过长度限制")
	private java.lang.String districtCode; // 区编码

	@Length(max = 50, message = "address超过长度限制")
	private java.lang.String address; // 详细地址

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

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
	
	private java.lang.String provinceCn; // 省编码
	private java.lang.String cityCn; // 市编码
	private java.lang.String districtCn; // 区编码
}