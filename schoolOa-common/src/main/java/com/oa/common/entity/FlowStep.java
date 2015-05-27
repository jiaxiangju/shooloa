package com.oa.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 流程步骤对应的实体类
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_FLOWSTEP")
public class FlowStep {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;
	
	@Column(name = "C_WORKFLOWID")
	private long workFlowId;//流程ID
	
	@Column(name = "C_STEPNAME",length=100)
	private String stepName;//步骤名称
	
	@Column(name = "C_USERIDS",length=500)
	private String userIds;//审批人ids
	
	@Column(name = "I_REQ",length=500)
	private double req;//序列
	
	@Column(name = "C_DESC",length=500)
	private String desc;//备注
	
	
	//TODO: 此处配置主外键关联有问题
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "OA_REL_WFSTEP", joinColumns = { @JoinColumn(name = "C_WORKFLOWID") }, inverseJoinColumns = { @JoinColumn(name = "C_STEPID") })
	private WorkFlow workFlow;
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public double getReq() {
		return req;
	}

	public void setReq(double req) {
		this.req = req;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}

	
}
