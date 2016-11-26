package com.gzzhwl.admin.line.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.line.service.CBSLineService;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/line")
public class CBSLineController {

	@Autowired
	private CBSLineService lineService;
	
	/**
	 * 获取线路列表
	 * @param driverInfoId
	 * @param accountInfo
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/getLineList", method = { RequestMethod.GET })
	public ResponseModel getLineList(@RequestParam String driverInfoId) {
		List<Map<String,Object>> result=lineService.getLineList(driverInfoId);
		return new ResponseModel(result);
	}
}
