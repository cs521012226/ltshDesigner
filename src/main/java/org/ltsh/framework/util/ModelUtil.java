package org.ltsh.framework.util;

public class ModelUtil {
	
	
	private static String[] MODEL_BASE_PACKAGES;
	static{
		MODEL_BASE_PACKAGES = new String[]{
				"org.ltsh.app.model",
				"org.ltsh.framework.model"
		};
	}
	
	/**
	 * 通过实体名得到class
	 * @param entityName
	 * @return
	 */
	public static Class getEntityClass(String entityName){
		Class clazz = null;
		for(int i=0; i<MODEL_BASE_PACKAGES.length; i++){
			try {
				clazz = Class.forName(MODEL_BASE_PACKAGES[i]+"."+entityName);
				break;
			} catch (ClassNotFoundException e) {
				continue;
			}
		}
		if(clazz == null){
			throw new RuntimeException("未找到实体："+entityName);
		}
		return clazz;
	}
}
