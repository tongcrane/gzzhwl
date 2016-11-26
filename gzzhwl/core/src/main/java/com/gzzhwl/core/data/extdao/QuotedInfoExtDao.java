package com.gzzhwl.core.data.extdao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface QuotedInfoExtDao {
	public final static String PREFIX = QuotedInfoExtDao.class.getName();

	public <E, K, V> Page<E> queryQuotedManagerList(Map<K, V> params, int current, int pagesize);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public <T, K, V> List<T> getBidVehicleInfo(String sourceId);

	public <T, K, V> List<T> find(Map<K, V> params);

	public Integer getOrderCount(String accountId, String[] statusArray);

	public <E, K, V> Page<E> getQuotedList(Map<K, V> params, int current, int pagesize);

	public <K, V> Map<K, V> getLoadVADInfo(String loadId);

	/**
	 * 获取中标信息
	 */
	public <K, V> Map<K, V> queryBidQuotedInfo(Map<K, V> params);
}
