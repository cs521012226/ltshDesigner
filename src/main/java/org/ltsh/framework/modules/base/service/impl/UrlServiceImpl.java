package org.ltsh.framework.modules.base.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.ltsh.framework.modules.base.dao.IBaseDao;
import org.ltsh.framework.modules.base.service.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("urlService")
public class UrlServiceImpl implements IUrlService{
	
	private final String ENTITY_NAME = "Entity";
	private final String EQ = "EQ_";	//==
	private final String NE = "NE_";	//!=
	private final String GT = "GT_";	//>
	private final String GE = "GE_";	//>=
	private final String LT = "LT_";	//<
	private final String LE = "LE_";	//<=
	private final String OY = "Oy_";	//Order By 按照这样的格式提交：Oy_field1,field2 desc,field3
	private final String OY_SEPARATOR = ",";	//Order by的分割符号
	private final String OY_DESC = "desc";	//Order by的降序
	
	@Autowired
	private IBaseDao baseDao;
	
	public List getListData(Map<String, String> queryParams){
		DetachedCriteria detachedCriteria = null;
		if(queryParams.containsKey(ENTITY_NAME)){
			detachedCriteria = DetachedCriteria.forEntityName(queryParams.get(ENTITY_NAME));
			for(Map.Entry<String, String> entry : queryParams.entrySet()){
				String key = entry.getKey();		//参数键
				String value = entry.getValue();	//参数值
				searchFieldFilter(detachedCriteria, key, value);
			}
			if(queryParams.containsKey(OY)){
				searchOrderByFilter(detachedCriteria, queryParams.get(OY));
			}
		}
		return baseDao.findByCriteria(detachedCriteria);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 设置查询条件
	 * @param key
	 * @param value
	 * @return Criterion: 
	 */
	public void searchFieldFilter(DetachedCriteria detachedCriteria, String key, String value){
		Criterion criterion = null;
		String propertyName = null;
		if(key.startsWith(EQ)){
			propertyName = key.substring(EQ.length());
			criterion = Restrictions.eq(propertyName, value);
		}else if(key.startsWith(NE)){
			propertyName = key.substring(NE.length());
			criterion = Restrictions.ne(propertyName, value);
		}else if(key.startsWith(GT)){
			propertyName = key.substring(GT.length());
			criterion = Restrictions.gt(propertyName, value);
		}else if(key.startsWith(GE)){
			propertyName = key.substring(GE.length());
			criterion = Restrictions.ge(propertyName, value);
		}else if(key.startsWith(LT)){
			propertyName = key.substring(LT.length());
			criterion = Restrictions.lt(propertyName, value);
		}else if(key.startsWith(LE)){
			propertyName = key.substring(LE.length());
			criterion = Restrictions.le(propertyName, value);
		}
		detachedCriteria.add(criterion);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 设置OrderBy条件
	 * @param detachedCriteria
	 * @param propertyName	实体字段名称，应该按照这样的格式：Oy_ = field1,field2 desc,field3
	 */
	public void searchOrderByFilter(DetachedCriteria detachedCriteria, String paramValue){
		String propertyName = null;
		String descStr = null;
		String[] fieldArr = paramValue.split(OY_SEPARATOR);
		for(String f : fieldArr){
			String[] opts = f.split("\\s+");
			propertyName = opts[0];
			if(opts.length > 1){
				descStr = opts[1];
				if(OY_DESC.equalsIgnoreCase(descStr)){
					detachedCriteria.addOrder(Order.desc(propertyName));
				}else{
					detachedCriteria.addOrder(Order.asc(propertyName));
				}
			}else{
				detachedCriteria.addOrder(Order.asc(propertyName));
			}
		}
	}
	
}
