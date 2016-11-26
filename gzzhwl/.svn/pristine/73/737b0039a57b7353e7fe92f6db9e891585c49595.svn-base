package com.gzzhwl.template.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Data;
import lombok.ToString;

/**
 * POI导出模板xml配置文件解析类
 */
@XStreamAlias("column")
@Data
@ToString
public class ExcelRow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7710235924500284979L;
	@XStreamAlias("title")
	@XStreamAsAttribute
	private String title;

	@XStreamAlias("key")
	@XStreamAsAttribute
	private String key;

	@XStreamAlias("parse")
	@XStreamAsAttribute
	private String parse;
}
