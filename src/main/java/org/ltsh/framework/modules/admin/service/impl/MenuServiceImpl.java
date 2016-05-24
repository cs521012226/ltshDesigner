package org.ltsh.framework.modules.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.ltsh.framework.algorithm.Recursion;
import org.ltsh.framework.modules.admin.dao.IMenuDao;
import org.ltsh.framework.modules.admin.entity.Menu;
import org.ltsh.framework.modules.admin.service.IMenuService;
import org.ltsh.framework.modules.base.dao.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements IMenuService{
	
	@Autowired
	private IBaseDao baseDao;
	

	@Autowired
	private IMenuDao menuDao;
	/** 
	 * @Author: Charles
	 * @Description: 获取菜单树
	 * @return List<Menu>: 
	 */
	public List<Menu> getMenuTree() throws Exception{
		List<Menu> allList = this.findAllBySort();	//数据库一次查出所有菜单数据
		Map<Long, List<Menu>> dataSource = Recursion.assembleDataSource(allList);
		List<Menu> result = Recursion.getTreeByRecursion(0L, dataSource);
		return result;
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 添加菜单
	 * @param m void: 
	 */
	public void saveMenu(Menu m) throws Exception{
		menuDao.saveOrUpdate(m);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 添加菜单
	 * @param m void: 
	 */
	public void addMenu(Menu m) throws Exception{
		menuDao.persist(m);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 添加菜单
	 * @param m void: 
	 */
	public Menu findUnique(Long id) throws Exception{
		return menuDao.findUnique(Menu.class, Restrictions.idEq(id));
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 排序并找出所有记录
	 * @return List<Menu>: 
	 */
	public List<Menu> findAllBySort() throws Exception{
		return menuDao.findAll("menuSort", true);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 通过父节点查找菜单
	 * @param parentId
	 * @return List<Menu>: 
	 */
	public List<Menu> getMenuByParentId(long parentId) throws Exception{
		return menuDao.findAllByParentId(parentId);
	}
	
	/** 
	 * @Author: Charles
	 * @Description: 通过菜单名查找菜单
	 * @param parentId
	 * @return List<Menu>: 
	 */
	public Menu getMenuByName(String name) throws Exception{
		return menuDao.findAllByName(name);
	}

}
