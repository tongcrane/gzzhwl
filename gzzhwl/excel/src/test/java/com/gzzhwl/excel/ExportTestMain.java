package com.gzzhwl.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gzzhwl.template.support.ExcelService;

public class ExportTestMain {
	public static void main(String[] args) throws IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config-poi.xml");
		ExcelService service = ac.getBean(ExcelService.class);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("orderNo", System.currentTimeMillis() + "");
			List<Map<String, Object>> feedBackList = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < 5; j++) {
				Map<String, Object> feedBack = new HashMap<String, Object>();
				feedBack.put("createdTime", DateTime.now().toString("yyyy-MM-dd HH:mm"));
				feedBack.put("createdBy", "adfadf");
				feedBackList.add(feedBack);
			}
			row.put("feedBackList", feedBackList);
			data.add(row);
		}
		OutputStream os = new FileOutputStream(new File("/Users/maenwei/Desktop/test.xlsx"));
		service.export(data, "receivables_export", os);

	}
}
