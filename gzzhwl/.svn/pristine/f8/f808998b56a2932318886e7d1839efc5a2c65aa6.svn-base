package com.gzzhwl.admin.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.store.service.StoreService;
import com.gzzhwl.core.data.model.StoreAddrInfo;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/store")
public class StoreController {
	@Autowired
	private StoreService storeService;

	@RequirePerm
	@RequestMapping(value = "/addr", method = { RequestMethod.GET })
	public ResponseModel listStoreAddr() {
		Subject subject = SecurityUtils.getSubject();
		Integer departId = subject.getDepartId();
		List<StoreAddrInfo> data = storeService.listStoreAddr(departId, subject.getStaffId());
		return new ResponseModel(data);
	}
}
