package com.gzzhwl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;

import com.gzzhwl.excel.enums.ExcelFileType;
import com.gzzhwl.excel.exception.ExcelParsingException;

public class TestMain {

	public static void main(String[] args) {
		ExcelUtil excel = new ExcelUtil();
		try {
			InputStream is = new FileInputStream(new File("/Users/maenwei/Desktop/hr.xlsx"));
			List<AccountInfo> data = excel.importExcel(is, AccountInfo.class, ExcelFileType.XLSX);
			String template = "insert into zh_staff_info VALUES(uuid(),''{0}'', md5(rand()),''{1}'',''{2}'',''{3}'',''{4}'',{5},''02'',''1c4b00ff-e194-4fbe-8931-7d9188ed8031'',now(),now(),''00'',''01'');";
			for (AccountInfo ai : data) {
				String sql = MessageFormat.format(template, ai.getNumber(), ai.getName(), ai.getPosition(),
						ai.getTelphone(), ai.getEmail(), ai.getDepartId());
				System.out.println(sql);
			}
		} catch (IOException | ExcelParsingException e) {
			e.printStackTrace();
		}

	}

}
