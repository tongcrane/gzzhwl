package com.gzzhwl.admin.load.vo;


import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.utils.DateUtils;
import com.gzzhwl.rest.exception.RestException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArriveQueryVo {
	private String queryType;
	private String keyWord;
	private String customerBillNo;//客户单号
	private String customerId;//客户ID
	private String startCodeP;// 线路（起）-省
	private String startCodeC;// 线路（起）-市
	private String endCodeP;// 线路（止）-省
	private String endCodeC;// 线路（止）-市
	private String realName;//司机姓名
	private String telphone;//司机手机
	private String tripTimeStart;//实际发车时间（起）
	private String tripTimeEnd;//实际发车时间（止）
	private String sort;//排序方式  00-按实际到达时间升序  01-按实际到达时间降序
	
	private static final String QUERYTYPE_NORMAL = "0";
	private static final String QUERYTYPE_SENIOR = "1";
	
	public Map<String, Object> getParam() throws ParseException {
		Map<String, Object> params =  Maps.newHashMap();
		
		if(QUERYTYPE_NORMAL.equals(queryType)) {
			if(StringUtils.isNotBlank(keyWord)) {
				params.put("keyWord", keyWord);
				params.put("keyWordLike", "%" + keyWord + "%");
			}
		}else if(QUERYTYPE_SENIOR.equals(queryType)) {
			if(StringUtils.isNotBlank(customerId)) {
				params.put("customerId", customerId);
			}
			if(StringUtils.isNotBlank(customerBillNo)) {
				params.put("customerBillNo", customerBillNo);
			}
			if(StringUtils.isNotBlank(startCodeP)) {
				params.put("startCodeP", startCodeP);
			}
			if(StringUtils.isNotBlank(startCodeC)) {
				params.put("startCodeC", startCodeC);
			}
			if(StringUtils.isNotBlank(endCodeP)) {
				params.put("endCodeP", endCodeP);
			}
			if(StringUtils.isNotBlank(endCodeC)) {
				params.put("endCodeC", endCodeC);
			}
			if(StringUtils.isNotBlank(realName)) {
				params.put("realName", realName);
				params.put("realNameLike", "%" + realName + "%");
			}
			if(StringUtils.isNotBlank(telphone)) {
				params.put("telphone", telphone);
			}
			if(StringUtils.isNotBlank(tripTimeStart)) {
				params.put("tripTimeStart", DateUtils.getStartTime(tripTimeStart));
			}
			if(StringUtils.isNotBlank(tripTimeEnd)) {
				params.put("tripTimeEnd", DateUtils.getEndTime(tripTimeEnd));
			}
		}else {
			throw new RestException(ErrorCode.ERROR_900006);
		}
		
		params.put("statusArray", new String[]{LoadBillType.ARRIVED.getCode(), LoadBillType.ELECRECEIPT.getCode()});
		params.put("type", OrderLoadInfo.LOAD_BILL);
		
		if(StringUtils.isNotBlank(sort)) {
			params.put("sort", sort);
		}else {
			params.put("sort", "01");
		}
		return params;
	}
	
}
