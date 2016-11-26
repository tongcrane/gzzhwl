package com.gzzhwl.admin.quoted.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzzhwl.admin.quoted.service.QuotedHisService;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.constant.QuotedType;
import com.gzzhwl.core.data.dao.QuotedHisDao;
import com.gzzhwl.core.data.extdao.QuotedHisExtDao;
import com.gzzhwl.core.data.model.QuotedHis;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class QuotedHisServiceImpl implements QuotedHisService  {

	
	@Autowired
	private QuotedHisDao quotedHisDao;
	
	@Autowired
	private QuotedHisExtDao quotedHisExtDao;
	
	@Override
	public void saveQuotedHis(String quotedId, String srcStatus, String destStatus,
			FlowActionCategory flowActionCategory, String msgInfo, String createdBy) {
		
		QuotedHis quotedHis = new QuotedHis();
		
		String quotedHisId = IdentifierUtils.getId().generate().toString();
		
		quotedHis.setHisId(quotedHisId);
		quotedHis.setCreatedBy(createdBy);
		quotedHis.setDestStatus(destStatus);
		quotedHis.setSrcStatus(srcStatus);
		quotedHis.setMsgInfo(msgInfo);
		quotedHis.setQuotedId(quotedId);
		quotedHis.setUserType(flowActionCategory.getCode());
		
		quotedHisDao.insert(quotedHis);
	}

	@Override
	public QuotedHis getQuotedHisBySourceId(String sourceId, QuotedType quotedType) {
		
		if(quotedType.equals(QuotedType.CLOSED)){
			throw new RestException(ErrorCode.ERROR_900014);
		}
		
		return quotedHisExtDao.getQuotedHisByDestStatus(sourceId, quotedType.getCode());
	}

	@Override
	public <T, K, V> List<T> getQuotedHisList(String sourceId) {
		
		return quotedHisExtDao.getQuotedHisList(sourceId);
	}

}
