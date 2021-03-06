package com.gzzhwl.admin.customer.vo;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.rest.exception.RestException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerQueryVo {
	private String queryType;
	private String customerNo; // 客户编号
	private String type; // 客户类型
	private String fullName; // 客户全名
	private String taxRegCode; // 税务登记号
	private String contactName; // 联系人
	private String mobile; // 联系手机
	private String address; // 联系地址
	private String isAgreement; // 是否合同
	private String keyWord;//快捷搜索关键字
	
	private static final String QUERYTYPE_NORMAL = "0";
	private static final String QUERYTYPE_SENIOR = "1";

	public Map<String, Object> getParam() {
		Map<String, Object> params = Maps.newHashMap();
		if(QUERYTYPE_NORMAL.equals(this.getQueryType())) {
			//快捷搜索
			if(StringUtils.isNotBlank(this.getKeyWord())) {
				params.put("keyWord", this.getKeyWord());
				params.put("keyWordLike", "%" + this.getKeyWord() + "%");
			}
			params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		}else if(QUERYTYPE_SENIOR.equals(this.getQueryType())){
			//高级搜索
			if(StringUtils.isNotBlank(this.getCustomerNo())) {
				params.put("customerNo", this.getCustomerNo());
			}
			if(StringUtils.isNotBlank(this.getType())) {
				params.put("type", this.getType());
			}
			
			if(StringUtils.isNotBlank(this.getFullName())) {
				params.put("fullName", this.getFullName());
			}
			
			if(StringUtils.isNotBlank(this.getTaxRegCode())) {
				params.put("taxRegCode", this.getTaxRegCode());
			}
			
			if(StringUtils.isNotBlank(this.getContactName())) {
				params.put("contactName", this.getContactName());
			}
			
			if(StringUtils.isNotBlank(this.getMobile())) {
				params.put("mobile", this.getMobile());
			}
			
			if(StringUtils.isNotBlank(this.getIsAgreement())) {
				params.put("isAgreement", this.getIsAgreement());
			}
			
			if(StringUtils.isNotBlank(this.getAddress())) {
				params.put("address", this.getAddress());
			}
			params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		}else {
			throw new RestException(ErrorCode.ERROR_900006);
		}
		
		return params;
	}

	
}
