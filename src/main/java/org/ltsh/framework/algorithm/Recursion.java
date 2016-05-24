package org.ltsh.framework.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ltsh.framework.i.Tree;
import org.ltsh.framework.modules.admin.entity.Menu;

/**
 * 递归算法类
 * @author Charles
 *
 */
public class Recursion {
	
	
	/** 
	 * @Author: Charles
	 * @Description: 从数据库里查出所有数据组装数据源（如果不使用此方法，在递归时每次都查询数据库，效率低下，
	 * 				所以此方法为一次查询出组装树的数据，然后组装为类似数据库的数据）
	 * @param allList	传进来原生数据库的数据列表
	 * @return Map<ID,List<T>>: 
	 */
	public static <ID, T extends Tree<ID, T>> Map<ID, List<T>> assembleDataSource(List<T> allList) throws Exception{
		Map<ID, List<T>> dataSource = new HashMap<ID, List<T>>();
		for(T m : allList){
			ID parentId = m.getParentId();
			if(dataSource.containsKey(parentId)){
				dataSource.get(parentId).add(m);
			}else{
				List<T> list = new ArrayList<T>();
				list.add(m);
				dataSource.put(parentId, list);
			}
		}
		return dataSource;
	}
	
	/** 
	 * @Description: 递归组装菜单树算法
	 * @param parentId
	 * @param dataSource
	 * @return List<Menu>: 
	 */
	public static <ID, T extends Tree<ID, T>> List<T> getTreeByRecursion(ID parentId, Map<ID, List<T>> dataSource){
		List<T> list = dataSource.get(parentId);
		for(T e : list){
			List<T> twoList = dataSource.get(e.getId());
			if(twoList == null || twoList.isEmpty()){
				continue;
			}else{
				e.getLeafNode().addAll(twoList);
				getTreeByRecursion(e.getId(),dataSource);
			}
		}
		return list;
	}

}
