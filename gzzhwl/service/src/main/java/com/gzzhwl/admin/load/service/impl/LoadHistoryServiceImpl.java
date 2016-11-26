package com.gzzhwl.admin.load.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.gzzhwl.admin.load.service.LoadHistoryService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.dao.LoadHisDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.LoadHis;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class LoadHistoryServiceImpl implements LoadHistoryService {
	@Autowired
	private LoadHisDao loadHisDao;

	@Autowired
	private Mapper beanMapper;

	@Override
	public String saveLoadHistory(String loadId, String current, String actionTime, FlowDef flowDef, String staffId)
			throws RestException {
		LoadHis loadHis = new LoadHis();
		loadHis.setCreatedBy(staffId);
		loadHis.setHisId(IdentifierUtils.getId().generate().toString());
		loadHis.setLoadId(loadId);
		if (StringUtils.isNotBlank(actionTime)) {
			loadHis.setActionTime(actionTime);
		}
		loadHis.setUserType(flowDef.getCategory());
		loadHis.setSrcStatus(current);
		loadHis.setDestStatus(flowDef.getTransitionStatus());
		loadHis.setMsgInfo(flowDef.getMsgTemplate());
		loadHis.setIsDeleted(Global.ISDEL_NORMAL.toString());
		loadHisDao.insert(loadHis);
		return loadId;
	}

	@Override
	public LoadHis getLoadHistory(String loadId, LoadBillType loadBillType) throws RestException {

		Map<String, Object> params = new HashMap<>();
		params.put("loadId", loadId);
		params.put("destStatus", loadBillType.getCode());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());

		List<Map<String, Object>> listObj = loadHisDao.find(params);

		if (listObj.size() > 0) {
			return beanMapper.map(listObj.get(0), LoadHis.class);
		}

		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String saveErrorLoadHistory(String loadId, String current, String actionTime, FlowDef flowDef,
			String staffId) throws RestException {
		LoadHis loadHis = new LoadHis();
		loadHis.setCreatedBy(staffId);
		loadHis.setHisId(IdentifierUtils.getId().generate().toString());
		loadHis.setLoadId(loadId);
		if (StringUtils.isNotBlank(actionTime)) {
			loadHis.setActionTime(actionTime);
		}
		loadHis.setUserType(flowDef.getCategory());
		loadHis.setSrcStatus(current);
		loadHis.setDestStatus(flowDef.getTransitionStatus());
		loadHis.setMsgInfo(flowDef.getMsgTemplate());
		loadHis.setIsDeleted(Global.ISDEL_DELETE.toString());
		loadHisDao.insert(loadHis);
		return loadId;
	}

	@Override
	public String saveElecReceiptHis(String loadId, String current, String actionTime, FlowDef flowDef, String staffId,
			String elecImageId) {
		LoadHis loadHis = new LoadHis();
		loadHis.setCreatedBy(staffId);
		loadHis.setHisId(IdentifierUtils.getId().generate().toString());
		loadHis.setLoadId(loadId);
		if (StringUtils.isNotBlank(actionTime)) {
			loadHis.setActionTime(actionTime);
		}
		loadHis.setUserType(flowDef.getCategory());
		loadHis.setSrcStatus(current);
		loadHis.setDestStatus(flowDef.getTransitionStatus());
		loadHis.setIsDeleted(Global.ISDEL_NORMAL.toString());
		Map<String,Object> resMap = new HashMap<>();
		resMap.put("msgInfo",  flowDef.getMsgTemplate());
		resMap.put("elecImageId", elecImageId);
		loadHis.setMsgInfo(JSON.toJSONString(resMap));
		loadHisDao.insert(loadHis);
		return loadId;
	}

	

}
