package com.gzzhwl.tms.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.tms.service.TMSApplyReturnService;
import com.gzzhwl.tms.service.TMSOrderService;
import com.gzzhwl.tms.vo.OrderVO;
import com.gzzhwl.tms.vo.SourceVO;

@RestController
@RequestMapping("/tms/order")
public class TMSController {
	@Autowired
	private TMSOrderService orderService;
	@Autowired
	private TMSApplyReturnService applyReturnService;
	private static final Logger logger = LoggerFactory.getLogger(TMSController.class);

	/**
	 * 订单推送
	 */
	@RequestMapping(value = "/push", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel pushOrder(@RequestBody OrderVO orderVo) {
		logger.debug("/order/push 接收到的参数：{}", JSON.toJSONString(orderVo));
		String sourceId = orderService.pushOrder(orderVo);
		Map<String, Object> result = Maps.newHashMap();
		result.put("sourceId", sourceId);
		return new ResponseModel(result);
	}

	/**
	 * 订单撤回
	 */
	@RequestMapping(value = "/applyReturn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel applyReturn(@RequestBody SourceVO sourceVo) {
		logger.debug("/order/applyReturn 接收到的参数：{}", JSON.toJSONString(sourceVo));
		boolean success = applyReturnService.applyReturn(sourceVo);
		return new ResponseModel(success);
	}

	/**
	 * 订单作废
	 */
	@RequestMapping(value = "/invalid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel invalid(@RequestBody SourceVO sourceVo) {
		logger.debug("/order/invalid 接收到的参数：{}", JSON.toJSONString(sourceVo));
		boolean success = orderService.cancelTrip(sourceVo);
		return new ResponseModel(success);
	}

	/**
	 * 订单完成
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseModel complete(@RequestBody SourceVO sourceVo) {
		logger.debug("/order/invalid 接收到的参数：{}", JSON.toJSONString(sourceVo));
		boolean success = orderService.complete(sourceVo);
		return new ResponseModel(success);
	}

}
