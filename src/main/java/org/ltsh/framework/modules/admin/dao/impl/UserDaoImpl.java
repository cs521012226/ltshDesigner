package org.ltsh.framework.modules.admin.dao.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.ltsh.framework.modules.admin.dao.IUserDao;
import org.ltsh.framework.modules.admin.entity.User;
import org.ltsh.framework.modules.base.dao.impl.HibernateBaseDaoImpl;
import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDaoImpl extends HibernateBaseDaoImpl<User, Long> implements IUserDao   {
	
	
	public User findUserByUname(String uname){
		Criterion[] condition = new Criterion[]{ 
				Restrictions.eq("username", uname)
				};
		return findUnique(User.class, condition);
	}
}
