package org.ltsh.framework.modules.admin.dao;

import org.ltsh.framework.modules.admin.entity.User;
import org.ltsh.framework.modules.base.dao.IHibernateBaseDao;

public interface IUserDao extends IHibernateBaseDao<User, Long>{
	
	
	public User findUserByUname(String uname);
}
