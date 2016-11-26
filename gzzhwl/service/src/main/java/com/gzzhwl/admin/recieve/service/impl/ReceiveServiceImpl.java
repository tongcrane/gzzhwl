package com.gzzhwl.admin.recieve.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.consignment.service.ConsignmentInfoService;
import com.gzzhwl.admin.load.service.LoadBillService;
import com.gzzhwl.admin.load.service.LoadFeedbackService;
import com.gzzhwl.admin.recieve.service.ReceiveService;
import com.gzzhwl.admin.recieve.vo.ReceiveQueryVo;
import com.gzzhwl.admin.recieve.vo.ReceiveStatementQueryVo;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.ConsignType;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.constant.LoadFeedBackType;
import com.gzzhwl.core.data.dao.ConsignmentInfoDao;
import com.gzzhwl.core.data.dao.CustomerInfoDao;
import com.gzzhwl.core.data.dao.DepartmentInfoDao;
import com.gzzhwl.core.data.dao.ReceivablesInfoDao;
import com.gzzhwl.core.data.extdao.ConsignmentInfoExtDao;
import com.gzzhwl.core.data.model.ConsignmentInfo;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.data.model.DepartmentInfo;
import com.gzzhwl.core.data.model.LoadFeedbackLog;
import com.gzzhwl.core.data.model.ReceivablesInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.DateUtils;

@Service
public class ReceiveServiceImpl implements ReceiveService {
	@Autowired
	private LoadFeedbackService feedBackService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ConsignmentInfoService consignService;
	@Autowired
	private ConsignmentInfoExtDao consignExtDao;
	@Autowired
	private ConsignmentInfoDao consignDao;
	@Autowired
	private LoadBillService loadBillService;
	@Autowired
	private ReceivablesInfoDao receivablesInfoDao;
	@Autowired
	private CustomerInfoDao custDao;
	@Autowired
	private DepartmentInfoDao departDao;

	@Override
	public Page<Map<String, Object>> pageReceiveList(ReceiveQueryVo vo, int currentPage, int pageSize)
			throws ParseException {
		Map<String, Object> params = vo.getParam();
		Page<Map<String, Object>> recievePage = consignExtDao.pageRecieveList(params, currentPage, pageSize);

		List<Map<String, Object>> recieves = recievePage.getRows();

		if (CollectionUtils.isNotEmpty(recieves)) {
			for (Map<String, Object> recieveMap : recieves) {
				String startCodeP = (String) recieveMap.get("startCodeP");
				String startCodePCn = this.getCodeCn(startCodeP);
				recieveMap.put("startCodePCn", startCodePCn);

				String startCodeC = (String) recieveMap.get("startCodeC");
				String startCodeCCn = this.getCodeCn(startCodeC);
				recieveMap.put("startCodeCCn", startCodeCCn);

				String endCodeP = (String) recieveMap.get("endCodeP");
				String endCodePCn = this.getCodeCn(endCodeP);
				recieveMap.put("endCodePCn", endCodePCn);

				String endCodeC = (String) recieveMap.get("endCodeC");
				String endCodeCCn = this.getCodeCn(endCodeC);
				recieveMap.put("endCodeCCn", endCodeCCn);

				String loadId = (String) recieveMap.get("loadId");

				String status = (String) recieveMap.get("status");
				String statusCn = "";
				if (ConsignType.CONSIGN_VERIFIED.getCode().equals(status)) {
					statusCn = "已审核";
				} else if (ConsignType.CONSIGN_WAIT.getCode().equals(status)) {
					statusCn = "未审核";
				}
				recieveMap.put("statusCn", statusCn);

				// 计算其他费用
				String feedBackPrice = feedBackService.getRevFeedbackCharges(loadId);
				if (StringUtils.isBlank(feedBackPrice)) {
					feedBackPrice = "0.00";
				}
				recieveMap.put("feedBackPrice", new DecimalFormat("#0.00").format(Double.parseDouble(feedBackPrice)));
			}
		}

		return recievePage;
	}

	public String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = regionService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public Map<String, Object> getReceiveDetail(String consignId) {
		Map<String, Object> payInfo = Maps.newHashMap();

		// 获取订单信息
		Map<String, Object> orderInfo = consignService.getOrderGeneralInfo(consignId);

		// 获取费用信息
		String loadId = (String) orderInfo.get("loadId");
		String freightPrice = (String) orderInfo.get("freightPrice");
		Map<String, Object> chargeInfo = getChargeInfo(loadId, freightPrice);

		// 获取异常信息
		List<Map<String, Object>> feedBackList = feedBackService.getLoadFeedbackList(loadId, null,
				new String[] { LoadFeedBackType.SOURCEADMIN.getCode() }, LoadFeedbackLog.ISEXCEPTION_YES,
				LoadFeedbackLog.ISEND_YES, null);

		payInfo.put("orderInfo", orderInfo);
		payInfo.put("chargeInfo", chargeInfo);
		payInfo.put("feedBackList", feedBackList);

		return payInfo;
	}

