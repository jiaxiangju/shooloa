package com.oa.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色表对应的实体类
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_ROLE")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;

	@Column(name = "C_ROLECODE",length=50)
	private String roleCode;//角色编码
	
	@Column(name = "C_ROLENAME",length=100)
	private String roleName;//角色名称
	
	@Column(name = "I_SEQ")
	private double seq;//排序
	
	@Column(name = "C_DESC", length=255)
	private String desc;//备注
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "OA_REL_ROLEMENU", joinColumns = { @JoinColumn(name = "C_ROLEID") }, inverseJoinColumns = { @JoinColumn(name = "C_MENUID") })
	@OrderBy(value=" REQ ASC ")
	private Set<Menu> menus = new HashSet<Menu>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "OA_REL_ROLEUSER", joinColumns = { @JoinColumn(name = "C_ROLEID") }, inverseJoinColumns = { @JoinColumn(name = "C_USERID") })
	@OrderBy(value=" REQ ASC ")
	private Set<User> users = new HashSet<User>();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public double getReq() {
		return seq;
	}

	public void setReq(double req) {
		this.seq = req;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
