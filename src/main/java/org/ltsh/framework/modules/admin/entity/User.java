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
 * 用户表
 */
@Entity
@Table(name = Tables.USER)
public class User extends BaseEntity{
	
	private static final long serialVersionUID = 4828461369041452692L;
	
	
	private String username;		//账号
    private String password;		//密码
//    private String passwordQuestion;	//密码问题
//    private String passwordAnswer;		//问题答案
    private String passwordSalt;	//用于密码加密
    private String fullName;		//姓名
    private String sex;				//性别
    private String workPhone;		//工作电话号码
    private String telephone;		//手机
    private String email;			//电邮
    private String isSuperuser;	//是否超级用户
    private String isLock;			//是否锁定
    private String status;		//账户状态, BLOCK : 停用， NORMAL : 正常
//    private Set<Role> roles = new HashSet<Role>();	//关联角色表

    
    public User(){
    	this.createDateTime = new Date();
    	this.lastUpdateTime = new Date();
    }
    
    public User(String username, String password){
    	this();
    	this.username = username;
    	this.password = password;
    }
    
    @Column(name = "username", nullable = false, length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
//	@Column(name = "password_question", length = 50)
//	public String getPasswordQuestion() {
//		return passwordQuestion;
//	}

//	public void setPasswordQuestion(String passwordQuestion) {
//		this.passwordQuestion = passwordQuestion;
//	}
//	@Column(name = "password_answer", length = 50)
//	public String getPasswordAnswer() {
//		return passwordAnswer;
//	}
//
//	public void setPasswordAnswer(String passwordAnswer) {
//		this.passwordAnswer = passwordAnswer;
//	}
	
	@Column(name = "password_salt", length = 128)
	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	@Column(name = "full_name", length = 100)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name = "sex", length = 50)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "work_phone", length = 50)
	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "email", length = 128)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
//	@Column(name = "is_superuser", length = 50)
//	public String getIsSuperuser() {
//		return isSuperuser;
//	}
//
//	public void setIsSuperuser(String isSuperuser) {
//		this.isSuperuser = isSuperuser;
//	}
	
	@Column(name = "is_lock", length = 50)
	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	@Column(name = "status", length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(name = Tables.USER_ROLE,
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
//	public Set<Role> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + this.id.hashCode();
		if(this.username != null){
			result = result * 31 + this.username.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;   
		if (!(obj instanceof User)) return false;
		if(this.username == null) return false;
		User u = (User) obj;
		return this.id.longValue() == u.getId().longValue() 
				&& this.username.equals(u.getUsername());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("id:"+this.id);
		sb.append(",username:"+this.username);
		sb.append(",fullName:"+this.fullName);
//		sb.append(",passwordQuestion:"+this.passwordQuestion);	//密码问题
//		sb.append(",passwordAnswer:"+this.passwordAnswer);		//问题答案
		sb.append(",sex:"+this.sex);				//性别
		sb.append(",workPhone:"+this.workPhone);		//工作电话号码
		sb.append(",telephone:"+this.telephone);		//手机
		sb.append(",email:"+this.email);			//电邮
//		sb.append(",isSuperuser:"+this.isSuperuser);	//是否超级用户
		sb.append(",isLock:"+this.isLock);			//是否锁定
		sb.append(",status:"+this.status);		//账户状态, BLOCK : 停用， NORMAL : 正常
		sb.append("}");
		return sb.toString();
	}
	
}
