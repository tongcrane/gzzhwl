package com.gzzhwl.admin.orderReturn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.orderReturn.service.OrderReturnService;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/orderReturn")
public class OrderReturnController {
	@Autowired
	private OrderReturnService orderReturnService;

	@RequirePerm
	@RequestMapping(value = "/pageList", method = { RequestMethod.GET })
	public ResponseModel pageOrderReturnList(String queryContent,String status, @Pagination PageParameter page) {
		Page<Map<String, Object>> data = orderReturnService.pageOrderSourceList(queryContent,status, page.getPageIndex(),
				page.getPageSize());
		return new ResponseModel(data);
	}

	/**
	 * 同意申请退回
	 */
	@RequirePerm
	@RequestMapping(value = "/agree", method = { RequestMethod.POST })
	public ResponseModel agreeReturn(@RequestParam String sourceId) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = orderReturnService.agreeReturn(sourceId, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 不同意申请退回
	 */
	@RequirePerm
	@RequestMapping(value = "/disagree", method = { RequestMethod.POST })
	public ResponseModel disagreeReturn(@RequestParam String sourceId) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = orderReturnService.disAgreeReturn(sourceId, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 按钮列表
	 */
	@RequirePerm
	@RequestMapping(value = "/wtd", method = { RequestMethod.GET })
	public ResponseModel whatToDo(@RequestParam String sourceId) {
		List<Map<String, Object>> data = orderReturnService.whatToDo(sourceId);
		return new ResponseModel(data);
	}

}
