package com.gzzhwl.admin.consignment.service;

import java.text.ParseException;
import java.util.Map;

import com.gzzhwl.admin.consignment.vo.ConsignmentInfoVo;
import com.gzzhwl.admin.consignment.vo.consignQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;

public interface ConsignmentInfoService {
	// 生成合同
	public String createConsignment(ConsignmentInfoVo consignmentInfoVo, Integer departId, String staffId);

	// 运单合同分页查询
	public Page<Map<String, Object>> pageConsignList(consignQueryVo queryVo, int currentPage, int pageSize)  throws ParseException;

	// 查询运单明细
	public Map<String, Object> getConsignDetail(String consignId);

	// 根据订单Id查询运单
	public Map<String, Object> getConsignByOrderId(String orderId);

	/**
	 * 根据订单ID获取当前的运单
	 */
	public String getCurrentConsign(String orderId) throws RestException;

	// 取消运单合同
	public void cancleConsign(String consignId, String staffId);

	//发车时自动生成运单
	public String createConsignment(String orderId,String customerBillNo,String goodsVolume,String goodsWeight, Integer departId, String staffId);

	//计算运费
	public String getFreightPrice(String chargeId, String unitPrice, String miles, String goodsVolume, String goodsWeight);

	public Map<String, Object> getConsignInfo(String orderId);

	//根据consignId获取订单概要信息（应收详情用）
	public Map<String, Object> getOrderGeneralInfo(String consignId);
	
	//运单等待审核
	public boolean waitVerify(String consignId,  String staffId);
	
	//运单审核通过
	public boolean doVerified(String consignId,  String staffId);
}
