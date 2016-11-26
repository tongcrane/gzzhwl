package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.SmsCaptcha;
import com.gzzhwl.core.data.dao.SmsCaptchaDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class SmsCaptchaDaoImpl implements SmsCaptchaDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public SmsCaptcha get(java.lang.String captchaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("captchaId", captchaId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String captchaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("captchaId", captchaId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(SmsCaptcha smsCaptcha) {
		return dao.insert(PREFIX + ".insert", smsCaptcha);
	}

	@Override
	public int update(SmsCaptcha smsCaptcha) {
		return dao.update(PREFIX + ".update", smsCaptcha);
	}
	
	@Override
	public int updateSelective(SmsCaptcha smsCaptcha) {
		return dao.update(PREFIX + ".updateSelective", smsCaptcha);
	}

	@Override
	public int delete(java.lang.String captchaId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("captchaId", captchaId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
	
	@Override
	public <T, K, V> List<T> findCountSameDay(Map<K, V> params) {
		return dao.find(PREFIX + ".findCountSameDay", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOneByTel(String tel,String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("telphone", tel);
		params.put("type", type);
		return dao.get(PREFIX + ".findOneByTel", params);
	}
	
	@Override
	public int deleteExpiredCaptcha(Map<String, Object> params) {
		return dao.update(PREFIX + ".deleteExpiredCaptcha", params);
	}
}