	private Map<String, Object> getChargeInfo(String loadId, String freightPrice) {
		Map<String, Object> chargeInfo = Maps.newHashMap();
		// 获取异常费用信息
		String feedBackPrice = feedBackService.getRevFeedbackCharges(loadId);

		if (StringUtils.isEmpty(freightPrice)) {
			freightPrice = "0.00";
		}

		if (StringUtils.isEmpty(feedBackPrice)) {
			feedBackPrice = "0.00";
		}

		// 计算总费用
		String totPrice = new BigDecimal(freightPrice).add(new BigDecimal(feedBackPrice)).toString();

		chargeInfo.put("freightPrice",  new DecimalFormat("#0.00").format(Double.parseDouble(freightPrice)));
		chargeInfo.put("feedBackPrice",  new DecimalFormat("#0.00").format(Double.parseDouble(feedBackPrice)));
		chargeInfo.put("totPrice",  new DecimalFormat("#0.00").format(Double.parseDouble(totPrice)));
		return chargeInfo;
	}

	@Override
	public boolean verifyReceive(String consignId, String staffId) {
		// 获取运单信息
		ConsignmentInfo consignmentInfo = consignDao.get(consignId);
		String loadId = loadBillService.getCurrentNoCanceledLoadBill(consignmentInfo.getOrderId());

		// 验证货主异常反馈是否都有金额
		feedBackService.revFeedbackValidator(loadId);

		// 货主合同流程结转
		consignService.doVerified(consignId, staffId);

		// 保存应收信息
		this.saveReceiveInfo(consignId, consignmentInfo.getFreightPrice(), loadId, staffId);
		return false;
	}

	private void saveReceiveInfo(String consignId, String freightPrice, String loadId, String staffId) {
		// 获取异常费用信息
		String feedBackPrice = feedBackService.getRevFeedbackCharges(loadId);

		if (StringUtils.isEmpty(freightPrice)) {
			freightPrice = "0";
		}

		if (StringUtils.isEmpty(feedBackPrice)) {
			feedBackPrice = "0";
		}

		// 计算总费用
		String totPrice = new BigDecimal(freightPrice).add(new BigDecimal(feedBackPrice)).toString();

		ReceivablesInfo receivablesInfo = new ReceivablesInfo();
		receivablesInfo.setConsignId(consignId);
		receivablesInfo.setTotal(totPrice);
		receivablesInfo.setExceptionTotal(feedBackPrice);
		receivablesInfo.setFreightPrice(freightPrice);
		receivablesInfo.setCreatedBy(staffId);
		receivablesInfoDao.insert(receivablesInfo);
	}

