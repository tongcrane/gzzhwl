package com.gzzhwl.api.line.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.api.line.service.LineService;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.rest.springmvc.annotation.Authorization;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

@RestController
@RequestMapping("/api/line")
public class LineController {

	@Autowired
	private LineService lineService;

	/**
	 * 添加线路
	 * 
	 * @param departureCode
	 * @param destinationCode
	 * @param driverInfoId
	 * @param accountInfo
	 * @return
	 */
	@RequestMapping(value = "/addLine", method = { RequestMethod.POST })
	public ResponseModel addLine(@RequestParam String departureCode, @RequestParam String destinationCode,
			@Authorization AccountInfo accountInfo) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(departureCode, destinationCode);
		String accountId = accountInfo.getAccountId();
		// if (StringUtils.isNotEmpty(driverInfoId)) {
		// lineService.saveLine(null, driverInfoId, departureCode,
		// destinationCode);
		// } else {
		lineService.saveLine(accountId, departureCode, destinationCode);
		// }
		return new ResponseModel(null);
	}

	/**
	 * 修改线路
	 * 
	 * @param departureCode
	 * @param destinationCode
	 * @param lineInfoId
	 * @param accountInfo
	 * @return
	 */
	@RequestMapping(value = "/updateLine", method = { RequestMethod.POST })
	public ResponseModel updateLine(@RequestParam String departureCode, @RequestParam String destinationCode,
			@RequestParam String lineInfoId, @Authorization AccountInfo accountInfo) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(departureCode, destinationCode, lineInfoId);
		lineService.updateLine(lineInfoId, null, accountInfo.getAccountId(), departureCode, destinationCode);
		return new ResponseModel(null);
	}

	/**
	 * 删除线路
	 * 
	 * @param lineInfoId
	 * @param accountInfo
	 * @return
	 */
	@RequestMapping(value = "/delLine", method = { RequestMethod.POST })
	public ResponseModel delLine(@RequestParam String lineInfoId, @Authorization AccountInfo accountInfo) {
		lineService.delLine(lineInfoId, null, accountInfo.getAccountId());
		return new ResponseModel(null);
	}

	/**
	 * 获取线路列表
	 * 
	 * @param driverInfoId
	 * @param accountInfo
	 * @return
	 */
	@RequestMapping(value = "/getLineList", method = { RequestMethod.GET })
	public ResponseModel getLineList(@Authorization AccountInfo accountInfo) {
		String accountId = accountInfo.getAccountId();
		List<Map<String, Object>> result = lineService.getLineList(accountId, null);
		return new ResponseModel(result);
	}
}
