package org.ltsh.framework.modules.base.dao.impl;

import org.ltsh.framework.modules.base.dao.IBaseDao;
import org.springframework.stereotype.Component;

@Component("baseDao")
public class BaseDaoImpl extends HibernateBaseDaoImpl<Object, Long> implements IBaseDao   {
	
}
