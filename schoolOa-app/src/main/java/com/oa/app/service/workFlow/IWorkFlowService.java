package com.oa.app.service.workFlow;

import java.util.HashMap;

import com.oa.common.entity.WorkFlow;
import com.oa.common.util.PageUtil;

public interface IWorkFlowService {
	
	/**
	 * 分页查询所工作流列表
	 * @return
	 */
	public HashMap<String,Object> queryWorkFlowsByPage(PageUtil pageUtil,String keyWord);
	
	public void addWorkFlow(WorkFlow workFlow);
	
	public void updateWorkFlow(WorkFlow workFlow);
	
	public void deleteWorkFlow(WorkFlow workFlow);
	
	public WorkFlow queryWfById(Long id);
}
