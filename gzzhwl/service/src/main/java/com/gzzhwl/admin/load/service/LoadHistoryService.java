package com.gzzhwl.admin.load.service;

import com.gzzhwl.core.constant.LoadBillType;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.LoadHis;
import com.gzzhwl.rest.exception.RestException;

public interface LoadHistoryService {
	public String saveLoadHistory(String loadId, String current, String actionTime, FlowDef flowDef, String staffId)
			throws RestException;
	
	public String saveErrorLoadHistory(String loadId, String current, String actionTime, FlowDef flowDef, String staffId)
			throws RestException;
	
	public LoadHis getLoadHistory(String loadId,LoadBillType loadBillType)
			throws RestException;
	
	public String saveElecReceiptHis(String loadId, String current, String actionTime, FlowDef flowDef, String staffId,String elecImageId);
}
