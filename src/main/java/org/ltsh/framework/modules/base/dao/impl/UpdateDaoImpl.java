/**
 * 
 */
package org.ltsh.framework.modules.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.ltsh.framework.modules.base.dao.IUpdateDao;
import org.ltsh.framework.util.ReflectUtils;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author fengjianzhong
 * 
 */
public abstract class UpdateDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements IUpdateDao<T, PK>{
	
	/**
	 * log4j日志
	 */
	protected final Logger logger = Logger.getLogger(getClass());

	protected Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 在构造函数中利用反射机制获得参数T的具体类
	 */
	public UpdateDaoImpl() {
		entityClass = ReflectUtils.getClassGenricType(getClass());
	}

	/** 
	 * @Author: Charles
	 * @Description: 初始化HibernateTemplate
	 * @param sessionFactory void: 
	 */
	@Resource(name="sessionFactory")
	protected void initHibernateTemplate(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
		logger.info("初始化 HibernateTemplate : " + getHibernateTemplate());
	}
	
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public T get(final PK id){
		return this.getHibernateTemplate().get(entityClass, id);
	}
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public <X> X get(Class<X> clazz, final PK id){
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public T load(final PK id){
		return this.getHibernateTemplate().load(entityClass, id);
	}
	
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public <X> X load(Class<X> clazz, final PK id){
		return this.getHibernateTemplate().load(clazz, id);
	}
	
	
	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体类
	 */
	public void delete(final T entity) {
		this.getHibernateTemplate().delete(entity);
	}
	//批量删除都是for循环删除的，效率较低
	//考虑到可以连表删除，不做修改
	//如果考虑，性能问题，则batchExecute*  方法组装修改
	/**
	 * 根据实体类与ID组批量删除对象
	 * 
	 * @param clazz
	 * @param ids
	 */
	public void deleteAll(final Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 *            主键ID
	 */
	public void deleteById(final PK id) {
		delete(get(id));
	}

	/**
	 * 根据实体类与ID组批量删除对象
	 * 
	 * @param clazz
	 * @param ids
	 */
	public void deleteByIds(final PK[] ids) {
		for (PK id : ids) {
			deleteById(id);
		}
	}

	/**
	 * 根据条件删除数据
	 * @param clazz
	 * @param obj 条件
	 */
	public void deleteByExample(final Class<?> clazz, Object example){
		deleteAll(this.getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(clazz).add(Example.create(example))));
	}
	/**
	 * @Description: 新增一个实体记录
	 * @param 实体
	 */
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	/**
	 * @Description: 批量添加个实体记录
	 * @param 实体
	 */
	public void saveAll(Collection<T> entities) {
		saveOrUpdateAll(entities);
	}
	/**
	 * @Description: 新增或更新一个实体记录
	 * @param 实体
	 */
	public void saveOrUpdate(final T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void saveOrUpdateAll(final Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
	
	/**
	 * @Description: 持久化一个实体记录
	 * @param 实体
	 */
	public void persist(T entity) {
		this.getHibernateTemplate().persist(entity);
		
	}

	/**
	 * @Description: 更新实体
	 * @param entity
	 *            参数
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * @Description: 批量更新实体
	 * @param list
	 *            参数
	 */
	public void updateAll(Collection<T> entities) {
		this.saveOrUpdateAll(entities);
	}

	/**
	 * 清除缓存，执行SQL
	 */
	public void flush() {
		this.getHibernateTemplate().flush();
	}

	/**
	  * 
	  */
	public void clear() {
		this.getHibernateTemplate().clear();
	}
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param hql
	 * @return 更新记录数.
	 */
	public int batchExecuteHql(final String hql) {
		return this.batchExecuteHql(hql, null);
	}
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param hql
	 * @param params	参数
	 * @return 更新记录数.
	 */
	public int batchExecuteHql(final String hql, final Object params) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session) {
						Query query = getHqlQuery(hql,-1,-1,params,session);
						int i = query.executeUpdate();
						return i;
					}
		});
	}
	/**
	 * 执行sql进行批量修改/删除操作.
	 * @param sql
	 * @return 更新记录数.
	 */
	public int batchExecuteSql(final String sql) {
		return this.batchExecuteSql(sql, null);
	}
	/**
	 * 执行sql进行批量修改/删除操作.
	 * @param hql
	 * @param obj
	 * @return 更新记录数.
	 */
	public int batchExecuteSql(final String sql, final Object param) {
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session) {
						Query query = getSqlQuery(sql,-1,-1,param,session);
						int i = query.executeUpdate();
						return i;
					}
		});
	}
	
	/**
	 * @param queryString
	 * @param pageIndex
	 * @param pageSize
	 * @param param
	 * @param type type 1=sql 2=hql
	 * @param session
	 * @return
	 */
	public Query getQuery(final String queryString,
			final int pageIndex, final int pageSize, final Object param,
			final int type, Session session, Class<?> entity) {
		Query query = null;	
		if(type == 2){	//createQuery	HQL
			query= session.createQuery(queryString);
		}else if(type == 1){	//createSQLQuery	SQL
			if(queryString.toLowerCase().trim().startsWith("select count"))
				query = session.createSQLQuery(queryString);
			else
				query = session.createSQLQuery(queryString).addEntity(entity);
		}
		if (param != null){
			if(param instanceof Map<?,?>){
				query.setProperties((Map<?,?>)param);
			}else if(param.getClass().isArray()){
				int len = Array.getLength(param);
				for (int i = 0; i <len; i++) {
					query.setParameter(i, Array.get(param, i));
				}
			}else{
				query.setProperties(param);
			}
		}
		setPageResult(query, pageIndex, pageSize);	//设置分页
		return query;
	}
	
	/** 
	 * @Author: Charles
	 * @Description:  获取HQL的Query对象 
	 * @param queryString
	 * @param pageIndex
	 * @param pageSize
	 * @param param
	 * @param session
	 * @return Query: 
	 */
	public Query getHqlQuery(final String queryString,
			final int pageIndex, final int pageSize,
			final Object param, Session session){
		return getQuery(queryString, pageIndex, pageSize, param, 2, session, entityClass);
	}
	/** 
	 * @Author: Charles
	 * @Description: 获取SQL的Query对象 
	 * @param queryString	查询语句
	 * @param pageIndex		分页位置
	 * @param pageSize		分页大小
	 * @param param			查询参数
	 * @param session		
	 * @return Query: 
	 */
	public Query getSqlQuery(final String queryString,
			final int pageIndex, final int pageSize,
			final Object param, Session session){
		return getQuery(queryString, pageIndex, pageSize, param, 1, session, entityClass);
	}
	
	/** 
	 * @Author: Charles
	 * @Description:	设置分页数据（辅助函数）
	 * @param query		Hibernate，Query对象
	 * @param pageIndex	分页位置
	 * @param pageSize	分页大小
	 */
	public void setPageResult(Query query, int pageIndex, int pageSize){
		if (pageIndex >= 0) query.setFirstResult(pageIndex);
		if (pageSize > 0) query.setMaxResults(pageSize);
	}
}
