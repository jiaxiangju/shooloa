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
 * 
 * 类名称    ：Announcement
 * 类描述    ：(公告表对应的实体类)
 * 
 */
@Entity
@Table(name = "OA_S_ANNOUNCEMENT")
public class Announcement {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;
	
	@Column(name = "C_TITLE",length=200)
	private String title;//公告标题
	
	@Column(name = "C_CREATER",length=100)
	private String creater;//创建人
	
	@Column(name = "C_CONTENT")
	private String content;//内容
	
	@Column(name = "I_COUNT")
	private long count;//浏览数
	
	@Column(name = "D_CREATEDATE")
	private Date createDate = new Date();//创建日期
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
