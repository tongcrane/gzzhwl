package com.gzzhwl.admin.agreement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.agreement.service.AgreementInfoService;
import com.gzzhwl.admin.agreement.validate.AgreementValidate;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.data.dao.AgreementInfoDao;
import com.gzzhwl.core.data.extdao.AgreementInfoExtDao;
import com.gzzhwl.core.data.model.AgreementInfo;
import com.gzzhwl.core.data.model.RegionInfo;

@Service
public class AgreementInfoServiceImpl implements AgreementInfoService {
	@Autowired
	private AgreementInfoExtDao agreementExtDao;
	@Autowired
	private AgreementInfoDao agreementDao;
	@Autowired
	private RegionService reginService;
	@Autowired
	private Mapper beanMapper;

	@Override
	public List<Map<String, Object>> getAgreements(String custId) {
		List<Map<String, Object>> agreements = agreementExtDao.getAgreementsByCustId(custId);
		for (Map<String, Object> map : agreements) {
			String startCodeP = (String) map.get("startCodeP");
			map.put("startCodePCn", this.getCodeCn(startCodeP));

			String startCodeC = (String) map.get("startCodeC");
			map.put("startCodeCCn", this.getCodeCn(startCodeC));

			String endCodeP = (String) map.get("endCodeP");
			map.put("endCodePCn", this.getCodeCn(endCodeP));

			String endCodeC = (String) map.get("endCodeC");
			map.put("endCodeCCn", this.getCodeCn(endCodeC));
		}
		return agreements;
	}

	private String getCodeCn(String code) {
		if (StringUtils.isNotBlank(code)) {
			RegionInfo startCodePCn = reginService.findByCode(code);
			if (startCodePCn != null) {
				return startCodePCn.getRegionName();
			}
		}
		return "";
	}

	@Override
	public Map<String,Object> getAgreementDetail(String agreementId) {
		AgreementValidate.valid_agreement_id(agreementId);
		AgreementInfo agreementInfo = agreementDao.get(agreementId);
		//AgreementValidate.valid_agreement_exist(!ValidateUtils.isEmpty(agreementInfo));
		Map<String, Object> agreementMap = new HashMap<String, Object>();
		beanMapper.map(agreementInfo, agreementMap);
		String startCodeP = (String) agreementMap.get("startCodeP");
		agreementMap.put("startCodePCn", this.getCodeCn(startCodeP));

		String startCodeC = (String) agreementMap.get("startCodeC");
		agreementMap.put("startCodeCCn", this.getCodeCn(startCodeC));

		String endCodeP = (String) agreementMap.get("endCodeP");
		agreementMap.put("endCodePCn", this.getCodeCn(endCodeP));

		String endCodeC = (String) agreementMap.get("endCodeC");
		agreementMap.put("endCodeCCn", this.getCodeCn(endCodeC));
		return agreementMap;
	}

}
