package com.gzzhwl.template.model;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Data;
import lombok.ToString;

@XStreamAlias("template")
@Data
@ToString
public class ExcelSheet implements Serializable {
	private static final long serialVersionUID = 8752340895098882283L;

	@XStreamAlias("id")
	@XStreamAsAttribute
	private String id;

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String name;

	@XStreamImplicit(itemFieldName = "column")
	private List<ExcelRow> rows;

}
