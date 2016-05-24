package org.ltsh.framework.modules.admin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.ltsh.framework.modules.admin.dao.IMenuDao;
import org.ltsh.framework.modules.admin.entity.Menu;
import org.ltsh.framework.modules.base.dao.impl.HibernateBaseDaoImpl;
import org.springframework.stereotype.Component;

@Component("menuDao")
public class MenuDaoImpl extends HibernateBaseDaoImpl<Menu, Long> implements IMenuDao   {
	

	public List<Menu> findAllByParentId(long parentId){
		Criterion[] condition = new SimpleExpression[]{ 
			Restrictions.eq("parentId", parentId) 
		};
		Order[] orderBy = new Order[]{ Order.asc("menuSort")};
		return this.findAll(Menu.class, condition, orderBy);
	}
	
	public Menu findAllByName(String name){
		Criterion[] condition = new Criterion[]{ Restrictions.like("name", name) };
		return this.findUnique(Menu.class, condition);
	}
}
