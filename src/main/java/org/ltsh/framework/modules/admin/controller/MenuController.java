package org.ltsh.framework.modules.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ltsh.framework.common.handler.HttpHandler;
import org.ltsh.framework.modules.admin.entity.Menu;
import org.ltsh.framework.modules.admin.service.IMenuService;
import org.ltsh.framework.modules.base.controller.BaseController;
import org.ltsh.framework.modules.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/framework/modules/admin/menu")
public class MenuController extends BaseController{
	
	@Autowired
	IBaseService baseService;
	
	@Autowired
	IMenuService menuService;
	
	@RequestMapping("/getTreeList")
	public void getTreeList(HttpServletRequest req, HttpServletResponse resp) throws Exception{ 
		List<Menu> menuList = menuService.getMenuTree();
		HttpHandler.writeJsonToResponse(resp, menuList);
	}
	
	@RequestMapping("/getMenuByParent/{parentId}")
	public void getMenuByParent(@PathVariable Long parentId, HttpServletRequest req, HttpServletResponse resp) throws Exception{ 
		
		List<Menu> menuList = menuService.getMenuByParentId(parentId);
		HttpHandler.writeJsonToResponse(resp, menuList);
	}
	
	@RequestMapping("/page")
	public String page(HttpServletRequest req, HttpServletResponse resp) throws Exception{ 
		return "framework/modules/admin/menu/page";
	}
	
}
