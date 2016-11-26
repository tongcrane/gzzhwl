package ${config.model};

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * ${table.cnName?if_exists}
 * ${table.tabelName?if_exists}表
 * @author mew
 *
 */
@Data
@ToString
public class ${table.className} implements Serializable {
<#list table.cloums as c>

	<#if c.javaType == "java.lang.String">
	@Length(max = ${c.size}, message = "${c.propertyName}超过长度限制")
	</#if>
	private ${c.javaType} ${c.propertyName}; <#if c.cnName??>// ${c.cnName}</#if>
</#list>
}