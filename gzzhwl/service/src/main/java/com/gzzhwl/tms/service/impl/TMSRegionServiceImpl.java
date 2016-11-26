package com.gzzhwl.tms.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.common.service.H2DbService;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.tms.service.TMSRegionService;

@Service
public class TMSRegionServiceImpl implements TMSRegionService {
	@Autowired
	private H2DbService h2DbService;

	@Override
	public RegionInfo getRegionInfoParent(String name, Integer parentRegionId, Integer level) {
		if (StringUtils.isNotBlank(name)) {
			String value = this.getName(name, level);
			List<RegionInfo> result = h2DbService.findRegion(value, parentRegionId, level);
			if (CollectionUtils.isNotEmpty(result)) {
				if (result.size() > 1) {
					return null;
				} else {
					return result.get(0);
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private String getName(String name, Integer level) {
		return StringUtils.left(name, 2);
	}
}
