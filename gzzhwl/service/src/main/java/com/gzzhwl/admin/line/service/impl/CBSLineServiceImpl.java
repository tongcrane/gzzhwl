package com.gzzhwl.admin.line.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.line.service.CBSLineService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.LineInfoDao;
import com.gzzhwl.core.data.model.RegionInfo;

@Service
public class CBSLineServiceImpl implements CBSLineService {
	@Autowired
	private LineInfoDao lineInfoDao;
	@Autowired
	private RegionService regionService;

	@Override
	public List<Map<String, Object>> getLineList(String driverInfoId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("driverInfoId", driverInfoId);
		List<Map<String, Object>> result = lineInfoDao.find(params);
		for (Map<String, Object> map : result) {
			String departureCode = map.get("departureCode").toString();
			String destinationCode = map.get("destinationCode").toString();
			RegionInfo depInfo = regionService.findByCode(departureCode);
			RegionInfo desInfo = regionService.findByCode(destinationCode);
			map.put("departureCode", depInfo.getRegionName());
			map.put("destinationCode", desInfo.getRegionName());
		}
		return result;
	}

}
