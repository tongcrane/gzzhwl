package com.gzzhwl.api.load.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzzhwl.api.load.service.TripMapService;
import com.gzzhwl.api.load.vo.ReportGeoVo;
import com.gzzhwl.common.service.DeferredResultStore;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.dao.AccountInfoDao;
import com.gzzhwl.core.data.dao.OrderLoadInfoDao;
import com.gzzhwl.core.data.extdao.LoadFeedbackLogExtDao;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.utils.JodaDateUtils;
import com.gzzhwl.core.utils.MD5;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.push.model.PushEvent;
import com.gzzhwl.push.model.PushItem;
import com.gzzhwl.push.service.PushService;
import com.gzzhwl.rest.exception.AsyncException;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@Service
public class TripMapServiceImpl implements TripMapService {
	@Autowired
	private LoadFeedbackLogExtDao loadFeedbackLogExtDao;
	@Autowired
	private OrderLoadInfoDao orderLoadInfoDao;
	@Autowired
	private RegionService regionService;
	@Autowired
	private DeferredResultStore<ResponseModel> deferredResultStore;
	@Autowired
	private PushService pushService;
	@Autowired
	private AccountInfoDao accountInfoDao;

	@Override
	public Map<String, Object> findTrackInfo(String loadId) throws RestException {
		Map<String, Object> info = this.getSourceBaseInfo(loadId);
		if (info != null) {
			Map<String, Object> result = Maps.newHashMap();
			List<Map<String, Object>> trackData = this.getFeedBack(loadId);
			result.put("track", trackData);
			result.put("info", info);
			return result;
		} else {
			return Maps.newHashMap();
		}
	}

	private Map<String, Object> getSourceBaseInfo(String loadId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("loadId", loadId);
		params.put("type", OrderLoadInfo.LOAD_BILL);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		Map<String, Object> result = orderLoadInfoDao.findBaseInfo(params);
		if (result != null) {
			String startCodePCn = this.getCodeCn((String) result.get("startCodeP"));
			result.put("startCodePCn", startCodePCn);
			String endCodePCn = this.getCodeCn((String) result.get("endCodeP"));
			result.put("endCodePCn", endCodePCn);
			String startCodeCCn = this.getCodeCn((String) result.get("startCodeC"));
			result.put("startCodeCCn", startCodeCCn);
			String endCodeCCn = this.getCodeCn((String) result.get("endCodeC"));
			result.put("endCodeCCn", endCodeCCn);

			String senderAreaCode = (String) result.get("senderAreaCode");
			List<RegionInfo> senderArea = regionService.findRootByCode(senderAreaCode);
			result.put("senderArea", senderArea);
			String receiverAreaCode = (String) result.get("receiverAreaCode");
			List<RegionInfo> receiverArea = regionService.findRootByCode(receiverAreaCode);
			result.put("receiverArea", receiverArea);

		}
		return result;
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

	public static void main(String[] args) {
		System.out.println(
				MD5.md5_short("12a6b8bc-1cb1-411f-8314-f9a6e3bfd966" + "b940a6b6-541c-4343-a708-12d5a83dc7a5"));
	}

	@Override
	public DeferredResult<ResponseModel> saveAndgetGeoRecord(String loadId, String accountId) throws RestException {
		String tokenId = MD5.md5_short(loadId + accountId);
		boolean exists = deferredResultStore.has(tokenId);
		if (exists) {
			DeferredResult<ResponseModel> deferredResult = deferredResultStore.getAndRemove(tokenId);
			if (deferredResult != null) {
				deferredResult.setErrorResult(new AsyncException());
			}
		}
		DeferredResult<ResponseModel> deferredResult = new DeferredResult<ResponseModel>(10 * 1000);
		deferredResultStore.store(tokenId, deferredResult);
		Map<String, Object> content = Maps.newHashMap();
		content.put("tokenId", tokenId);
		PushItem item = new PushItem(PushEvent.LOCATION, content);

		// TODO:根据loadId获取主司机的accountId
		List<Map<String, Object>> accountIdMap = accountInfoDao.getAccountIdByLoadId(loadId);

		if (accountIdMap.size() > 1 || ValidateUtils.isEmpty(accountIdMap)) {
			// throw new RestException(ErrorCode.ERROR_900010.getCode(),
			// ErrorCode.ERROR_900010.getDesc());
		} else {
			String driverAccountId = (String) accountIdMap.get(0).get("accountId");
			pushService.pushToSingleUser(item, driverAccountId);
		}
		//TODO 为节约executor资源，此处可以调优。
		return deferredResult;

	}

	@Override
	public boolean reportGeo(ReportGeoVo reportGeo, String accountId) throws RestException {
		String tokenId = reportGeo.getTokenId();
		DeferredResult<ResponseModel> deferredResult = deferredResultStore.getAndRemove(tokenId);
		if (deferredResult != null) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("longitude", reportGeo.getLongitude());
			result.put("latitude", reportGeo.getLatitude());
			result.put("speed", reportGeo.getSpeed());
			result.put("feedbackTime", JodaDateUtils.currentStringDate("yyyy-MM-dd HH:mm"));
			deferredResult.setResult(new ResponseModel(result));
		}
		return true;
	}
}
