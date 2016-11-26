package com.gzzhwl.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gzzhwl.excel.annotations.ExcelFieldMappingByHeadName;
import com.gzzhwl.excel.annotations.ExcelFieldMappingByPosition;
import com.gzzhwl.excel.annotations.ExcelObjectMappingByPosition;
import com.gzzhwl.excel.annotations.ExcelObjectMappingByTableHead;
import com.gzzhwl.excel.annotations.MappedExcelObject;
import com.gzzhwl.excel.enums.ExcelFileType;
import com.gzzhwl.excel.enums.ExcelMappingType;
import com.gzzhwl.excel.enums.ParseType;
import com.gzzhwl.excel.exception.ExcelParsingException;
import com.gzzhwl.excel.helper.SSFHelper;
import com.gzzhwl.excel.model.ExcelFieldMappingInfo;
import com.gzzhwl.excel.model.ExcelMappingInfo;

public class ExcelUtil {
	private SSFHelper hssfHelper;
	private Map<String, ExcelMappingInfo> excelMappingInfoMapCache;
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

	public ExcelUtil() {
		hssfHelper = new SSFHelper();
		excelMappingInfoMapCache = new ConcurrentHashMap<String, ExcelMappingInfo>();
	}

	/**
	 * importExcel:导入excel.
	 * 
	 * @since JDK 1.6
	 */
	public <T> List<T> importExcel(InputStream inputStream, Class<T> clazz, ExcelFileType excelFileType)
			throws IOException, ExcelParsingException {
		Workbook workbook = null;
		if (ExcelFileType.XLS == excelFileType) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			workbook = new XSSFWorkbook(inputStream);
		}
		return buildJaveBeans(workbook, clazz);
	}

	/**
	 * exportExcel:导出excel.
	 * 
	 * @since JDK 1.6
	 */
	public <T> void exportExcel(List<T> list, Class<T> clazz, OutputStream outputStream, ExcelFileType excelFileType)
			throws IOException, ExcelParsingException, IllegalArgumentException, IllegalAccessException {
		Workbook workbook = null;
		if (ExcelFileType.XLS == excelFileType) {
			workbook = new HSSFWorkbook();
		} else {
			workbook = new XSSFWorkbook();
		}
		ExcelMappingInfo excelMappingInfo = getExcelFieldMappingInfo(clazz);
		// 生成一个表格
		Sheet sheet;
		if (StringUtils.isNotBlank(excelMappingInfo.getSheetName())) {
			sheet = workbook.createSheet(excelMappingInfo.getSheetName());
		} else {
			sheet = workbook.createSheet();
		}
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(10);

		// 生成表头的样式
		CellStyle tableHeadStyle = workbook.createCellStyle();
		// 设置这些样式
		tableHeadStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		tableHeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		tableHeadStyle.setBorderBottom(CellStyle.BORDER_THIN);
		tableHeadStyle.setBorderLeft(CellStyle.BORDER_THIN);
		tableHeadStyle.setBorderRight(CellStyle.BORDER_THIN);
		tableHeadStyle.setBorderTop(CellStyle.BORDER_THIN);
		tableHeadStyle.setAlignment(CellStyle.ALIGN_CENTER);
		// 生成表头的字体
		Font tableHeadfont = workbook.createFont();
		tableHeadfont.setColor(HSSFColor.VIOLET.index);
		tableHeadfont.setFontHeightInPoints((short) 12);
		// tableHeadfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把表头的字体应用到表头的样式
		tableHeadStyle.setFont(tableHeadfont);

		// 生成数据的样式
		CellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		dataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER);
		dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 生成数据的字体
		Font dataFont = workbook.createFont();
		dataFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		// 把数据的字体应用到数据的样式
		dataStyle.setFont(dataFont);

		buildExcel(list, clazz, excelMappingInfo, sheet, tableHeadStyle, dataStyle);
		workbook.write(outputStream);
		workbook.close();
	}

	/**
	 * buildJaveBeans 
	 * @since JDK 1.6
	 */
	private <T> List<T> buildJaveBeans(Workbook workbook, Class<T> clazz) throws ExcelParsingException {
		List<T> list = new ArrayList<T>();

		ExcelMappingInfo excelMappingInfo = getExcelFieldMappingInfo(clazz);
		Sheet sheet = null;
		if (StringUtils.isNotBlank(excelMappingInfo.getSheetName())) {
			sheet = workbook.getSheet(excelMappingInfo.getSheetName());
		} else {
			int sheetIndex = excelMappingInfo.getSheetIndex();
			sheet = workbook.getSheetAt(sheetIndex);
		}

		// if (excelMappingInfo.getHeadNamePositionMap() == null) {
		loadHeadNamePositionMapCache(clazz, sheet, excelMappingInfo);
		// }
		// if (excelMappingInfo.getFieldMappingInfoListForImport() == null) {
		loadFieldMappingInfoListForImport(clazz, excelMappingInfo);
		// }

		for (int currentLocation = excelMappingInfo.getDataStart(); excelMappingInfo.getDataEnd() == 0
				|| currentLocation <= excelMappingInfo.getDataEnd(); currentLocation++) {
			T object = getNewInstance(sheet, clazz, excelMappingInfo, currentLocation, excelMappingInfo.isZeroIfNull());
			// 出现空行则结束解析
			if (object == null) {
				break;
			}
			List<Field> mappedExcelFields = getMappedExcelObjects(clazz);
			for (Field mappedField : mappedExcelFields) {
				Class<?> fieldType = mappedField.getType();
				List<?> fieldValue = buildJaveBeans(workbook,
						fieldType.equals(List.class) ? getFieldType(mappedField) : fieldType);
				if (fieldType.equals(List.class)) {
					setFieldValue(mappedField, object, fieldValue);
				} else if (!fieldValue.isEmpty()) {
					setFieldValue(mappedField, object, fieldValue.get(0));
				}
			}
			list.add(object);
		}
		return list;
	}

	private <T> T getNewInstance(Sheet sheet, Class<T> clazz, ExcelMappingInfo excelMappingInfo,
			Integer currentLocation, boolean zeroIfNull) throws ExcelParsingException {
		T object = getInstance(clazz);
		boolean hasValidField = false;
		for (ExcelFieldMappingInfo excelFieldMappingInfo : excelMappingInfo.getFieldMappingInfoListForImport()) {
			Object cellValue;
			if (ParseType.ROW == excelMappingInfo.getParseType()) {
				cellValue = hssfHelper.getCellValue(sheet, excelFieldMappingInfo.getField().getType(),
						excelFieldMappingInfo.getImportDataFormat(), currentLocation,
						excelFieldMappingInfo.getPosition());
			} else {
				cellValue = hssfHelper.getCellValue(sheet, excelFieldMappingInfo.getField().getType(),
						excelFieldMappingInfo.getImportDataFormat(), excelFieldMappingInfo.getPosition(),
						currentLocation);
			}
			if (cellValue != null) {
				hasValidField = true;
			}
			// 处理Integer, Float, Double, Long为null的默认值
			else if (zeroIfNull) {
				if (excelFieldMappingInfo.getField().getType().equals(Integer.class)) {
					cellValue = 0;
				} else if (excelFieldMappingInfo.getField().getType().equals(Float.class)) {
					cellValue = 0f;
				} else if (excelFieldMappingInfo.getField().getType().equals(Double.class)) {
					cellValue = 0d;
				} else if (excelFieldMappingInfo.getField().getType().equals(Long.class)) {
					cellValue = 0l;
				}
			}

			setFieldValue(excelFieldMappingInfo.getField(), object, cellValue);
		}
		// 全部字段都为null，则将这条数据忽略
		if (!hasValidField) {
			return null;
		}
		return object;
	}

	/**
	 * buildExcel 
	 * @since JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	private <T> void buildExcel(List<?> list, Class<T> clazz, ExcelMappingInfo excelMappingInfo, Sheet sheet,
			CellStyle tableHeadStyle, CellStyle dataStyle)
			throws IllegalArgumentException, IllegalAccessException, ExcelParsingException {
		if (excelMappingInfo.getFieldMappingInfoListForExport() == null) {
			loadFieldMappingInfoListForExport(clazz, excelMappingInfo);
		}
		List<ExcelFieldMappingInfo> fieldMappingInfoListForExport = excelMappingInfo.getFieldMappingInfoListForExport();
		// 生成表头
		int rowIndex = 0;
		int columnIndex = 0;
		if (excelMappingInfo.getParseType() == ParseType.ROW) {
			rowIndex = excelMappingInfo.getHeadPosition() - 1;
		} else {
			columnIndex = excelMappingInfo.getHeadPosition() - 1;
		}

		if (excelMappingInfo.getParseType() == ParseType.ROW) {
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			++rowIndex;
			for (ExcelFieldMappingInfo excelFieldMappingInfo : fieldMappingInfoListForExport) {
				if (excelFieldMappingInfo.isNotExport()) {
					continue;
				}
				Cell cell = row.createCell(excelFieldMappingInfo.getPosition());
				sheet.setColumnWidth(excelFieldMappingInfo.getPosition(),
						excelFieldMappingInfo.getExportFieldWidth() * 256);
				cell.setCellStyle(tableHeadStyle);
				hssfHelper.setCellValue(cell, excelFieldMappingInfo.getHeadName(), excelFieldMappingInfo);
			}
		} else {
			for (ExcelFieldMappingInfo excelFieldMappingInfo : fieldMappingInfoListForExport) {
				if (excelFieldMappingInfo.isNotExport()) {
					continue;
				}
				Row row = sheet.getRow(excelFieldMappingInfo.getPosition());
				if (row == null) {
					row = sheet.createRow(excelFieldMappingInfo.getPosition());
				}
				Cell cell = row.createCell(columnIndex);
				sheet.setColumnWidth(columnIndex, excelFieldMappingInfo.getExportFieldWidth() * 256);
				cell.setCellStyle(tableHeadStyle);
				hssfHelper.setCellValue(cell, excelFieldMappingInfo.getHeadName(), excelFieldMappingInfo);
			}
			++columnIndex;
		}

		// 生成数据
		for (Object data : list) {
			if (excelMappingInfo.getParseType() == ParseType.ROW) {
				Row row = sheet.getRow(rowIndex);
				if (row == null) {
					row = sheet.createRow(rowIndex);
				}
				++rowIndex;
				for (ExcelFieldMappingInfo excelFieldMappingInfo : fieldMappingInfoListForExport) {
					if (excelFieldMappingInfo.isNotExport()) {
						continue;
					}
					Cell cell = row.createCell(excelFieldMappingInfo.getPosition());
					Object value = excelFieldMappingInfo.getField().get(data);
					if (value != null) {
						cell.setCellStyle(dataStyle);
					}
					hssfHelper.setCellValue(cell, value, excelFieldMappingInfo);
				}
			} else {
				for (ExcelFieldMappingInfo excelFieldMappingInfo : fieldMappingInfoListForExport) {
					if (excelFieldMappingInfo.isNotExport()) {
						continue;
					}
					Row row = sheet.getRow(excelFieldMappingInfo.getPosition());
					if (row == null) {
						row = sheet.createRow(excelFieldMappingInfo.getPosition());
					}
					Cell cell = row.createCell(columnIndex);
					Object value = excelFieldMappingInfo.getField().get(data);
					if (value != null) {
						cell.setCellStyle(dataStyle);
					}
					hssfHelper.setCellValue(cell, value, excelFieldMappingInfo);
				}
				++columnIndex;
			}

			// 处理嵌套的JavaBean
			List<Field> mappedExcelFields = getMappedExcelObjects(clazz);
			for (Field mappedField : mappedExcelFields) {
				Class<?> fieldType = mappedField.getType();
				List<Object> fieldList = null;
				if (fieldType.equals(List.class)) {
					fieldType = getFieldType(mappedField);
					fieldList = (List<Object>) mappedField.get(data);
				} else {
					Object fieldValue = mappedField.get(data);
					if (fieldValue != null) {
						fieldList = new ArrayList<Object>();
						fieldList.add(fieldValue);
					}
				}
				if (fieldList != null) {
					ExcelMappingInfo fieldExcelMappingInfo = getExcelFieldMappingInfo(fieldType);
					buildExcel(fieldList, fieldType, fieldExcelMappingInfo, sheet, tableHeadStyle, dataStyle);
				}
			}
		}
	}

	private Class<?> getFieldType(Field field) {
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			return (Class<?>) pt.getActualTypeArguments()[0];
		}

		return null;
	}

	private <T> List<Field> getMappedExcelObjects(Class<T> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			MappedExcelObject mappedExcelObject = field.getAnnotation(MappedExcelObject.class);
			if (mappedExcelObject != null) {
				field.setAccessible(true);
				fieldList.add(field);
			}
		}
		return fieldList;
	}

	private <T> T getInstance(Class<T> clazz) throws ExcelParsingException {
		T object = null;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelParsingException("Exception occured while instantiating the class " + clazz.getName(), e);
		}
		return object;
	}

	private <T> void setFieldValue(Field field, T object, Object cellValue) throws ExcelParsingException {
		try {
			field.set(object, cellValue);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelParsingException("Exception occured while setting field value ", e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ExcelParsingException("Exception occured while setting field value ", e);
		}
	}

	private <T> ExcelMappingInfo getExcelFieldMappingInfo(Class<T> clazz) throws ExcelParsingException {
		ExcelMappingInfo excelMappingInfo = excelMappingInfoMapCache.get(clazz.getName());
		return excelMappingInfo == null ? loadExcelFieldMappingInfo(clazz) : excelMappingInfo;
	}

	private <T> ExcelMappingInfo loadExcelFieldMappingInfo(Class<T> clazz) throws ExcelParsingException {
		ExcelMappingInfo excelMappingInfo = null;

		ExcelObjectMappingByTableHead excelObjectMappingByTableHead = clazz
				.getAnnotation(ExcelObjectMappingByTableHead.class);
		ExcelObjectMappingByPosition excelObjectMappingByPosition = clazz
				.getAnnotation(ExcelObjectMappingByPosition.class);
		if (excelObjectMappingByTableHead != null) {
			excelMappingInfo = new ExcelMappingInfo();
			excelMappingInfo.setExcelMappingType(ExcelMappingType.TableHeadMapping);
			excelMappingInfo.setSheetName(excelObjectMappingByTableHead.sheetName());
			excelMappingInfo.setSheetIndex(excelObjectMappingByTableHead.sheetIndex());
			excelMappingInfo.setParseType(excelObjectMappingByTableHead.parseType());
			excelMappingInfo.setHeadPosition(excelObjectMappingByTableHead.headPosition());
			excelMappingInfo.setHeadStart(excelObjectMappingByTableHead.headStart());
			excelMappingInfo.setHeadEnd(excelObjectMappingByTableHead.headEnd());
			excelMappingInfo.setDataStart(excelObjectMappingByTableHead.dataStart());
			excelMappingInfo.setDataEnd(excelObjectMappingByTableHead.dataEnd());
			excelMappingInfo.setZeroIfNull(excelObjectMappingByTableHead.zeroIfNull());
		} else if (excelObjectMappingByPosition != null) {
			excelMappingInfo = new ExcelMappingInfo();
			excelMappingInfo.setExcelMappingType(ExcelMappingType.PositionMapping);
			excelMappingInfo.setSheetName(excelObjectMappingByPosition.sheetName());
			excelMappingInfo.setSheetIndex(excelObjectMappingByPosition.sheetIndex());
			excelMappingInfo.setParseType(excelObjectMappingByPosition.parseType());
			excelMappingInfo.setDataStart(excelObjectMappingByPosition.dataStart());
			excelMappingInfo.setDataEnd(excelObjectMappingByPosition.dataEnd());
			excelMappingInfo.setZeroIfNull(excelObjectMappingByPosition.zeroIfNull());
		} else {
			throw new ExcelParsingException(
					"Invalid class configuration - ExcelObjectMappingByTableHead or ExcelObjectMappingByPosition annotation missing - "
							+ clazz.getSimpleName());
		}
		excelMappingInfoMapCache.put(clazz.getName(), excelMappingInfo);
		return excelMappingInfo;
	}

	/**
	 * loadExcelHeadNameMapCache:. 
	 * @since JDK 1.6
	 */
	private <T> void loadHeadNamePositionMapCache(Class<T> clazz, Sheet sheet, ExcelMappingInfo excelMappingInfo)
			throws ExcelParsingException {
		Map<String, Integer> headNamePositionMap = new HashMap<String, Integer>();
		for (int currentLocation = excelMappingInfo.getHeadStart(); excelMappingInfo.getHeadEnd() == 0
				|| currentLocation <= excelMappingInfo.getHeadEnd(); currentLocation++) {
			String headName;
			if (ParseType.ROW == excelMappingInfo.getParseType()) {
				headName = hssfHelper.getCellValue(sheet, String.class, null, excelMappingInfo.getHeadPosition(),
						currentLocation);
			} else {
				headName = hssfHelper.getCellValue(sheet, String.class, null, currentLocation,
						excelMappingInfo.getHeadPosition());
			}
			if (headName != null) {
				headNamePositionMap.put(headName, currentLocation);
			} else {
				break;
			}
		}
		excelMappingInfo.setHeadNamePositionMap(headNamePositionMap);
	}

	/**
	 * Load cached for the given class.
	 * 
	 * @param clazz
	 *            Class object to investigate.
	 * @return Map.
	 */
	private <T> void loadFieldMappingInfoListForImport(Class<T> clazz, ExcelMappingInfo excelMappingInfo) {
		List<ExcelFieldMappingInfo> fieldMappingInfoListForImport = new ArrayList<ExcelFieldMappingInfo>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ExcelFieldMappingByHeadName excelFieldMappingByHeadName = field
					.getAnnotation(ExcelFieldMappingByHeadName.class);
			if (excelFieldMappingByHeadName != null) {
				if (excelMappingInfo.getHeadNamePosition(excelFieldMappingByHeadName.headName()) != null) {
					field.setAccessible(true);

					ExcelFieldMappingInfo excelFieldMappingInfo = new ExcelFieldMappingInfo();
					excelFieldMappingInfo.setField(field);
					excelFieldMappingInfo.setHeadName(excelFieldMappingByHeadName.headName());
					excelFieldMappingInfo
							.setPosition(excelMappingInfo.getHeadNamePosition(excelFieldMappingByHeadName.headName()));
					excelFieldMappingInfo.setImportDataFormat(excelFieldMappingByHeadName.importDataFormat());
					// excelFieldMappingInfo.setExportDataFormat(excelFieldMappingByHeadName.exportDataFormat());
					// excelFieldMappingInfo.setExportFieldWidth(excelFieldMappingByHeadName.exportFieldWidth());
					// excelFieldMappingInfo.setNotExport(excelFieldMappingByHeadName.notExport());

					fieldMappingInfoListForImport.add(excelFieldMappingInfo);
				}
			} else {
				ExcelFieldMappingByPosition excelFieldMappingByPosition = field
						.getAnnotation(ExcelFieldMappingByPosition.class);
				if (excelFieldMappingByPosition != null) {
					field.setAccessible(true);

					ExcelFieldMappingInfo excelFieldMappingInfo = new ExcelFieldMappingInfo();
					excelFieldMappingInfo.setField(field);
					excelFieldMappingInfo.setPosition(excelFieldMappingByPosition.position());
					excelFieldMappingInfo.setImportDataFormat(excelFieldMappingByPosition.importDataFormat());

					fieldMappingInfoListForImport.add(excelFieldMappingInfo);
				}
			}
		}
		excelMappingInfo.setFieldMappingInfoListForImport(fieldMappingInfoListForImport);
	}

	private <T> void loadFieldMappingInfoListForExport(Class<T> clazz, ExcelMappingInfo excelMappingInfo) {
		List<ExcelFieldMappingInfo> fieldMappingInfoListForExport = new ArrayList<ExcelFieldMappingInfo>();
		Field[] fields = clazz.getDeclaredFields();
		int position = excelMappingInfo.getHeadStart() - 1;
		for (Field field : fields) {
			ExcelFieldMappingByHeadName excelFieldMappingByHeadName = field
					.getAnnotation(ExcelFieldMappingByHeadName.class);
			if (excelFieldMappingByHeadName != null) {
				if (excelFieldMappingByHeadName.notExport()) {
					continue;
				}
				field.setAccessible(true);

				ExcelFieldMappingInfo excelFieldMappingInfo = new ExcelFieldMappingInfo();
				excelFieldMappingInfo.setField(field);
				excelFieldMappingInfo.setHeadName(excelFieldMappingByHeadName.headName());
				excelFieldMappingInfo.setPosition(position);
				// excelFieldMappingInfo.setImportDataFormat(excelFieldMappingByHeadName.importDataFormat());
				excelFieldMappingInfo.setExportDataFormat(excelFieldMappingByHeadName.exportDataFormat());
				excelFieldMappingInfo.setExportFieldWidth(excelFieldMappingByHeadName.exportFieldWidth());
				excelFieldMappingInfo.setNotExport(excelFieldMappingByHeadName.notExport());

				fieldMappingInfoListForExport.add(excelFieldMappingInfo);
				++position;
			}

		}
		excelMappingInfo.setFieldMappingInfoListForExport(fieldMappingInfoListForExport);
	}
}
