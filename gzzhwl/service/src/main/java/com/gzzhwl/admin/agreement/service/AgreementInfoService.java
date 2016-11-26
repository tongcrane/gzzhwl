package com.gzzhwl.admin.agreement.service;

import java.util.List;
import java.util.Map;

public interface AgreementInfoService {
	//根据客户ID查询合同列表
	public List<Map<String,Object>> getAgreements(String custId);
	
	//查询合同明细
	public Map<String,Object> getAgreementDetail(String agreementId);
}
