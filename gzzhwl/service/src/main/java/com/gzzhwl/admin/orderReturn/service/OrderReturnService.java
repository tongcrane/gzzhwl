package com.gzzhwl.admin.orderReturn.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;

public interface OrderReturnService {
	public Page<Map<String, Object>> pageOrderSourceList(String keyWord,String status, int currentPage, int pageSize);

	public boolean applyReturn(String orderId, String staffId) throws RestException;

	public String allowApply(String orderId) throws RestException;

	public String getApplyLog(String sourceId) throws RestException;

	public boolean agreeReturn(String sourceId, String staffId) throws RestException;

	public boolean disAgreeReturn(String sourceId, String staffId) throws RestException;

	public boolean autoAgreeReturn(String sourceId, String staffId) throws RestException;

	public List<Map<String, Object>> whatToDo(String sourceId) throws RestException;

}
