///**
// * 
// */
//package org.ltsh.framework.dao.impl;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.ltsh.app.model.Demo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//@RunWith(SpringJUnit4ClassRunner.class)//spring的测试类
//@ContextConfiguration({"/applicationContext.xml"})//spring的配置文件
//public class UpdateDaoImplTest {
//	@Autowired
//	private HibernateBaseDaoImpl<Demo> demoDao;
//	
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
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#getEntityClass()}.
//	 */
//	@Test
//	public void testGetEntityClass() {
//		assertEquals(Demo.class, demoDao.entityClass);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#UpdateDaoImpl()}.
//	 */
//	@Test
//	public void testUpdateDaoImpl() {
//	}
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#delete(java.lang.Object)}.
//	 */
//	@Test
//	public void testDeleteObject() {
//		Demo d=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		demoDao.delete(d);
//		d=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals(null,d);
//		assertEquals(9,demoDao.findAll().size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#delete(java.util.Collection)}.
//	 */
//	@Test
//	public void testDeleteCollectionOfQ() {
//		demoDao.delete(demoDao.findAll());
//		assertEquals(0,demoDao.findAll().size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#delete(java.io.Serializable)}.
//	 */
//	@Test
//	public void testDeleteSerializable() {
//		demoDao.delete(demoDao.getHql("from Demo d where d.name='demoDao1'").getId());
//		assertEquals(9,demoDao.findAll().size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#delete(java.util.List)}.
//	 */
//	@Test
//	public void testDeleteListOfSerializable() {
//		List list=new ArrayList();
//		for(Demo d:demoDao.findAll())
//			list.add(d.getId());
//		demoDao.delete(list);
//		assertEquals(0,demoDao.findAll(Demo.class).size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#delete(java.lang.Class, java.io.Serializable)}.
//	 */
//	@Test
//	public void testDeleteClassOfQSerializable() {
//		demoDao.delete(Demo.class,demoDao.getHql("from Demo d where d.name='demoDao1'").getId());
//		assertEquals(9,demoDao.findAll().size());
//	}
//
//	/** XXX
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#delete(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testDeleteStringObject() {
//		Demo demo=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		demoDao.delete("Demo",demo);
//		assertEquals(9,demoDao.findAll().size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#saveOrUpdate(java.lang.Object)}.
//	 */
//	@Test
//	public void testSaveOrUpdateT() {
//		Demo d=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		d.setName("11111111");
//		demoDao.saveOrUpdate(d);
//		d=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals(null,d);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#saveOrUpdate(java.util.Collection)}.
//	 */
//	@Test
//	public void testSaveOrUpdateCollectionOfT() {
//		
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#add(java.lang.Object)}.
//	 */
//	@Test
//	public void testAddT() {
//		Demo d=new Demo();
//		d.setLastUpdateTime(new Date());
//		d.setName("11111111");
//		demoDao.add(d);
//		assertEquals(11,demoDao.findAll().size());
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#add(java.util.Collection)}.
//	 */
//	@Test
//	public void testAddCollectionOfT() {
//		
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#update(java.lang.Object)}.
//	 */
//	@Test
//	public void testUpdateT() {
//		Demo d=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		d.setName("11111111");
//		demoDao.update(d);
//		d=demoDao.getHql("from Demo d where d.name='demoDao1'");
//		assertEquals(null,d);
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#update(java.util.Collection)}.
//	 */
//	@Test
//	public void testUpdateCollectionOfT() {
//		//
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#flush()}.
//	 */
//	@Test
//	public void testFlush() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#clear()}.
//	 */
//	@Test
//	public void testClear() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#batchExecuteHql(java.lang.String)}.
//	 */
//	@Test
//	public void testBatchExecuteHqlString() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#batchExecuteHql(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testBatchExecuteHqlStringObject() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#batchExecuteSql(java.lang.String)}.
//	 */
//	@Test
//	public void testBatchExecuteSqlString() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#batchExecuteSql(java.lang.String, java.lang.Object)}.
//	 */
//	@Test
//	public void testBatchExecuteSqlStringObject() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#getQuery(java.lang.String, java.lang.Object, int, org.hibernate.Session)}.
//	 */
//	@Test
//	public void testGetQueryStringObjectIntSession() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#getQuery(java.lang.String, int, int, java.lang.Object, int, org.hibernate.Session)}.
//	 */
//	@Test
//	public void testGetQueryStringIntIntObjectIntSession() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#getQuery(java.lang.String, int, int, java.lang.Object, int, org.hibernate.Session, java.lang.Class)}.
//	 */
//	@Test
//	public void testGetQueryStringIntIntObjectIntSessionClassOfQ() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#get(java.io.Serializable)}.
//	 */
//	@Test
//	public void testGetSerializable() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link org.ltsh.framework.dao.impl.UpdateDaoImpl#get(java.lang.Class, java.io.Serializable)}.
//	 */
//	@Test
//	public void testGetClassOfXSerializable() {
//		fail("Not yet implemented");
//	}
//
//}
