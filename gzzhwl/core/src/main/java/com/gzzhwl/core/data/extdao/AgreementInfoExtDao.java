package com.gzzhwl.core.data.extdao;

import java.util.List;

public interface AgreementInfoExtDao {
	public final static String PREFIX = AgreementInfoExtDao.class.getName();
	public <T, K, V> List<T> getAgreementsByCustId(String custId);
}
