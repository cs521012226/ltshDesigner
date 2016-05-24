package org.ltsh.framework.common.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ltsh.framework.component.ListPage;

/**
 * @author Charles
 * @Description: 表格数据处理类
 */
public class GirdServerHandler {

	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String,Object> parameterMap;
	
	private ListPage listPage;
	
	public GirdServerHandler(HttpServletRequest req, HttpServletResponse resp){
		this.request = req;
		this.response = resp;
		this.parameterMap = HttpHandler.getParameterMap(this.request);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public ListPage getListPage() {
		return listPage;
	}

	public void setListPage(ListPage listPage) {
		this.listPage = listPage;
	}
	
}
