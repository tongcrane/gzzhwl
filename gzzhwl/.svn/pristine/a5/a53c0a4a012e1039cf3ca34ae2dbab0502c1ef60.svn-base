package ${config.extDaoImpl};


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ${config.model}.${table.className};
import ${config.extdao}.${table.className}ExtDao;
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
public class ${table.className}ExtDaoImpl implements ${table.className}ExtDao {
	@Autowired	
	private DaoSupport dao;

	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


