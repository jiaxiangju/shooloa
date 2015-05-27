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
 * 类描述：活动表对应的实体
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_EVENTS")
public class Events {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;

	@Column(name = "C_TYPE",length=100)
	private String type;//活动类型
	
	@Column(name = "C_EVENTNAME",length=200)
	private String eventName;//活动名称
	
	@Column(name = "C_EVENTTHEME",length=200)
	private String eventTheme;//活动主题
	
	@Column(name = "D_EVENTDATE")
	private Date eventDate;//活动时间
	
	@Column(name = "C_EVENTCREATER",length=100)
	private String eventCreater;//活动发起人
	
	@Column(name = "C_EVENTCONTENT")
	private String eventContent;//内容
	
	@Column(name = "C_STATE",length=100)
	private String state;//活动申请状态
	
	@Column(name = "D_CREATEDATE")
	private Date createDate;//活动创建时间
	
	@Column(name = "D_UPDATEDATE")
	private Date updateDate;//活动更新时间
	
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventTheme() {
		return eventTheme;
	}

	public void setEventTheme(String eventTheme) {
		this.eventTheme = eventTheme;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventCreater() {
		return eventCreater;
	}

	public void setEventCreater(String eventCreater) {
		this.eventCreater = eventCreater;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
