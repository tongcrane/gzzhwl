package com.gzzhwl.cbs.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gzzhwl.cbs.constants.SessionConstant;
import com.gzzhwl.cbs.exception.RestException;
import com.gzzhwl.cbs.model.Subject;
import com.gzzhwl.cbs.model.User;
import com.gzzhwl.cbs.service.MenuService;
import com.gzzhwl.cbs.service.UserService;
import com.gzzhwl.cbs.support.MenuSupport;
import com.gzzhwl.cbs.vo.LoginVO;
import com.gzzhwl.cbs.vo.MenuVO;

@Controller
@SessionAttributes(SessionConstant.CURRENT_USER)
public class KernelController {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("user") User command, ModelMap model) {
		boolean isLogin = model.containsAttribute(SessionConstant.CURRENT_USER);
		if (isLogin) {
			return this.index(model);
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String auth(@ModelAttribute("user") User command, Model model, BindingResult errors) {
		if (StringUtils.isNoneBlank(command.getUserName(), command.getPassWord())) {
			try {
				LoginVO data = userService.doLogin(command.getUserName(), command.getPassWord());
				model.addAttribute(SessionConstant.CURRENT_USER, data.getSubject());
				return "redirect:/index";
			} catch (RestException e) {
				errors.reject("error.login.generic", "用户名或密码无效，请重新输入！");
				return "login";
			}
		} else {
			errors.reject("error.login.generic", "请填写用户名或密码。");
			return "login";
		}
	}

	/**
	 * 执行登出操作
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus session) {
		session.setComplete();
		return "redirect:/index";
	}

	/**
	 * 跳转至后台首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap model) {
		boolean isLogin = model.containsAttribute(SessionConstant.CURRENT_USER);
		if (isLogin) {
			Subject subject = (Subject) model.get(SessionConstant.CURRENT_USER);
			List<MenuVO> allMenu = menuService.getUserMenu(subject.getFunctionIds());
			MenuVO menuVO = MenuSupport.getFirstFun(allMenu);
			return "redirect:" + menuVO.getMenuPath();
		} else {
			return "redirect:/login";
		}
	}

}
