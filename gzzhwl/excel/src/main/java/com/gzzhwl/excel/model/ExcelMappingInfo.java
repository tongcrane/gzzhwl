
package com.gzzhwl.excel.model;

import java.util.List;
import java.util.Map;

import com.gzzhwl.excel.enums.ExcelMappingType;
import com.gzzhwl.excel.enums.ParseType;

public class ExcelMappingInfo {
	private ExcelMappingType excelMappingType;
	private String sheetName;
	private int sheetIndex;
	private ParseType parseType;
	private int headPosition;
	private int headStart;
	private int headEnd;
	private int dataStart;
	private int dataEnd;
	private boolean zeroIfNull;

	private Map<String, Integer> headNamePositionMap;
	private List<ExcelFieldMappingInfo> fieldMappingInfoListForImport;
	private List<ExcelFieldMappingInfo> fieldMappingInfoListForExport;

	/**
	 * excelMappingType.
	 * 
	 * @return the excelMappingType
	 * @since JDK 1.6
	 */
	public ExcelMappingType getExcelMappingType() {
		return excelMappingType;
	}

	/**
	 * excelMappingType.
	 * 
	 * @param excelMappingType
	 *            the excelMappingType to set
	 * @since JDK 1.6
	 */
	public void setExcelMappingType(ExcelMappingType excelMappingType) {
		this.excelMappingType = excelMappingType;
	}

	/**
	 * sheetName.
	 * 
	 * @return the sheetName
	 * @since JDK 1.6
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * sheetName.
	 * 
	 * @param sheetName
	 *            the sheetName to set
	 * @since JDK 1.6
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * sheetIndex.
	 * 
	 * @return the sheetIndex
	 * @since JDK 1.6
	 */
	public int getSheetIndex() {
		return sheetIndex;
	}

	/**
	 * sheetIndex.
	 * 
	 * @param sheetIndex
	 *            the sheetIndex to set
	 * @since JDK 1.6
	 */
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	/**
	 * parseType.
	 * 
	 * @return the parseType
	 * @since JDK 1.6
	 */
	public ParseType getParseType() {
		return parseType;
	}

	/**
	 * parseType.
	 * 
	 * @param parseType
	 *            the parseType to set
	 * @since JDK 1.6
	 */
	public void setParseType(ParseType parseType) {
		this.parseType = parseType;
	}

	/**
	 * headPosition.
	 * 
	 * @return the headPosition
	 * @since JDK 1.6
	 */
	public int getHeadPosition() {
		return headPosition;
	}

	/**
	 * headPosition.
	 * 
	 * @param headPosition
	 *            the headPosition to set
	 * @since JDK 1.6
	 */
	public void setHeadPosition(int headPosition) {
		this.headPosition = headPosition;
	}

	/**
	 * headStart.
	 * 
	 * @return the headStart
	 * @since JDK 1.6
	 */
	public int getHeadStart() {
		return headStart;
	}

	/**
	 * headStart.
	 * 
	 * @param headStart
	 *            the headStart to set
	 * @since JDK 1.6
	 */
	public void setHeadStart(int headStart) {
		this.headStart = headStart;
	}

	/**
	 * headEnd.
	 * 
	 * @return the headEnd
	 * @since JDK 1.6
	 */
	public int getHeadEnd() {
		return headEnd;
	}

	/**
	 * headEnd.
	 * 
	 * @param headEnd
	 *            the headEnd to set
	 * @since JDK 1.6
	 */
	public void setHeadEnd(int headEnd) {
		this.headEnd = headEnd;
	}

	/**
	 * dataStart.
	 * 
	 * @return the dataStart
	 * @since JDK 1.6
	 */
	public int getDataStart() {
		return dataStart;
	}

	/**
	 * dataStart.
	 * 
	 * @param dataStart
	 *            the dataStart to set
	 * @since JDK 1.6
	 */
	public void setDataStart(int dataStart) {
		this.dataStart = dataStart;
	}

	/**
	 * dataEnd.
	 * 
	 * @return the dataEnd
	 * @since JDK 1.6
	 */
	public int getDataEnd() {
		return dataEnd;
	}

	/**
	 * dataEnd.
	 * 
	 * @param dataEnd
	 *            the dataEnd to set
	 * @since JDK 1.6
	 */
	public void setDataEnd(int dataEnd) {
		this.dataEnd = dataEnd;
	}

	/**
	 * zeroIfNull.
	 * 
	 * @return the zeroIfNull
	 * @since JDK 1.6
	 */
	public boolean isZeroIfNull() {
		return zeroIfNull;
	}

	/**
	 * zeroIfNull.
	 * 
	 * @param zeroIfNull
	 *            the zeroIfNull to set
	 * @since JDK 1.6
	 */
	public void setZeroIfNull(boolean zeroIfNull) {
		this.zeroIfNull = zeroIfNull;
	}

	/**
	 * headNamePositionMapCache.
	 * 
	 * @return the headNamePositionMapCache
	 * @since JDK 1.6
	 */
	public Map<String, Integer> getHeadNamePositionMap() {
		return headNamePositionMap;
	}

	/**
	 * headNamePositionMapCache.
	 * 
	 * @param headNamePositionMap
	 *            the headNamePositionMapCache to set
	 * @since JDK 1.6
	 */
	public void setHeadNamePositionMap(Map<String, Integer> headNamePositionMap) {
		this.headNamePositionMap = headNamePositionMap;
	}

	/**
	 * fieldMappingInfoListForImport.
	 * 
	 * @return the fieldMappingInfoListForImport
	 * @since JDK 1.6
	 */
	public List<ExcelFieldMappingInfo> getFieldMappingInfoListForImport() {
		return fieldMappingInfoListForImport;
	}

	/**
	 * fieldMappingInfoListForImport.
	 * 
	 * @param fieldMappingInfoListForImport
	 *            the fieldMappingInfoListForImport to set
	 * @since JDK 1.6
	 */
	public void setFieldMappingInfoListForImport(List<ExcelFieldMappingInfo> fieldMappingInfoListForImport) {
		this.fieldMappingInfoListForImport = fieldMappingInfoListForImport;
	}

	/**
	 * fieldMappingInfoListForExport.
	 * 
	 * @return the fieldMappingInfoListForExport
	 * @since JDK 1.6
	 */
	public List<ExcelFieldMappingInfo> getFieldMappingInfoListForExport() {
		return fieldMappingInfoListForExport;
	}

	/**
	 * fieldMappingInfoListForExport.
	 * 
	 * @param fieldMappingInfoListForExport
	 *            the fieldMappingInfoListForExport to set
	 * @since JDK 1.6
	 */
	public void setFieldMappingInfoListForExport(List<ExcelFieldMappingInfo> fieldMappingInfoListForExport) {
		this.fieldMappingInfoListForExport = fieldMappingInfoListForExport;
	}

	public Integer getHeadNamePosition(String headName) {
		return headNamePositionMap.get(headName);
	}

}
