package com.gzzhwl.admin.load.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.gzzhwl.admin.load.vo.LoadFeedbackListVo;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.LoadBillType;

public interface LoadFeedbackService {
	
	/**
	 * 异常反馈上报
	 * @param loadId
	 * @param loadFeedBackType
	 * @param loadBillType
	 */
	public void saveLoadFeedback(LoadFeedbackListVo LoadFeedbackList,String staffId,DataSource source,String isException,String isEnd);
	
	/**
	 * 是否能上传异常反馈
	 * @param loadId
	 * @return
	 */
	public boolean isCanSaveYSJFeedback(String loadId);
	
	/**
	 * 保存GPS信息
	 * @param LoadFeedbackList
	 * @param staffId
	 * @param source
	 * @param isException
	 * @param isEnd
	 */
	public void saveGPSInfo(LoadFeedbackListVo LoadFeedbackList,String accountId);
	
	/**
	 * 内部司机异常反馈上报
	 * @param LoadFeedbackList
	 * @param staffId
	 * @param source
	 * @param isException
	 * @param isEnd
	 */
	public void saveCBSDriverFeedback(LoadFeedbackListVo LoadFeedbackList,String accountId);
	
	/**
	 * 结束异常反馈
	 * @param feedbackId
	 * @param accountId
	 */
	public void endFeedback(String feedbackId,String actionTime,String accountId);
	
	/**
	 * 运势界司机异常反馈上报
	 * @param LoadFeedbackList
	 * @param staffId
	 * @param source
	 * @param isException
	 * @param isEnd
	 */
	public void saveYSJDriverFeedback(LoadFeedbackListVo LoadFeedbackList,String accountId);
	
	/**
	 * 查看异常反馈列表
	 * @param loadId 提货单ID
	 * @param loadFeedBackType 异常反馈类型  01-货主异常反馈 02-车辆异常反馈
	 * @param loadBillType 提货单类型 01-车检反馈 02-靠台反馈
	 * @return
	 */
	public List<Map<String,Object>>  getLoadFeedbackList(String loadId,DataSource source,String[] loadFeedBackTypes,String isException,String isEnd,String[] loadBillTypes);
	
	/**
	 * 获取CBS异常反馈列表
	 * @return
	 */
	public Map<String,Object> getCbsLoadFeedbackList(String loadId,LoadBillType loadBillType);
	
	/**
	 * 获取CBS异常反馈列表
	 * @return
	 */
	public Map<String,Object> getCbsLoadFeedbackList(String loadId);
	
	/**
	 * 获取跟踪列表
	 * @return
	 */
	public List<Map<String,Object>> getTrackList(String loadId);
	
	/**
	 * 获取电子回单异常反馈列表
	 * @return
	 */
	public List<Map<String, Object>> getElecFeedbackList(String loadId);
	
//	/**
//	 * 删除异常反馈
//	 * @param feedbackId
//	 * @param staffId
//	 */
//	public void removeLoadFeedback(String feedbackId,String staffId);
	
	/**
	 * 异常反馈图片上传
	 * @param file
	 * @param staffId
	 * @return
	 */
	public String updateImage(MultipartFile file, String staffId);
	
	/**
	 * 应收费用检查
	 * @param loadId
	 * @return
	 */
	public boolean revFeedbackValidator(String loadId);
	
	/**
	 * 应付费用检查
	 * @param loadId
	 * @return
	 */
	public boolean payFeedbackValidator(String loadId);
	
	/**
	 * 获取应收异常费用
	 * @param loadId
	 * @return
	 */
	public String getRevFeedbackCharges(String loadId);
	
	/**
	 * 获取应付异常费用
	 * @param loadId
	 * @return
	 */
	public String getPayFeedbackCharges(String loadId);
	
	/**
	 * 调整异常费用
	 * @param feedBackId
	 * @param price
	 */
	public void updateFeedbackCharges(String feedBackId,String itemPrice,String priceSymbol);
	 

}
