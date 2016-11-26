package com.gzzhwl.template.parse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.gzzhwl.template.model.ExcelSheet;
import com.gzzhwl.template.model.ExcelTemplate;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * xml配置文件解析工具类
 */
public class ExcelXmlParse {
	private Map<String, ExcelSheet> cache;

	/**
	 * 构造方法 解析poi导出xml模板，缓存至内存
	 */
	public ExcelXmlParse(String config) throws IOException {
		Resource resource = new ClassPathResource(config);
		XStream xstream = new XStream(new XppDriver());
		xstream.ignoreUnknownElements();
		xstream.processAnnotations(ExcelTemplate.class);
		ExcelTemplate template = (ExcelTemplate) xstream.fromXML(resource.getInputStream());
		List<ExcelSheet> sheetList = template.getTemplate();
		cache = new HashMap<String, ExcelSheet>();
		for (ExcelSheet sheet : sheetList) {
			String id = sheet.getId();
			cache.put(id, sheet);
		}
	}

	/**
	 * 获取模板内容
	 */
	public ExcelSheet getSheet(String id) {
		return cache.get(id);
	}
}
