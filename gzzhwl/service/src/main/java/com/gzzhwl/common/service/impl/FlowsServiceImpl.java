package com.gzzhwl.common.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gzzhwl.rest.exception.FlowException;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.FlowDefDao;
import com.gzzhwl.core.data.dao.FlowStatusDao;
import com.gzzhwl.core.data.model.FlowDef;
import com.gzzhwl.core.data.model.FlowStatus;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class FlowsServiceImpl implements FlowsService {
	private static final Logger logger = LoggerFactory.getLogger(FlowsService.class);
	@Autowired
	private FlowDefDao flowDefDao;
	@Autowired
	private FlowStatusDao flowStatusDao;

	@Override
	public FlowDef startFlow(ZHFlow flowType, FlowActionCategory category) throws FlowException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowType", flowType.getName());
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("started", FlowDef.IS_STARTED);
		FlowDef flowDef = flowDefDao.get(params);
		if (ValidateUtils.isEmpty(flowDef)) {
			throw new FlowException("此" + flowType + "流程类型没有有效的开始结点。");
		}
		String operator = flowDef.getCategory();
		if (!StringUtils.equals(category.getCode(), operator)) {
			throw new FlowException("没有操作权限。");
		}
		return flowDef;
	}

	@Override
	public FlowDef executeFlow(ZHFlow flowType, String actionCode, String currentStatus, FlowActionCategory category)
			throws FlowException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowType", flowType.getName());
		params.put("actionCode", actionCode);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("started", FlowDef.NOT_STARTED);
		params.put("linkedStatus", "%" + currentStatus + "%");
		FlowDef flowDef = flowDefDao.get(params);
		if (ValidateUtils.isEmpty(flowDef)) {
			throw new FlowException("当前状态" + currentStatus + "无法操作步骤" + actionCode + "，操作无效。");
		}
		String operator = flowDef.getCategory();
		if (!StringUtils.equals(category.getCode(), operator)) {
			throw new FlowException("没有操作权限。");
		}
		return flowDef;
	}

	@Override
	public List<FlowDef> getAllAction(ZHFlow flowType, String currentStatus, FlowActionCategory category)
			throws FlowException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowType", flowType.getName());
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("started", FlowDef.NOT_STARTED);
		params.put("category", category.getCode());
		params.put("linkedStatus", "%" + currentStatus + "%");
		return flowDefDao.find(params);
	}

	@Override
	public Boolean isBillRight(ZHFlow flowType, String actionCode, String currentStatus, FlowActionCategory category)
			throws FlowException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowType", flowType.getName());
		params.put("actionCode", actionCode);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("started", FlowDef.NOT_STARTED);
		params.put("linkedStatus", "%" + currentStatus + "%");
		FlowDef flowDef = flowDefDao.get(params);
		if (ValidateUtils.isEmpty(flowDef)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public FlowStatus getStatus(ZHFlow flowType, String currentStatus) {
		if (StringUtils.isNotBlank(currentStatus)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("flowType", flowType.getName());
			params.put("code", currentStatus);
			return flowStatusDao.get(params);
		} else {
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> whatToDo(ZHFlow zhflow, String currentStatus,
			FlowActionCategory flowActionCategory) throws RestException {
		List<FlowDef> data = getAllAction(zhflow, currentStatus, flowActionCategory);
		if (CollectionUtils.isNotEmpty(data)) {
			List<Map<String, Object>> result = Lists.newArrayList();
			Iterator<FlowDef> _iflow = data.iterator();
			while (_iflow.hasNext()) {
				FlowDef flowDef = _iflow.next();
				String code = flowDef.getActionCode();
				String name = flowDef.getActionName();
				Map<String, Object> row = Maps.newHashMap();
				row.put("code", code);
				row.put("name", name);
				result.add(row);
			}
			return result;
		}
		return null;
	}

	@Override
	public List<FlowStatus> getStatus(ZHFlow flowType) throws FlowException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", flowType.getName());

		return flowStatusDao.find(params);
	}

	@Override
	public FlowDef getFlowByActionCode(ZHFlow flowType, String actionCode) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowType", flowType.getName());
		params.put("actionCode", actionCode);
		params.put("status", Global.STATUS_NORMAL.toString());
		params.put("started", FlowDef.NOT_STARTED);
		FlowDef flowDef = flowDefDao.get(params);

		if (ValidateUtils.isEmpty(flowDef)) {
			throw new FlowException("未找到该动作。");
		}

		return flowDef;
	}
}
