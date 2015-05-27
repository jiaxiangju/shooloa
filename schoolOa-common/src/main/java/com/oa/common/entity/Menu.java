package com.oa.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 类描述：菜单类实体
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_MENU")
public class Menu {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;
	@Column(name = "C_PID")
	private long pid;
	@Column(name = "C_TEXT",length=200)
	private String text;
	@Column(name = "C_URL",length=200)
	private String url;
	@Column(name = "I_REQ")
	private long req;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)  
    @JoinTable(name="OA_REL_ROLEMENU", joinColumns = {@JoinColumn(name ="C_MENUID" )},   
     inverseJoinColumns = { @JoinColumn(name = "C_ROLEID") })
	private Set<Role> roles = new HashSet<Role>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getReq() {
		return req;
	}
	public void setReq(long req) {
		this.req = req;
	}
	
	
}
