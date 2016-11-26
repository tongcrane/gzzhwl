package com.gzzhwl.admin.payables.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.gzzhwl.admin.payables.vo.PayStatementQueryVo;
import com.gzzhwl.admin.recieve.vo.ReceiveStatementQueryVo;

public interface PayablesService {

	/**
	 * 查询应付明细
	 */
	public Map<String, Object> getPayDetail(String contractId);

	/**
	 * 获取应付费用
	 * 
	 * @param loadId
	 * @return
	 */
	public Map<String, Object> getPayChargesInfo(String contractId);

	public void verifyPayables(String loadId, String staffId);

	/**
	 * 应付对账单查询
	 * 
	 * @param payStatementQueryVo
	 * @return
	 * @throws ParseException
	 */
	public List<Map<String, Object>> getPayStatementList(PayStatementQueryVo payStatementQueryVo) throws ParseException;

	/**
	 * 导出应付对账单
	 */
	public void exportPayStatement(List<Map<String, Object>> payMapList, OutputStream os)
			throws ParseException, IOException;

	/**
	 * 获取对账单名称
	 */
	public String getFileName(PayStatementQueryVo queryVo);

}
