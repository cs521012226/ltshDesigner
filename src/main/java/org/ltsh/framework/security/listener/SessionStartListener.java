package org.ltsh.framework.security.listener;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @author Charles
 * session监听类，每个会话都会监听
 *
 */
public class SessionStartListener implements SessionListener{

	/**
	 * log4j日志
	 */
	protected final Logger logger = Logger.getLogger(SessionStartListener.class);
	
	@Override
	public void onStart(Session session) {
		logger.info("session开启....");
	}

	@Override
	public void onStop(Session session) {
		logger.info("session停止....");
	}

	@Override
	public void onExpiration(Session session) {
		logger.info("session超时....");
	}

	
	
}
