package com.gzzhwl.admin.lend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.lend.service.LendService;
import com.gzzhwl.admin.lend.vo.LendQryVo;
import com.gzzhwl.admin.lend.vo.LendVO;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/lend")
public class LendController {
	@Autowired
	private LendService lendService;

	/**
	 * 外车司机车辆
	 */
	@RequirePerm
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseModel addLend(LendVO lendVo) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		Integer departId = SecurityUtils.getSubject().getDepartId();
		String lendId = lendService.addLend(lendVo, departId, staffId);
		return new ResponseModel(lendId);
	}

	/**
	 * 获取车辆最晚外借的日期
	 */
	@RequirePerm
	@RequestMapping(value = "/validInfo", method = RequestMethod.GET)
	public ResponseModel validInfo(@RequestParam String vehicleInfoId) {
		Integer departId = SecurityUtils.getSubject().getDepartId();
		Map<String, Object> info = lendService.validInfo(vehicleInfoId, departId);
		return new ResponseModel(info);
	}

	/**
	 * 获取车辆最晚外借的日期
	 */
	@RequirePerm
	@RequestMapping(value = "/validDriver", method = RequestMethod.GET)
	public ResponseModel getLendInfo(@RequestParam String vehicleInfoId, @RequestParam String driverInfoId) {
		Integer departId = SecurityUtils.getSubject().getDepartId();
		boolean valid = lendService.validDriver(vehicleInfoId, driverInfoId, departId);
		return new ResponseModel(valid);
	}

	/**
	 * 归还司机车辆
	 */
	@RequirePerm
	@RequestMapping(value = "/return", method = RequestMethod.POST)
	public ResponseModel offLend(@RequestParam String lendId) {
		String staffId = SecurityUtils.getSubject().getStaffId();
		boolean success = lendService.offLend(lendId, staffId);
		return new ResponseModel(success);
	}

	/**
	 * 借车列表
	 */
	@RequirePerm
	@RequestMapping(value = "/getLendPage", method = RequestMethod.GET)
	public ResponseModel offLend(LendQryVo lendQryVo, @Pagination PageParameter page) {

		lendQryVo.setCurrentDepartId(SecurityUtils.getSubject().getDepartId() + "");

		Page<Map<String, Object>> resPage = lendService.getLendLogPage(lendQryVo, page.getPageIndex(),
				page.getPageSize());

		return new ResponseModel(resPage);
	}

	/**
	 * 借车列表
	 */
	@RequirePerm
	@RequestMapping(value = "/getLendDetail", method = RequestMethod.GET)
	public ResponseModel getLendLogDetail(String lendId) {

		Map<String, Object> resMap = lendService.getLendLogDetail(lendId);

		return new ResponseModel(resMap);
	}

}
