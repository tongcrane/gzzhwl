package com.gzzhwl.common.service;

import java.util.List;

import com.gzzhwl.core.data.model.Region;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.rest.exception.RestException;

public interface RegionService {

	public Region findRegion() throws RestException;

	public Region findRegion(Integer parentId) throws RestException;

	public List<RegionInfo> findRootByCode(String regionCode) throws RestException;

	public RegionInfo findByCode(String regionCode) throws RestException;

	public RegionInfo findById(Integer regionId) throws RestException;

}
