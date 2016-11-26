package com.gzzhwl.core.data.extdao;

import java.util.List;
import java.util.Map;

public interface CommonCardInfoExtDao {
	public final static String PREFIX = CommonCardInfoExtDao.class.getName();

	public List<Map<String, Object>> findSourceCardInfo(Map<String, Object> params);

	public List<Map<String, Object>> findQuoteCardInfo(Map<String, Object> params);

}
