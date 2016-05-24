package org.ltsh.framework.modules.admin.service;

import org.ltsh.framework.modules.admin.entity.User;


public interface IUserService {
	
	
	public void addUser(User u ) throws Exception;
	
	
	/** 
	 * @Author: Charles
	 * @Description: 通过用户名获取用户
	 * @return User: 
	 */
	public User findUserByUname(String username);
}
