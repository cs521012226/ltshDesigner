//package org.ltsh.framework.dao.impl;
//
//import static org.junit.Assert.assertEquals;
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
//import org.ltsh.framework.component.Page;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//@RunWith(SpringJUnit4ClassRunner.class)//spring的测试类
//@ContextConfiguration({"/applicationContext.xml"})//spring的配置文件
//public class GenericDaoImplTest {
//	@Autowired
//	private GenericDaoImpl demoDao;
//	
//	@Before //① 准备测试数据 
//    public void prepareTestData() {
//		List<Object> list=new ArrayList<Object>(10);
//		for (int i = 0; i < 10; i++) {
//			Demo demo=new Demo();
//			demo.setLastUpdateTime(new Date());
//			demo.setName("demoDao"+i);
//			list.add(demo);
//		}
//		demoDao.adds(list);
//    }
//@After //① 删除测试数据
//    public void deleteTestData() {
//		demoDao.batchExecuteHql("delete Demo");
//    }
//
//	@Test
//	public void testFindHqlString() {
//		List<Object> list=demoDao.findHql("from Demo");
//		assertEquals(10,list.size());
//	}
//
//	@Test
//	public void testFindSqlString() {
//	
//	}
//	@Test
//	public void testGetHqlString() {
//		List<Object> list=demoDao.findHql("from Demo",new Page(1,1));
//		assertEquals(1,list.size());
//	}
//}
