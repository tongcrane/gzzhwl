package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_agent_info表
 * @author mew
 *
 */
@Data
@ToString
public class AgentInfo implements Serializable {

	@Length(max = 36, message = "agentInfoId超过长度限制")
	private java.lang.String agentInfoId; // 经纪人信息标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 2, message = "agentType超过长度限制")
	private java.lang.String agentType; // 类型

	@Length(max = 50, message = "realName超过长度限制")
	private java.lang.String realName; // 真实姓名

	@Length(max = 50, message = "idno超过长度限制")
	private java.lang.String idno; // 身份证号

	@Length(max = 2, message = "sex超过长度限制")
	private java.lang.String sex; // 性别

	@Length(max = 10, message = "telphone超过长度限制")
	private java.lang.String telphone; // 联系电话

	@Length(max = 20, message = "mobile超过长度限制")
	private java.lang.String mobile; // 手机

	@Length(max = 100, message = "companyFullName超过长度限制")
	private java.lang.String companyFullName; // 公司全称

	@Length(max = 20, message = "companyPhone超过长度限制")
	private java.lang.String companyPhone; // 公司电话

	@Length(max = 10, message = "areaCode超过长度限制")
	private java.lang.String areaCode; // 所在区域

	@Length(max = 100, message = "address超过长度限制")
	private java.lang.String address; // 详细地址

	@Length(max = 2, message = "certType超过长度限制")
	private java.lang.String certType; // 证件类型

	@Length(max = 20, message = "uscCode超过长度限制")
	private java.lang.String uscCode; // 统一社会信用代码

	@Length(max = 20, message = "busCode超过长度限制")
	private java.lang.String busCode; // 营业执照代码

	@Length(max = 20, message = "orgCode超过长度限制")
	private java.lang.String orgCode; // 组织机构证代码

	@Length(max = 20, message = "taxRegCode超过长度限制")
	private java.lang.String taxRegCode; // 税务登记证代码

	@Length(max = 36, message = "idFImageRefId超过长度限制")
	private java.lang.String idFImageRefId; // 身份照片(正)

	@Length(max = 36, message = "idBImageRefId超过长度限制")
	private java.lang.String idBImageRefId; // 身份照片(反)

	@Length(max = 36, message = "photoImageRefId超过长度限制")
	private java.lang.String photoImageRefId; // 个人照片

	@Length(max = 36, message = "busImageRefId超过长度限制")
	private java.lang.String busImageRefId; // 营业执照照片

	@Length(max = 36, message = "orgImageRefId超过长度限制")
	private java.lang.String orgImageRefId; // 组织机构证照片

	@Length(max = 36, message = "taxImageRefId超过长度限制")
	private java.lang.String taxImageRefId; // 税务登记证照片

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}