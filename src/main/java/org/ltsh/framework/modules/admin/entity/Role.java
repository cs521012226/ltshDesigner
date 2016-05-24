package org.ltsh.framework.modules.admin.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.ltsh.framework.constants.Tables;
import org.ltsh.framework.modules.base.entity.BaseEntity;

/**
 * @author Charles
 * 角色表
 */
@Entity
@Table(name = Tables.ROLE)
public class Role extends BaseEntity{
	
	private static final long serialVersionUID = 1832039564322980946L;
	/**
     * 角色名称
     */
	private String roleName;
	/**
	 * 关联User（用户表）
	 */
//	private Set<User> users = new HashSet<User>();
	/**
	 * 关联Menu（菜单表）
	 */
//	private Set<Menu> menus = new HashSet<Menu>();

	public Role(){
    	this.createDateTime = new Date();
    	this.lastUpdateTime = new Date();
    }
	
	public Role(String roleName){
		this();
		this.roleName = roleName;
    }
	
	@Column(name = "role_name", nullable = false, length = 200)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(name = Tables.USER_ROLE,
//        joinColumns = @JoinColumn(name = "role_id"),
//        inverseJoinColumns = @JoinColumn(name = "user_id"))
//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
	
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(name = Tables.MENU_ROLE,
//        joinColumns = @JoinColumn(name = "role_id"),
//        inverseJoinColumns = @JoinColumn(name = "menu_id"))
//	public Set<Menu> getMenus() {
//		return menus;
//	}
//
//	public void setMenus(Set<Menu> menus) {
//		this.menus = menus;
//	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + this.id.hashCode();
		if(this.roleName != null){
			result = result * 31 + this.roleName.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;   
		if (!(obj instanceof Role)) return false;
		if(this.roleName == null) return false;
		Role r = (Role) obj;
		return this.id.longValue() == r.getId().longValue() 
				&& this.roleName.equals(r.getRoleName());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("id:"+this.id);
		sb.append(",roleName:"+this.roleName);
		sb.append("}");
		return sb.toString();
	}
}
