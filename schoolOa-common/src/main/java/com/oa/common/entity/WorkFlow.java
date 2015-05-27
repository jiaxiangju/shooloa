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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 流程表对应的实体类
 * @author jia
 *
 */
@Entity
@Table(name = "OA_S_WORKFLOW")
public class WorkFlow {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(name = "ID", unique = true, nullable = true)
    private long id;
	
	@Column(name = "C_FLOWCODE",length=50)
	private String flowCode;//流程编码

	@Column(name = "C_FLOWNAME",length=100)
	private String flowName;//流程名称
	
	@Column(name = "C_TYPE",length=50)
	private String type;//活动类型
	
	@Column(name = "C_DESC",length=100)
	private String desc;//备注
	
	@OneToMany(mappedBy = "workFlow", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FlowStep> flowSteps = new HashSet<FlowStep>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<FlowStep> getFlowSteps() {
		return flowSteps;
	}

	public void setFlowSteps(Set<FlowStep> flowSteps) {
		this.flowSteps = flowSteps;
	}
	
}
