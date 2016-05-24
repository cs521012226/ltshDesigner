///**
// * 
// */
//package org.ltsh.framework.dao.impl;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Example;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.type.Type;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.ltsh.app.model.Demo;
//import org.ltsh.framework.component.ListPage;
//import org.ltsh.framework.component.Page;
//import org.ltsh.framework.util.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * @author fengjianzhong
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)//spring的测试类
//@ContextConfiguration({"/applicationContext.xml"})//spring的配置文件
//public class HibernateBaseDaoImplTest {
//	@Autowired
//	private HibernateBaseDaoImpl<Demo> demoDao;
//	@Before //① 准备测试数据 
//    public void prepareTestData() {
//		List<Demo> list=new ArrayList<Demo>(10);
//		for (int i = 0; i < 10; i++) {
//			Demo demo=new Demo();
//			demo.setLastUpdateTime(new Date());
//			demo.setName("demoDao"+i);
//			list.add(demo);
//		}
//		demoDao.adds(list);
//    }
//	@After //① 删除测试数据
//    public void deleteTestData() {
//		demoDao.batchExecuteHql("delete Demo");
//    }
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#get(java.io.Serializable)}.
//	 */
//	@Test
//	public void testGetSerializable() {
//		Demo demo=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//		Demo demo1=demoDao.get(demo.getId());
//		assertEquals(demo1.getName(),demo.getName());
//		assertEquals(demo1.getId(),demo.getId());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#get(java.lang.Class, java.io.Serializable)}.
//	 */
//	@Test
//	public void testGetClassOfXSerializable() {
//		Demo demo=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//		Demo demo1=demoDao.get(Demo.class,demo.getId());
//		assertEquals(demo1.getName(),demo.getName());
//		assertEquals(demo1.getId(),demo.getId());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#get(java.lang.Class, java.lang.String)}.
//	 */
//	@Test
//	public void testGetClassOfXString() {
//		Demo demo=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//		Demo demo1=demoDao.get(Demo.class,demo.getId()+"");
//		assertEquals(demo1.getName(),demo.getName());
//		assertEquals(demo1.getId(),demo.getId());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getHql(java.lang.String)}.
//	 */
//	@Test
//	public void testGetHqlString() {
//		Demo demo=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getHql(java.lang.String,java.lang.Object)}.
//	 */
//	@Test
//	public void testGetHqlStringObject() {
//		Demo demo=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//		demoDao.getHql("from Demo d where d.name=:name", demo);
//		assertEquals("demoDao1",demo.getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getSql(java.lang.String,java.lang.Object)}.
//	 */
//	@Test
//	public void testGetSqlStringObject() {
//		Demo demo=demoDao.getSql("select * from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//		demoDao.getSql("select * from Demo d where d.name=:name", demo);
//		assertEquals("demoDao1",demo.getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getSql(java.lang.String)}.
//	 */
//	@Test
//	public void testGetSqlString() {
//		Demo demo=demoDao.getSql("select * from Demo d where d.name='demoDao1'");
//		assertEquals("demoDao1",demo.getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getPkType(java.lang.Class)}.
//	 */
//	@Test
//	public void testGetPkType() {
//		Type t=demoDao.getPkType(Demo.class);
//		System.out.println("GetPkType:"+t);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findAll(java.lang.Class, java.lang.String, boolean)}.
//	 */
//	@Test
//	public void testFindAllClassOfXStringBoolean() {
//		List<Demo> list=demoDao.findAll(Demo.class, "id", false);
//		for (int i = 0,j=9; i < list.size(); i++,j--) {
//			assertEquals("demoDao"+j,list.get(i).getName());
//		}
//		 list=demoDao.findAll(Demo.class, "id", true);
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals("demoDao"+i,list.get(i).getName());
//		}
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findAll(java.lang.String, boolean)}.
//	 */
//	@Test
//	public void testFindAllStringBoolean() {
//		List<Demo> list=demoDao.findAll("id", false);
//		for (int i = 0,j=9; i < list.size(); i++,j--) {
//			assertEquals("demoDao"+j,list.get(i).getName());
//		}
//		 list=demoDao.findAll("id", true);
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals("demoDao"+i,list.get(i).getName());
//		}
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findAll(java.lang.Class)}.
//	 */
//	@Test
//	public void testFindAllClassOfX() {
//		List<Demo> list=demoDao.findAll(Demo.class);
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals("demoDao"+i,list.get(i).getName());
//		}
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findAll()}.
//	 */
//	@Test
//	public void testFindAll() {
//		List<Demo> list=demoDao.findAll();
//		for (int i = 0; i < list.size(); i++) {
//			assertEquals("demoDao"+i,list.get(i).getName());
//		}
//	}
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findStringLike(java.lang.Object)}.
//	 */
//	@Test
//	public void testFindStringLikeObject() {
//		//map 
////		Map<String,String> map=new HashMap<String,String>();
////		map.put("name","demo");
////		List<Demo> list=demoDao.findLike(map);
////		assertEquals(10,list.size());
//		//对象
//		Demo demo=new Demo();
//		demo.setName("demo");
//		List<Demo> list=demoDao.findStringLike(demo);
//		assertEquals(10,list.size());
//	}
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#find(java.lang.Object)}.
//	 */
//	@Test
//	public void testFindObject() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		List<Demo> list=demoDao.find(demo);
//		assertEquals(1,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#find(java.lang.Class, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testFindClassOfXCriterionArray() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		List<Demo> list=demoDao.find(Demo.class,new Criterion[]{Restrictions.sqlRestriction("name='DemoDao1'")});
//		assertEquals(1,list.size());
//		list=demoDao.find(Demo.class,new Criterion[]{Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())}
//		);
//		assertEquals(10,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#find(int, int, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testFindIntIntCriterionArray() {
//		List<Demo>list=demoDao.find(0,5,new Criterion[]{Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())}
//		);
//		assertEquals(5,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#find(org.hibernate.criterion.DetachedCriteria, org.ltsh.framework.component.Page, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testFindDetachedCriteriaPageCriterionArray() {
//		List<Demo>list=demoDao.find(DetachedCriteria.forClass(Demo.class),new Page(1,3),new Criterion[]{Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())}
//				);
//		assertEquals(3,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#find(org.ltsh.framework.component.Page, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testFindPageCriterionArray() {
//		List<Demo>list=demoDao.find(new Page(1,3),new Criterion[]{Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())}
//				);
//		assertEquals(3,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#find(org.ltsh.framework.component.Page, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindPageT() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		List<Demo>list=demoDao.find(new Page(1,3),demo);
//		assertEquals(1,list.size());
//	}
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findListPage(org.hibernate.criterion.DetachedCriteria,org.ltsh.framework.component.Page, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testFindListPageDetachedCriteriaPageCriterion() {
//		ListPage<Demo>list=demoDao.findListPage(DetachedCriteria.forClass(Demo.class),new Page(1,3),new Criterion[]{Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())}
//				);
//		assertEquals(3,list.getList().size());
//		assertEquals(4,list.getPage().getPageCount());
//	}
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findListPage(org.ltsh.framework.component.Page, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testFindListPageCriteriaPageCriterion() {
//		ListPage<Demo>list=demoDao.findListPage(new Page(1,3),new Criterion[]{Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())}
//				);
//		assertEquals(3,list.getList().size());
//		assertEquals(4,list.getPage().getPageCount());
//		list=demoDao.findListPage(new Page(1,3));
//		assertEquals(3,list.getList().size());
//		assertEquals(4,list.getPage().getPageCount());
//	}
//	
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findListPage(org.ltsh.framework.component.Page,java.lang.Object)}.
//	 */
//	@Test
//	public void testFindListPageCriteriaPageObject() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		ListPage<Demo>list=demoDao.findListPage(new Page(1,3),demo);
//		assertEquals(1,list.getList().size());
//		assertEquals(1,list.getPage().getPageCount());
//		assertEquals(1,list.getPage().getRowCount());
//	}
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#createDetachedCriteria(org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testCreateDetachedCriteriaCriterionArray() {
//		DetachedCriteria d=demoDao.createDetachedCriteria(new Criterion[]{Restrictions.eq("name","demoDao0"),Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())});
//		List<Demo>list=demoDao.find(d);
//		assertEquals(1,list.size());
//		assertEquals("demoDao0",list.get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#createDetachedCriteria(java.lang.Class, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testCreateDetachedCriteriaClassCriterionArray() {
//		DetachedCriteria d=demoDao.createDetachedCriteria(Demo.class,new Criterion[]{Restrictions.eq("name","demoDao0"),Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())});
//		List<Demo>list=demoDao.find(d);
//		assertEquals(1,list.size());
//		assertEquals("demoDao0",list.get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#createDetachedCriteria(org.hibernate.criterion.DetachedCriteria, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testCreateDetachedCriteriaDetachedCriteriaCriterionArray() {
//		DetachedCriteria d=demoDao.createDetachedCriteria(DetachedCriteria.forClass(Demo.class),new Criterion[]{Restrictions.eq("name","demoDao0"),Restrictions.between("lastUpdateTime", DateUtil.parse(DateUtil.format(new Date(), DateUtil.FORMAT_DATE), DateUtil.FORMAT_DATE) ,new Date())});
//		List<Demo>list=demoDao.find(d);
//		assertEquals(1,list.size());
//		assertEquals("demoDao0",list.get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getCriterionLike(java.util.Map)}.
//	 */
//	@Test
//	public void testGetCriterionLike() {
//		
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getBentweenCriterion(java.lang.String, java.lang.Object, java.lang.Object)}.
//	 */
//	@Test
//	public void testGetBentweenCriterionStringObjectObject() {
//		DetachedCriteria d=demoDao.createDetachedCriteria(new Criterion[]{demoDao.getBentweenCriterion("lastUpdateTime",DateUtil.parse("2000-08-08",DateUtil.FORMAT_DATE),DateUtil.parse("2200-08-08",DateUtil.FORMAT_DATE))});
//		List<Demo>list=demoDao.find(d);
//		assertEquals(10,list.size());
//		assertEquals("demoDao0",list.get(0).getName());
//		d=demoDao.createDetachedCriteria(new Criterion[]{demoDao.getBentweenCriterion("id",0L,1000000L)});
//		list=demoDao.find(d);
//		assertEquals(10,list.size());
//		assertEquals("demoDao0",list.get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHql(java.lang.String)}.
//	 */
//	@Test
//	public void testFindHqlString() {
//		
//		List<Demo> list=demoDao.findHql("from Demo");
//		assertEquals(10,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHql(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindHqlStringObject() {
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name","%demo%");
//		List<Demo> list=demoDao.findHql("from Demo where name like :name",map);
//		assertEquals(10,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHql(java.lang.String, int, int)}.
//	 */
//	@Test
//	public void testFindHqlStringIntInt() {
//		List<Demo> list=demoDao.findHql("from Demo", 1,2);
//		assertEquals(2,list.size());
//		assertEquals("demoDao1",list.get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHql(java.lang.String, org.ltsh.framework.component.Page)}.
//	 */
//	@Test
//	public void testFindHqlStringPage() {
//		List<Demo> list=demoDao.findHql("from Demo", new Page(2,3));
//		assertEquals(3,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHql(java.lang.String, org.ltsh.framework.component.Page, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindHqlStringPageObject() {
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name","%demo%");
//		List<Demo> list=demoDao.findHql("from Demo where name like :name", new Page(1,10),map);
//		assertEquals(10,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHqlListPage(java.lang.String, org.ltsh.framework.component.Page)}.
//	 */
//	@Test
//	public void testFindHqlListPageStringPage() {
//		ListPage<Demo> list=demoDao.findHqlListPage("from Demo", new Page(1, 5));
//		assertEquals(10,list.getPage().getRowCount());
//		assertEquals(5,list.getList().size());
//		assertEquals("demoDao0",list.getList().get(0).getName());
//		list=demoDao.findHqlListPage("from Demo", new Page(2, 5));
//		assertEquals(10,list.getPage().getRowCount());
//		assertEquals(5,list.getList().size());
//		assertEquals("demoDao5",list.getList().get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHqlListPage(java.lang.String, org.ltsh.framework.component.Page, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindHqlListPageStringPageObject() {
//		List<Demo> list=demoDao.findHql("from Demo d where d.name=? and d.lastUpdateTime<=?",new Page(1,5), new Object[]{"demoDao1",new Date()});
//		assertEquals(1,list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findHql(java.lang.String, int, int, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindHqlStringIntIntObject() {
//		List<Demo> list=demoDao.findHql("from Demo d where d.name=? and d.lastUpdateTime<=?",-1,-1, new Object[]{"demoDao1",new Date()});
//		assertEquals(1,list.size());
//		Demo demo=new Demo();
//		demo.setName("demoDao2");
//		list=demoDao.findHql("from Demo d where d.name=:name",-1,-1, demo);
//		Demo d=list.get(0);
//		assertEquals("demoDao2",d.getName());
//		assertEquals(1,list.size());
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name","%demoDao%");
//		list=demoDao.findHql("from Demo d where d.name like :name",0,20, map);
//		assertEquals(10,list.size());
//	}
//	
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSql(java.lang.String, int, int, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindSqlStringIntIntObject() {
//		//map
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name","%demo%");
//		List<Demo> list=demoDao.findSql("select * from demo where name like :name", 0,20,map);
//		assertEquals(10,list.size());
//		//对象
//		Demo demo=new Demo();
//		demo.setName("%demo%");
//		 list=demoDao.findSql("select * from demo where name like :name", 0,20,demo);
//		assertEquals(10,list.size());
//		//数组
//		 list=demoDao.findSql("select * from demo where name like ?",0,20,new Object[]{"%demo%"});
//		 for (int i = 0; i < 10; i++) {
//			 assertEquals("demoDao"+i,list.get(i).getName());
//		}
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSql(java.lang.String, org.ltsh.framework.component.Page, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindSqlStringPageObject() {
//		//map
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name","%demo%");
//		List<Demo> list=demoDao.findSql("select * from demo where name like :name", new Page(1,10),map);
//		assertEquals(10,list.size());
//		//对象
//		Demo demo=new Demo();
//		demo.setName("%demo%");
//		 list=demoDao.findSql("select * from demo where name like :name", new Page(1,20),map);
//		assertEquals(10,list.size());
//		//数组
//		 list=demoDao.findSql("select * from demo where name like ?", new Page(1,20),new Object[]{"%demo%"});
//		 for (int i = 0; i < 10; i++) 
//			 assertEquals("demoDao"+i,list.get(i).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSql(java.lang.String, org.ltsh.framework.component.Page)}.
//	 */
//	@Test
//	public void testFindSqlStringPage() {
//		List<Demo> list=demoDao.findSql("select * from demo where name in('demoDao1','demoDao2')",new Page(1,10));
//		assertEquals(2, list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSql(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindSqlStringObject() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		List<Demo> list=demoDao.findSql("select * from demo where name=:name",demo);
//		assertEquals(1, list.size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSql(java.lang.String)}.
//	 */
//	@Test
//	public void testFindSqlString() {
//		List<Demo> list=demoDao.findSql("select * from demo");
//		assertEquals(10, list.size());	
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSqlListPage(java.lang.String, org.ltsh.framework.component.Page)}.
//	 */
//	@Test
//	public void testFindSqlListPageStringPage() {
//		ListPage<Demo> l=demoDao.findSqlListPage("select * from demo", new Page(1,5));
//		assertEquals(5, l.getList().size());	
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSqlListPage(java.lang.String, org.ltsh.framework.component.Page, java.lang.Object)}.
//	 */
//	@Test
//	public void testFindSqlListPageStringPageObject() {
//	    Map<String,String> map=new HashMap<String,String>();
//		map.put("name", "demoDao0");
//		ListPage<Demo> l=demoDao.findSqlListPage("select * from demo where name=:name", new Page(1,10), map);
//		assertEquals(1, l.getList().size());	
//		
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findByExample(java.lang.Object)}.
//	 */
//	@Test
//	public void testFindByExampleObject() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		List<Demo> list=demoDao.findByExample(demo);
//		assertEquals(list.size(),1);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findByExample(java.lang.Object, org.ltsh.framework.component.Page)}.
//	 */
//	@Test
//	public void testFindByExampleObjectPage() {
//		//从9个开始一共1个
//		List<Demo> list=demoDao.findByExample(new Demo(), new Page(9,1));
//		assertEquals(1,list.size());
//		assertEquals("demoDao8",list.get(0).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findByExampleListPage(java.lang.Object, org.ltsh.framework.component.Page)}.
//	 */
//	@Test
//	public void testFindByExampleListPage() {
//		//从0个开始一共5个
//		ListPage<Demo> list=demoDao.findByExampleListPage(new Demo(),new Page(1,5));
//		assertEquals(5,list.getList().size());
//		assertEquals("demoDao0",list.getList().get(0).getName());
//		assertEquals("demoDao1",list.getList().get(1).getName());
//		assertEquals(10,list.getPage().getRowCount());
//		assertEquals(1,list.getPage().getPageIndex());
//		assertEquals(5,list.getPage().getPageSize());
//		//从(3-1)*3个开始一共3个
//		Demo d=new Demo();
//		list=demoDao.findByExampleListPage(d,new Page(3,3));
//		assertEquals(3,list.getList().size());
//		assertEquals("demoDao6",list.getList().get(0).getName());
//		assertEquals("demoDao7",list.getList().get(1).getName());
//		
//		//从(3-1)*3个开始一共5个
//		d=new Demo();
//		d.setName("demoDao7");
//		list=demoDao.findByExampleListPage(d,new Page(3,3));
//		assertEquals(0,list.getList().size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findByExample(java.lang.Object, int, int)}.
//	 */
//	@Test
//	public void testFindByExampleObjectIntInt() {
//		//从0个开始一共5个
//		List<Demo> list=demoDao.findByExample(new Demo(), 0, 5);
//		assertEquals(5,list.size());
//		assertEquals("demoDao0",list.get(0).getName());
//		assertEquals("demoDao1",list.get(1).getName());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getDetachedCriteriaRowCountByExample(java.lang.Object)}.
//	 */
//	@Test
//	public void testGetDetachedCriteriaRowCountByExample() {
//		Demo demo=new Demo();
//		demo.setName("demoDao1");
//		int i=demoDao.getDetachedCriteriaRowCount(demo);
//		assertEquals(1,i);	
//	}
//
////	/**
////	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getDetachedCriteriaRowCount(org.hibernate.criterion.Criterion[])}.
////	 */
////	@Test
////	public void testGetDetachedCriteriaRowCountCriterionArray() {
////		Demo demo=new Demo();
////		demo.setName("demo");
////		int i=demoDao.getDetachedCriteriaRowCount(Example.create(demo).enableLike(MatchMode.ANYWHERE));
////		assertEquals(10,i);	
////	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getDetachedCriteriaRowCount(java.lang.Class, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testGetDetachedCriteriaRowCountClassOfQCriterionArray() {
//		Demo demo=new Demo();
//		demo.setName("Dao");
//		int i=demoDao.getDetachedCriteriaRowCount(Demo.class,Example.create(demo).enableLike(MatchMode.ANYWHERE));
//		assertEquals(10,i);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getDetachedCriteriaRowCount(org.hibernate.criterion.DetachedCriteria, org.hibernate.criterion.Criterion[])}.
//	 */
//	@Test
//	public void testGetDetachedCriteriaRowCountDetachedCriteriaCriterionArray() {
//		Demo demo=new Demo();
//		demo.setName("demo");
//		int i=demoDao.getDetachedCriteriaRowCount(DetachedCriteria.forClass(demo.getClass()),Example.create(demo).enableLike(MatchMode.ANYWHERE));
//		assertEquals(10,i);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getHqlRowCount(java.lang.String)}.
//	 */
//	@Test
//	public void testGetHqlRowCountString() {
//	    int i=demoDao.getHqlRowCount("from Demo");
//		assertEquals(10,i);
//		i=demoDao.getHqlRowCount("from Demo d where d.name like '%1%'");
//		assertEquals(1,i);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getHqlRowCount(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testGetHqlRowCountStringObject() {
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("name","%demo%");
//		int i=demoDao.getHqlRowCount("from Demo where name like :name",map);
//		assertEquals(10,i);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getSqlRowCount(java.lang.String)}.
//	 */
//	@Test
//	public void testGetSqlRowCountString() {
//		int i=demoDao.getSqlRowCount("select * from Demo");
//		assertEquals(10,i);
//		i=demoDao.getSqlRowCount("select * from Demo where name='demoDao1'");
//		assertEquals(1,i);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#getSqlRowCount(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testGetSqlRowCountStringObject() {
//		int i=demoDao.getSqlRowCount("select * from Demo where name=?",new Object[]{"demoDao1"});
//		assertEquals(1,i);
//	}
//	
//	/**存储过程
//	 * create   PROCEDURE  demo_proc()   
//		begin   
//		    select   *   from  demo;   
//		end ;   
//	 * Test method for {@link org.ltsh.framework.dao.impl.HibernateBaseDaoImpl#findSql( java.lang.String)}.
//	 */
//	@Test
//	public void testCallListString() {
//		List<Demo> list=demoDao.findSql("{Call demo_proc()}");
//		assertEquals(10,list.size());
//		int i=demoDao.batchExecuteSql("{Call demo_proc()}");
//		assertEquals(10,i);
//	}
//	/**存储过程
//	 * create   PROCEDURE  demo_procs (in myname varchar(255),myinlast_update_time datetime)
//	 * begin
//	 * insert into demo (last_update_time, name) values (myinlast_update_time,myname);
//	 * select * from demo;
//	 * end;
//	 */
//	@Test
//	public void testCallListStringObject() {
//		List<Demo> list=demoDao.findSql("{Call demo_procs(?,?)}",new Object[]{
//				"1111111",new Date()
//		});
//		assertEquals(11,list.size());
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("myname", "22222");
//		map.put("myinlast_update_time", new Date());
//		list=demoDao.findSql("{Call demo_procs(:myname,:myinlast_update_time)}",map);
//		assertEquals(12,list.size());
//		
//		 list=demoDao.findSql("{Call demo_procs(?,?)}",new Object[]{
//				"1111111",new Date()
//		});
//		assertEquals(13,list.size());
//	}
//}
