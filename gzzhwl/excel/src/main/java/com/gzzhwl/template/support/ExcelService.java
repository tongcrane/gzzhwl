package com.gzzhwl.template.support;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.gzzhwl.template.callable.RowParseStrategyFactory;
import com.gzzhwl.template.handler.ExportHandler;
import com.gzzhwl.template.model.ExcelSheet;
import com.gzzhwl.template.parse.ExcelXmlParse;

public class ExcelService {
	private RowParseStrategyFactory rowParse;

	private ExcelXmlParse xmlParse;

	public void setXmlParse(ExcelXmlParse xmlParse) {
		this.xmlParse = xmlParse;
	}

	public void setRowParse(RowParseStrategyFactory rowParse) {
		this.rowParse = rowParse;
	}

	/**
	 * excel导出方法
	 */
	public void export(List<Map<String, Object>> data, String templateId, OutputStream outstream) throws IOException {
		ExcelSheet sheet = xmlParse.getSheet(templateId);
		ExportHandler rowHandler = new ExportHandler(sheet, rowParse);
		rowHandler.processRow(data);
		rowHandler.write(outstream);
	}
}
