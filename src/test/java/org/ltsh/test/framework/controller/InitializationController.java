//package org.ltsh.test.framework.controller;
//
//import org.apache.shiro.codec.Hex;
//import org.apache.shiro.crypto.RandomNumberGenerator;
//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
//import org.apache.shiro.crypto.hash.Sha256Hash;
//import org.apache.shiro.util.ByteSource;
//import org.junit.Test;
//import org.ltsh.framework.modules.admin.entity.Menu;
//import org.ltsh.framework.modules.admin.entity.User;
//import org.ltsh.framework.modules.admin.service.IMenuService;
//import org.ltsh.framework.modules.admin.service.IUserService;
//import org.ltsh.test.framework.common.BaseTest;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @author Charles
// * 第一次运行时候初始化数据
// *
// */
//public class InitializationController extends BaseTest{
//
//	@Autowired
//	public IUserService userService;
//	
//	@Autowired
//	public IMenuService menuService;
//	
//	@Test
//	public void initData() throws Exception{
//		try{
////			addUser();
////			addMenu();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	/** 
//	 * @Author: Charles
//	 * @Description: 添加admin管理员用户
//	 * @throws Exception void: 
//	 */
//	public void addUser() throws Exception{
//		String username = "test";
//		String password = "123456";
//		int hashIterations = 1024;	//hash迭代次数
//		
//		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
//		ByteSource salt = rng.nextBytes();
//		String saltStr = Hex.encodeToString(salt.getBytes());
//
//		String hashedPasswordBase64 = new Sha256Hash(password, salt, hashIterations).toBase64();
//		
//		User u = new User();
//		u.setPassword(hashedPasswordBase64);
//		u.setUsername(username);
//		u.setPasswordSalt(saltStr);
//		userService.addUser(u);
//	}
//	
//	public void addMenu() throws Exception{
//		Menu parent = new Menu("系统管理");
//		parent.setParentId(0L);
//		parent.setMenuSort(0L);
//		parent.setUrl("#");
//		menuService.saveMenu(parent);
//		
//		long parentId = parent.getId();
//
//		Menu child = new Menu("用户管理");
//		child.setParentId(parentId);
//		child.setMenuSort(1L);
//		child.setUrl("/framework/modules/admin/user/page");
//		
//		Menu child2 = new Menu("角色管理");
//		child2.setParentId(parentId);
//		child2.setMenuSort(2L);
//		child2.setUrl("/framework/modules/admin/role/page");
//		
//		Menu child3 = new Menu("菜单管理");
//		child3.setParentId(parentId);
//		child3.setMenuSort(3L);
//		child3.setUrl("/framework/modules/admin/menu/page");
//		
//		Menu child4 = new Menu("权限管理");
//		child4.setParentId(parentId);
//		child4.setMenuSort(4L);
//		child4.setUrl("/framework/modules/admin/perms/page");
//		
//		menuService.saveMenu(child);
//		menuService.saveMenu(child2);
//		menuService.saveMenu(child3);
//		menuService.saveMenu(child4);
//	}
//	
//}
