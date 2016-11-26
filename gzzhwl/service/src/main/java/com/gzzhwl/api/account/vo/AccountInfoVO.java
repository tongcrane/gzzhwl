package com.gzzhwl.api.account.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccountInfoVO {
	private String realName; // 真实姓名
	private String idno; // 身份证号
	private String idFImageRefId; // 身份照片(正)
	private String idBImageRefId; // 身份照片(反)
	private String busImageRefId; // 营业执照照片
	private String agentType;// 账户类型
}
