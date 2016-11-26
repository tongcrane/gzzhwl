package com.gzzhwl.admin.load.service;

import java.text.ParseException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.admin.load.vo.ArriveQueryVo;
import com.gzzhwl.admin.load.vo.LoadPrintreceiptVo;
import com.gzzhwl.admin.load.vo.ReceiptQueryVo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;

public interface ArrivedManageService {

	/**
	 * 到达
	 */
	public boolean doArrived(String loadId, String arriveTime, String staffId) throws RestException;

	/**
	 * 上传电子回单
	 */
	public boolean uploadElecReceipt(String loadId, String imageId, String actionTime, String staffId);

	/**
	 * 上传纸质回单
	 */
	public boolean uploadPrintReceipt(LoadPrintreceiptVo vo, String staffId);

	/**
	 * 审核电子回单
	 */
	public boolean verifyElecReceipt(String loadId, String receiptId, String verifyResult, String staffId);

	/**
	 * 确认纸质回单
	 */
	public boolean confirmPrintReceipt(String loadId, String receiptId, String signTime, String staffId);

	/**
	 * 车辆到达关闭
	 */
	public boolean close(String loadId, String reason, String staffId);

	/**
	 * 查询到达列表
	 */
	public Page<Map<String, Object>> pageArriveList(ArriveQueryVo queryVo, int currentPage, int pageSize)
			throws ParseException;

	/**
	 * 查询到达详情
	 */
	public Map<String, Object> getArriveDetail(String loadId);

	/**
	 * 获取电子回单详情
	 */
	public Map<String, Object> getElecReceiptDetail(String loadId);

	/**
	 * 获取纸质回单详情
	 * 
	 * @param loadId
	 * @return
	 */
	public Map<String, Object> getPrintReceiptDetail(String loadId);
	
	public Page<Map<String, Object>> pageReceiptList(ReceiptQueryVo queryVo, int currentPage, int pageSize)
			throws ParseException;

	/**
	 * 查询到达信息
	 */
	public Map<String, Object> getArriveInfo(String loadId);

	/**
	 * 电子回单图片上传
	 */
	public String updateElecImage(MultipartFile image, String staffId);

}
