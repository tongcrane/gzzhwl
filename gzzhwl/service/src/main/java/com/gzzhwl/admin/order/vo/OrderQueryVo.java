package com.gzzhwl.admin.order.vo;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.OrderType;
import com.gzzhwl.core.utils.DateUtils;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderQueryVo {
	private String queryType;// 查询方式 0-快捷搜索 1-高级搜索
	private String keyWord;// 快捷搜索关键字
	private String customerName;// 客户全称
	private String customerId;// 客户全称
	private String agreementId;// 客户合同
	private String createdTimeS;// 订单创建时间(起)
	private String createdTimeE;// 订单创建时间(止)
	private String createDepartName;// 订单创建部门
	private String startCodeP;// 线路（起）-省
	private String startCodeC;// 线路（起）-市
	private String endCodeP;// 线路（止）-省
	private String endCodeC;// 线路（止）-市
	private String sendContractName;// 发货联系人
	private String sendMobile;// 发货联系人手机
	private String reciContractName;// 发货联系人
	private String reciMobile;// 发货联系人手机

	private String orderStatus;// 订单状态
	private String retStatus;// 申请退回状态
	private String sort;// 00-下单时间升序 01-下单时间降序

	private static final String QUERYTYPE_NORMAL = "0";// 快捷查询
	private static final String QUERYTYPE_SENIOR = "1";// 高级查询

	public Map<String, Object> getParam() throws ParseException {
		String queryType = this.getQueryType();
		ParamEmptyValidator.VALID_PARAM_EMPTY(this.queryType);

		Map<String, Object> params = new HashMap<String, Object>();
		if (QUERYTYPE_NORMAL.equals(queryType)) {
			// 快捷搜索
			if (StringUtils.isNoneBlank(this.getKeyWord())) {
				params.put("keyWord", this.getKeyWord());
				params.put("keyWordLike", "%" + this.getKeyWord() + "%");
			}
		} else if (QUERYTYPE_SENIOR.equals(queryType)) {
			// 高级搜索
			if (StringUtils.isNotBlank(this.getCustomerName())) {
				params.put("customerName", "%" + this.getCustomerName() + "%");
			}

			if (StringUtils.isNotBlank(this.getCustomerId())) {
				params.put("customerId", this.getCustomerId());
			}

			if (StringUtils.isNotBlank(this.getAgreementId())) {
				params.put("agreementId", this.getAgreementId());
			}

			if (StringUtils.isNotBlank(this.getCreatedTimeS())) {
				params.put("createdTimeS", DateUtils.getStartTime(this.getCreatedTimeS()));
			}

			if (StringUtils.isNotBlank(this.getCreatedTimeE())) {
				params.put("createdTimeE", DateUtils.getEndTime(this.getCreatedTimeE()));
			}

			if (StringUtils.isNotBlank(this.getCreateDepartName())) {
				params.put("createDepartName", "%" + this.getCreateDepartName() + "%");
			}

			if (StringUtils.isNotBlank(this.getStartCodeP())) {
				params.put("lineStartP", this.getStartCodeP());
			}

			if (StringUtils.isNotBlank(this.getStartCodeC())) {
				params.put("lineStartC", this.getStartCodeC());
			}

			if (StringUtils.isNotBlank(this.getEndCodeP())) {
				params.put("lineEndP", this.getEndCodeP());
			}

			if (StringUtils.isNotBlank(this.getEndCodeC())) {
				params.put("lineEndC", this.getEndCodeC());
			}

			if (StringUtils.isNotBlank(this.getSendContractName())) {
				params.put("sendContractName", "%" + this.getSendContractName() + "%");
			}

			if (StringUtils.isNotBlank(this.getSendMobile())) {
				params.put("sendMobile", this.getSendMobile());
			}

			if (StringUtils.isNotBlank(this.getReciContractName())) {
				params.put("reciContractName", "%" + this.getReciContractName() + "%");
			}

			if (StringUtils.isNotBlank(this.getReciMobile())) {
				params.put("reciMobile", this.getReciMobile());
			}

		} else {
			throw new RestException(ErrorCode.ERROR_900006);
		}

		params.put("isDeleted", Global.ISDEL_NORMAL.toString());

		// 关闭,取消,已配载已发车,已到达，已纸质回单，已关闭的订单不展示在货源列表里
		params.put("statusArray",
				new String[] { OrderType.CANCLED.getCode(), OrderType.CLOSED.getCode(), OrderType.WAIT_LOAD.getCode(),
						OrderType.LOADED.getCode(), OrderType.TRIPED.getCode(), OrderType.ON_PASSAGE.getCode(),
						OrderType.ARRIVED.getCode(), OrderType.PRINT_RECEIPT.getCode(),
						OrderType.ARRIVED_COLSED.getCode() });

		if (StringUtils.isNotBlank(this.getOrderStatus())) {

			params.put("orderStatus", this.getOrderStatus().split(","));
		}

		if (StringUtils.isNotBlank(this.getRetStatus())) {
			params.put("retStatus", this.getRetStatus());
		}

		if (StringUtils.isNotBlank(this.getSort())) {
			params.put("sort", this.getSort());

		} else {
			params.put("sort", "01");
		}

		return params;
	}

}
