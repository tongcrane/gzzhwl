                 package com.gzzhwl.admin.load.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.admin.load.service.ArrivedManageService;
import com.gzzhwl.admin.load.vo.ArriveQueryVo;
import com.gzzhwl.admin.load.vo.LoadPrintreceiptVo;
import com.gzzhwl.admin.load.vo.ReceiptQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/arrived")
public class ArrivedManageController {
	@Autowired
	private ArrivedManageService service;

	/**
	 * 到达
	 */
	@RequirePerm
	@RequestMapping(value = "/arrive", method = { RequestMethod.POST })
	public ResponseModel cancelOrder(@RequestParam String loadId, @RequestParam String arriveTime) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = service.doArrived(loadId, arriveTime, subject.getStaffId());
		return new ResponseModel(success);
	}
	
	/**
	 * 上传电子回单图片
	 */
	@RequestMapping(value = "/uploadElecImage", method = RequestMethod.POST)
	public ResponseModel uploadElecmage(@RequestParam MultipartFile image) {
		Subject subject = SecurityUtils.getSubject();
		String imageId = service.updateElecImage(image, subject.getStaffId());
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("imageId", imageId);
		return new ResponseModel(resMap);
	}

	/**
	 * CBS上传电子回单
	 */
	@RequirePerm
	@RequestMapping(value = "/elecReceipt", method = { RequestMethod.POST })
	public ResponseModel uploadElecReceipt(@RequestParam String loadId, @RequestParam String actionTime,
			@RequestParam String imageId) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = service.uploadElecReceipt(loadId, imageId, actionTime, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * CBS上传纸质回单
	 */
	@RequirePerm
	@RequestMapping(value = "/printReceipt", method = { RequestMethod.POST })
	public ResponseModel uploadPrintReceipt(LoadPrintreceiptVo vo) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = service.uploadPrintReceipt(vo, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 审核电子回单
	 */
	@RequirePerm
	@RequestMapping(value = "/verifyElecReceipt", method = { RequestMethod.POST })
	public ResponseModel verifyElecReceipt(@RequestParam String loadId, @RequestParam String receiptId,
			@RequestParam String verifyResult) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = service.verifyElecReceipt(loadId, receiptId, verifyResult, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 确认纸质回单
	 */
	@RequirePerm
	@RequestMapping(value = "/confirmPrintReceipt", method = { RequestMethod.POST })
	public ResponseModel confirmPrintReceipt(@RequestParam String loadId, @RequestParam String receiptId,
			String signTime) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = service.confirmPrintReceipt(loadId, receiptId, signTime, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 关闭
	 * 
	 */
	@RequirePerm
	@RequestMapping(value = "/close", method = { RequestMethod.POST })
	public ResponseModel close(@RequestParam String loadId, String reason) {
		Subject subject = SecurityUtils.getSubject();
		boolean success = service.close(loadId, reason, subject.getStaffId());
		return new ResponseModel(success);
	}

	/**
	 * 查询到达列表
	 * 
	 * @throws ParseException
	 */
	@RequirePerm
	@RequestMapping(value = "/page", method = { RequestMethod.GET })
	public ResponseModel pageArriveList(ArriveQueryVo queryVo, @Pagination PageParameter page) throws ParseException {
		Page<Map<String, Object>> result = service.pageArriveList(queryVo, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(result);
	}

	/**
	 * 查询到达详情
	 * 
	 * @throws ParseException
	 */
	@RequirePerm
	@RequestMapping(value = "/getArriveDetail", method = { RequestMethod.GET })
	public ResponseModel getArriveDetail(@RequestParam String loadId) {
		Map<String, Object> result = service.getArriveDetail(loadId);
		return new ResponseModel(result);
	}
	
	/**
	 * 回单查询
	 * @param queryVo
	 * @param page
	 * @return
	 * @throws RestException
	 * @throws ParseException
	 */
	@RequirePerm
	@RequestMapping(value = "/pageReceiptList", method = { RequestMethod.GET })
	public ResponseModel pageReceiptList(ReceiptQueryVo queryVo, @Pagination PageParameter page) throws RestException, ParseException {
		Page<Map<String, Object>> result = service.pageReceiptList(queryVo, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(result);
	}

}
