package org.ltsh.framework.modules.base.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;


public interface IUpdateDao<T, PK extends Serializable> {
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	T get(final PK id);
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	<X> X get(Class<X> clazz, final PK id);
	
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	T load(final PK id);
	
	/**
	 * 根据id获得对象
	 * 
	 * @param id
	 *            主键ID
	 */
	<X> X load(Class<X> clazz, final PK id);
	
	
	/**
	 * 删除对象
	 * 
	 * @param entity
	 *            实体类
	 */
	void delete(final T entity) ;
	//批量删除都是for循环删除的，效率较低
	//考虑到可以连表删除，不做修改
	//如果考虑，性能问题，则batchExecute*  方法组装修改
	/**
	 * 根据实体类与ID组批量删除对象
	 * 
	 * @param clazz
	 * @param ids
	 */
	void deleteAll(final Collection<T> entities) ;

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 *            主键ID
	 */
	void deleteById(final PK id) ;

	/**
	 * 根据实体类与ID组批量删除对象
	 * 
	 * @param clazz
	 * @param ids
	 */
	void deleteByIds(final PK [] ids) ;

	/**
	 * 根据条件删除数据
	 * @param clazz
	 * @param obj 条件
	 */
	void deleteByExample(final Class<?> clazz, Object example) ;
	/**
	 * @Description: 新增一个实体记录
	 * @param 实体
	 */
	void save(T entity) ;
	/**
	 * @Description: 批量添加个实体记录
	 * @param 实体
	 */
	void saveAll(Collection<T> entities) ;
	/**
	 * @Description: 新增或更新一个实体记录
	 * @param 实体
	 */
	void saveOrUpdate(final T entity) ;
	
	void saveOrUpdateAll(final Collection<T> entities) ;
	
	/**
	 * @Description: 持久化一个实体记录
	 * @param 实体
	 */
	void persist(T entity) ;

	/**
	 * @Description: 更新实体
	 * @param entity
	 *            参数
	 */
	void update(T entity) ;

	/**
	 * @Description: 批量更新实体
	 * @param list
	 *            参数
	 */
	void updateAll(Collection<T> entities) ;

	/**
	 * 清除缓存，执行SQL
	 */
	void flush() ;

	/**
	  * 
	  */
	void clear() ;
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param hql
	 * @return 更新记录数.
	 */
	int batchExecuteHql(final String hql) ;
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @param hql
	 * @param params	参数
	 * @return 更新记录数.
	 */
	int batchExecuteHql(final String hql, final Object params) ;
	/**
	 * 执行sql进行批量修改/删除操作.
	 * @param sql
	 * @return 更新记录数.
	 */
	int batchExecuteSql(final String sql) ;
	
	/**
	 * @param queryString
	 * @param pageIndex
	 * @param pageSize
	 * @param obj
	 * @param type type 1=sql 2=hql
	 * @param session
	 * @return
	 */
	Query getQuery(final String queryString,
			final int pageIndex, final int pageSize, final Object obj,
			final int type, Session session, Class<?> entity);
	
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
			final Object param, Session session);
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
			final Object param, Session session);
	
	/** 
	 * @Author: Charles
	 * @Description:	设置分页数据（辅助函数）
	 * @param query		Hibernate，Query对象
	 * @param pageIndex	分页位置
	 * @param pageSize	分页大小
	 */
	public void setPageResult(Query query, int pageIndex, int pageSize);
}
