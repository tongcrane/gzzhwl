package com.gzzhwl.api.load.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.context.request.async.DeferredResult;

import com.gzzhwl.api.load.vo.ReportGeoVo;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

public interface TripMapService {

	public Map<String, Object> findTrackInfo(String loadId) throws RestException;

	public List<Map<String, Object>> getFeedBack(String loadId) throws RestException;

	public DeferredResult<ResponseModel> saveAndgetGeoRecord(String loadId, String accountId) throws RestException;

	public boolean reportGeo(ReportGeoVo reportGeo, String accountId) throws RestException;

}
