package ${config.daoImpl};


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ${config.model}.${table.className};
import ${config.dao}.${table.className}Dao;
import ${config.base}.mybatis.support.DaoSupport;
import ${config.base}.page.Page;

<#if table.cnName??>
/**
 * ${table.cnName}数据访问接口
 * @author mew
 *
 */
</#if>
@Repository
public class ${table.className}DaoImpl implements ${table.className}Dao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public ${table.className} get(<#list table.pkCloums as pk>${pk.javaType} ${pk.propertyName}<#if pk_has_next>, </#if></#list>) {
		Map<String, Object> params = new HashMap<String, Object>();
		<#list table.pkCloums as pk>
		params.put("${pk.propertyName}", ${pk.propertyName});
		</#list>		
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(<#list table.pkCloums as pk>${pk.javaType} ${pk.propertyName}<#if pk_has_next>, </#if></#list>) {
		Map<String, Object> params = new HashMap<String, Object>();
		<#list table.pkCloums as pk>
		params.put("${pk.propertyName}", ${pk.propertyName});
		</#list>		
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(${table.className} ${table.varName}) {
		return dao.insert(PREFIX + ".insert", ${table.varName});
	}

	@Override
	public int update(${table.className} ${table.varName}) {
		return dao.update(PREFIX + ".update", ${table.varName});
	}
	
	@Override
	public int updateSelective(${table.className} ${table.varName}) {
		return dao.update(PREFIX + ".updateSelective", ${table.varName});
	}

	@Override
	public int delete(<#list table.pkCloums as pk>${pk.javaType} ${pk.propertyName}<#if pk_has_next>, </#if></#list>) {
		Map<String, Object> params = new HashMap<String, Object>();
		<#list table.pkCloums as pk>
		params.put("${pk.propertyName}", ${pk.propertyName});
		</#list>
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


