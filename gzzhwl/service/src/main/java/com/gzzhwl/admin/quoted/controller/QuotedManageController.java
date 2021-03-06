package com.gzzhwl.admin.quoted.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.quoted.service.QuotedManageService;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/quotedmanage")
public class QuotedManageController {
	
	@Autowired
	private QuotedManageService quotedManageService;
	
	
	/**
	 * 报价列表
	 * @param sourceId
	 * @param status
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/quotedManagerList", method = RequestMethod.POST)
	private ResponseModel queryQuotedManagerList(@RequestParam(required=true)String sourceId,@Pagination PageParameter page) throws ParseException {
		
		Page<Map<String,Object>> paraMap = quotedManageService.queryQuotedManagerList(sourceId, page.getPageIndex(), page.getPageSize());

		return new ResponseModel(paraMap);
	}
	
	/**
	 * 获取报价列表（全部）
	 */
	@RequestMapping(value = "/queryAllQuotedList", method = RequestMethod.GET)
	private ResponseModel queryAllQuotedList(@RequestParam(required=true)String sourceId,@Pagination PageParameter page) throws ParseException {
		
		Page<Map<String,Object>> paraMap = quotedManageService.queryAllQuotedList(sourceId, page.getPageIndex(), page.getPageSize());

		return new ResponseModel(paraMap);
	}
	
	/**
	 * 获取中标信息
	 */
	@RequestMapping(value = "/getBidQuotedInfo", method = RequestMethod.GET)
	private ResponseModel getBidInfo(@RequestParam(required=true)String sourceId) throws ParseException {
		
		Map<String,Object> paraMap = quotedManageService.getBidQuotedInfo(sourceId);
		Map<String, Object> map = Maps.newHashMap();
		map.put("bidInfo", paraMap);

		return new ResponseModel(paraMap);
	}
	
	
	/**
	 * 获取运输车辆信息
	 * @param sourceId
	 * @param status
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getBidVehicleInfo", method = RequestMethod.POST)
	private ResponseModel getBidVehicleInfo(@RequestParam(required=true)String sourceId) throws ParseException {
		
		Map<String,Object> paraMap = quotedManageService.getBidVehicleInfo(sourceId);
		return new ResponseModel(paraMap);
	}
	
	
	/**
	 * 关闭报价
	 * @param quotedId
	 * @param accountInfo
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/closeQuotedInfo", method = RequestMethod.POST)
	private ResponseModel closeQuotedInfo(@RequestParam(required=true)String quotedId,@RequestParam(required=false)String remark) throws ParseException {
		
		String staffId = SecurityUtils.getSubject().getStaffId();
		
		String quotedInfoId = quotedManageService.closeQuotedInfo(quotedId, remark, staffId);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("quotedInfoId", quotedInfoId);
		
		return new ResponseModel(resMap);
	}
	
	/**
	 * 报价作废
	 * @param quotedId
	 * @param accountInfo
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/invalidTheBid", method = RequestMethod.POST)
	private ResponseModel invalidTheBid(@RequestParam(required=true)String quotedId,@RequestParam(required=false)String remark) throws ParseException {
		
		String staffId = SecurityUtils.getSubject().getStaffId();
		
		String quotedInfoId = quotedManageService.invalidTheBid(quotedId, remark, staffId);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("quotedInfoId", quotedInfoId);
		
		return new ResponseModel(resMap);
	}
	
	/**
	 * 中标
	 * @param quotedId
	 * @param accountInfo
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/winTheBid", method = RequestMethod.POST)
	private ResponseModel winTheBid(@RequestParam(required=true)String quotedId) throws ParseException {
		
		String staffId = SecurityUtils.getSubject().getStaffId();
		
		String quotedInfoId = quotedManageService.winTheBid(quotedId, staffId);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("quotedInfoId", quotedInfoId);
		
		return new ResponseModel(resMap);
	}
	
	
}
