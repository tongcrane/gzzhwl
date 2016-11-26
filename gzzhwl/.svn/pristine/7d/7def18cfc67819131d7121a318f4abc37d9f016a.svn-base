package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LineInfo;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.dao.LineInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LineInfoDaoImpl implements LineInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LineInfo get(java.lang.String lineInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lineInfoId", lineInfoId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String lineInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lineInfoId", lineInfoId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(LineInfo lineInfo) {
		return dao.insert(PREFIX + ".insert", lineInfo);
	}

	@Override
	public int update(LineInfo lineInfo) {
		return dao.update(PREFIX + ".update", lineInfo);
	}
	
	@Override
	public int updateSelective(LineInfo lineInfo) {
		return dao.update(PREFIX + ".updateSelective", lineInfo);
	}

	@Override
	public int delete(java.lang.String lineInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lineInfoId", lineInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public <T, K, V> List<T> getHotLineList(String accountId,Integer lineCount) {
		
		Map<String,Object> params = new HashMap<>();
		
		params.put("accountId", accountId);
		params.put("lineCount", lineCount);
		params.put("sourceStatusArray", new String[] { SourceType.PUBLISH.getCode() });
		
		return dao.find(PREFIX + ".getHotLineList", params);
	}
}


