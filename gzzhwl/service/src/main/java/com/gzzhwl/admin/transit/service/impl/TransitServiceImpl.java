package com.gzzhwl.admin.transit.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadFeedbackService;
import com.gzzhwl.admin.transit.service.TransitService;
import com.gzzhwl.admin.transit.vo.TransitQueryVo;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.constant.OrderType;
import com.gzzhwl.core.data.extdao.OrderInfoExtDao;
import com.gzzhwl.core.data.extdao.OrderLoadInfoExtDao;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.ValidateUtils;

@Service
public class TransitServiceImpl implements TransitService{
	@Autowired
	private OrderInfoExtDao orderExtDao;
	@Autowired
	private OrderLoadInfoExtDao loadExtDao;
	@Autowired
	private RegionService regionService;
	@Autowired
	private LoadFeedbackService loadFeedbackService;
	@Autowired
	private LoadBillService loadBillService;

	@Override
	public Page<Map<String, Object>> pageTransitList(TransitQueryVo vo, int currentPage, int pageSize) throws ParseException {
		Map<String, Object> params = vo.getParam();
		Page<Map<String, Object>> data = orderExtDao.pageTransitList(params, currentPage, pageSize);
		
		List<Map<String, Object>> list = data.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map<String, Object> map : list) {
				String startCodeP = (String) map.get("startCodeP");
				map.put("startCodePCn", this.getCodeCn(startCodeP));

				String startCodeC = (String) map.get("startCodeC");
				map.put("startCodeCCn", this.getCodeCn(startCodeC));

				String endCodeP = (String) map.get("endCodeP");
				map.put("endCodePCn", this.getCodeCn(endCodeP));

				String endCodeC = (String) map.get("endCodeC");
				map.put("endCodeCCn", this.getCodeCn(endCodeC));
				
				// 获取发车司机信息
				String loadId = (String) map.get("loadId");
				List<Map<String, Object>> drivers = loadExtDao.getTripDrivers(loadId);

				map.put("driverList", drivers);
			}
		}
		
		return data;
	}
	
	public String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = regionService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

//	@Override
//	public Map<String, Object> getOrderDetail(String orderId) {
//		Map<String, Object> params = Maps.newHashMap();
//		params.put("orderId", orderId);
//		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
//		params.put("loadType", OrderLoadInfo.LOAD_BILL);
//		params.put("status", OrderType.ON_PASSAGE.toString());
//		Map<String, Object> data = orderExtDao.getTransitOrderDetail(params);
//		if(!ValidateUtils.isEmpty(data)) {
//			String startCodeP = (String) data.get("startCodeP");
//			data.put("startCodePCn", this.getCodeCn(startCodeP));
//	
//			String startCodeC = (String) data.get("startCodeC");
//			data.put("startCodeCCn", this.getCodeCn(startCodeC));
//	
//			String endCodeP = (String) data.get("endCodeP");
//			data.put("endCodePCn", this.getCodeCn(endCodeP));
//	
//			String endCodeC = (String) data.get("endCodeC");
//			data.put("endCodeCCn", this.getCodeCn(endCodeC));
//		}
//		return data;
//	}
	
//	@Override
//	public Map<String, Object> getTransitDetail(String loadId, String orderId) {
//		Map<String, Object> result = Maps.newHashMap();
//		Map<String, Object> params = Maps.newHashMap();
//		params.put("orderId", orderId);
//		params.put("loadId", loadId);
//		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
//		params.put("loadType", OrderLoadInfo.LOAD_BILL);
//		params.put("status", OrderType.ON_PASSAGE.toString());
//		Map<String, Object> orderMap = orderExtDao.getTransitOrderDetail(params);
//		if(!ValidateUtils.isEmpty(orderMap)) {
//			String startCodeP = (String) orderMap.get("startCodeP");
//			orderMap.put("startCodePCn", this.getCodeCn(startCodeP));
//	
//			String startCodeC = (String) orderMap.get("startCodeC");
//			orderMap.put("startCodeCCn", this.getCodeCn(startCodeC));
//	
//			String endCodeP = (String) orderMap.get("endCodeP");
//			orderMap.put("endCodePCn", this.getCodeCn(endCodeP));
//	
//			String endCodeC = (String) orderMap.get("endCodeC");
//			orderMap.put("endCodeCCn", this.getCodeCn(endCodeC));
//			
//			loadId = (String) orderMap.get("loadId");
//		}
//		result.put("orderInfo", orderMap);
//		
//		if(StringUtils.isNotBlank(loadId)) {
//			List<Map<String,Object>> resList = loadFeedbackService.getTrackList(loadId);
//			result.put("feedBackList", resList);
//		}else {
//			result.put("feedBackList", null);
//		}
//		
//		return result;
//	}

	@Override
	public Map<String, Object> getTransitDetailByOrderId(String orderId) {
		Map<String, Object> result = Maps.newHashMap();
		Map<String, Object> params = Maps.newHashMap();
		params.put("orderId", orderId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("loadType", OrderLoadInfo.LOAD_BILL);
		params.put("status", OrderType.ON_PASSAGE.toString());
		String loadId = null;
		Map<String, Object> orderMap = orderExtDao.getTransitOrderDetail(params);
		if(!ValidateUtils.isEmpty(orderMap)) {
			String startCodeP = (String) orderMap.get("startCodeP");
			orderMap.put("startCodePCn", this.getCodeCn(startCodeP));
	
			String startCodeC = (String) orderMap.get("startCodeC");
			orderMap.put("startCodeCCn", this.getCodeCn(startCodeC));
	
			String endCodeP = (String) orderMap.get("endCodeP");
			orderMap.put("endCodePCn", this.getCodeCn(endCodeP));
	
			String endCodeC = (String) orderMap.get("endCodeC");
			orderMap.put("endCodeCCn", this.getCodeCn(endCodeC));
			
			loadId = (String) orderMap.get("loadId");
		}
		result.put("orderInfo", orderMap);
		
		if(StringUtils.isNotBlank(loadId)) {
			List<Map<String,Object>> resList = loadFeedbackService.getTrackList(loadId);
			result.put("feedBackList", resList);
		}else {
			result.put("feedBackList", null);
		}
		
		return result;
	}

	@Override
	public Map<String, Object> getTransitDetailByLoadId(String loadId) {
		Map<String, Object> result = Maps.newHashMap();
		Map<String, Object> loadVehicleMap = loadBillService.getLoadVehcleDetail(loadId);
		result.put("loadVehicleInfo", loadVehicleMap);
		
		if(StringUtils.isNotBlank(loadId)) {
			List<Map<String,Object>> resList = loadFeedbackService.getTrackList(loadId);
			result.put("feedBackList", resList);
		}else {
			result.put("feedBackList", null);
		}
		
		return result;
	}

}
