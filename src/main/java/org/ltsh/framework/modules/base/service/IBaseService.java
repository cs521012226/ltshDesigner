package org.ltsh.framework.modules.base.service;

import java.util.List;

public interface IBaseService {

	
	public void add(Object entity);

	public Object getHql(String hql);
	
	
	public List<Object> findAll();
}
