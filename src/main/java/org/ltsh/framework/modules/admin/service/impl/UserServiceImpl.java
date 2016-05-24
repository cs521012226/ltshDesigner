package org.ltsh.framework.modules.admin.service.impl;

import org.ltsh.framework.modules.admin.dao.IUserDao;
import org.ltsh.framework.modules.admin.entity.User;
import org.ltsh.framework.modules.admin.service.IUserService;
import org.ltsh.framework.modules.base.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IBaseDao baseDao;
	

	@Autowired
	private IUserDao userDao;
	
	public void addUser(User u ) throws Exception{
		userDao.persist(u);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 通过用户名获取用户
	 * @return User: 
	 */
	public User findUserByUname(String username){
		return userDao.findUserByUname(username);
	}

}
