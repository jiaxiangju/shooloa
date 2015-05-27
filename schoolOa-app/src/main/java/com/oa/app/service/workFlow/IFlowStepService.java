package com.oa.app.service.workFlow;

import java.util.HashMap;

import com.oa.common.entity.FlowStep;
import com.oa.common.util.PageUtil;

public interface IFlowStepService {
	
	/**
	 * 分页查询所工作流列表
	 * @return
	 */
	public HashMap<String,Object> queryFlowStepByPage(PageUtil pageUtil,String id);
	
	public void addFlowStep(FlowStep flowStep);
	
	public void updateFlowStep(FlowStep flowStep);
	
	public void deleteFlowStep(FlowStep flowStep);
}
