package com.gzzhwl.api.source.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.gzzhwl.api.common.service.CommonSourceService;
import com.gzzhwl.api.line.service.LineService;
import com.gzzhwl.api.quoted.service.QuotedService;
import com.gzzhwl.api.source.service.ApiSourceService;
import com.gzzhwl.api.source.vo.QuerySourceHallVo;
import com.gzzhwl.api.source.vo.QuerySourceVO;
import com.gzzhwl.api.utils.CardIdConvert;
import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.common.model.ZHFlow;
import com.gzzhwl.common.service.FlowsService;
import com.gzzhwl.common.service.RegionService;
import com.gzzhwl.core.constant.QuotedType;
import com.gzzhwl.core.constant.SourceType;
import com.gzzhwl.core.data.dao.OrderSourceInfoDao;
import com.gzzhwl.core.data.dao.QuotedInfoDao;
import com.gzzhwl.core.data.extdao.OrderSourceInfoExtDao;
import com.gzzhwl.core.data.model.OrderSourceInfo;
import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.data.model.RegionInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.page.PageContainer;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

@Service
public class ApiSourceServiceImpl implements ApiSourceService {
	@Autowired
	@Qualifier("SourceCardService")
	private CommonSourceService sourceCommonService;
	@Autowired
	private OrderSourceInfoDao orderSourceInfoDao;
	@Autowired
	private QuotedService quotedService;

	@Autowired
	private QuotedInfoDao quotedInfoDao;
	@Autowired
	private FlowsService flowService;
	@Autowired
	private LineService lineService;
	@Autowired
	private OrderSourceInfoExtDao orderSourceInfoExtDao;
	@Autowired
	private RegionService regionService;

	// 首页热门线路显示条数
	private final Integer hotLineTCount = 3;

	@Override
	public Page<Map<String, Object>> pageSource(QuerySourceVO param, String accountId, int pageIndex, int pageSize)
			throws RestException {
		Map<String, Object> params = param.getParam();
		List<String> sList = Lists.newArrayList();
		sList.add(SourceType.PUBLISH.getCode());
		params.put("statusList", sList);
		Page<Map<String, Object>> pageRela = orderSourceInfoDao.pageSource(params, pageIndex, pageSize);
		List<Map<String, Object>> sourceIdList = pageRela.getRows();
		if (CollectionUtils.isNotEmpty(sourceIdList)) {
			String[] proIds = CardIdConvert.getColumnData(sourceIdList, "sourceId");
			List<Map<String, Object>> resultList = sourceCommonService.getCardInfo(proIds, accountId);
			int dataSize = proIds.length;
			for (int i = 0; i < dataSize; i++) {
				Map<String, Object> current = sourceIdList.get(i);
				current.putAll(resultList.get(i));
			}
		}
		return pageRela;
	}

	@Override
	public Map<String, Object> infoSource(String sourceId, String accountId) throws RestException {
		String[] proIds = { sourceId };
		List<Map<String, Object>> resultList = sourceCommonService.getCardInfo(proIds, accountId);
		if (CollectionUtils.isNotEmpty(resultList)) {
			Map<String, Object> result = resultList.get(0);
			Long hasQuote = (Long) result.get("hasQuote");
			if (hasQuote > 0) {
				QuotedInfo quotedInfo = quotedService.getMyQuoted(sourceId, accountId);
				result.put("quotedInfo", quotedInfo);
			} else {
				result.put("quotedInfo", null);
			}
			boolean allow = this.allowBid(sourceId, accountId);
			result.put("allowBid", allow);
			return result;
		} else {
			return null;
		}
	}

