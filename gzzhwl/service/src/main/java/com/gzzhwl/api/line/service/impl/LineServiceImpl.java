package com.gzzhwl.api.line.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.gzzhwl.api.line.service.LineService;
import com.gzzhwl.api.line.validate.LineValidator;
import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.dao.LineInfoDao;
import com.gzzhwl.core.data.model.LineInfo;
import com.gzzhwl.core.utils.IdentifierUtils;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class LineServiceImpl implements LineService {
	@Autowired
	private LineInfoDao lineInfoDao;

	@Value("${hotline.tmp}")
	private String hotlineTmp;

	@Override
	public boolean saveLine(String accountId, String departureCode, String destinationCode) throws RestException {
		return this.saveLine(accountId, null, departureCode, destinationCode);
	}

	@Override
	public boolean saveLine(String accountId, String driverInfoId, String departureCode, String destinationCode)
			throws RestException {
		LineValidator.valid_add_line(departureCode, destinationCode);

		List<Map<String, String>> lineList = new ArrayList<Map<String, String>>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if (StringUtils.isNotEmpty(driverInfoId)) {
			params.put("driverInfoId", driverInfoId);
			lineList = lineInfoDao.find(params);
		} else {
			params.put("accountId", accountId);
			lineList = lineInfoDao.find(params);
		}
		// 验证常跑线路是否重复/数量是否大于5条
		LineValidator.valid_add_line(lineList);
		LineValidator.valid_line_repeat(lineList, departureCode, destinationCode);

		String lineInfoId = IdentifierUtils.getId().generate().toString();
		DataSource data = DataSource.VLORRY;
		LineInfo lineInfo = new LineInfo();
		lineInfo.setLineInfoId(lineInfoId);
		lineInfo.setAccountId(accountId);
		lineInfo.setDriverInfoId(driverInfoId);
		lineInfo.setDepartureCode(departureCode);
		lineInfo.setDestinationCode(destinationCode);
		lineInfo.setSource(data.getCode());
		lineInfo.setStatus(Global.STATUS_NORMAL.toString());
		lineInfo.setIsDeleted(Global.ISDEL_NORMAL.toString());
		return lineInfoDao.insert(lineInfo) > 0;
	}

	@Override
	public boolean updateLine(String lineInfoId, String driverInfoId, String accountId, String departureCode,
			String destinationCode) {
		LineInfo lineInfo = lineInfoDao.get(lineInfoId);

		LineValidator.valid_line(lineInfo, driverInfoId, accountId);

		List<Map<String, String>> lineList = new ArrayList<Map<String, String>>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if (StringUtils.isNotEmpty(driverInfoId)) {
			params.put("driverInfoId", driverInfoId);
			lineList = lineInfoDao.find(params);
		} else {
			params.put("accountId", accountId);
			lineList = lineInfoDao.find(params);
		}

		LineValidator.valid_line_repeat(lineList, departureCode, destinationCode);

		lineInfo.setDepartureCode(departureCode);
		lineInfo.setDestinationCode(destinationCode);
		lineInfo.setUpdatedTime("1");
		return lineInfoDao.updateSelective(lineInfo) > 0;
	}

	@Override
	public boolean delLine(String lineInfoId, String driverInfoId, String accountId) {
		LineInfo lineInfo = lineInfoDao.get(lineInfoId);
		List<Map<String, String>> lineList = new ArrayList<Map<String, String>>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if (StringUtils.isNotEmpty(driverInfoId)) {
			params.put("driverInfoId", driverInfoId);
			lineList = lineInfoDao.find(params);
		} else {
			params.put("accountId", accountId);
			lineList = lineInfoDao.find(params);
		}
		// 删除时最少保留一条线路
		LineValidator.valid_line(lineInfo, driverInfoId, accountId);
		LineValidator.valid_del_line(lineList);

		lineInfo.setIsDeleted(Global.ISDEL_DELETE.toString());
		return lineInfoDao.updateSelective(lineInfo) > 0;
	}

	@Override
	public List<Map<String, Object>> getLineList(String accountId, String driverInfoId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		if (StringUtils.isNotEmpty(driverInfoId)) {
			params.put("driverInfoId", driverInfoId);
		} else {
			params.put("accountId", accountId);
		}
		List<Map<String, Object>> result = lineInfoDao.find(params);
		return result;
	}

	@Override
	public List<Map<String, Object>> getHotLineList(String accountId, Integer lineCount) {
		return lineInfoDao.getHotLineList(accountId, lineCount);
	}

	@Override
	public List<Map<String, Object>> getTmpHotLineList(Integer count) {
		List<Map<String, Object>> mapListJson = (List) JSONArray.parse(hotlineTmp);
		if (!ValidateUtils.isEmpty(mapListJson)) {
			if (mapListJson.size() > count) {
				for (int i = count; i < mapListJson.size();) {
					mapListJson.remove(i);
				}
			}
		}
		return mapListJson;
	}

}
