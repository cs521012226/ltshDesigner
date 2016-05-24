package org.ltsh.framework.modules.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5054687248639725684L;
	
	/**
	 * @Description: 主键ID
	 */
	protected Long id;
	/**
	 * @Description: 创建时间
	 */
	protected Date createDateTime;
	/**
	 * @Description: 最后更新时间
	 */
	protected Date lastUpdateTime;
	/**
	 * @Description: 备注
	 */
	protected String remark;
	
	public BaseEntity(){}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(TemporalType.DATE)
    @Column(name = "create_date_time")
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	@Temporal(TemporalType.DATE)
    @Column(name = "last_update_time")
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	@Column(name = "remark", length = 2000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
