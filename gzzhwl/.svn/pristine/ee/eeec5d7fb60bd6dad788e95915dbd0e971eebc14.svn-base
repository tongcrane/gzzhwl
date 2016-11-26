package com.gzzhwl.core.data.extdao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.extdao.CustomerInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

@Repository
public class CustomerInfoExtDaoImpl implements CustomerInfoExtDao{
	@Autowired	
	private DaoSupport dao;
	
	public <E, K, V> Page<E> pageCustList(Map<K,V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".pageCustList", params, current, pagesize);
	}
	
	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}
}
