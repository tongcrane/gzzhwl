package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.SmsCaptcha;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SmsCaptchaDao {    
    public final static String PREFIX = SmsCaptchaDao.class.getName();
    
	public SmsCaptcha get(java.lang.String captchaId);
	
	public <K, V> Map<K, V> findOne(java.lang.String captchaId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(SmsCaptcha smsCaptcha);
	
	public int update(SmsCaptcha smsCaptcha);
	
	public int updateSelective(SmsCaptcha smsCaptcha);
	
	public int delete(java.lang.String captchaId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public <T, K, V> List<T> findCountSameDay(Map<K, V> params);
	
	public <K, V> Map<K, V> findOneByTel(java.lang.String tel,java.lang.String type);
	
	public int deleteExpiredCaptcha(Map<String, Object> params);

}


