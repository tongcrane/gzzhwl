package com.gzzhwl.excel.helper;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.gzzhwl.excel.exception.ExcelParsingException;
import com.gzzhwl.excel.model.ExcelFieldMappingInfo;

public class SSFHelper {

	private static final String DATA_TYPE_NOT_SUPPORTED = "{0} Data type not supported for parsing";
	private static final String INVALID_NUMBER_FORMAT = "Invalid number found in sheet {0} at row {1}, column {2}";
	private static final String INVALID_DATE_FORMAT = "Invalid date found in sheet {0} at row {1}, column {2}";;

	// private static Logger LOGGER = Logger.getLogger(HSSFHelper.class);

	/**
	 * Returns the cell value. Supports Integer, Float, Double, Long, String,
	 * Date.
	 * 
	 * @param sheet
	 *            HSSF Sheet.
	 * @param sheetName
	 *            Sheet name.
	 * @param type
	 *            Class (Integer, Double, etc.)
	 * @param row
	 *            Row number (Same as excelsheet). API will reduce -1 and invoke
	 *            POI API.
	 * @param col
	 *            Column number (Same as excelsheet). API will reduce -1 and
	 *            invoke POI API.
	 * @param zeroIfNull
	 *            whether Zero should be returned for Number fields when data is
	 *            not found in excel.
	 * @return Class.
	 * @throws ExcelParsingException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getCellValue(Sheet sheet, Class<T> type, String dataFormat, Integer row, Integer col)
			throws ExcelParsingException {
		Cell cell = getCell(sheet, row, col);
		if (type.equals(String.class)) {
			return cell == null ? null : (T) getStringCell(cell, dataFormat);
		} else if (type.equals(Date.class)) {
			return cell == null ? null : (T) getDateCell(cell, sheet.getSheetName(), row, col);
		} else if (type.equals(Integer.class)) {
			return (T) getIntegerCell(cell, sheet.getSheetName(), row, col);
		} else if (type.equals(Float.class)) {
			return (T) getFloatCell(cell, sheet.getSheetName(), row, col);
		} else if (type.equals(Double.class)) {
			return (T) getDoubleCell(cell, sheet.getSheetName(), row, col);
		} else if (type.equals(Long.class)) {
			return (T) getLongCell(cell, sheet.getSheetName(), row, col);
		} else if (type.equals(Boolean.class)) {
			return (T) getBooleanCell(cell, sheet.getSheetName(), row, col);
		}
		throw new ExcelParsingException(getErrorMessage(DATA_TYPE_NOT_SUPPORTED, type.getName()));
	}

	/**
	 * setCellValue <br/>
	 * 
	 * @author yuhui.mo
	 * @param cell
	 * @param value
	 * @throws ExcelParsingException
	 * @since JDK 1.6
	 */
	public void setCellValue(Cell cell, Object value, ExcelFieldMappingInfo excelFieldMappingInfo)
			throws ExcelParsingException {
		if (value == null) {
			return;
		}
		if (value instanceof Integer) {
			int intValue = (Integer) value;
			cell.setCellValue(intValue);
		} else if (value instanceof Float) {
			float fValue = (Float) value;
			cell.setCellValue(fValue);
		} else if (value instanceof Double) {
			double dValue = (Double) value;
			cell.setCellValue(dValue);
		} else if (value instanceof Long) {
			long longValue = (Long) value;
			cell.setCellValue(longValue);
		} else if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			cell.setCellValue(bValue);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(excelFieldMappingInfo.getExportDataFormat());
			String textValue = sdf.format(date);
			cell.setCellValue(textValue);
		} else if (value instanceof String) {
			String textValue = value.toString();
			Pattern p = Pattern.compile("^//d+(//.//d+)?{1}");
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
				// 是数字当作double处理
				cell.setCellValue(Double.parseDouble(textValue));
			} else {
				cell.setCellValue(textValue);
			}
		} else {
			throw new ExcelParsingException(getErrorMessage(DATA_TYPE_NOT_SUPPORTED, value.getClass().getName()));
		}
	}

	/**
	 * Gets the cell in a sheet in the given row and column.
	 */
	Cell getCell(Sheet sheet, int rowNumber, int columnNumber) {
		Row row = sheet.getRow(rowNumber - 1);
		return row == null ? null : row.getCell(columnNumber - 1);
	}

	/**
	 * Gets the value of string in the cell.
	 * 
	 * @param cell
	 *            TODO
	 * 
	 * @return date present in the given cell.
	 * @throws ExcelParsingException
	 *             if the cell is of wrong type or the given location of cell is
	 *             invalid.
	 */
	String getStringCell(Cell cell, String dataFormat) {
		String value = null;
		if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			int type = cell.getCachedFormulaResultType();
			switch (type) {
			case Cell.CELL_TYPE_STRING:
				RichTextString richTextString = cell.getRichStringCellValue();
				if (richTextString != null) {
					value = richTextString.getString();
				}
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
					if (date != null) {
						if (dataFormat == null) {
							dataFormat = "yyyy-MM-dd HH:mm:ss";
						}
						SimpleDateFormat formatter = new SimpleDateFormat(dataFormat);
						value = formatter.format(date).toString();
					} else {
						double doubleNum = cell.getNumericCellValue();
						long longNum = new Double(doubleNum).longValue();
						if (doubleNum == longNum) {
							value = "" + longNum;
						} else {
							value = "" + doubleNum;
						}
					}
				} else {
					double doubleNum = cell.getNumericCellValue();
					long longNum = new Double(doubleNum).longValue();
					if (doubleNum == longNum) {
						value = "" + longNum;
					} else {
						value = "" + doubleNum;
					}
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue() ? "TRUE" : "FALSE";
				break;
			default:
				break;
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			RichTextString richTextString = cell.getRichStringCellValue();
			if (richTextString != null) {
				value = richTextString.getString();
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
				if (date != null) {
					if (dataFormat == null) {
						dataFormat = "yyyy-MM-dd HH:mm:ss";
					}
					SimpleDateFormat formatter = new SimpleDateFormat(dataFormat);
					value = formatter.format(date).toString();
				} else {
					double doubleNum = cell.getNumericCellValue();
					long longNum = new Double(doubleNum).longValue();
					if (doubleNum == longNum) {
						value = "" + longNum;
					} else {
						value = "" + doubleNum;
					}
				}
			} else {
				double doubleNum = cell.getNumericCellValue();
				long longNum = new Double(doubleNum).longValue();
				if (doubleNum == longNum) {
					value = "" + longNum;
				} else {
					value = "" + doubleNum;
				}
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			value = cell.getBooleanCellValue() ? "TRUE" : "FALSE";
		}

		if (value != null) {
			value = value.trim();
			if (value.length() <= 0) {
				value = null;
			}
		}
		return value;
	}

	/**
	 * Gets the value of date cell.
	 * 
	 * @param cell
	 *            TODO
	 * @param sheetName
	 *            Sheet Name
	 * @param rowNumber
	 *            the row number where the cell is placed.
	 * @param columnNumber
	 *            the column number where the cell is placed
	 * 
	 * @return date present in the given cell.
	 * @throws ExcelParsingException
	 *             if the cell is of wrong type or the given location of cell is
	 *             invalid.
	 */
	Date getDateCell(Cell cell, Object... errorMessageArgs) throws ExcelParsingException {
		try {
			if (!DateUtil.isCellDateFormatted(cell)) {
				throw new ExcelParsingException(getErrorMessage(INVALID_DATE_FORMAT, errorMessageArgs));
			}
		} catch (IllegalStateException illegalStateException) {
			throw new ExcelParsingException(getErrorMessage(INVALID_DATE_FORMAT, errorMessageArgs));
		}
		return DateUtil.getJavaDate(cell.getNumericCellValue());
	}

	/**
	 * @param errorMessage
	 *            Error Message.
	 * @param errorMessageArgs
	 *            arguments.
	 * @return
	 */
	private String getErrorMessage(String errorMessage, Object... errorMessageArgs) {
		return MessageFormat.format(errorMessage, errorMessageArgs);
	}

	Double getDoubleCell(Cell cell, Object... errorMessageArgs) throws ExcelParsingException {
		if (cell == null) {
			return null;
		}
		Double value = null;

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			int type = cell.getCachedFormulaResultType();
			if (type == Cell.CELL_TYPE_NUMERIC) {
				value = cell.getNumericCellValue();
			} else if (type == Cell.CELL_TYPE_STRING) {
				try {
					String s = cell.getRichStringCellValue().getString();
					if (s != null) {
						s = s.trim();
					}
					value = Double.parseDouble(s);
				} catch (Exception e) {
					throw new ExcelParsingException(getErrorMessage(INVALID_NUMBER_FORMAT, errorMessageArgs));
				}
			}
			break;
		case Cell.CELL_TYPE_STRING:
			try {
				String s = cell.getRichStringCellValue().getString();
				if (s != null) {
					s = s.trim();
				}
				value = Double.parseDouble(s);
			} catch (Exception e) {
				throw new ExcelParsingException(getErrorMessage(INVALID_NUMBER_FORMAT, errorMessageArgs));
			}
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		default:
			throw new ExcelParsingException(getErrorMessage(INVALID_NUMBER_FORMAT, errorMessageArgs));
		}
		return value;
	}

	Float getFloatCell(Cell cell, Object... errorMessageArgs) throws ExcelParsingException {
		Double doubleValue = getDoubleCell(cell, errorMessageArgs);
		return doubleValue == null ? null : doubleValue.floatValue();
	}

	Long getLongCell(Cell cell, Object... errorMessageArgs) throws ExcelParsingException {
		Double doubleValue = getNumberWithoutDecimals(cell, errorMessageArgs);
		return doubleValue == null ? null : doubleValue.longValue();
	}

	Integer getIntegerCell(Cell cell, Object... errorMessageArgs) throws ExcelParsingException {
		Double doubleValue = getNumberWithoutDecimals(cell, errorMessageArgs);
		return doubleValue == null ? null : doubleValue.intValue();
	}

	private Double getNumberWithoutDecimals(Cell cell, Object... errorMessageArgs) throws ExcelParsingException {
		Double doubleValue = getDoubleCell(cell, errorMessageArgs);
		if (doubleValue != null && doubleValue % 1 != 0) {
			throw new ExcelParsingException(getErrorMessage(INVALID_NUMBER_FORMAT, errorMessageArgs));
		}
		return doubleValue;
	}

	Boolean getBooleanCell(Cell cell, String sheetName, Integer row, Integer col) {
		Boolean value = null;
		int type = cell.getCachedFormulaResultType();
		if (type == Cell.CELL_TYPE_BOOLEAN || type == Cell.CELL_TYPE_FORMULA) {
			value = cell.getBooleanCellValue();
		}
		return value;
	}
}
