package com.gzzhwl.core.data.extdao.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.extdao.LendLogExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LendLogExtDaoImpl implements LendLogExtDao {
	@Autowired	
	private DaoSupport dao;



	@Override
	public <E, K, V> Page<E> getLendLogPage(Map<String, Object> params, int current, int pagesize) {
		
		return dao.page(PREFIX + ".getLendLogPage", params, current, pagesize);
	}
}


