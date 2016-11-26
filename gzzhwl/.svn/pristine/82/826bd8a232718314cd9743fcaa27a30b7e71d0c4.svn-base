package com.gzzhwl.api.load.service;

import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.rest.exception.RestException;

public interface ArrivedService {
	
	/**
	 * 添加电子回单
	 * @param loadId
	 * @param loadNo
	 * @param billImageId
	 * @param actionTime
	 * @throws RestException
	 */
	public void addElecReceipt(String loadId,String loadNo,String receiptImageRefId,String actionTime,String accountId) throws RestException;
	
	/**
	 * 添加纸质回单
	 * @param loadId
	 * @param loadNo
	 * @param expressCompany
	 * @param expressNo
	 * @param billImageId
	 * @param actionTime
	 * @throws RestException
	 */
	public void addPrintReceipt(String loadId,String loadNo,String expressDelivery,String billNo,String accountId) throws RestException;
	
	/**
	 * 上传电子回单图片
	 * @param file
	 * @param accountId
	 */
	public String updateElecmage(MultipartFile file, String accountId);
	
	/**
	 * 上传快递单图片
	 * @param file
	 * @param accountId
	 */
	public String uploadExpressBillImage(MultipartFile file, String accountId);
	
	/**
	 * 获取当前纸质回单状态
	 * 
	 * @param loadId
	 * @return
	 */
	public String getCurrentPrintStatus(String loadId);

	/**
	 * 获取当前电子回单状态
	 * 
	 * @param loadId
	 * @return
	 */
	public String getCurrentElecStatus(String loadId);
	
	
	public boolean isCanUploadReceipt(String currentStatus);
	
}
