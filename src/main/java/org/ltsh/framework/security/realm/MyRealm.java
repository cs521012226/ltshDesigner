package org.ltsh.framework.security.realm;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.ltsh.framework.modules.admin.entity.User;
import org.ltsh.framework.modules.admin.service.IUserService;
import org.ltsh.framework.security.subject.ShiroUser;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm{
	
	protected final Logger logger = Logger.getLogger(MyRealm.class);
	
	@Autowired
	private IUserService userService;

	/** 
	 * @Author: Charles
	 * @Description: 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("进行授权操作...");
		
		ShiroUser shiroUser = null;
		
		Collection<ShiroUser> userList = principalCollection.fromRealm(getName());
		if(!userList.isEmpty()){
			shiroUser = userList.iterator().next();
		}
		
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		return authorizationInfo;
	}

	/** 
	 * @Author: Charles
	 * @Description: 认证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) 
			throws AuthenticationException {
		logger.info("进行认证操作...");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationtoken;
		String uname = token.getUsername();
		
		User user = userService.findUserByUname(uname);
		
		SimpleAuthenticationInfo authenticationInfo = null;
		if(user != null){
			byte[] salt = Hex.decode(user.getPasswordSalt());
			ByteSource byteSource = ByteSource.Util.bytes(salt);
			authenticationInfo = new SimpleAuthenticationInfo(new ShiroUser(user), user.getPassword(), byteSource, getName());
		}else{
			throw new UnknownAccountException();	//抛出用户名错误异常
		}
		return authenticationInfo;
	}

}
