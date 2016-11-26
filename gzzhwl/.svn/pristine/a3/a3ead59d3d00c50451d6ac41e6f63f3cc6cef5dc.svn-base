package com.gzzhwl.admin.supply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.supply.service.SupplyService;
import com.gzzhwl.admin.supply.vo.SupplyInfoVo;
import com.gzzhwl.admin.supply.vo.SupplyQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/supply")
public class SupplyController {
	@Autowired
	private SupplyService service;

	/**
	 * 新增供货商
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/saveSupply", method = RequestMethod.POST)
	public ResponseModel saveSupply(SupplyInfoVo vo) {
		Subject subject = SecurityUtils.getSubject();
		String supplyId = service.saveSupplyVo(vo, subject.getStaffId(), subject.getDepartId());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("supplyId", supplyId);
		return new ResponseModel(data);
	}

	/**
	 * 更新供货商
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/updateSupply", method = RequestMethod.POST)
	public ResponseModel updateSupply(SupplyInfoVo vo) {
		Subject subject = SecurityUtils.getSubject();
		String supplyId = service.updateSupply(vo, subject.getStaffId());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("supplyId", supplyId);
		return new ResponseModel(data);
	}

	/**
	 * 删除供货商信息
	 * 
	 * @param supplyId
	 * @return
	 */
	@RequestMapping(value = "/removeSupply", method = RequestMethod.POST)
	public ResponseModel removeSupply(@RequestParam String supplyId) {
		Subject subject = SecurityUtils.getSubject();
		service.removeSupply(supplyId, subject.getStaffId());
		return new ResponseModel(null);
	}

	/**
	 * 查询供货商列表
	 * 
	 * @param keyWord
	 * @return
	 */
	@RequestMapping(value = "/getSupplyList", method = RequestMethod.GET)
	public ResponseModel getSupplyList(@RequestParam(required = false) String keyWord) {
		List<Map<String, Object>> data = service.getSupplyList(keyWord);
		return new ResponseModel(data);
	}
	
	/**
	 *获取供应商分页信息
	 * @param queryVo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/pageSupplyList", method = RequestMethod.GET)
	public ResponseModel getSupplyList(SupplyQueryVo queryVo, @Pagination PageParameter page) {
		Page<Map<String, Object>> data = service.pageSupplyList(queryVo, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(data);
	}
	
	/**
	 * 查询供应商明细
	 * @param supplyId
	 * @return
	 */
	@RequestMapping(value = "/getSupplyDetail", method = RequestMethod.GET)
	public ResponseModel getSupplyDetail(@RequestParam String supplyId) {
		Map<String,Object> result = service.getSupplyDetail(supplyId);
		return new ResponseModel(result);
	}
}
