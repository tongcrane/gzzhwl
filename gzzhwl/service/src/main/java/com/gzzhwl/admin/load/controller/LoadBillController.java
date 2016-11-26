package com.gzzhwl.admin.load.controller;

import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.vo.LoadInfoVO;
import com.gzzhwl.admin.load.vo.LoadQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/load")
public class LoadBillController {
	@Autowired
	private LoadBillService loadBillService;

	/**
	 * 创建提货单
	 */
	@RequirePerm
	@RequestMapping(value = "/create", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseModel createLoad(@RequestBody LoadInfoVO loadInfo) {
		Subject subject = SecurityUtils.getSubject();
		String loadId = loadBillService.createLoad(loadInfo, subject.getStaffId());
		return new ResponseModel(loadId);
	}

	/**
	 * 提货单分页
	 * 
	 * @param loadInfo
	 * @return
	 * @throws ParseException 
	 * @throws RestException 
	 */
	@RequirePerm
	@RequestMapping(value = "/pageLoads", method = { RequestMethod.GET })
	public ResponseModel pageLoads(LoadQueryVo queryVo, @Pagination PageParameter page) throws RestException, ParseException {
		Page<Map<String, Object>> result = loadBillService.pageLoads(queryVo, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(result);
	}

	/**
	 * 提货单详情
	 * 
	 * @param orderId
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/getLoadDetail", method = { RequestMethod.GET })
	public ResponseModel getLoadInfo(@RequestParam(required = true) String loadId) {
		Map<String, Object> result = loadBillService.getLoadDetail(loadId);
		return new ResponseModel(result);
	}

	/**
	 * 取消提货单
	 */
	@RequirePerm
	@RequestMapping(value = "/cancel", method = { RequestMethod.POST })
	public ResponseModel cancelLoad(@RequestParam String loadId) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = loadBillService.cancelLoad(loadId, subject.getStaffId());
		return new ResponseModel(success);
	}

	@RequirePerm
	@RequestMapping(value = "/cancelBatch", method = { RequestMethod.POST })
	public ResponseModel cancelBatch(@RequestParam String loadIds) {
		Subject subject = SecurityUtils.getSubject();
		String[] loadArr = StringUtils.split(loadIds, ",");
		boolean success = loadBillService.cancelLoadBatch(loadArr, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 修改提货单
	 * 
	 * @param loadInfo
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/modify", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseModel modifyLoad(@RequestBody LoadInfoVO loadInfoVO) {
		Subject subject = SecurityUtils.getSubject();
		String loadId = loadBillService.modifyLoad(loadInfoVO, subject.getStaffId());
		return new ResponseModel(loadId);
	}

	/**
	 * 发车
	 * 
	 * @param loadId
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/doTrip", method = { RequestMethod.POST })
	public ResponseModel doTrip(@RequestParam(required = true) String loadId) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = loadBillService.doTrip(loadId, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 发车
	 * 
	 * @param loadId
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/tripBatch", method = { RequestMethod.POST })
	public ResponseModel tripBatch(@RequestParam(required = true) String loadIds) {
		Subject subject = SecurityUtils.getSubject();
		String[] loadArr = StringUtils.split(loadIds, ",");
		boolean success = loadBillService.tripBatch(loadArr, subject.getStaffId());
		return new ResponseModel(success);
	}
}
