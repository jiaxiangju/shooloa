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
 * 类描述    ：(活动审批步骤对应的实体类)
 * @author jia
 * 
 */
@Entity
@Table(name = "OA_S_APPROVESTEP")
public class ApproveStep {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;

	@Column(name = "C_EVENTID")
	private long eventId;//活动ID
	
	@Column(name = "C_FLOWID")
	private String flowId;//流程ID
	
	@Column(name = "C_STATE",length=100)
	private String state;//审批状态，未审批 ，审批中，结束
	
	@Column(name = "C_STEP",length=100)
	private String step;//第几步审批 
	
	@Column(name = "C_APPROVERID")
	private long approverId;//审批者ID
	
	@Column(name = "C_APPROVERNAME",length=100)
	private String approverName;//审批者姓名
	
	@Column(name = "C_NEXTAPPROVERID")
	private long nextApproverId;//下一步审批者ID
	
	@Column(name = "C_NEXTAPPROVERNAME",length=100)
	private String nextApproverName;//下一步审批者姓名
	
	@Column(name = "C_RESULT",length=100)
	private String result;//审批结果
	
	@Column(name = "D_APPROVEDATE",length=100)
	private Date approveDate;//审批时间
	
	@Column(name = "D_CREATEDATE",length=100)
	private Date createDate;//创建时间
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public long getApproverId() {
		return approverId;
	}

	public void setApproverId(long approverId) {
		this.approverId = approverId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public long getNextApproverId() {
		return nextApproverId;
	}

	public void setNextApproverId(long nextApproverId) {
		this.nextApproverId = nextApproverId;
	}

	public String getNextApproverName() {
		return nextApproverName;
	}

	public void setNextApproverName(String nextApproverName) {
		this.nextApproverName = nextApproverName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
