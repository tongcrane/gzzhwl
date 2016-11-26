package com.gzzhwl.tms.service;

import com.gzzhwl.core.data.model.RegionInfo;

public interface TMSRegionService {

	public RegionInfo getRegionInfoParent(String name, Integer parentRegionId, Integer level);
}
