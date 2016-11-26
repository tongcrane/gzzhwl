package com.gzzhwl.core.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.dao.PushRecordDao;
import com.gzzhwl.core.data.model.PushRecord;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class PushRecordDaoImpl implements PushRecordDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public int insert(PushRecord pushRecord) {
		return dao.insert(PREFIX + ".insert", pushRecord);
	}
}
