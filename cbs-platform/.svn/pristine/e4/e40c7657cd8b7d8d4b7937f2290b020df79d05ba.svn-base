package com.gzzhwl.cbs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/list", method = RequestMethod.GET)
public class ListController {

	@RequestMapping(value = "/{floder}/{page}")
	public String list(@PathVariable(value = "floder") String floder, @PathVariable(value = "page") String page,
			@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("floder", floder);
		model.addAttribute("page", page);
		model.addAttribute("urlParam", params);
		return "list/" + floder + "/" + page;
	}
}
