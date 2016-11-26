package com.gzzhwl.admin.transit.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.transit.service.TransMapService;
import com.gzzhwl.admin.transit.vo.MapSearchVO;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.extdao.LoadFeedbackLogExtDao;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

@Service
public class TransMapServiceImpl implements TransMapService {
	@Autowired
	private LoadFeedbackLogExtDao loadFeedbackLogExtDao;
	@Autowired
	private LoadBillService loadBillService;

	@Override
	public Map<String, Object> mapSearch(MapSearchVO mapSearch) throws RestException {
		String type = mapSearch.getType();
		String billNo = mapSearch.getBillNo();
		ParamEmptyValidator.VALID_PARAM_EMPTY(type, billNo);
		String loadId = null;
		if (StringUtils.equals(type, "01")) {// 车牌号
			loadId = loadBillService.getLoadBillByVehicle(billNo);
		} else if (StringUtils.equals(type, "02")) {// 提货单号
			OrderLoadInfo orderLoadInfo = loadBillService.getLoadInfoByLoadNo(billNo);
			if (orderLoadInfo != null) {
				loadId = orderLoadInfo.getLoadId();
			}
		} else if (StringUtils.equals(type, "03")) {// 订单号
			loadId = loadBillService.getCurrentLoadBillByOrderNo(billNo);
		}
		if (StringUtils.isNotBlank(loadId)) {
			Map<String, Object> result = Maps.newHashMap();
			Map<String, Object> info = loadBillService.getLoadDetail(loadId);
			List<Map<String, Object>> trackData = this.getFeedBack(loadId);
			result.put("track", trackData);
			result.put("info", info);
			return result;
		} else {
			throw new RestException(ErrorCode.ERROR_29302);
		}
	}

	@Override
	public List<Map<String, Object>> getFeedBack(String loadId) throws RestException {
		if (StringUtils.isNotBlank(loadId)) {
			Map<String, Object> params = Maps.newHashMap();
			params.put("loadId", loadId);
			params.put("status", LoadBillType.DEPART.getCode());// 支查询已发车后的反馈
			params.put("isDeleted", Global.ISDEL_NORMAL.toString());
			params.put("ysjSource", DataSource.VLORRY.getCode());
			params.put("cbsSource", DataSource.PLATFORM.getCode());
			return loadFeedbackLogExtDao.findFeedBackOnTrans(params);
		} else {
			return Lists.newArrayList();
		}
	}
}
