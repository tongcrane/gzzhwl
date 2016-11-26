package com.gzzhwl.template.callable.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gzzhwl.template.annotations.RowParse;
import com.gzzhwl.template.callable.RowParseCallable;

@RowParse("common-parse")
public class CommonRowParse implements RowParseCallable {

	@Override
	public String parse(XSSFWorkbook workbook, XSSFSheet sheet, XSSFCell cell, String key, Object value,
			Map<String, Object> rowData) {
		if (value != null) {
			return (String) value;
		}
		return StringUtils.EMPTY;
	}

}
