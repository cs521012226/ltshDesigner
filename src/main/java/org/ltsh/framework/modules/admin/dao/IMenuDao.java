package org.ltsh.framework.modules.admin.dao;

import java.util.List;

import org.ltsh.framework.modules.admin.entity.Menu;
import org.ltsh.framework.modules.base.dao.IHibernateBaseDao;

public interface IMenuDao extends IHibernateBaseDao<Menu, Long>{

	List<Menu> findAllByParentId(long parentId);
	
	Menu findAllByName(String name);
}
