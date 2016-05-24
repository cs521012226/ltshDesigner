package org.ltsh.framework.modules.base.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ltsh.framework.common.handler.HttpHandler;
import org.ltsh.framework.modules.base.service.IUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/urlController")
public class UrlController extends BaseController{
	
	/**
	 * log4j日志
	 */
	protected final Logger logger = Logger.getLogger(UrlController.class);
	
	@Autowired
	IUrlService urlService;
	
	protected final String SEARCH_PREFIX = "$Q_";	//查询的参数名前缀
	
	@RequestMapping(value="/getListData.do")
	public void getListData(HttpServletRequest request, HttpServletResponse response){
		Map queryParams = HttpHandler.getParameterMapStartWith(request, SEARCH_PREFIX);	//过滤参数，只取$Q_前缀的参数
		List dataList = urlService.getListData(queryParams);
		HttpHandler.writeJsonToResponse(response, dataList);
	}
}
