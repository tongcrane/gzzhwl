package com.gzzhwl.core.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.PushDeviceDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class PushDeviceDaoImpl implements PushDeviceDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

}
