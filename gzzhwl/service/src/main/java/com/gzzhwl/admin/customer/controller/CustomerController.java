package com.gzzhwl.admin.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.customer.service.CustomerInfoService;
import com.gzzhwl.admin.customer.vo.CustomerInfoVo;
import com.gzzhwl.admin.customer.vo.CustomerQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/customer")
public class CustomerController {
	@Autowired
	CustomerInfoService service;
	@Autowired
	Mapper beanMapper;
	
	
	/**
	 * 生成客户编号
	 * @param seqType
	 * @return
	 */
//	@RequestMapping(value = "/getCustno", method = RequestMethod.POST)
//	public ResponseModel getSeqNo() {
//		String custno = service.createCustno();
//		return new ResponseModel(custno);
//	}
	
	/**
	 * 保存客户信息
	 * @param cust
	 * @return
	 */
	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public ResponseModel saveCustomer(CustomerInfoVo custVo) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		String departId = "";
		Integer depId = SecurityUtils.getSubject().getDepartId();
		if(depId != null) {
			departId = depId.toString();
		}
		String custId = service.saveCust(custVo, staffId,departId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("custId", custId);
		return new ResponseModel(map);
	}

	/**
	 * 删除客户信息
	 * @param cust
	 * @return
	 */
	@RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
	public ResponseModel removeCustomer(@RequestParam String custId) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		service.removeCustomer(custId, staffId);
		return new ResponseModel(null);
	}
	
	/**
	 * 更新客户信息
	 * @param cust
	 * @return
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public ResponseModel updateCustomer(CustomerInfoVo custVo) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		service.updateCustomer(custVo, staffId);
		return new ResponseModel(null);
	}
	
	/**
	 * 获取客户分页信息
	 */
	@RequestMapping(value = "/pageCustList", method = RequestMethod.GET)
	public ResponseModel queryCustList(CustomerQueryVo queryVo,@Pagination PageParameter page) {
		Page<Map<String,Object>> data = service.pageCustList(queryVo,page.getPageIndex(),page.getPageSize());
		return new ResponseModel(data);		
	}
	
	/**
	 * 获取客户详细信息
	 * @param cust
	 * @return
	 */
	@RequestMapping(value = "/queryCustDetail", method = RequestMethod.GET)
	public ResponseModel queryCustDetail(@RequestParam String custId) {
		CustomerInfoVo custVo = service.queryCustDetail(custId);
		return new ResponseModel(custVo);
	}
	
	/**
	 * 获取客户列表
	 * @param cust
	 * @return
	 */
	@RequestMapping(value = "/queryCustList", method = RequestMethod.GET)
	public ResponseModel queryCustDetail() {
		List<Map<String,Object>> custs = service.queryCustList();
		return new ResponseModel(custs);
	}
}
