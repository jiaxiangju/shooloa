package com.oa.app.service.workFlow;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.common.dao.IWorkFlowDao;
import com.oa.common.entity.WorkFlow;
import com.oa.common.util.PageUtil;

@Service(value="workFlowService")
public class WorkFlowServiceImpl implements IWorkFlowService {

	@Resource(name="workFlowDao")
	private IWorkFlowDao workFlowDao;
	
	@Override
	public HashMap<String, Object> queryWorkFlowsByPage(PageUtil pageUtil,
			String keyWord) {
		// TODO Auto-generated method stub
		return workFlowDao.queryMiniListByPage(pageUtil, keyWord);
	}

	@Override
	public void addWorkFlow(WorkFlow workFlow) {
		workFlowDao.add(workFlow);

	}

	@Override
	public void updateWorkFlow(WorkFlow workFlow) {
		workFlowDao.update(workFlow);

	}

	@Override
	public void deleteWorkFlow(WorkFlow workFlow) {
		workFlowDao.delete(workFlow);

	}

	@Override
	public WorkFlow queryWfById(Long id) {
		return workFlowDao.queryObjectById(WorkFlow.class, id);
	}

}
