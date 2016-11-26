package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_driver_info表
 * @author mew
 *
 */
@Data
@ToString
public class DriverInfo implements Serializable {

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 驾驶员信息标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 100, message = "realName超过长度限制")
	private java.lang.String realName; // 真实姓名

	@Length(max = 50, message = "idno超过长度限制")
	private java.lang.String idno; // 身份证号

	@Length(max = 2, message = "sex超过长度限制")
	private java.lang.String sex; // 性别

	@Length(max = 10, message = "birthday超过长度限制")
	private java.lang.String birthday; // 出生年月

	@Length(max = 400, message = "criminalRecord超过长度限制")
	private java.lang.String criminalRecord; // 犯罪记录

	@Length(max = 11, message = "telphone超过长度限制")
	private java.lang.String telphone; // 手机号

	@Length(max = 10, message = "attributes超过长度限制")
	private java.lang.String attributes; // 驾驶员属性

	@Length(max = 10, message = "areaCode超过长度限制")
	private java.lang.String areaCode; // 所在区域

	@Length(max = 50, message = "address超过长度限制")
	private java.lang.String address; // 详细地址

	@Length(max = 10, message = "dlType超过长度限制")
	private java.lang.String dlType; // 驾驶证类型

	@Length(max = 10, message = "dlIssueDate超过长度限制")
	private java.lang.String dlIssueDate; // 驾驶证领证日期

	@Length(max = 10, message = "dlStartDate超过长度限制")
	private java.lang.String dlStartDate; // 驾驶证有效期(起)

	@Length(max = 10, message = "dlEndDate超过长度限制")
	private java.lang.String dlEndDate; // 驾驶证有效期(止)

	@Length(max = 50, message = "qcNo超过长度限制")
	private java.lang.String qcNo; // 从业资格证号

	@Length(max = 10, message = "qcType超过长度限制")
	private java.lang.String qcType; // 从业资格证类型

	@Length(max = 10, message = "qcIssueDate超过长度限制")
	private java.lang.String qcIssueDate; // 从业资格领证日期

	@Length(max = 10, message = "qcStartDate超过长度限制")
	private java.lang.String qcStartDate; // 从业资格证有效期(起)

	@Length(max = 10, message = "qcEndDate超过长度限制")
	private java.lang.String qcEndDate; // 从业资格证有效期(止)

	@Length(max = 36, message = "idFImageRefId超过长度限制")
	private java.lang.String idFImageRefId; // 身份照片(正)

	@Length(max = 36, message = "idBImageRefId超过长度限制")
	private java.lang.String idBImageRefId; // 身份照片(反)

	@Length(max = 36, message = "photoFImageRefId超过长度限制")
	private java.lang.String photoFImageRefId; // 个人照片

	@Length(max = 36, message = "drivingFImageRefId超过长度限制")
	private java.lang.String drivingFImageRefId; // 驾驶证照片(正)

	@Length(max = 36, message = "drivingBImageRefId超过长度限制")
	private java.lang.String drivingBImageRefId; // 驾驶证照片(反)

	@Length(max = 36, message = "qualifiImageRefId超过长度限制")
	private java.lang.String qualifiImageRefId; // 从业资格证照片1

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 36, message = "updatedBy超过长度限制")
	private java.lang.String updatedBy; // 修改人

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除

	@Length(max = 36, message = "qualifiImage2RefId超过长度限制")
	private java.lang.String qualifiImage2RefId; // 从业资格证照片2
}