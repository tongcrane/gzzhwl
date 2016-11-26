package com.gzzhwl.core.data.extdao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.QuotedType;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.extdao.OrderSourceInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.DateUtils;

/**
 * 数据访问接口
 * 
 * @author mew
 *
 */
@Repository
public class OrderSourceInfoExtDaoImpl implements OrderSourceInfoExtDao {
	@Autowired
	private DaoSupport dao;

	@Override
	public <K, V> Map<K, V> getQuotedSourceDetail(String sourceId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sourceId", sourceId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.get(PREFIX + ".getQuotedSourceDetail", params);
	}

	@Override
	public <E, K, V> Page<E> getOrderSourcePage(Map<K, V> params, int current, int pagesize) {

		return dao.page(PREFIX + ".getOrderSourcePage", params, current, pagesize);
	}

	@Override
	public <E, K, V> Page<E> getHotLineSourcePage(String accountId, String startCodeP, int current, int pagesize) {

		Map<String, Object> params = new HashMap<>();
		params.put("accountId", accountId);
		params.put("startCodeP", startCodeP);

		params.put("sourceStatusArray", new String[] { SourceType.PUBLISH.getCode() });

		return dao.page(PREFIX + ".getHotLineSourceList", params, current, pagesize);
	}

	@Override
	public <E, K, V> Page<E> getTmpHotLineSourcePage(String startCodeC, String endCodeC, int current, int pagesize) {

		Map<String, Object> params = new HashMap<>();
		params.put("startCodeC", startCodeC);
		params.put("endCodeC", endCodeC);

		params.put("sourceStatusArray", new String[] { SourceType.PUBLISH.getCode() });

		return dao.page(PREFIX + ".getHotLineSourceList", params, current, pagesize);
	}

	@Override
	public <T, K, V> List<T> getNewSource(int count) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", SourceType.PUBLISH.getCode());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		params.put("count", count);
		return dao.find(PREFIX + ".getNewSource", params);
	}

	@Override
	public int getNewSourceCnt() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", SourceType.PUBLISH.getCode());
		Date currentDate = DateUtils.currentDate();
		String startDate = DateUtils.toString(currentDate);
		String endDate = DateUtils.toString(DateUtils.add(Calendar.DAY_OF_MONTH, currentDate, 1));
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.get(PREFIX + ".getNewSourceCnt", params);
	}

	@Override
	public int getQutoSuccessCnt() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", QuotedType.NOTIMPROVE.getCode() + "," + QuotedType.ONLINE.getCode()+ "," + QuotedType.ONLINE_TRIP.getCode()+ "," + QuotedType.COMPLETE.getCode());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.get(PREFIX + ".getQutoSuccessCnt", params);
	}

	@Override
	public <E, K, V> Page<E> getRecommandSourcePage(Map<String, Object> params, int current, int pagesize) {
		
		return dao.page(PREFIX + ".getRecommandSourceList", params, current, pagesize);
	}

}
