package com.gzzhwl.template.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gzzhwl.template.callable.RowParseCallable;
import com.gzzhwl.template.callable.RowParseStrategyFactory;
import com.gzzhwl.template.model.ExcelRow;
import com.gzzhwl.template.model.ExcelSheet;

/**
 * POI Excel
 */
public class ExportHandler {
	private RowParseStrategyFactory rowParseFactory;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private CellStyle cellBorder;// 边框样式
	private List<ExcelRow> rows;// 一般属性

	/**
	 * 构造方法
	 */
	public ExportHandler(ExcelSheet template, RowParseStrategyFactory rowParseFactory) {
		this.rowParseFactory = rowParseFactory;
		workbook = new XSSFWorkbook();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		Font headFont = workbook.createFont();
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 11);// 设置字体大小
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		cellStyle.setFont(headFont);

		cellBorder = workbook.createCellStyle();
		cellBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框
		cellBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		Font bodyFont = workbook.createFont();
		bodyFont.setFontName("宋体");
		bodyFont.setFontHeightInPoints((short) 11);// 设置字体大小
		cellBorder.setFont(bodyFont);
		String sheetName = template.getName();
		sheet = workbook.createSheet(sheetName);
		row = sheet.createRow(0);
		rows = template.getRows();

		int start = rows.size();

		for (int i = 0; i < start; i++) {
			ExcelRow r = rows.get(i);
			XSSFCell cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(r.getTitle());
			cell.setCellStyle(cellStyle);
		}
	}

	/**
	 * 数据结果集处理
	 */
	public void processRow(List<Map<String, Object>> dataList) {
		cellBorder.setWrapText(true);// 设置支持换行
		for (int s = 0; s < dataList.size(); s++) {
			row = sheet.createRow(s + 1);
			Map<String, Object> data = dataList.get(s);
			data.put("_index", String.valueOf(s));
			int start = rows.size();
			for (int i = 0; i < start; i++) {
				ExcelRow r = rows.get(i);
				XSSFCell cell = row.createCell(i);
				cell.setCellStyle(cellBorder);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				String transValue = this.convertValue(workbook, sheet, cell, r, data);
				cell.setCellValue(transValue);// 填充值
			}
		}
	}

	private String convertValue(XSSFWorkbook workbook, XSSFSheet sheet, XSSFCell cell, ExcelRow row,
			Map<String, Object> data) {
		final String key = row.getKey();
		final String parse = row.getParse();
		RowParseCallable rowParse = rowParseFactory.getRowParse(parse);
		if (rowParse != null) {
			Object value = data.get(key);
			return rowParse.parse(workbook, sheet, cell, key, value, data);
		} else {
			return StringUtils.EMPTY;
		}
	}

	/**
	 * 文件输出
	 */
	public void write(OutputStream outstream) throws IOException {
		workbook.write(outstream);
		outstream.flush();
		outstream.close();
		workbook.close();
	}

}
