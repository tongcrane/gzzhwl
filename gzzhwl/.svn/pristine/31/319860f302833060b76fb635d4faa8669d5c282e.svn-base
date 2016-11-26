package com.gzzhwl.api.source.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.api.source.service.ApiSourceService;
import com.gzzhwl.api.source.vo.QuerySourceHallVo;
import com.gzzhwl.api.source.vo.QuerySourceVO;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.springmvc.annotation.Authorization;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/api/source")
public class ApiSourceController {

	@Autowired
	private ApiSourceService apiSourceService;

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	private ResponseModel querySource(QuerySourceVO param, @Authorization(required = false) AccountInfo accountInfo,
			@Pagination PageParameter page) {
		String accountId = null;
		if (accountInfo != null) {
			accountId = accountInfo.getAccountId();
		}
		Page<Map<String, Object>> data = apiSourceService.pageSource(param, accountId, page.getPageIndex(),
				page.getPageSize());
		return new ResponseModel(data);
	}

	@RequestMapping(value = "/queryHall", method = RequestMethod.GET)
	private ResponseModel querySourceHall(QuerySourceHallVo param,
			@Authorization(required = false) AccountInfo accountInfo, @Pagination PageParameter page) {
		String accountId = null;
		if (accountInfo != null) {
			accountId = accountInfo.getAccountId();
		}
		Page<Map<String, Object>> data = apiSourceService.pageSourceHall(param, accountId, page.getPageIndex(),
				page.getPageSize());
		return new ResponseModel(data);
	}

	@RequestMapping(value = "/allowBid", method = RequestMethod.GET)
	private ResponseModel allowBid(@RequestParam String sourceId, @Authorization AccountInfo accountInfo) {
		String accountId = accountInfo.getAccountId();
		boolean allow = apiSourceService.allowBid(sourceId, accountId);
		return new ResponseModel(allow);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	private ResponseModel infoSource(@RequestParam String sourceId, @Authorization AccountInfo accountInfo) {
		String accountId = accountInfo.getAccountId();
		Map<String, Object> data = apiSourceService.infoSource(sourceId, accountId);
		return new ResponseModel(data);
	}

	/**
	 * 获取热门线路货源列表
	 * 
	 * @param accountInfo
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getHotLineSourceList", method = RequestMethod.GET)
	private ResponseModel getHotLineSourceList(@Authorization(required = false) AccountInfo accountInfo,
			@RequestParam(required = true) Integer sourceCount) throws ParseException {

		String accountId = null;

		if (accountInfo != null) {
			accountId = accountInfo.getAccountId();
		}

		List<Map<String, Object>> paraMap = apiSourceService.getHotLineSourceList(accountId, sourceCount);

		return new ResponseModel(paraMap);
	}

	/**
	 * 查询最新货源
	 * 
	 * @param sourceId
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/getNewSource", method = { RequestMethod.GET })
	public ResponseModel getNewSource(@Authorization(required = false) AccountInfo accountInfo,
			@RequestParam(required = true) Integer sourceCount) {
		String accountId = "";
		if (!ValidateUtils.isEmpty(accountInfo)) {
			accountId = accountInfo.getAccountId();
		}
		List<Map<String, Object>> data = apiSourceService.getNewSource(sourceCount, accountId);
		return new ResponseModel(data);
	}

	/**
	 * 获取今天新增货源数量和成功签单的经纪人和司机数量
	 * 
	 * @param sourceId
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/getSourceAndQutoedCnt", method = { RequestMethod.GET })
	public ResponseModel getSourceAndQutoedCnt() {
		Map<String, Object> data = apiSourceService.getSourceAndQutoedCnt();
		return new ResponseModel(data);
	}

	/**
	 * 获取推荐货源列表
	 * 
	 * @param sourceId
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/getRecommendSource", method = { RequestMethod.GET })
	public ResponseModel getRecommendSource(@Authorization AccountInfo accountInfo,
			@RequestParam(required = true) Integer sourceCount, @RequestParam(required = false) String startCodeP,
			@RequestParam(required = false) String endCodeP) {

		List<Map<String, Object>> data = apiSourceService.getRecommendSource(accountInfo.getAccountId(), sourceCount,startCodeP,endCodeP);
		return new ResponseModel(data);
	}

}
