package org.ltsh.framework.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.ltsh.framework.common.vo.MessageResult;
import org.ltsh.framework.security.util.SubjectUtil;

public class LoginFormAuthenticationFilter extends FormAuthenticationFilter{
	
	protected final Logger logger = Logger.getLogger(LoginFormAuthenticationFilter.class);
	

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		subject.getSession().setAttribute(SubjectUtil.CURRENT_USER, subject.getPrincipal());	//设置用户身份进session属性
		subject.getSession().setTimeout(5 * 1000);
		
		logger.info("用户 "+SubjectUtil.getShiroUser(subject).toString() + " 登陆成功");
		
		String url = this.getSuccessUrl();
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url);	//页面跳转
		return false;
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, 
			ServletRequest request, ServletResponse response) {
		
		String loginFailure = "登陆失败 : ";
		String msg = null;
		try{
			throw e;
		} catch ( LockedAccountException lae ) { 
			msg = "用户被锁，请联系管理员";
		} catch ( ExcessiveAttemptsException eae ) {
			msg = "你错误的次数太多，请刷新页面重试";
		} catch ( AuthenticationException ae ) {
			msg = "用户名或密码错误";
		}
		logger.info(loginFailure + msg);
		MessageResult messageResult = new MessageResult(loginFailure + msg);
		request.setAttribute(MessageResult.MESSAGE_RESULT, messageResult);	//设置返回信息
		
		return super.onLoginFailure(token, e, request, response);
	}
	
	

	
	
}