	@Override
	public void exportReceiveStatement(ReceiveStatementQueryVo queryVo, OutputStream os)
			throws ParseException, IOException {

		// 第一步，创建一个webbook，对应一个Excel文件
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workbook.createSheet("应收对账单");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		XSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		CellStyle style = workbook.createCellStyle();

		// 把水平对齐方式指定为居中
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 把垂直对齐方式指定为居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		style.setFont(font);

		XSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("客户全称");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("订单号");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("运单号");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("客户单号");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("线路");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("发车时间");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("业务部门");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("纯运费");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("是否预估");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("其他费用汇总");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("异常类型");
		cell.setCellStyle(style);
		cell = row.createCell(12);
		cell.setCellValue("异常时间");
		cell.setCellStyle(style);
		cell = row.createCell(13);
		cell.setCellValue("费用名称");
		cell.setCellStyle(style);
		cell = row.createCell(14);
		cell.setCellValue("费用金额");
		cell.setCellStyle(style);
		cell = row.createCell(15);
		cell.setCellValue("上报人");
		cell.setCellStyle(style);

		Map<String, Object> params = queryVo.getParams();
		List<Map<String, Object>> receiveStatementList = consignExtDao.getReceiveSatementList(params);

		if (CollectionUtils.isNotEmpty(receiveStatementList)) {
			int orderNum = 1;// 记序号
			int startRows = 1;
			for (Map<String, Object> map : receiveStatementList) {
				String startCodeP = (String) map.get("startCodeP");
				String startCodePCn = this.getCodeCn(startCodeP);
				map.put("startCodePCn", startCodePCn);

				String startCodeC = (String) map.get("startCodeC");
				String startCodeCCn = this.getCodeCn(startCodeC);
				map.put("startCodeCCn", startCodeCCn);

				String endCodeP = (String) map.get("endCodeP");
				String endCodePCn = this.getCodeCn(endCodeP);
				map.put("endCodePCn", endCodePCn);

				String endCodeC = (String) map.get("endCodeC");
				String endCodeCCn = this.getCodeCn(endCodeC);
				map.put("endCodeCCn", endCodeCCn);

				String loadId = (String) map.get("loadId");
				List<Map<String, Object>> feedBackList = feedBackService.getLoadFeedbackList(loadId, null,
						new String[] { LoadFeedBackType.SOURCEADMIN.getCode() }, LoadFeedbackLog.ISEXCEPTION_YES,
						LoadFeedbackLog.ISEND_YES, null);
				if (CollectionUtils.isNotEmpty(feedBackList)) {

					for (int i = 0; i < feedBackList.size(); i++) {
						row = sheet.createRow(startRows);
						int endRows = startRows + feedBackList.size() - 1;
						for (int j = 0; j < 16; j++) {
							if (j < 11) {
								if (i == 0) {
									cell = row.createCell(j);

									// 起始行号，终止行号， 起始列号，终止列号
									CellRangeAddress cra = new CellRangeAddress(startRows, (short) endRows, j,
											(short) j);
									sheet.addMergedRegion(cra);
									String cellValue = getCellValue(j, orderNum, feedBackList.get(i), map);
									if (j == 8 || j == 10) {
										// 对金额字段进行格式转换
										this.getNumbericCell(workbook, cell, cellValue);
									} else if (j == 6) {
										// 对时间字段进行格式转换
										this.getDateCell(workbook, cell, cellValue);
									} else {
										cell.setCellType(XSSFCell.CELL_TYPE_STRING);
										cell.setCellStyle(style);
										cell.setCellValue(cellValue);
									}
								}
							} else {
								cell = row.createCell(j);
								String cellValue = getCellValue(j, orderNum, feedBackList.get(i), map);
								if (j == 14) {
									this.getNumbericCell(workbook, cell, cellValue);
								} else if (j == 12) {
									this.getDateCell(workbook, cell, cellValue);
								} else {
									cell.setCellType(XSSFCell.CELL_TYPE_STRING);
									cell.setCellStyle(style);
									cell.setCellValue(cellValue);
								}
							}
						}
						startRows++;

					}
					orderNum++;
				} else {
					row = sheet.createRow(startRows);
					for (int j = 0; j < 16; j++) {
						cell = row.createCell(j);
						String cellValue = getCellValue(j, orderNum, null, map);
						if (j == 8 || j == 10) {
							// 对金额字段进行格式转换
							this.getNumbericCell(workbook, cell, cellValue);
						} else if (j == 6) {
							// 对时间字段进行格式转换
							this.getDateCell(workbook, cell, cellValue);
						} else {
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							cell.setCellStyle(style);
							cell.setCellValue(cellValue);
						}
					}
					startRows++;
					orderNum++;
				}

				// 每一笔运单合同空一格
				row = sheet.createRow(startRows);
				startRows++;
			}

		}

		for (int j = 0; j < 16; j++) {
			sheet.autoSizeColumn((short) j);
		}

		// 第六步，将文件存到指定位置
		try {
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workbook.close();
			os.close();
		}
	}

