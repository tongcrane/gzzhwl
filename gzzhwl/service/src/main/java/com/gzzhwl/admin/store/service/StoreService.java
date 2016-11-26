package com.gzzhwl.admin.store.service;

import java.util.List;

import com.gzzhwl.core.data.model.StoreAddrInfo;
import com.gzzhwl.rest.exception.RestException;

public interface StoreService {

	public List<StoreAddrInfo> listStoreAddr(Integer departId, String staffId) throws RestException;

	public StoreAddrInfo getStoreAddr(String addrId) throws RestException;

}
