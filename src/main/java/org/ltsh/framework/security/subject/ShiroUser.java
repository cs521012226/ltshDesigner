package org.ltsh.framework.security.subject;

import java.io.Serializable;

import org.ltsh.framework.modules.admin.entity.User;

/**
 * @author Charles
 * Shiro框架Principal,代表的就是Principal，为了和实体User区别
 *
 */
public class ShiroUser implements Serializable{
	
	private User user;
	
	public ShiroUser(){
		//无参构造
	}
	
	public ShiroUser(User user){
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		String username = null;
		if(this.user != null){
			username = this.user.getUsername();
		}
		return username;
	}
	
}
