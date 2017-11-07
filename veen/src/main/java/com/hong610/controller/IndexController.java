package com.hong610.controller;

import com.hong610.controller.base.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * Created by Hong on 2016/12/7.
 */
@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "/", method = { RequestMethod.GET})
	public String index_(){
		return "redirect:/index.html";
	}

	@PreAuthorize("authenticated and hasPermission('skip', 'index')")
	@RequestMapping(value = "/index.html", method = { RequestMethod.GET})
	public String index(){
		return "index";
	}

	@RequestMapping(value = "/welcome.html", method = { RequestMethod.GET})
	public String init(){
		return "welcome";
	}

}
