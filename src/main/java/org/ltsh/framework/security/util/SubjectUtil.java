package org.ltsh.framework.security.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.ltsh.framework.modules.admin.entity.User;
import org.ltsh.framework.security.subject.ShiroUser;

public class SubjectUtil {
	
	public static final String CURRENT_USER = "currentUser";
	
	/** 
	 * @Author: Charles
	 * @Description: 获取当前用户身份，其实就是ShiroUser
	 * @return ShiroUser: 
	 */
	public static ShiroUser getShiroUser(Subject subject){
		ShiroUser shiroUser = null;
		
		Object principal = null;
		if(subject == null){
			principal = getSubject().getPrincipal();
		}else{
			principal = subject.getPrincipal();
		}
		
		if(principal instanceof ShiroUser){
			shiroUser = (ShiroUser) principal;
		}
		return shiroUser;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取当前用户身份，其实就是ShiroUser
	 * @return ShiroUser: 
	 */
	public static ShiroUser getShiroUser(){
		ShiroUser shiroUser = null;
		
		Object principal = getShiroUser(null);
		if(principal instanceof ShiroUser){
			shiroUser = (ShiroUser) principal;
		}
		return shiroUser;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取当前用户信息
	 * @return User: 
	 */
	public static User getCurrentUser(){
		User currentUser = null;
		ShiroUser shiroUser = getShiroUser();
		if(shiroUser != null){
			currentUser = shiroUser.getUser();
		}
		return currentUser;
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 获取当前Session
	 * @param create
	 * @return Session: 
	 */
	public static Session getSession(boolean create){
		return getSubject().getSession(create);
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 获取当前Session
	 * @return Session: 
	 */
	public static Session getSession(){
		return getSubject().getSession();
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取当前Session属性的用户身份ShiroUser
	 * @return ShiroUser: 
	 */
	public static ShiroUser getSessionAttShiroUser(){
		ShiroUser shiroUser = null;
		
		Object principal = getSession().getAttribute(CURRENT_USER);		//登陆成功后设进去的principal是ShiroUser
		if(principal instanceof ShiroUser){
			shiroUser = (ShiroUser) principal;
		}
		return shiroUser;
	}

	/** 
	 * @Author: Charles
	 * @Description: 获取Subject
	 * @return Subject: 
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
}
