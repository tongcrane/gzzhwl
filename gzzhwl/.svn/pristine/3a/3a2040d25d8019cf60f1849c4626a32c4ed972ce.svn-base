package com.gzzhwl.api.region.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.data.model.Region;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/api/region")
public class RegionController {
	@Autowired
	private RegionService regionService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseModel getAll() throws IOException {
		Region region = regionService.findRegion();
		return new ResponseModel(region);
	}

	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public ResponseModel findById(@RequestParam("parentId") Integer parentId) throws IOException {
		Region region = regionService.findRegion(parentId);
		if (region != null) {
			return new ResponseModel(region.getSubRegion());
		} else {
			return new ResponseModel(Lists.newArrayList());
		}
	}

	@RequestMapping(value = "/findByCode", method = RequestMethod.GET)
	public ResponseModel findByCode(@RequestParam("regionCode") String regionCode) throws IOException {
		RegionInfo regionInfo = regionService.findByCode(regionCode);
		Integer parentId = regionInfo.getRegionId();
		Region region = regionService.findRegion(parentId);
		if (region != null) {
			return new ResponseModel(region.getSubRegion());
		} else {
			return new ResponseModel(Lists.newArrayList());
		}
	}

	@RequestMapping(value = "/findRootByCode", method = RequestMethod.GET)
	public ResponseModel findRootByCode(@RequestParam String regionCode) throws IOException {
		List<RegionInfo> region = regionService.findRootByCode(regionCode);
		return new ResponseModel(region);
	}
}
