package com.gzzhwl.admin.order.validate;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.admin.order.vo.OrderVO;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class OrderValidate {
	//订单必输项及格式校验
	public static void valid_order(OrderVO vo) {
		if(StringUtils.isBlank(vo.getBaseInfo().getOrderType())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "订单类型" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getSender().getCustomerId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "请选择客户。");
		} 
//		
//		if(StringUtils.isBlank(vo.getBaseInfo().getAgreementId())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(), "客户合同" + ErrorCode.ERROR_900003.getDesc());
//		} 
//		
		if(StringUtils.isBlank(vo.getLineInfo().getStartCodeP())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "线路（启）-省" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getLineInfo().getStartCodeC())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "线路（启）-市" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getLineInfo().getEndCodeP())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "线路（终）-省" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getLineInfo().getEndCodeC())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "线路（终）-市" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		
		if(StringUtils.isBlank(vo.getBaseInfo().getLineAttribute())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "线路属性" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getNeedType())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "车型要求" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getNeedLength())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "车长要求" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getNeedImportedVehicles())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "是否要求进口车" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getNeedOwnVehicles())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "是否允许外请" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isNotBlank(vo.getBaseInfo().getCustomerBillNo())) {
			if(ValidateUtils.length(vo.getBaseInfo().getCustomerBillNo()) > 20) {
				throw new RestException(ErrorCode.ERROR_900004.getCode(), "客户单号长度不得超过20位。");
			}
		}
		
		if(StringUtils.isBlank(vo.getSender().getContactName())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "发货联系人" + ErrorCode.ERROR_900003.getDesc());
		}
		
//		if(StringUtils.isBlank(vo.getSender().getAddress())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(), "发货人地址" + ErrorCode.ERROR_900003.getDesc());
//		} 
//		
//		if(StringUtils.isBlank(vo.getSender().getAreaCode())){
//			throw new RestException(ErrorCode.ERROR_900003.getCode(), "发货人地址行政区域码" + ErrorCode.ERROR_900003.getDesc());
//		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getAddrId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "货场地址" + ErrorCode.ERROR_900003.getDesc());
		}
		
		if(StringUtils.isBlank(vo.getSender().getMobile()) && StringUtils.isBlank(vo.getSender().getTelphone())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "发货人手机，发货人电话必填其一。");
		}
		
		if(StringUtils.isNotBlank(vo.getSender().getMobile())) {
			if(!ValidateUtils.isMobileNo(vo.getSender().getMobile())) {
				throw new RestException(ErrorCode.ERROR_900004.getCode(), "发货人手机" + ErrorCode.ERROR_900004.getDesc());
			}
		}
		
		if(StringUtils.isBlank(vo.getReceiver().getContactName())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "收货联系人" + ErrorCode.ERROR_900003.getDesc());
		}
		
		if(StringUtils.isBlank(vo.getReceiver().getAddress())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "收货人地址" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getReceiver().getAreaCode())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "收货人地址行政区域码" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getReceiver().getMobile()) && StringUtils.isBlank(vo.getSender().getTelphone())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "收货人手机，发货人电话必填其一。");
		} 
		
		if(StringUtils.isNotBlank(vo.getReceiver().getMobile())) {
			if(!ValidateUtils.isMobileNo(vo.getReceiver().getMobile())) {
				throw new RestException(ErrorCode.ERROR_900004.getCode(), "收货人手机" + ErrorCode.ERROR_900004.getDesc());
			}
		}
		
		if(StringUtils.isBlank(vo.getBaseInfo().getGoodsName())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "货物品名" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		
		if(StringUtils.isBlank(vo.getBaseInfo().getUnitPrice())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "单价金额" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(!ValidateUtils.isMoney(vo.getBaseInfo().getUnitPrice())) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "单价金额有误，可精确到小数点后两位。");
		}
		
		if(StringUtils.isBlank(vo.getBaseInfo().getGoodsWeight())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "预估重量(kg)" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(!ValidateUtils.validWeight(vo.getBaseInfo().getGoodsWeight())) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "预估重量(kg)输入有误，5位整数及1位小数。" );
		}
		
		if(StringUtils.isBlank(vo.getBaseInfo().getGoodsVolume())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "预估体积(m³)" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(!ValidateUtils.validVolumn(vo.getBaseInfo().getGoodsVolume())) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "预估体积(m³)输入有误，3位整数及1位小数。" );
		}
		
		if(StringUtils.isBlank(vo.getBaseInfo().getOrderAdvice())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "回单要求" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getNeedStartTime())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "计划发车时间" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getNeedArriveTime())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "要求到达时间" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getPickUpTime())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "提货时间" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(StringUtils.isBlank(vo.getBaseInfo().getChargeId())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "请选择计价方式。");
		} 

		
		if(StringUtils.isBlank(vo.getBaseInfo().getMiles())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "公里数（km）" + ErrorCode.ERROR_900003.getDesc());
		} 
		
		if(!ValidateUtils.validCode(vo.getBaseInfo().getMiles())) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "公里数（km）输入有误，可精确到小数点后一位。" );
		}
		
		if(StringUtils.isBlank(vo.getBaseInfo().getPaymentType())){
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "结算方式" + ErrorCode.ERROR_900003.getDesc());
		} 	
	} 
	
	public static void valid_order_id(String orderId) {
		if(StringUtils.isBlank(orderId)) {
			throw new RestException(ErrorCode.ERROR_300002.getCode(), ErrorCode.ERROR_300002.getDesc());
		}
	}
}
