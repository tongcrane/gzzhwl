package org.roundface.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ProReader {
	private Properties properties = null;
	private static ProReader instance = null;

	public static synchronized ProReader getInstance() {
		if (instance == null) {
			instance = new ProReader();
		}
		return instance;
	}

	private ProReader() {
		try {
			properties = new Properties();
			URL url = this.getClass().getClassLoader().getResource("config.properties");
			InputStream is = url.openStream();
			properties.load(is);
		} catch (IOException e) {
			System.err.println("读取属性文件失败，请确认文件是否存在！");
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
