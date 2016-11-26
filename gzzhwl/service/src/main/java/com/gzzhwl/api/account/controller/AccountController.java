package com.gzzhwl.api.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.api.account.service.AccountService;
import com.gzzhwl.core.data.model.LoginResultModel;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/loginByBillForYSJ", method = RequestMethod.POST)
	public ResponseModel loginByBill(@RequestParam String telphone, @RequestParam String idno,
			@RequestParam String loadNo, @RequestParam String deviceType) {
		LoginResultModel result = accountService.loginByBillForYSJ(telphone, idno, loadNo, deviceType);
		return new ResponseModel(result);
	}

	@RequestMapping(value = "loginByBillForCBS", method = RequestMethod.POST)
	public ResponseModel loginByLoad(@RequestParam String idno, @RequestParam String loadNo, @RequestParam String deviceType) {
		LoginResultModel result = accountService.loginByBillForCBS(idno, loadNo, deviceType);
		return new ResponseModel(result);
	}

}
