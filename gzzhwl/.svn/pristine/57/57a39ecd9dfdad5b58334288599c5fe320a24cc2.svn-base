package com.gzzhwl.template.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Data;
import lombok.ToString;

@XStreamAlias("export")
@Data
@ToString
public class ExcelTemplate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -123705566913167969L;

	@XStreamImplicit(itemFieldName = "template")
	private List<ExcelSheet> template;

}
