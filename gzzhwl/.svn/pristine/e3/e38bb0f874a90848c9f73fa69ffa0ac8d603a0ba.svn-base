package com.gzzhwl.admin.agreement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.agreement.service.AgreementInfoService;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/admin/agreement")
public class AgreementController {
	@Autowired
	private AgreementInfoService service;

	@RequestMapping(value = "/getAgreements",method=RequestMethod.GET)
	public ResponseModel getAgreements(@RequestParam String custId) {
		List<Map<String,Object>> agreements = service.getAgreements(custId);
		return new ResponseModel(agreements);
		
	}
	
	@RequestMapping(value = "/getAgreementDetail",method =RequestMethod.GET)
	public ResponseModel getAgreementDetail(@RequestParam String agreementId) {
		Map<String,Object> agreementMap = service.getAgreementDetail(agreementId);
		return new ResponseModel(agreementMap);
	}
	
	
}
