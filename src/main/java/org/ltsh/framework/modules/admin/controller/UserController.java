package org.ltsh.framework.modules.admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ltsh.framework.modules.admin.entity.User;
import org.ltsh.framework.modules.admin.service.IUserService;
import org.ltsh.framework.modules.base.controller.BaseController;
import org.ltsh.framework.modules.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/framework/modules/admin/user")
public class UserController extends BaseController{
	
	@Autowired
	IBaseService baseService;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping("/page")
	public String page(HttpServletRequest req, HttpServletResponse resp) throws Exception{ 
		return "framework/modules/admin/user/page";
	}
}
