package org.ltsh.framework.modules.admin.service;

import java.util.List;

import org.ltsh.framework.modules.admin.entity.Menu;

public interface IMenuService {
	
	/** 
	 * @Author: Charles
	 * @Description: 获取菜单树
	 * @return List<Menu>: 
	 */
	public List<Menu> getMenuTree() throws Exception;
	
	/** 
	 * @Author: Charles
	 * @Description: 添加菜单
	 * @param m void: 
	 */
	public void saveMenu(Menu m) throws Exception;
	
	/** 
	 * @Author: Charles
	 * @Description: 添加菜单
	 * @param m void: 
	 */
	public Menu findUnique(Long id) throws Exception;
	/** 
	 * @Author: Charles
	 * @Description: 排序并找出所有记录
	 * @return List<Menu>: 
	 */
	public List<Menu> findAllBySort() throws Exception;
	
	/** 
	 * @Author: Charles
	 * @Description: 通过父节点查找菜单
	 * @param parentId
	 * @return List<Menu>: 
	 */
	public List<Menu> getMenuByParentId(long parentId) throws Exception;
	
	/** 
	 * @Author: Charles
	 * @Description: 通过菜单名查找菜单
	 * @param parentId
	 * @return List<Menu>: 
	 */
	public Menu getMenuByName(String name) throws Exception;
	
	
}
