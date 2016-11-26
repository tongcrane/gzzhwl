package com.gzzhwl.admin.driver.vo;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.utils.DateUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DriverInfoQueryVo {
	private String queryType;
	
	private String keyWord;//全文搜索关键字
	
	private String realName;//驾驶员姓名
	
	private String idno;//身份证号
	
	private String attributes; // 驾驶员属性
	
	private String telphone;//联系电话
	
	private String address; // 详细地址
	
	private String dlType; // 准驾车型
	
	private String qcNo; // 从业资格证号
	
	private String qcType; // 从业资格证类型
	
	private String status;
	
	private String dlEndDateStart;//驾驶证有效期止（查询范围的起日）
	
	private String dlEndDateEnd;//驾驶证有效期止（查询范围的止日）
	
	private String qcEndDateStart;//从业资格证有效期止（查询范围的起日）
	
	private String qcEndDateEnd;//从业资格证有效期止（查询范围的止日）
	
	private String createdStartTime;//创建时间起日
	
	private String createdEndTime;//创建时间止日

	
	private static final String QUERYTYPE_NORMAL = "0";// 快捷查询
	private static final String QUERYTYPE_SENIOR = "1";// 高级查询


	public Map<String, Object> getParam() throws ParseException {
		String queryType = this.getQueryType();
		ParamEmptyValidator.VALID_PARAM_EMPTY(this.queryType);

		Map<String, Object> params = new HashMap<String, Object>();
		if (QUERYTYPE_NORMAL.equals(queryType)) {
			// 快捷搜索
			if (StringUtils.isNotBlank(this.getKeyWord())) {
				params.put("keyWord", this.getKeyWord());
				params.put("keyWordLike", "%" + this.getKeyWord() + "%");
			}

		} else if (QUERYTYPE_SENIOR.equals(queryType)) {
			// 高级搜索
			if (StringUtils.isNotBlank(this.getRealName())) {
				params.put("realName", this.getRealName());
			}
			
			if (StringUtils.isNotBlank(this.getIdno())) {
				params.put("idno", this.getIdno());
			}
			
			if (StringUtils.isNotBlank(this.getAttributes())) {
				params.put("attributes", this.getAttributes());
			}
			
			if (StringUtils.isNotBlank(this.getTelphone())) {
				params.put("telphone", this.getTelphone());
			}

			if (StringUtils.isNotBlank(this.getAddress())) {
				params.put("address", "%" + this.getAddress() + "%");
			}

			if (StringUtils.isNotBlank(this.getDlType())) {
				params.put("dlType", this.getDlType());
			}
			
			if (StringUtils.isNotBlank(this.getQcNo())) {
				params.put("qcNo", this.getQcNo());
			}

			if (StringUtils.isNotBlank(this.getQcType())) {
				params.put("qcType", this.getQcType());
			}
			
			if (StringUtils.isNotBlank(this.getStatus())) {
				params.put("status", this.getStatus());
			}	
			
			if (StringUtils.isNotBlank(this.getDlEndDateStart())) {
				params.put("dlEndDateStart", DateUtils.getStartTime(this.getDlEndDateStart()));
			}
			
			if (StringUtils.isNotBlank(this.getDlEndDateEnd())) {
				params.put("dlEndDateEnd", DateUtils.getEndTime(this.getDlEndDateEnd()));
			}
			
			if (StringUtils.isNotBlank(qcEndDateStart)) {
				params.put("qcEndDateStart", DateUtils.getStartTime(qcEndDateStart));
			}
			
			if (StringUtils.isNotBlank(qcEndDateEnd)) {
				params.put("qcEndDateEnd", DateUtils.getEndTime(qcEndDateEnd));
			}
			
			
			if (StringUtils.isNotBlank(createdStartTime)) {
				params.put("createdStartTime", DateUtils.getStartTime(createdStartTime));
			}
			
			if (StringUtils.isNotBlank(createdEndTime)) {
				params.put("createdEndTime", DateUtils.getEndTime(createdEndTime));
			}
			

		} else {
			throw new RestException(ErrorCode.ERROR_900006);
		}

		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("source", DataSource.PLATFORM.getCode());

		return params;
	}
	
}
