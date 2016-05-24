package org.ltsh.framework.modules.base.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.ltsh.framework.component.ListPage;
import org.ltsh.framework.component.Page;
import org.ltsh.framework.modules.base.dao.IHibernateBaseDao;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.Assert;

/**
 * @author Charles
 * @Description: 所有Dao的基类，继承该类获取许多公共方法
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class HibernateBaseDaoImpl<T, PK extends Serializable> extends UpdateDaoImpl<T, PK> implements
		IHibernateBaseDao<T, PK> {
	

	/**
	 * @Description: 通过HQL查询一个实体
	 * @param hql
	 * @return 参数
	 * @return T 返回类型
	 */
	public T getHql(final String hql) {
		return getHql(hql, null);
	}

	/**
	 * @Description: 通过HQL查询一个实体
	 * @param hql	
	 * @param obj   输入参数 分别有map、数组和对象类型
	 * @return 参数
	 * @return T 返回类型
	 */
	public T getHql(final String hql, final Object obj) {
		T entity = (T) this.getHibernateTemplate().execute(
				new HibernateCallback<T>() {
					public T doInHibernate(Session session) {
						Query q = getHqlQuery(hql, -1, -1, obj, session);
						T entity = (T) q.uniqueResult();
						return entity;
					}
				});
		return entity;
	}
	
	/**
	 * @Description: 通过sql查询一个实体
	 * @param sql
	 * @param obj   输入参数 分别有map、数组和对象类型
	 * @return T 返回类型
	 */
	public T getSql(final String sql, final Object obj) {
		T entity = (T) this.getHibernateTemplate().execute(
				new HibernateCallback<T>() {
					public T doInHibernate(Session session) {
						Query q = getSqlQuery(sql, -1, -1, obj, session);
						T entity = (T) q.uniqueResult();
						return entity;
					}
				});
		return entity;
	}

	/**
	 * @Description: 通过sql查询一个实体
	 * @param sql
	 * @return 参数
	 * @return T 返回类型
	 */
	public T getSql(final String sql) {
		return getSql(sql, null);
	}
	

	/**
	 * 获取全部对象,带排序字段与升降序参
	 * @Author: fjz
	 * @param entityClass	 要获得类型的class
	 * @param orderBy		排序字段名
	 * @param isAsc	是否是正序
	 * @return
	 */
	public <X> List<X> findAll(Class<X> entityClass, String orderBy,
			boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.asc(orderBy)));
		else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.desc(orderBy)));
	}
	
	/** QBC	查询方式：
	 * @Author: Charles
	 * @Description: 获取全部对象,带查询、排序、分页
	 * @param entityClass
	 * @param criterions
	 * @param orderBys
	 * @param firstResult
	 * @param maxResults
	 * @return List<X>: 
	 */
	public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys, int firstResult, int maxResults) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		if(criterions != null){
			for (Criterion c : criterions) {
				detachedCriteria.add(c);
			}
		}
		if(orderBys != null){
			for (Order o : orderBys) {
				detachedCriteria.addOrder(o);
			}
		}
		return getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取全部对象,带查询、排序、分页
	 * @param entityClass
	 * @param criterions
	 * @param orderBys
	 * @param page
	 * @return List<X>: 
	 */
	public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys, Page page) {
		return this.findAll(entityClass, criterions, orderBys, page.getCurrentRow(), page.getPageSize());
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取全部对象,带查询、排序、不带分页
	 * @param entityClass
	 * @param criterions
	 * @param orderBys
	 * @return List<X>: 
	 */
	public <X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys) {
		return this.findAll(entityClass, criterions, orderBys, -1, -1);
	}

	/**
	 * 获取全部对象,带排序字段与升降序参
	 * @Author: fjz
	 * @param orderBy		排序字段名
	 * @param isAsc	是否是正序
	 * @return
	 */
	public List<T> findAll(String orderBy, boolean isAsc) {
		return findAll(entityClass, orderBy, isAsc);
	}

	/**
	 * 获取全部对象
	 * @Author: fjz
	 * @param entityClass	 要获得类型的class
	 * @return
	 */
	public <X> List<X> findAll(Class<X> entityClass) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass));
	}


	/**
	 * 获取全部对象
	 */
	public List<T> findAll() {
		return findAll(entityClass);
	}
	/**
	 * 	@Author: fjz
	 * @param entity必须是hibernate映射对象
	 * String 类型值的全部用like查询
	 * 其他用等号
	 * MatchMode.ANYWHERE 是对 String 类型的属性，无论在那里值在那里都匹配。相当于 %entity% 获取全部对象
	 */
	public List<T> findStringLike(Object entity) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entity.getClass()).add(
						Example.create(entity).enableLike(MatchMode.ANYWHERE)));
	}
	/**
	 * @Author fjz
	 * @param entity所有有值的值都是查询条件
	 * 如：Demo(id=1,name="name") 则 等于 from Deom d where d.id=1 and d.name='name'; 
	 */
	public List<T> find(Object entity) {
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entity.getClass()).add(
						Example.create(entity)));
	}
	/**
	 * 根据条件获取数据
	 * @param clazz 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public <X> List<X> find(Class<X> clazz,final Criterion... criterions) {
		return getHibernateTemplate().findByCriteria(createDetachedCriteria(clazz,criterions));
	}
	
	/**
	 * 根据条件获取数据
	 * @param clazz 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public <X> X findUnique(Class<X> clazz, final Criterion... criterions) {
		Criteria executableCriteria = getSession(false).createCriteria(clazz);
		for(Criterion c : criterions){
			executableCriteria.add(c);
		}
		X result = (X) executableCriteria.uniqueResult();
		return result;
	}
	/**
	 * 根据条件获取数据
	 * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	 */
	public <X>List<X> find(DetachedCriteria detachedCriteria) {
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	/**
	 * 根据条件获取数据
	 * @param firstResult 	第几条开始查询
	 * @param maxResults	最大返回数据行数
	 * @param criterions 数量可变的Criterion(面向对象查询条件)
	 * @return
	 */
	 public List<T> find(int firstResult, int maxResults,final Criterion... criterions) {
			return getHibernateTemplate().findByCriteria(createDetachedCriteria(criterions), firstResult, maxResults);
	 }
	/**
	 * 根据条件获取数据
	 * @param firstResult 	从第几条开始查询
	 * @param maxResults	最大返回数据行数
	 * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	 * @return
	 */
	 public List<T> find(DetachedCriteria detachedCriteria,int firstResult, int maxResults) {
			return getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	 }
	 /**
	  * 根据条件获取数据,带分页
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 public List<T> find(Page page,final Criterion... criterions) {
			return getHibernateTemplate().findByCriteria(createDetachedCriteria(criterions), page.getCurrentRow(), page.getPageSize());
	 }
	 /**
	  * 根据条件获取数据,带分页
	  * @param page
	  * @param entity 对象
	  * @return
	  */
	 public List<T> find(final Page page,T entity){
		 return find(null,page,Example.create(entity));
	 }
	 /**
	  * 根据条件获取数据,带分页
	  * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 public List<T> find(DetachedCriteria detachedCriteria,Page page,final Criterion... criterions) {
			return getHibernateTemplate().findByCriteria(createDetachedCriteria(detachedCriteria,criterions), page.getCurrentRow(), page.getPageSize());
	 }
	 
	 /** 
		 * @Author: Charles
		 * @Description: 通过离线条件查询
		 * @param criteria
		 * @return List: 
		 */
	public List findByCriteria(DetachedCriteria criteria){
			return this.getHibernateTemplate().findByCriteria(criteria);
	}
		
	 /**
	  * ,根据条件获取数据,带分页,有总记录数
	  * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 public ListPage<T> findListPage(DetachedCriteria detachedCriteria,final Page page,final Criterion... criterions){
		 detachedCriteria=detachedCriteria==null?createDetachedCriteria(criterions):this.createDetachedCriteria(detachedCriteria, criterions);
		 List<T> ls=this.find(detachedCriteria,page.getCurrentRow(),page.getPageSize());
		 page.setRowCount(this.getDetachedCriteriaRowCount(detachedCriteria));
		 return new ListPage<T>(ls, page);
	 }
	 /**
	  * ,根据条件获取数据,带分页,有总记录数
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 public ListPage<T> findListPage(final Page page,final Criterion... criterions){
		 return findListPage(null,page,criterions);
	 }
	 /**
	  * ,根据条件获取数据,带分页,有总记录数
	  * @param page 
	  * @param entity 对象 里面的值为查询条件
	  * @return
	  */
	 public ListPage<T> findListPage(final Page page,T entity){
		 return findListPage(null,page,Example.create(entity));
	 }
	/**
	 * 对象化查询
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public DetachedCriteria createDetachedCriteria(
			final Criterion... criterions) {
		return createDetachedCriteria(entityClass, criterions);
	}
	/**
	 * 对象化查询
	 * @param entityClass
	 *            参数T的反射类型
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public DetachedCriteria createDetachedCriteria(final Class clazz,
			final Criterion... criterions) {
		return createDetachedCriteria(DetachedCriteria.forClass(clazz),criterions);
	}
	/**
	 * 对象化查询
	 * 
	 * @param detachedCriteria
	 *            离线查询 DetachedCriteria
	 * @param criterions
	 *            数量可变的Criterion
	 */
	public DetachedCriteria createDetachedCriteria(DetachedCriteria detachedCriteria,
			final Criterion... criterions) {
		
		if(detachedCriteria==null)
			detachedCriteria=DetachedCriteria.forClass(entityClass);
		if(criterions==null)
			return detachedCriteria;
		for (Criterion d : criterions) {
			detachedCriteria.add(d);
		}
		return detachedCriteria;
	}
	/**
	 * 根据字段生成 like
	 * 
	 * @param clazz
	 * @param values
	 * @return
	 */
	public Criterion[] getCriterionLike(Map<String, ?> values) {
		if (values == null || values.isEmpty())
			return null;
		List<Criterion> list = new ArrayList<Criterion>();
		for (Entry<String, ?> m : values.entrySet()) {
			list.add(Restrictions.like(m.getKey(), m.getValue()));
		}
		return list.toArray(new Criterion[0]);
	}

	/**
	 * 生成 smart<=propertyName<=big
	 * 
	 * @return
	 */
	public Criterion getBentweenCriterion(String propertyName, Object smart,
			Object big) {
		if (smart == null || big == null)
			return null;
		return Restrictions.between(propertyName, smart, big);
	}
	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @return
	 */
	public List<T> findHql(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param obj
	 *            命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return 
	 */
	public List<T> findHql(final String hql, Object obj) {
		return findHql(hql, -1, -1, obj);
	}

	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @return List<T> 返回类型
	 */
	public List<T> findHql(final String hql, final int pageIndex,
			final int pageSize) {
		return findHql(hql, pageIndex, pageSize, null);
	}

	/**
	 * @Author: fjz
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return List<T> 返回类型
	 */
	public List<T> findHql(final String hql, final Page page) {
		return findHql(hql, page, null);
	}

	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param obj
	 *            命名参数,按名称绑定.输入参数 分别有map、数组和对象类型
	 * @param page
	 * @return 更新记录数.
	 */
	public List<T> findHql(final String hql, final Page page,
			final Object obj){
		return this.findHql(hql, page.getCurrentRow(), page.getPageSize(),
				obj);
	}

	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return ListPage<T> 返回类型
	 */
	public ListPage<T> findHqlListPage(final String hql, final Page page) {
		return findHqlListPage(hql, page, null);
	}

	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * 	@param obj
	 *            命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return ListPage<T> 返回类型
	 */
	public ListPage<T> findHqlListPage(final String hql, final Page page,
			final Object obj) {
		List<T> list = findHql(hql, page, obj);
		page.setRowCount(this.getHqlRowCount(hql,obj));
		return new ListPage<T>(list, page);
	}

	/**
	 * @param hql
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	public List<T> findHql(final String hql, final int pageIndex,
			final int pageSize, final Object obj) {
		return (List<T>)this.getQueryList(hql, pageIndex, pageSize, obj,2);
	}
	
	/**
	 * 如果obj是map类型则对应语句是  XXX x where x.name=:name(:name---->key名字对应name  value则是要注入的值)
	 * 如果obj是数组类型则对应语句是  XXX x where x.name=?
	 * 如果obj对应的不是上述两种参数，则默认obj为映射的类型  语句是  XXX x where x.name=:name(:name的值是对应的同名[name]字段值)
	 * 通过hql查出数据
	 * @param queryString
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @param obj
	 * @param type 1=sql，2=hql
	 * @return
	 */
	private List<T> getQueryList(final String queryString, final int pageIndex,
			final int pageSize, final Object obj,final int type){
		return getQueryList(queryString, pageIndex, pageSize, obj, entityClass, type);
	}
	/**
	 * 如果obj是map类型则对应语句是  XXX x where x.name=:name(:name---->key名字对应name  value则是要注入的值)
	 * 如果obj是数组类型则对应语句是  XXX x where x.name=?
	 * 如果obj对应的不是上述两种参数，则默认obj为映射的类型  语句是  XXX x where x.name=:name(:name的值是对应的同名[name]字段值)
	 * 通过hql查出数据
	 * @param queryString
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @param obj
	 * @param class 转换类型
	 * @param type 1=sql，2=hql
	 * @return
	 */
	private <X>List<X> getQueryList(final String queryString, final int pageIndex,
			final int pageSize, final Object obj,final Class<X> clazz,final int type){
		List<X> list=this.getHibernateTemplate().executeFind(new HibernateCallback<List<X>>() {
			public List<X> doInHibernate(Session session) {
				Query query = getQuery(queryString, pageIndex, pageSize, obj,
						type, session,clazz);
				return query.list();
			}
		});
		return list;
	}

	/**
	 * @Author: Charles
	 * @Description: 根据SQL查询所有实体并分页
	 * @param sql
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	public List<T> findSql(final String sql, final int pageIndex,
			final int pageSize, Object obj) {
		return (List<T>)this.getQueryList(sql, pageIndex, pageSize, obj, 1);
	}

	/**
	 * @Author: fjz
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	public List<T> findSql(final String sql, final Page page,
			final Object obj) {
		return this.findSql(sql, page.getCurrentRow(), page.getPageSize(),
				obj);
	}

	/**
	 * @Author: fjz
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return List<T> 返回类型
	 */
	public List<T> findSql(final String sql, final Page page) {
		return this.findSql(sql, page.getCurrentRow(), page.getPageSize(), null);
	}

	/**
	 * @Author: fjz
	 * @Description: 通过SQL查询实体列表
	 * @param sql
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T>:
	 */
	public List<T> findSql(final String sql, final Object obj) {
		return this.findSql(sql, -1, -1, obj);
	}

	/**
	 * @Author: fjz
	 * @Description: 通过SQL查询实体列表
	 * @param sql
	 * @return List<T>:
	 */
	public List<T> findSql(final String sql) {
		return this.findSql(sql, -1, -1, null);
	}

	/**
	 * @Author: Charles
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return List<T> 返回类型
	 */
	public ListPage<T> findSqlListPage(final String sql, final Page page) {
		return this.findSqlListPage(sql, page, null);
	}

	/**
	 * @Author: Charles
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	public ListPage<T> findSqlListPage(final String sql, final Page page,
			final Object obj) {
		List<T> list = this.findSql(sql, page, obj);
		page.setRowCount(this.getSqlRowCount(sql,obj));
		return new ListPage<T>(list, page);
	}

	/*****************************XXX*(findByExample start）findByExample 与 find方法有重叠，建议使用find****************************************************************************************/
	/**
	 * 示例： User u=new User(); u.setPassword("123" );//必须
	 * 符合的条件但是这两个条件时并列的（象当于sql中的and） u.setName("bb" ); list=this
	 * .getHibernateTemplate().findByExample(u); 返回：用户名为bb密码为123的对象
	 * @param entity
	 * @return
	 */
	public List<T> findByExample(Object entity) {
		return this.getHibernateTemplate().findByExample(entity);
	}

	/**
	 * 分页
	 */
	public List<T> findByExample(Object entity, final Page page) {
		return this.getHibernateTemplate().findByExample(entity,
				page.getCurrentRow(), page.getPageSize());
	}

	public ListPage<T> findByExampleListPage(final T entity,Page page){
		ListPage<T> list=new ListPage<T>();
		list.setList(this.findByExample(entity, page.getCurrentRow(), page.getPageSize()));
		page.setRowCount(getDetachedCriteriaRowCount(entity));
		list.setPage(page);
		return list;
	}

	/**
	 * 自firstResultmaxmaxResults 自start起共max个对象
	 * @param entity
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<T> findByExample(Object entity, int firstResult, int maxResults) {
		return this.getHibernateTemplate().findByExample(entity, firstResult,maxResults);
	}
	/******************************(findByExample end）findByExample 与 find方法有重叠，建议使用find****************************************************************************************/
	
	/*=================下面这个方法作用不大，可以去掉=======================*/
//	/**
//	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
//	 * @param criterions
//	 * @return
//	 */
//	public int getDetachedCriteriaRowCount(Criterion... criterions) {
//		return getDetachedCriteriaRowCount(entityClass,criterions);
//	}
	
	/**
	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
	 * @param entity
	 * @return
	 */
	public int getDetachedCriteriaRowCount(final T entity) {
		return getDetachedCriteriaRowCount(entityClass,Example.create(entity));
	}
	/**
	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
	 * @param criterions
	 * @param clazz
	 * @return
	 */
	public int getDetachedCriteriaRowCount(Class<?> clazz,final Criterion... criterions) {
		return getDetachedCriteriaRowCount(DetachedCriteria.forClass(clazz),criterions);
	}
	/**
	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
	 * @param entity
	 * @return
	 */
	public int getDetachedCriteriaRowCount(final DetachedCriteria detachedCriteria,final Criterion... criterions) {
		this.createDetachedCriteria(detachedCriteria, criterions);
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>(){
			public Integer doInHibernate(Session session){
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				return totalCount;
			}
		});
	}
	
	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询数据总数
	 * @param hql
	 * @return
	 */
	public int getHqlRowCount(String hql) {
		return getHqlRowCount(hql,null);
	}
	
	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询数据总数
	 * @param hql
	 * @return long:
	 */
	public int getHqlRowCount(String hql,Object obj) {
		if (!hql.trim().toLowerCase().startsWith("from")) {
			hql = hql.substring(hql.toLowerCase().indexOf("from "));
		}
		hql = "select count(*) " + hql;
		List<?> list = this.findHql(hql, obj);
		int row = ((Long) list.get(0)).intValue();
		return row;
	}
	public int getSqlRowCount(String sql) {
		return getSqlRowCount(sql,null);
	}
	/**
	 * @Author: Charles
	 * @Description: 获取sql数据的总记录数
	 * @param sql
	 * @return long:
	 */
	public int getSqlRowCount(String sql,final Object obj) {
		sql = "select count(1) from (" + sql + ") a";
		List<?> list = this.findSql(sql,obj);
		int row = ((BigInteger) list.get(0)).intValue();
		return row;
	}
	
	/**
	 * XXX 调用sql语句的方法可以调用存储过程，这些都是重复去掉。可以看hibernaterBaseDaoImplTest的
	 */
//	
//	/**
//	 * 调用存储过程
//	 * @param call
//	 * @return
//	 */
//	public List<T> callList(final String call){
//		List<T> list  = (List<T>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session) {
//						Query q =getQuery(call, null, 1, session);
//						return q.list();
//					}
//				});
//		return list;
//	}
//	/**
//	 * 调用存储过程
//	 * @param call
//	 * @return
//	 */
//	public List<T> callList(final String call,final Object obj){
//		List<T> list  = (List<T>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session) {
//						Query q =getQuery(call, obj, 1, session);
//						return q.list();
//					}
//				});
//		return list;
//	}
//	/**
//	 * 调用存储过程
//	 * @param call
//	 * @return
//	 */
//	public int callUpdate(final String call){
//		int i=this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session) {
//						Query q =getQuery(call, null, 1, session);
//						return q.executeUpdate();
//					}
//				});
//		return i;
//	}
//	/**
//	 * 调用存储过程
//	 * @param call
//	 * @return
//	 */
//	public int callUpdate(final String call,final Object obj){
//		int i=this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session) {
//						Query q =getQuery(call, obj, 1, session);
//						return q.executeUpdate();
//					}
//				});
//		return i;
//	}
	
}
