package com.oa.app.service.workFlow;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.common.dao.IFlowStepDao;
import com.oa.common.entity.FlowStep;
import com.oa.common.util.PageUtil;
@Service(value="flowStepService")
public class FlowStepServiceImpl implements IFlowStepService {

	@Resource(name="flowStepDao")
	private IFlowStepDao flowStepDao;
	@Override
	public HashMap<String, Object> queryFlowStepByPage(PageUtil pageUtil,
			String id) {
		return flowStepDao.queryMiniListByPage(pageUtil, Long.valueOf(id));
	}
	@Override
	public void addFlowStep(FlowStep flowStep) {
		double req = flowStepDao.queryMaxReq(flowStep.getWorkFlowId());
		flowStep.setReq(req);
		flowStepDao.add(flowStep);
		
	}
	@Override
	public void updateFlowStep(FlowStep flowStep) {
		// TODO Auto-generated method stub
		flowStepDao.update(flowStep);
		
	}
	@Override
	public void deleteFlowStep(FlowStep flowStep) {
		// TODO Auto-generated method stub
		flowStepDao.delete(flowStep);
	}

}
