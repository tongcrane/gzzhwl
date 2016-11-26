package com.gzzhwl.admin.remark.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.remark.service.RemarkInfoService;
import com.gzzhwl.core.constant.RemarkType;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/remark")
public class RemarkController {
	@Autowired
	private RemarkInfoService remarkService;

	/**
	 * 添加备注
	 * @param quotedId
	 * @param accountInfo
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/addRemark", method = RequestMethod.POST)
	private ResponseModel addRemark(@RequestParam(required=true)String targetId,@RequestParam(required=true)String remarkType,@RequestParam(required=true)String content) throws ParseException {
		
		String staffId = SecurityUtils.getSubject().getStaffId();
		
		RemarkType remarkTypeEnmu = RemarkType.getRemarkType(remarkType);
		
		remarkService.saveRemark(targetId, remarkTypeEnmu, content, staffId);
		
		return new ResponseModel(null);
	}
	
	/**
	 * 获取操作记录
	 * @param targetId
	 * @param remarkType
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getRecordList", method = RequestMethod.GET)
	private ResponseModel getRecordList(@RequestParam(required=true)String targetId,@RequestParam(required=true)String remarkType) throws ParseException {
		
		RemarkType remarkTypeEnmu = RemarkType.getRemarkType(remarkType);
		
		List<Map<String,Object>> listMap = remarkService.getRecordList(targetId, remarkTypeEnmu);
		
		return new ResponseModel(listMap);
	}
	
}
