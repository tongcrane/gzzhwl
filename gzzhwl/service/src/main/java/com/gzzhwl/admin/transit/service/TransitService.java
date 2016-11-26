package com.gzzhwl.admin.transit.service;

import java.text.ParseException;
import java.util.Map;

import com.gzzhwl.admin.transit.vo.TransitQueryVo;
import com.gzzhwl.core.page.Page;

public interface TransitService {
	/*
	 *获取在途列表
	 */
	public Page<Map<String, Object>> pageTransitList(TransitQueryVo vo, int currentPage, int pageSize) throws ParseException;

	/*
	 * 获取在途车辆详情
	 */
	//public Map<String, Object> getOrderDetail(String orderId);

	/*
	 * 获取在途详情
	 */
	//public Map<String, Object> getTransitDetail(String loadId, String orderId);

	public Map<String, Object> getTransitDetailByOrderId(String orderId);

	public Map<String, Object> getTransitDetailByLoadId(String loadId);
}
