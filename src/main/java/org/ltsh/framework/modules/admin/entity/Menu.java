package org.ltsh.framework.modules.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ltsh.framework.constants.Tables;
import org.ltsh.framework.i.Tree;
import org.ltsh.framework.modules.base.entity.BaseEntity;



/**
 * @author Charles
 * 菜单表
 *
 */
@Entity
@Table(name = Tables.MENU)
public class Menu extends BaseEntity implements Tree<Long, Menu>{
	
	private static final long serialVersionUID = -4343292255323698989L;
	
	
	private Long parentId;		//父ID
	private String name;		//菜单名
	private String realPath;		//真实地址
	private String url;		//连接地址
	private Long menuSort;		//菜单排序字段
	private String isLeaf;		//是否是叶子结点
//	private Set<Role> roles = new HashSet<Role>();	//关联角色表
	
	private List<Menu> leafNode = new ArrayList<Menu>();		//不是数据库字段，瑾存放子菜单
	
	public Menu(){
		this.createDateTime = new Date();
		this.lastUpdateTime = new Date();
	}
	
	public Menu(String name){
		this();
		this.name = name;
	}
	
	@Column(name = "parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "real_path", length = 512)
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	@Column(name = "url", length = 512)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "menu_sort")
	public Long getMenuSort() {
		return menuSort;
	}
	public void setMenuSort(Long menuSort) {
		this.menuSort = menuSort;
	}

	@Column(name = "is_leaf", length = 50)
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(name = Tables.MENU_ROLE,
//        joinColumns = @JoinColumn(name = "menu_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	@Transient
	public List<Menu> getLeafNode() {
		return leafNode;
	}
	public void setLeafNode(List<Menu> leafNode) {
		this.leafNode = leafNode;
	}
	

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + this.id.hashCode();
		if(this.name != null){
			result = result * 31 + this.name.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;   
		if (!(obj instanceof Role)) return false;
		if(this.name == null) return false;
		Menu m = (Menu) obj;
		return this.id.longValue() == m.getId().longValue() 
				&& this.name.equals(m.getName());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("id:"+this.id);
//		sb.append("parentId:"+this.parent.getId());		//父ID
		sb.append(",name:"+this.name);		//菜单名
		sb.append(",realPath:"+this.realPath);		//真实地址
		sb.append(",url:"+this.url);		//连接地址
		sb.append(",menuSort:"+this.menuSort);		//菜单排序字段
		sb.append(",isLeaf:"+this.isLeaf);		//是否是叶子结点
		sb.append("}");
		return sb.toString();
	}
}