	private void getNumbericCell(XSSFWorkbook workbook, XSSFCell cell, String cellValue) {
		CellStyle cellStyle = workbook.createCellStyle();
		XSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("0.00"));
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cell.setCellStyle(cellStyle);
		cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
		if (NumberUtils.isNumber(cellValue)) {
			cell.setCellValue(Double.parseDouble(cellValue));
		} else {
			cell.setCellValue(cellValue);
		}
	}

	private void getDateCell(XSSFWorkbook workbook, XSSFCell cell, String cellValue) throws ParseException {
		CellStyle cellStyle = workbook.createCellStyle();
		XSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("yyyy/m/d h:mm"));
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(DateUtils.parse(cellValue, "yyyy-MM-dd HH:mm"));
	}

	private String getCellValue(int j, int orderNum, Map<String, Object> feedBackMap, Map<String, Object> orderMap) {
		String returnVal = "";
		switch (j) {
		case 0:
			returnVal = orderNum + "";
			break;
		case 1:
			returnVal = (String) orderMap.get("customerName");
			break;
		case 2:
			returnVal = (String) orderMap.get("orderNo");
			break;
		case 3:
			returnVal = (String) orderMap.get("consignNo");
			break;
		case 4:
			returnVal = (String) orderMap.get("customerBillNo");
			break;
		case 5:
			returnVal = (String) orderMap.get("startCodeCCn") + "-" + (String) orderMap.get("endCodeCCn");
			break;
		case 6:
			returnVal = (String) orderMap.get("tripTime");
			break;
		case 7:
			returnVal = (String) orderMap.get("name");
			break;
		case 8:
			returnVal = (String) orderMap.get("freightPrice");
			break;
		case 9:
			String isPredit = (String) orderMap.get("isPredit");
			if ("01".equals(isPredit)) {
				returnVal = "精确";
			} else if ("02".equals(isPredit)) {
				returnVal = "预估";
			}
			break;
		case 10:
			returnVal = (String) orderMap.get("exceptionTotal");
			break;
		case 11:
			if (feedBackMap != null) {
				String status = (String) feedBackMap.get("status");
				if (LoadBillType.NOTVEHICLE.getCode().equals(status)) {
					returnVal = "车检异常";
				} else if (LoadBillType.VEHICLECHECK.getCode().equals(status)) {
					returnVal = "靠台异常";
				} else if (LoadBillType.CLOSETOSURFACE.getCode().equals(status)) {
					returnVal = "发车异常";
				} else if (LoadBillType.ARRIVED.getCode().equals(status)) {
					returnVal = "电子回单异常";
				}
			}
			break;
		case 12:
			if (feedBackMap != null) {
				returnVal = (String) feedBackMap.get("feedbackTime");
			}
			break;
		case 13:
			if (feedBackMap != null) {
				returnVal = (String) feedBackMap.get("itemName");
			}
			break;
		case 14:
			if (feedBackMap != null) {
				returnVal = (String) feedBackMap.get("itemPrice");
			}
			break;
		case 15:
			if (feedBackMap != null) {
				returnVal = (String) feedBackMap.get("realName");
			}
			break;
		}
		return returnVal;
	}

	@Override
	public String getFileName(ReceiveStatementQueryVo queryVo) {
		StringBuffer buffer = new StringBuffer("应收对账单");
		if (StringUtils.isNotBlank(queryVo.getCustomerId())) {
			CustomerInfo cust = custDao.get(queryVo.getCustomerId());
			if (cust != null && StringUtils.isNotBlank(cust.getFullName())) {
				buffer.append("_" + cust.getFullName());
			}
		}
		if (StringUtils.isNotBlank(queryVo.getStartCodeP())) {
			String startCodePCn = this.getCodeCn(queryVo.getStartCodeP());
			if (StringUtils.isNotBlank(startCodePCn)) {
				buffer.append("_" + startCodePCn);
			}
		}
		if (StringUtils.isNotBlank(queryVo.getStartCodeC())) {
			String startCodeCCn = this.getCodeCn(queryVo.getStartCodeC());
			if (StringUtils.isNotBlank(startCodeCCn)) {
				buffer.append("_" + startCodeCCn);
			}
		}
		if (StringUtils.isNotBlank(queryVo.getEndCodeP())) {
			String endCodePCn = this.getCodeCn(queryVo.getEndCodeP());
			if (StringUtils.isNotBlank(endCodePCn)) {
				buffer.append("_" + endCodePCn);
			}
		}
		if (StringUtils.isNotBlank(queryVo.getEndCodeC())) {
			String endCodeCCn = this.getCodeCn(queryVo.getEndCodeC());
			if (StringUtils.isNotBlank(endCodeCCn)) {
				buffer.append("_" + endCodeCCn);
			}
		}
		if (StringUtils.isNotBlank(queryVo.getDepartId())) {
			DepartmentInfo depart = departDao.get(Integer.parseInt(queryVo.getDepartId()));
			if (depart != null && StringUtils.isNotBlank(depart.getName())) {
				buffer.append("_" + depart.getName());
			}
			;
		}
		if (StringUtils.isNotBlank(queryVo.getTripStartTime())) {
			buffer.append("_" + queryVo.getTripStartTime());
		}
		if (StringUtils.isNotBlank(queryVo.getTripEndTime())) {
			buffer.append("_" + queryVo.getTripEndTime());
		}
		if (StringUtils.isNotBlank(queryVo.getIsPredict())) {
			if ("01".equals(queryVo.getIsPredict())) {
				buffer.append("_精确");
			} else if ("02".equals(queryVo.getIsPredict())) {
				buffer.append("_预估");
			}
		}
		return buffer.toString();
	}

}
