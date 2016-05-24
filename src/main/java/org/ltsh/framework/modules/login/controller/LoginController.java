package org.ltsh.framework.modules.login.controller;



import org.ltsh.framework.modules.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	public static final String LOGIN_VIEW = "login";
	public static final String INDEX_VIEW = "index";
	public static final String HEADER_VIEW = "framework/common/header";
	public static final String LEFT_VIEW = "framework/common/left";
	public static final String RIGHT_VIEW = "framework/common/right";
	
	/** 
	 * @Author: Charles
	 * @Description: 登陆页面
	 * @return
	 * @throws Exception String: 
	 */
	@RequestMapping(value = "/loginPage")
	public String loginPage() throws Exception{ 
		return LOGIN_VIEW;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 主页面
	 * @return
	 * @throws Exception String: 
	 */
	@RequestMapping(value = "/mainPage")
	public String mainPage() throws Exception{ 
		return INDEX_VIEW;
	}
	
	@RequestMapping(value = "/headerPage")
	public String headerPage() throws Exception{ 
		return HEADER_VIEW;
	}
	
	@RequestMapping(value = "/leftPage")
	public String leftPage() throws Exception{ 
		return LEFT_VIEW;
	}
	
	@RequestMapping(value = "/rightPage")
	public String rightPage() throws Exception{ 
		return RIGHT_VIEW;
	}
}
