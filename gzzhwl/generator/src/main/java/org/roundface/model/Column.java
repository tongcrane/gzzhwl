package org.roundface.model;

import org.roundface.parser.JavaTypeResolverDefaultImpl;
import org.roundface.parser.JdbcTypeNameTranslator;
import org.roundface.util.StringUtility;

/**
 * 
 * @author mew
 * 
 */
public class Column {

	private String cloumName;
	private String cnName;
	private boolean notNull;
	private int size;
	private int digits;
	private int dataType;

	public String getCloumName() {
		return cloumName;
	}

	public void setCloumName(String cloumName) {
		this.cloumName = cloumName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getJavaType() {
		return new JavaTypeResolverDefaultImpl().calculateJavaTypeName(this);
	}

	public String getJdbcType() {
		return JdbcTypeNameTranslator.getJdbcTypeName(dataType);
	}

	public String getPropertyName() {
		return StringUtility.getCamelCaseString(cloumName, false);
	}

	public String getMethodName() {
		return StringUtility.getCamelCaseString(cloumName, true);
	}

	public String getDictModuleCode(Tabel t) {
		return t.getFixTableName() + "_" + cloumName;
	}
}
