package com.gzzhwl.api.push.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.push.service.PushNotificationService;
import com.gzzhwl.rest.springmvc.annotation.Authorization;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

@RestController
@RequestMapping("/api/push")
public class PushController {
	@Autowired
	private PushNotificationService pushNotificationService;

	// channel 01 个推
	// deviceType 02 安卓
	// token 个推对应clientId
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ResponseModel addCondition(@RequestParam String token, @RequestParam String channel,
			@RequestParam String deviceType, @Authorization(required = true) AccountInfo accountInfo) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(token, channel, deviceType);
		String accountId = accountInfo.getAccountId();
		pushNotificationService.addPushInfo(channel, deviceType, token, accountId);
		return new ResponseModel(null);
	}
}
