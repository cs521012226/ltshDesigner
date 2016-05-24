package org.ltsh.framework.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 字符串工具类
 * @author Charles
 * x
 */
public class StringUtil {

	public static final String GBK = "GBK";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	/** 
	 * @Author: Charles
	 * @Description: 判断对象是否不为空
	 * @param obj
	 * @return boolean: 
	 */
	public static boolean isNotEmpty(Object obj){
		if(obj == null){
			return false;
		}
		if(obj instanceof String){
			String str = (String)obj;
			if("".equals(str.trim()) || "null".equals(str.trim().toLowerCase())){
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 判断对象是否为空
	 * @param obj
	 * @return boolean: 
	 */
	public static boolean isEmpty(Object obj){
		return !isNotEmpty(obj);
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: 字符串数组通过分隔符组成字符串
	 * @param array		数组
	 * @param separator		分割符号
	 * @return String: 	返回字符串
	 */
	public static String typeConvertArrayToString(String[] array, String separator) {
        if (array == null || separator == null) {
            return null;
        }
        StringBuilder actual = new StringBuilder();
        for(String arr : array){
        	actual.append(arr + separator);
        }
        String result = actual.toString();
        result = result.substring(0, result.lastIndexOf(separator));
        return result;
    }
	
	/** 
	 * @Author: Charles
	 * @Description: 数组转List集合
	 * @param array
	 * @param separator
	 * @return List: 
	 */
	public static List typeConvertArrayToList(Object[] array) {
		List resultList = Arrays.asList(array);
		return resultList;
	}
	/** 
	 * @Author: Charles
	 * @Description: list字符串通过分隔符组成字符串
	 * @param list		集合数据
	 * @param separator	分隔符
	 * @return String: 返回字符串
	 */
	public static String typeConvertListToString(Collection<String> list, String separator) {
		String[] arr = new String[list.size()];
		list.toArray(arr);
		return typeConvertArrayToString(arr, separator);
	}
	
	
	/** 
	 * @Author: Charles
	 * @Description: list集合转换为数组
	 * @param list
	 * @return String[]: 
	 */
	public static <T> T[] typeConvertListToArray(Collection<T> list) {
		T[] resultArr = (T[])list.toArray();
		
		return resultArr;
	}
	/** 
	 * @Author: Charles
	 * @Description: 字符串通过分隔符组成字符串数组
	 * @param str	字符串
	 * @param separator	分隔符，如：','
	 * @return String[]: 	返回字符串数组
	 */
	public static String[] typeConvertStringToArray(String str, String separator) {
        if (str == null || separator == null) {
            return null;
        }
        String[] result = str.split(separator);
        return result;
    }
	
	/** 
	 * @Author: Charles
	 * @Description: 字符串通过分隔符组成字符串List集合
	 * @param str		字符串
	 * @param separator		分隔符，如：','
	 * @return List<String>: 返回字符串List集合
	 */
	public static List<String> typeConvertStringToList(String str, String separator) {
		String[] result = typeConvertStringToArray(str, separator);
		List<String> list = Arrays.asList(result);
		return list;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 转换字符编码
	 * @param s
	 * @param fromCode	编码格式，如：GBK
	 * @param toCode	编码格式，如：ISO-8859-1
	 * @return String: 	返回已编码的字符串
	 */
	public static String convertCode(String str, String fromCode, String toCode) {
        if (str == null){
        	return str;
        }
        byte[] abyte;
		try {
			abyte = str.getBytes(fromCode);
			return new String(abyte, toCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
    }
	/** 
	 * @Author: Charles
	 * @Description: 由GBK转换为ISO-8859-1字符编码
	 * @param str	待转换字符串
	 * @return String: 	返回编码后的字符串
	 */
	public static String convertGBKToISO(String str) {
		return convertCode(str, GBK, ISO_8859_1);
    }
}
