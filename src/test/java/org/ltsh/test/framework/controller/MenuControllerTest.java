//package org.ltsh.test.framework.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Test;
//import org.ltsh.framework.modules.admin.entity.Menu;
//import org.ltsh.framework.modules.admin.service.IMenuService;
//import org.ltsh.framework.modules.base.service.IBaseService;
//import org.ltsh.framework.util.JsonUtil;
//import org.ltsh.test.framework.common.BaseTest;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class MenuControllerTest extends BaseTest{
//	@Autowired
//	public IBaseService baseService;
//	
//	@Autowired
//	public IMenuService menuService;
//	
//	@Test
//	public void getMenuTree() throws Exception{ 
//		
//		List<Menu> ml = menuService.getMenuTree();
//		String json = JsonUtil.toJsonString(ml);
//		System.out.println(json);
//	}
//	
////	@Test
//	public void addMenu() throws Exception{
//		Menu m = new Menu();
//		m.setName("模块");
//		m.setParentId(0L);
//		m.setCreateDateTime(new Date());
//		m.setMenuSort(4L);
//		
//		Menu m1 = new Menu();
//		m1.setName("模块1");
//		m1.setParentId(5L);
//		m1.setCreateDateTime(new Date());
//		m1.setMenuSort(5L);
//		
//		Menu m2 = new Menu();
//		m2.setName("模块2");
//		m2.setParentId(5L);
//		m2.setCreateDateTime(new Date());
//		m2.setMenuSort(6L);
//		
//		menuService.addMenu(m);
//		menuService.addMenu(m1);
//		menuService.addMenu(m2);
//	}
//}