	/**
	 * true 可以报价
	 */
	@Override
	public boolean allowBid(String sourceId, String accountId) throws RestException {

		String actionCode = "04";// 中标动作
		OrderSourceInfo orderSourceInfo = orderSourceInfoDao.get(sourceId);
		String currentStatus = orderSourceInfo.getStatus();
		boolean statusAllow = flowService.isBillRight(ZHFlow.SOURCE, actionCode, currentStatus,
				FlowActionCategory.SYSTEM);
		if (statusAllow) {// 货源状态是否允许
			boolean hasQuote = this.hasQuote(sourceId, accountId);// 是否报价过
			if (hasQuote) {
				return false;
			} else {
				boolean hasStart = this.isBidTime(sourceId);// 报价是否开始
				if (hasStart) {
					return true;
				} else {
					return this.lineAllow(sourceId, accountId);// 是否是回程货
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * @return true 已报价 \ false 未报价
	 */
	public boolean hasQuote(String sourceId, String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sourceId", sourceId);
		params.put("accountId", accountId);
		params.put("status", QuotedType.CLOSED.getCode());
		return quotedInfoDao.hasQuoted(params) > 0;
	}

	/**
	 * @return true是回程货源 \ false 非回程货源
	 */
	public boolean lineAllow(String sourceId, String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sourceId", sourceId);
		params.put("accountId", accountId);
		List<String> sList = Lists.newArrayList();
		sList.add(QuotedType.QUOTED.getCode());
		sList.add(QuotedType.NOTIMPROVE.getCode());
		sList.add(QuotedType.ONLINE.getCode());
		sList.add(QuotedType.ONLINE_TRIP.getCode());
		sList.add(QuotedType.COMPLETE.getCode());
		params.put("statusList", sList);
		return quotedInfoDao.findLineQuote(params) > 0;
	}

	public boolean isBidTime(String sourceId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sourceId", sourceId);
		return orderSourceInfoDao.hasBidBegin(params) > 0;
	}

	@Override
	public List<Map<String, Object>> getHotLineSourceList(String accountId, Integer sourceCount) {
		// 如果是匿名登录,热门线路货源取临时线路货源
		if (ValidateUtils.isEmpty(accountId)) {
			return getTmpHotLineSourceList(sourceCount, hotLineTCount);
		}

		List<Map<String, Object>> resList = new ArrayList<>();
		// 获取热门线路货源
		getHotLineSourceList(accountId, sourceCount, resList);

		// 补充临时线路个数
		Integer count = hotLineTCount - resList.size();
		if (count > 0) {
			// 账户下的线路不够时用临时线路补充
			getTmpHotLineSourceList(sourceCount, count, resList);
		}

		return resList;
	}

	public List<Map<String, Object>> getHotLineSourceList(String accountId, Integer sourceCount,
			List<Map<String, Object>> resList) {

		// 获取账户下已有热门线路
		List<Map<String, Object>> hotLineList = lineService.getHotLineList(accountId, hotLineTCount);

		if (resList == null) {
			resList = new ArrayList<>();
		}

		for (int i = 0; i < hotLineList.size(); i++) {

			RegionInfo region = regionService.findByCode(hotLineList.get(i).get("departureCode").toString());

			Map<String, Object> hotLine = hotLineList.get(i);

			// 获取临时热门线路
			Page<Map<String, Object>> resPage = orderSourceInfoExtDao.getHotLineSourcePage(accountId,
					hotLine.get("departureCode").toString(), 0, sourceCount);

			Map<String, Object> hotLineMap = new HashMap<>();

			List<Map<String, Object>> sourceIdList = resPage.getRows();
			if (CollectionUtils.isNotEmpty(sourceIdList)) {
				String[] proIds = CardIdConvert.getColumnData(sourceIdList, "sourceId");
				List<Map<String, Object>> resultList = sourceCommonService.getCardInfo(proIds, null);
				int dataSize = proIds.length;
				for (int j = 0; j < dataSize; j++) {
					Map<String, Object> current = sourceIdList.get(j);
					current.putAll(resultList.get(j));
				}
			}

			if (region != null) {
				hotLineMap.put("regionName", region.getRegionName());
				hotLineMap.put("lineInfoId", hotLine.get("lineInfoId").toString());
			} else {
				hotLineMap.put("lineInfoId", "");
			}

			if (resPage.getRows().size() > 0) {
				hotLineMap.put("sourceList", resPage.getRows());
				resList.add(hotLineMap);
			}

		}

		return resList;
	}

	public List<Map<String, Object>> getTmpHotLineSourceList(Integer sourceCount, Integer tmpLineCount,
			List<Map<String, Object>> resList) {

		List<Map<String, Object>> lineMapList = lineService.getTmpHotLineList(tmpLineCount);

		if (resList == null) {
			resList = new ArrayList<>();
		}

		for (int i = 0; i < lineMapList.size(); i++) {

			RegionInfo region = regionService.findByCode(lineMapList.get(i).get("startCodeC").toString());

			String startCodeC = (String) lineMapList.get(i).get("startCodeC");

			String endCodeC = (String) lineMapList.get(i).get("endCodeC");

			// 获取临时热门线路
			Page<Map<String, Object>> resPage = orderSourceInfoExtDao.getTmpHotLineSourcePage(startCodeC, endCodeC, 0,
					sourceCount);

			Map<String, Object> hotLineMap = new HashMap<>();

			List<Map<String, Object>> sourceIdList = resPage.getRows();
			if (CollectionUtils.isNotEmpty(sourceIdList)) {
				String[] proIds = CardIdConvert.getColumnData(sourceIdList, "sourceId");
				List<Map<String, Object>> resultList = sourceCommonService.getCardInfo(proIds, null);
				int dataSize = proIds.length;
				for (int j = 0; j < dataSize; j++) {
					Map<String, Object> current = sourceIdList.get(j);
					current.putAll(resultList.get(j));
				}
			}

			if (region != null) {
				hotLineMap.put("regionName", region.getRegionName());
			}

			if (resPage.getRows().size() > 0) {
				hotLineMap.put("sourceList", resPage.getRows());

			}
			resList.add(hotLineMap);

		}

		return resList;
	}

	@Override
	public List<Map<String, Object>> getTmpHotLineSourceList(Integer sourceCount, Integer tmpLineCount) {

		return getTmpHotLineSourceList(sourceCount, tmpLineCount, null);
	}

	// 获取最新货源信息
	@Override
	public List<Map<String, Object>> getNewSource(int count, String accountId) {
		List<Map<String, Object>> ids = orderSourceInfoExtDao.getNewSource(count);
		if (!ValidateUtils.isEmpty(ids)) {
			String[] proIds = CardIdConvert.getColumnData(ids, "sourceId");
			List<Map<String, Object>> cardsInfo = sourceCommonService.getCardInfo(proIds, null);

			return cardsInfo;
		}
		return Lists.newArrayList();
	}

	// 获取今天新增货源数量和成功签单的经纪人和司机数量
	@Override
	public Map<String, Object> getSourceAndQutoedCnt() {
		int sourceCnt = orderSourceInfoExtDao.getNewSourceCnt();
		int qutoedCnt = orderSourceInfoExtDao.getQutoSuccessCnt();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sourceCnt", sourceCnt);
		result.put("qutoedCnt", qutoedCnt);
		return result;
	}

	@Override
	public List<Map<String, Object>> getRecommendSource(String accountId, Integer sourceCount, String startCodeP,
			String endCodeP) {

		List<Map<String, String>> prarmList = new ArrayList<>();

		if (StringUtils.isNotBlank(startCodeP) && StringUtils.isNotBlank(endCodeP)) {
			Map<String, String> map = new HashMap<>();
			map.put("startCodeP", startCodeP);
			map.put("endCodeP", endCodeP);
			prarmList.add(map);
		} else {
			List<Map<String, Object>> lineMap = lineService.getLineList(accountId, null);

			for (int i = 0; i < lineMap.size(); i++) {

				Map<String, String> map = new HashMap<>();
				map.put("startCodeP", lineMap.get(i).get("departureCode").toString());
				map.put("endCodeP", lineMap.get(i).get("destinationCode").toString());
				prarmList.add(map);

			}
		}

		Map<String, Object> params = new HashMap<>();

		if (prarmList.size() == 0) {
			return null;
		}

		params.put("mapArray", prarmList);
		params.put("sourceStatusArray", new String[] { SourceType.PUBLISH.getCode() });

		Page<Map<String, Object>> resPage = orderSourceInfoExtDao.getRecommandSourcePage(params, 0, sourceCount);

		List<Map<String, Object>> sourceIdList = resPage.getRows();
		if (CollectionUtils.isNotEmpty(sourceIdList)) {
			String[] proIds = CardIdConvert.getColumnData(sourceIdList, "sourceId");
			List<Map<String, Object>> resultList = sourceCommonService.getCardInfo(proIds, accountId);
			int dataSize = proIds.length;
			for (int j = 0; j < dataSize; j++) {
				Map<String, Object> current = sourceIdList.get(j);
				current.putAll(resultList.get(j));
			}
		}

		return sourceIdList;
	}

	@Override
	public Page<Map<String, Object>> pageSourceHall(QuerySourceHallVo parm, String accountId, int pageIndex,
			int pageSize) {
		String content = parm.getQueryContent();
		if (StringUtils.isBlank(content)) {
			return PageContainer.createEmptyPage(pageIndex, pageSize);
		} else {
			Map<String, Object> params = parm.getParam();
			List<String> sList = Lists.newArrayList();
			sList.add(SourceType.PUBLISH.getCode());
			params.put("statusList", sList);
			params.put("accountId", accountId);
			Page<Map<String, Object>> pageRela = orderSourceInfoDao.pageSourceHall(params, pageIndex, pageSize);
			List<Map<String, Object>> sourceIdList = pageRela.getRows();
			if (CollectionUtils.isNotEmpty(sourceIdList)) {
				String[] proIds = CardIdConvert.getColumnData(sourceIdList, "sourceId");
				List<Map<String, Object>> resultList = sourceCommonService.getCardInfo(proIds, accountId);
				int dataSize = proIds.length;
				for (int i = 0; i < dataSize; i++) {
					Map<String, Object> current = sourceIdList.get(i);
					current.putAll(resultList.get(i));
				}
			}
			return pageRela;
		}
	}

}
