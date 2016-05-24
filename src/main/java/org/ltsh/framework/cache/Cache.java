package org.ltsh.framework.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class Cache {

	public static final Logger logger = Logger.getLogger(Cache.class);
	/**
	 * 存放properties文件的目录
	 */
	public static final String PROPERTIES_FILES_DIRECTORY = "/properties/";
	/**
	 * properties文件
	 */
	public static final String[] PROPERTIES_FILES = new String[]{"config.properties"};
	/**
	 * properties文件内容的键值对map
	 */
	public static final Map<String, String> PROPERTIES_MAP = new HashMap<String, String>();
	
	/**
	 * WEB项目的绝对路径
	 */
	public static final String URL_REAL_PATH = "urlRealPath";
	/**
	 * WEB项目的相对路径
	 */
	public static final String URL_CONTEXT_PATH = "urlContextPath";
	/**
	 * 存放WEB项目URL的内容
	 */
	public static final Map<String, String> URL_MAP = new HashMap<String, String>();
	
	/** 
	* @Description: 初始化缓存
	* @param sc    参数 
	* @return void    返回类型 
	*/
	public static void initCache(ServletContext sc){
		try {
			initWebUrlPath(sc);
			initPropertiesFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 
	* @Description: 注销缓存
	* @return void    返回类型 
	*/
	public static void destroyCache(){
		PROPERTIES_MAP.clear();
		URL_MAP.clear();
	}
	/** 
	* @Description: 初始化WEB项目的URL信息
	* @param sc    参数 
	* @return void    返回类型 
	*/
	public static void initWebUrlPath(ServletContext sc){
		
		String urlRealPath = sc.getRealPath(File.separator);
		String urlContextPath = sc.getContextPath();
		
		URL_MAP.put(URL_REAL_PATH, urlRealPath);
		URL_MAP.put(URL_CONTEXT_PATH, urlContextPath);
	}
	
	/** 
	* @Description: 初始化载入的properties文件内容到缓存里
	* @return void    返回类型 
	*/
	public static void initPropertiesFile() throws Exception{
		for(String propertiesFile : PROPERTIES_FILES){
			InputStream in = null;
			URL url = Cache.class.getResource(PROPERTIES_FILES_DIRECTORY + propertiesFile);
			Properties p = new Properties();
			try {
				if(url == null){
					throw new NullPointerException("找不到properties文件,可能是目录名或文件名错误");
				}
				in = new FileInputStream(url.getFile());
				p.load(in);
				Set keySet = p.keySet();
				Iterator it = keySet.iterator();
				while(it.hasNext()){
					String key = (String)it.next();
					if(key!=null && p.getProperty(key)!=null){
						PROPERTIES_MAP.put(key.trim(),p.getProperty(key).trim());
					}
				}
			}catch(Exception e){
				throw new Exception("载入properties文件出错", e);
			}finally{
				if(in != null){
					in.close();
				}
			}
		}
	}
	
	
}
