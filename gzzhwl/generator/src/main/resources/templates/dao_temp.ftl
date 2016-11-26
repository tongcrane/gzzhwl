package ${config.dao};


import java.util.Map;
import java.util.List;
import ${config.model}.${table.className};
import ${config.base}.page.Page;

<#if table.cnName??>
/**
 * ${table.cnName}数据访问接口
 *
 */
</#if>
public interface ${table.className}Dao {    
    public final static String PREFIX = ${table.className}Dao.class.getName();
    
	public ${table.className} get(<#list table.pkCloums as pk>${pk.javaType} ${pk.propertyName}<#if pk_has_next>, </#if></#list>);
	
	public <K, V> Map<K, V> findOne(<#list table.pkCloums as pk>${pk.javaType} ${pk.propertyName}<#if pk_has_next>, </#if></#list>);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(${table.className} ${table.varName});
	
	public int update(${table.className} ${table.varName});
	
	public int updateSelective(${table.className} ${table.varName});
	
	public int delete(<#list table.pkCloums as pk>${pk.javaType} ${pk.propertyName} <#if pk_has_next>, </#if></#list>);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


