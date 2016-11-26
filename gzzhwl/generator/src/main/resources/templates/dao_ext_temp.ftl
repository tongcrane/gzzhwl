package ${config.extdao};


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
public interface ${table.className}ExtDao {    
    public final static String PREFIX = ${table.className}ExtDao.class.getName();
    
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


