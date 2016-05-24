package org.ltsh.framework.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.ltsh.framework.cache.Cache;
import org.ltsh.framework.util.ApplicationContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartupServletContextListener implements ServletContextListener{
	
	private static Logger logger = Logger.getLogger(StartupServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
		logger.info("注销properties文件");
		Cache.destroyCache();
	}

	/** 
	 * WEB项目启动进行的初始化工作
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		logger.info("初始化ApplicationContextUtil");
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ApplicationContextUtil.applicationContext = ctx; //初始化applicationContext
		
		logger.info("初始化properties文件，建立缓存");
		Cache.initCache(servletContext);	//初始化properties配置文件，建立缓存
	}

	
}
