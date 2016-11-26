package com.gzzhwl.admin.store.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.store.service.StoreService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.StoreAddrInfoDao;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.data.model.StoreAddrInfo;
import com.gzzhwl.rest.exception.RestException;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreAddrInfoDao storeAddrInfoDao;
	@Autowired
	private RegionService reginService;

	@Override
	public List<StoreAddrInfo> listStoreAddr(Integer departId, String staffId) throws RestException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("departId", departId);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		List<StoreAddrInfo> data = storeAddrInfoDao.find(params);
		if (CollectionUtils.isNotEmpty(data)) {
			for (StoreAddrInfo addrInfo : data) {
				addrInfo.setProvinceCn(this.getCodeCn(addrInfo.getProvinceCode()));
				addrInfo.setCityCn(this.getCodeCn(addrInfo.getCityCode()));
				addrInfo.setDistrictCn(this.getCodeCn(addrInfo.getDistrictCode()));
			}
		}
		return data;
	}

	private String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = reginService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public StoreAddrInfo getStoreAddr(String addrId) throws RestException {
		StoreAddrInfo addrInfo = storeAddrInfoDao.get(addrId);
		if (addrInfo != null) {
			addrInfo.setProvinceCn(this.getCodeCn(addrInfo.getProvinceCode()));
			addrInfo.setCityCn(this.getCodeCn(addrInfo.getCityCn()));
			addrInfo.setDistrictCn(this.getCodeCn(addrInfo.getDistrictCn()));
		}
		return addrInfo;
	}

}
