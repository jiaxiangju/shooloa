package com.oa.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.oa.common.dao.BaseDao;
import com.oa.common.dao.IFlowStepDao;
import com.oa.common.entity.FlowStep;
import com.oa.common.util.PageUtil;
import com.oa.common.util.StringUtil;

import org.springframework.stereotype.Repository;

@Repository(value="flowStepDao")
public class FlowStepDaoImpl extends BaseDao implements IFlowStepDao{

	public HashMap<String, Object> queryMiniListByPage(PageUtil pageUtil,
			long id) {
		String where = " and workFlowId = "+id;
		return queryMiniListByPage(FlowStep.class,where, pageUtil.getPageIndex(), pageUtil.getPageSize(), new Object[0]);
	}

	public double queryMaxReq(Long wfId) {
		String hql = "select id from FlowStep where workFlowId = "+wfId;
		@SuppressWarnings("rawtypes")
		List list = queryListByParam(hql, new Object[0]);
		return StringUtil.isNull(list)?0:list.size();
	}


}
