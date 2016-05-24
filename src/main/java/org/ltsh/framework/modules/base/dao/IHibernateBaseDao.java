package org.ltsh.framework.modules.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.ltsh.framework.component.ListPage;
import org.ltsh.framework.component.Page;


public interface IHibernateBaseDao<T, PK extends Serializable> extends IUpdateDao<T, PK>{
	/**
	 * @Description: 通过HQL查询一个实体
	 * @param hql
	 * @return 参数
	 * @return T 返回类型
	 */
	T getHql(final String hql) ;

	/**
	 * @Description: 通过HQL查询一个实体
	 * @param hql	
	 * @param obj   输入参数 分别有map、数组和对象类型
	 * @return 参数
	 * @return T 返回类型
	 */
	T getHql(final String hql, final Object obj) ;
	
	/**
	 * @Description: 通过sql查询一个实体
	 * @param sql
	 * @param obj   输入参数 分别有map、数组和对象类型
	 * @return T 返回类型
	 */
	T getSql(final String sql, final Object obj) ;

	/**
	 * @Description: 通过sql查询一个实体
	 * @param sql
	 * @return 参数
	 * @return T 返回类型
	 */
	T getSql(final String sql) ;
	

	/**
	 * 获取全部对象,带排序字段与升降序参
	 * @Author: fjz
	 * @param entityClass	 要获得类型的class
	 * @param orderBy		排序字段名
	 * @param isAsc	是否是正序
	 * @return
	 */
	<X> List<X> findAll(Class<X> entityClass, String orderBy,
			boolean isAsc) ;
	
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
	<X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys, int firstResult, int maxResults) ;
	
	/** 
	 * @Author: Charles
	 * @Description: 获取全部对象,带查询、排序、分页
	 * @param entityClass
	 * @param criterions
	 * @param orderBys
	 * @param page
	 * @return List<X>: 
	 */
	<X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys, Page page) ;
	
	/** 
	 * @Author: Charles
	 * @Description: 获取全部对象,带查询、排序、不带分页
	 * @param entityClass
	 * @param criterions
	 * @param orderBys
	 * @return List<X>: 
	 */
	<X> List<X> findAll(Class<X> entityClass, final Criterion[] criterions, 
			final Order[] orderBys) ;

	/**
	 * 获取全部对象,带排序字段与升降序参
	 * @Author: fjz
	 * @param orderBy		排序字段名
	 * @param isAsc	是否是正序
	 * @return
	 */
	List<T> findAll(String orderBy, boolean isAsc) ;

	/**
	 * 获取全部对象
	 * @Author: fjz
	 * @param entityClass	 要获得类型的class
	 * @return
	 */
	<X> List<X> findAll(Class<X> entityClass);


	/**
	 * 获取全部对象
	 */
	List<T> findAll() ;
	/**
	 * 	@Author: fjz
	 * @param entity必须是hibernate映射对象
	 * String 类型值的全部用like查询
	 * 其他用等号
	 * MatchMode.ANYWHERE 是对 String 类型的属性，无论在那里值在那里都匹配。相当于 %entity% 获取全部对象
	 */
	List<T> findStringLike(Object entity) ;
	/**
	 * @Author fjz
	 * @param entity所有有值的值都是查询条件
	 * 如：Demo(id=1,name="name") 则 等于 from Deom d where d.id=1 and d.name='name'; 
	 */
	List<T> find(Object entity) ;
	/**
	 * 根据条件获取数据
	 * @param clazz 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	<X> List<X> find(Class<X> clazz,final Criterion... criterions);
	
	/**
	 * 根据条件获取数据
	 * @param clazz 
	 * @param criterions
	 *            数量可变的Criterion
	 */
	<X> X findUnique(Class<X> clazz, final Criterion... criterions);
	/**
	 * 根据条件获取数据
	 * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	 */
	<X>List<X> find(DetachedCriteria detachedCriteria) ;
	/**
	 * 根据条件获取数据
	 * @param firstResult 	第几条开始查询
	 * @param maxResults	最大返回数据行数
	 * @param criterions 数量可变的Criterion(面向对象查询条件)
	 * @return
	 */
	 List<T> find(int firstResult, int maxResults,final Criterion... criterions) ;
	/**
	 * 根据条件获取数据
	 * @param firstResult 	从第几条开始查询
	 * @param maxResults	最大返回数据行数
	 * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	 * @return
	 */
	 List<T> find(DetachedCriteria detachedCriteria,int firstResult, int maxResults) ;
	 /**
	  * 根据条件获取数据,带分页
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 List<T> find(Page page,final Criterion... criterions) ;
	 /**
	  * 根据条件获取数据,带分页
	  * @param page
	  * @param entity 对象
	  * @return
	  */
	 List<T> find(final Page page,T entity);
	 /**
	  * 根据条件获取数据,带分页
	  * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 List<T> find(DetachedCriteria detachedCriteria,Page page,final Criterion... criterions) ;
	 
	 /** 
		 * @Author: Charles
		 * @Description: 通过离线条件查询
		 * @param criteria
		 * @return List: 
		 */
	List findByCriteria(DetachedCriteria criteria);
		
	 /**
	  * ,根据条件获取数据,带分页,有总记录数
	  * @param detachedCriteria  hibernate 离线查询 DetachedCriteria
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 ListPage<T> findListPage(DetachedCriteria detachedCriteria,final Page page,final Criterion... criterions);
	 /**
	  * ,根据条件获取数据,带分页,有总记录数
	  * @param page
	  * @param criterions 数量可变的Criterion(面向对象查询条件)
	  * @return
	  */
	 ListPage<T> findListPage(final Page page,final Criterion... criterions);
	 /**
	  * ,根据条件获取数据,带分页,有总记录数
	  * @param page 
	  * @param entity 对象 里面的值为查询条件
	  * @return
	  */
	 ListPage<T> findListPage(final Page page,T entity);
	/**
	 * 对象化查询
	 * @param criterions
	 *            数量可变的Criterion
	 */
	DetachedCriteria createDetachedCriteria(
			final Criterion... criterions) ;
	/**
	 * 对象化查询
	 * @param entityClass
	 *            参数T的反射类型
	 * @param criterions
	 *            数量可变的Criterion
	 */
	DetachedCriteria createDetachedCriteria(final Class clazz,
			final Criterion... criterions) ;
	/**
	 * 对象化查询
	 * 
	 * @param detachedCriteria
	 *            离线查询 DetachedCriteria
	 * @param criterions
	 *            数量可变的Criterion
	 */
	DetachedCriteria createDetachedCriteria(DetachedCriteria detachedCriteria,
			final Criterion... criterions) ;
	/**
	 * 根据字段生成 like
	 * 
	 * @param clazz
	 * @param values
	 * @return
	 */
	Criterion[] getCriterionLike(Map<String, ?> values) ;

	/**
	 * 生成 smart<=propertyName<=big
	 * 
	 * @return
	 */
	Criterion getBentweenCriterion(String propertyName, Object smart,
			Object big) ;
	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @return
	 */
	List<T> findHql(String hql) ;
	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param obj
	 *            命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return 
	 */
	List<T> findHql(final String hql, Object obj) ;

	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @return List<T> 返回类型
	 */
	List<T> findHql(final String hql, final int pageIndex,
			final int pageSize) ;

	/**
	 * @Author: fjz
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return List<T> 返回类型
	 */
	List<T> findHql(final String hql, final Page page) ;

	/**
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param obj
	 *            命名参数,按名称绑定.输入参数 分别有map、数组和对象类型
	 * @param page
	 * @return 更新记录数.
	 */
	List<T> findHql(final String hql, final Page page,
			final Object obj);

	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return ListPage<T> 返回类型
	 */
	ListPage<T> findHqlListPage(final String hql, final Page page) ;

	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * 	@param obj
	 *            命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return ListPage<T> 返回类型
	 */
	ListPage<T> findHqlListPage(final String hql, final Page page,
			final Object obj) ;

	/**
	 * @param hql
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	List<T> findHql(final String hql, final int pageIndex,
			final int pageSize, final Object obj) ;

	/**
	 * @Author: Charles
	 * @Description: 根据SQL查询所有实体并分页
	 * @param sql
	 * @param pageIndex=第几条数据开始
	 * @param pageSize=查询的数据条数
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	List<T> findSql(final String sql, final int pageIndex,
			final int pageSize, Object obj) ;

	/**
	 * @Author: fjz
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	List<T> findSql(final String sql, final Page page,
			final Object obj) ;

	/**
	 * @Author: fjz
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return List<T> 返回类型
	 */
	List<T> findSql(final String sql, final Page page) ;

	/**
	 * @Author: fjz
	 * @Description: 通过SQL查询实体列表
	 * @param sql
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T>:
	 */
	List<T> findSql(final String sql, final Object obj) ;

	/**
	 * @Author: fjz
	 * @Description: 通过SQL查询实体列表
	 * @param sql
	 * @return List<T>:
	 */
	List<T> findSql(final String sql) ;

	/**
	 * @Author: Charles
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @return List<T> 返回类型
	 */
	ListPage<T> findSqlListPage(final String sql, final Page page) ;

	/**
	 * @Author: Charles
	 * @Description: 根据SQL查询所有实体并分页
	 * @param hql
	 * @param page
	 * @param obj  命名参数,按名称绑定. 输入参数 分别有map、数组和对象类型
	 * @return List<T> 返回类型
	 */
	ListPage<T> findSqlListPage(final String sql, final Page page,
			final Object obj) ;

	/*****************************XXX*(findByExample start）findByExample 与 find方法有重叠，建议使用find****************************************************************************************/
	/**
	 * 示例： User u=new User(); u.setPassword("123" );//必须
	 * 符合的条件但是这两个条件时并列的（象当于sql中的and） u.setName("bb" ); list=this
	 * .getHibernateTemplate().findByExample(u); 返回：用户名为bb密码为123的对象
	 * @param entity
	 * @return
	 */
	List<T> findByExample(Object entity) ;

	/**
	 * 分页
	 */
	List<T> findByExample(Object entity, final Page page) ;

	ListPage<T> findByExampleListPage(final T entity,Page page);

	/**
	 * 自firstResultmaxmaxResults 自start起共max个对象
	 * @param entity
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	List<T> findByExample(Object entity, int firstResult, int maxResults) ;
	/******************************(findByExample end）findByExample 与 find方法有重叠，建议使用find****************************************************************************************/
	
	/**
	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
	 * @param entity
	 * @return
	 */
	int getDetachedCriteriaRowCount(final T entity) ;
	/**
	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
	 * @param criterions
	 * @param clazz
	 * @return
	 */
	int getDetachedCriteriaRowCount(Class<?> clazz,final Criterion... criterions) ;
	/**
	 * QBE（Query By Example）DetachedCriteria 查询方式  查询数据的总行
	 * @param entity
	 * @return
	 */
	int getDetachedCriteriaRowCount(final DetachedCriteria detachedCriteria,final Criterion... criterions) ;
	
	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询数据总数
	 * @param hql
	 * @return
	 */
	int getHqlRowCount(String hql) ;
	
	/**
	 * @Author: Charles
	 * @Description: 根据HQL查询数据总数
	 * @param hql
	 * @return long:
	 */
	int getHqlRowCount(String hql,Object obj) ;
	
	int getSqlRowCount(String sql) ;
	/**
	 * @Author: Charles
	 * @Description: 获取sql数据的总记录数
	 * @param sql
	 * @return long:
	 */
	int getSqlRowCount(String sql,final Object obj) ;
	
}
