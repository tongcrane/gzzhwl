package com.gzzhwl.admin.flows.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/flow")
public class FlowsController {
	@Autowired
	private FlowsService service;

	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	public ResponseModel getStatus(@RequestParam(required = true) String statusType) {
		return new ResponseModel(service.getStatus(ZHFlow.getZHFlow(statusType)));
	}

}
