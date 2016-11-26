package com.gzzhwl.admin.transit.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.gzzhwl.admin.transit.service.TransMapService;
import com.gzzhwl.admin.transit.vo.MapSearchVO;
import com.gzzhwl.api.load.service.TripMapService;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/maptrans")
public class TransMapController {
	@Autowired
	private TransMapService transMapService;
	@Autowired
	private TripMapService tripMapService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel pageTransitList(MapSearchVO mapSearch) throws ParseException {
		Map<String, Object> data = transMapService.mapSearch(mapSearch);
		return new ResponseModel(data);
	}

	/**
	 * 获取当前位置
	 */
	@RequestMapping(value = "/getgeo", method = RequestMethod.GET)
	public DeferredResult<ResponseModel> getGeo(@RequestParam String loadId) {
		Subject subject = SecurityUtils.getSubject();
		return tripMapService.saveAndgetGeoRecord(loadId, subject.getStaffId());
	}
}
