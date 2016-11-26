package com.gzzhwl.api.record.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.remark.service.RemarkInfoService;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.RemarkType;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.annotation.Authorization;
import com.gzzhwl.rest.springmvc.model.ResponseModel;


@RestController
@RequestMapping("/api/record")
public class RecordController {
	
	@Autowired
	private RemarkInfoService remarkService;
	
	/**
	 * 获取操作记录
	 * @param targetId
	 * @param remarkType
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getRecordList", method = RequestMethod.GET)
	private ResponseModel getRecordList(@RequestParam(required=true)String targetId,@RequestParam(required=true)String remarkType,@Authorization AccountInfo accountInfo) throws ParseException {
		
		RemarkType remarkTypeEnmu = RemarkType.getRemarkType(remarkType);
	
		if(!remarkTypeEnmu.equals(RemarkType.ONLINE)){
			throw new RestException(ErrorCode.ERROR_900014.getCode(),ErrorCode.ERROR_900014.getDesc());
		}
		
		List<Map<String,Object>> listMap = remarkService.getRecordList(targetId, remarkTypeEnmu);
		
		return new ResponseModel(listMap);
	}

}
