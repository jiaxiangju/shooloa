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
 * 考勤表对应的实体类
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_ATTENDANCE")
public class Attendance {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;

	@Column(name = "C_TYPE",length=20)
	private String type;//签到/签退
	
	@Column(name = "C_USERID")
	private long userId;//用户id
	
	@Column(name = "C_USERNAME",length=100)
	private String userName;//用户名
	
	@Column(name = "D_SIGNIN",length=100)
	private Date signIn;//用户名
	
	@Column(name = "D_SIGNOUT",length=100)
	private Date signOut;//用户名
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getSignIn() {
		return signIn;
	}

	public void setSignIn(Date signIn) {
		this.signIn = signIn;
	}

	public Date getSignOut() {
		return signOut;
	}

	public void setSignOut(Date signOut) {
		this.signOut = signOut;
	}
}
