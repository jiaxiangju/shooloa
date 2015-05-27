package com.oa.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户表对应的实体类
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_USER")
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;
	@Column(name = "C_LOGINNAME")
	private String loginName;//登录名
	@Column(name = "C_PASSWORD")
	private String password;//密码
	@Column(name = "C_USERNAME")
	private String userName;//姓名
	@Column(name = "I_SEX")
	private long sex;//性别
	@Column(name = "I_AGE",columnDefinition="INT")
	private long age;//年龄
	@Column(name = "I_SALARY",columnDefinition="INT")
	private long salary;//薪资
	@Column(name = "I_TITLE")
	private long title;//职称  教授，副教授，讲师，助教，教员，研究员
	@Column(name = "C_EMAIL")
	private String email;//邮箱
	@Column(name = "I_EDULEV",columnDefinition="INT")
	private long eduLev;//学历 博士 硕士 研究生 双学士 大学本科 高中 大专 中专 初中及以下
	@Column(name = "I_POLSTATUS",columnDefinition="INT")
	private long polStatus;//政治面貌 群众 团员 党员 其他
	@Column(name = "D_CREATEDATE")
	private Date createDate = new Date();//创建日期
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getSex() {
		return sex;
	}
	public void setSex(long sex) {
		this.sex = sex;
	}
	public long getAge() {
		return age;
	}
	public void setAge(long age) {
		this.age = age;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public long getTitle() {
		return title;
	}
	public void setTitle(long title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getEduLev() {
		return eduLev;
	}
	public void setEduLev(long eduLev) {
		this.eduLev = eduLev;
	}
	public long getPolStatus() {
		return polStatus;
	}
	public void setPolStatus(long polStatus) {
		this.polStatus = polStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
