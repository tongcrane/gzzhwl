package com.gzzhwl.cbs.support.impl;

import java.lang.reflect.Method;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;

import com.gzzhwl.cbs.support.PropertiesSupport;

/**
 * Properties工具类
 */
@Component
public class PropertiesSupportImpl implements PropertiesSupport, InitializingBean {
	@Autowired
	private PropertyPlaceholderConfigurer propertyResourceConfigurer;
	private Properties properties;

	/**
	 * 获取配置文件中的值
	 */
	@Override
	public String getValue(String key) {
		return properties.getProperty(key, null);
	}

	/**
	 * 初始化工具类，将配置在spring context中的properties文件注入到对象中
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		properties = new Properties();
		Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
		mergeProperties.setAccessible(true);
		Properties props = (Properties) mergeProperties.invoke(propertyResourceConfigurer);
		Method convertProperties = PropertyResourceConfigurer.class.getDeclaredMethod("convertProperties",
				Properties.class);
		convertProperties.setAccessible(true);
		convertProperties.invoke(propertyResourceConfigurer, props);
		properties.putAll(props);
	}

}
