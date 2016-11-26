package com.gzzhwl.admin.contract.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.contract.service.ContractService;
import com.gzzhwl.admin.contract.vo.ContractQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;

	/**
	 * 生成司机合同
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseModel createContract(@RequestParam String loadId) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		Integer departId = SecurityUtils.getSubject().getDepartId();
		String contractId = contractService.createContract(loadId, departId, staffId);
		return new ResponseModel(contractId);
	}

	/**
	 * 作废司机合同
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public ResponseModel cancelContract(@RequestParam String contractId) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		boolean success = contractService.cancelContract(contractId, staffId);
		return new ResponseModel(success);
	}

	/**
	 * 是否允许生成司机合同
	 */
	@RequestMapping(value = "/allow", method = RequestMethod.POST)
	public ResponseModel allowCreate(@RequestParam String loadId) {
		boolean allow = contractService.allowCreate(loadId);
		return new ResponseModel(allow);
	}
	
	/**
	 * 查询司机合同列表
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseModel pageContractList(ContractQueryVo vo, @Pagination PageParameter page) {
		Page<Map<String,Object>> result = contractService.pageContractList(vo, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(result);
	}
	
	/**
	 * 查询司机合同详情
	 */
	@RequestMapping(value = "/queryDetail", method = RequestMethod.GET)
	public ResponseModel queryContractDetail(@RequestParam String contractId) {
		Map<String,Object> result = contractService.queryContractDetail(contractId);
		return new ResponseModel(result);
	}
}
