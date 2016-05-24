package org.ltsh.framework.common.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ltsh.framework.util.JsonUtil;

/**
 * @author Charles
 * @Description:Request与Response页面参数及数据的辅助类
 *
 */
public class HttpHandler {
	
	/**
	 * log4j日志
	 */
	protected static final Logger logger = Logger.getLogger(HttpHandler.class);
	
	/** 
	 * @Author: Charles
	 * @Description: 获取参数
	 * @param request
	 * @param paramName		参数名称
	 * @param isRequired	是否是必须的
	 * @return String: 
	 */
	public static String getParameter(HttpServletRequest request, String paramName, boolean isRequired){
		String paramValue = request.getParameter(paramName);
		if(isRequired && (paramValue == null || "".equals(paramValue.trim()))){
			logger.error("[ 参数" + paramName + "的值为空 ]");
		}
		return paramValue;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取request所有参数存在一个map里
	 * @param request
	 * @return Map<String,String>: 
	 */
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames != null && paramNames.hasMoreElements()) {
            String parameterName = paramNames.nextElement();	//得到参数名
            String parameterValue = getParameter(request, parameterName, false);		//得到参数值
			map.put(parameterName,  parameterValue);
        }
        return map;
    }
	
	/** 
	 * @Author: Charles
	 * @Description: 根据前缀查找参数并存在MAP里返回
	 * @param request	request
	 * @param prefix	参数前缀
	 * @return Map<String,Object>: 
	 */
	public static Map<String, Object> getParameterMapStartWith(HttpServletRequest request, String prefix) {
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unPrefixed = paramName.substring(prefix.length());	//除掉前缀后的参数名称
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// 什么都不做，找不到
				}
				else if (values.length > 1) {
					params.put(unPrefixed, values);
				}
				else {
					params.put(unPrefixed, values[0]);
				}
			}
		}
		return params;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取项目绝对路径
	 * @param servletContext
	 * @param path
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request){
		String realPath = request.getSession().getServletContext().getRealPath("/");
		if (realPath == null) {
			logger.error("真实路径为空");
		}
		return realPath;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取项目相对路径
	 * @param request
	 * @return String: 
	 */
	public static String getContextPath(HttpServletRequest request){
		String contextPath = request.getContextPath();
		if (contextPath == null) {
			logger.error("项目路径为空");
		}
		return contextPath;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 获取Session属性
	 * @param request
	 * @param name
	 * @return Object: 
	 */
	public static Object getSessionAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 设置Session属性
	 * @param request
	 * @param name	属性名
	 * @param value 属性值 
	 */
	public static void setSessionAttribute(HttpServletRequest request, String name, Object value) {
		if (value != null) {
			request.getSession().setAttribute(name, value);
		}
		else {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.removeAttribute(name);
			}
		}
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 输出字符串到response
	 * @param response
	 * @param str void: 
	 */
	public static void writeStringToResponse(HttpServletResponse response, String str) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charaset=utf-8");
			out = response.getWriter();
			if(str != null){
				out.write(str);
				out.flush();  
			}
		} catch (IOException e) {
			logger.error("输出页面response错误");
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 输出Collection到response
	 * @param response
	 * @param entityList void: 
	 */
	public static void writeJsonToResponse(HttpServletResponse response, Collection entityList) {
		String json = JsonUtil.toJsonString(entityList);
		writeStringToResponse(response, json);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 输出Map到response
	 * @param response
	 * @param entityMap void: 
	 */
	public static void writeJsonToResponse(HttpServletResponse response, Map entityMap) {
		String json = JsonUtil.toJsonString(entityMap);
		writeStringToResponse(response, json);
	}
}
