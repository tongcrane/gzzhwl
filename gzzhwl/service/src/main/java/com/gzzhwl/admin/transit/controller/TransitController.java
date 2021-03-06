package com.gzzhwl.admin.transit.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.transit.service.TransitService;
import com.gzzhwl.admin.transit.vo.TransitQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/transit")
public class TransitController {
	@Autowired
	private TransitService service;

	@RequestMapping(value = "/page", method = RequestMethod.GET) 
	public ResponseModel pageTransitList(TransitQueryVo vo, @Pagination PageParameter page) throws ParseException {
		Page<Map<String, Object>> data = service.pageTransitList(vo, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(data);
		
	}
 	
	
	@RequestMapping(value = "/getDetail", method = RequestMethod.GET) 
	public ResponseModel getOrderDetail(@RequestParam String orderId){
		Map<String, Object> data = service.getTransitDetailByOrderId(orderId);
		return new ResponseModel(data);
		
	}
	
	@RequestMapping(value = "/getTransitDetail", method = RequestMethod.GET) 
	public ResponseModel getTransitDetail(@RequestParam String loadId){
		Map<String, Object> data = service.getTransitDetailByLoadId(loadId);
		return new ResponseModel(data);
		
	}
}
