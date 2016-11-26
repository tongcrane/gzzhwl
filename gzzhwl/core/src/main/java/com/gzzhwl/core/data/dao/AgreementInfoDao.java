package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.AgreementInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface AgreementInfoDao {    
    public final static String PREFIX = AgreementInfoDao.class.getName();
    
	public AgreementInfo get(java.lang.String agreementId);
	
	public <K, V> Map<K, V> findOne(java.lang.String agreementId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(AgreementInfo agreementInfo);
	
	public int update(AgreementInfo agreementInfo);
	
	public int updateSelective(AgreementInfo agreementInfo);
	
	public int delete(java.lang.String agreementId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


