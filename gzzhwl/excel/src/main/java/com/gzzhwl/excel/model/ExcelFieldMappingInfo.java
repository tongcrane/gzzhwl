package com.gzzhwl.excel.model;

import java.lang.reflect.Field;

public class ExcelFieldMappingInfo {
	Field field;
	private String headName;
	private int position;
	private String importDataFormat;
	private String exportDataFormat;
	private int exportFieldWidth;
	boolean notExport;

	/**
	 * field.
	 * 
	 * @return the field
	 * @since JDK 1.6
	 */
	public Field getField() {
		return field;
	}

	/**
	 * field.
	 * 
	 * @param field
	 *            the field to set
	 * @since JDK 1.6
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * headName.
	 * 
	 * @return the headName
	 * @since JDK 1.6
	 */
	public String getHeadName() {
		return headName;
	}

	/**
	 * headName.
	 * 
	 * @param headName
	 *            the headName to set
	 * @since JDK 1.6
	 */
	public void setHeadName(String headName) {
		this.headName = headName;
	}

	/**
	 * position.
	 * 
	 * @return the position
	 * @since JDK 1.6
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * position.
	 * 
	 * @param position
	 *            the position to set
	 * @since JDK 1.6
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * importDataFormat.
	 * 
	 * @return the importDataFormat
	 * @since JDK 1.6
	 */
	public String getImportDataFormat() {
		return importDataFormat;
	}

	/**
	 * importDataFormat.
	 * 
	 * @param importDataFormat
	 *            the importDataFormat to set
	 * @since JDK 1.6
	 */
	public void setImportDataFormat(String importDataFormat) {
		this.importDataFormat = importDataFormat;
	}

	/**
	 * exportDataFormat.
	 * 
	 * @return the exportDataFormat
	 * @since JDK 1.6
	 */
	public String getExportDataFormat() {
		return exportDataFormat;
	}

	/**
	 * exportDataFormat.
	 * 
	 * @param exportDataFormat
	 *            the exportDataFormat to set
	 * @since JDK 1.6
	 */
	public void setExportDataFormat(String exportDataFormat) {
		this.exportDataFormat = exportDataFormat;
	}

	/**
	 * exportFieldWidth.
	 * 
	 * @return the exportFieldWidth
	 * @since JDK 1.6
	 */
	public int getExportFieldWidth() {
		return exportFieldWidth;
	}

	/**
	 * exportFieldWidth.
	 * 
	 * @param exportFieldWidth
	 *            the exportFieldWidth to set
	 * @since JDK 1.6
	 */
	public void setExportFieldWidth(int exportFieldWidth) {
		this.exportFieldWidth = exportFieldWidth;
	}

	/**
	 * notExport.
	 * 
	 * @return the notExport
	 * @since JDK 1.6
	 */
	public boolean isNotExport() {
		return notExport;
	}

	/**
	 * notExport.
	 * 
	 * @param notExport
	 *            the notExport to set
	 * @since JDK 1.6
	 */
	public void setNotExport(boolean notExport) {
		this.notExport = notExport;
	}

}
