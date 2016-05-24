package org.ltsh.framework.modules.base.service.impl;

import java.util.List;

import org.ltsh.framework.modules.base.dao.IBaseDao;
import org.ltsh.framework.modules.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("baseService")
public class BaseServiceImpl implements IBaseService{
	
	@Autowired
	private IBaseDao baseDao;
	
	public void add(Object entity){
		baseDao.save(entity);
	}
	
	public Object getHql(String hql){
		return baseDao.getHql(hql);
	}
	
	public List<Object> findAll(){
		return baseDao.findAll();
	}
}
