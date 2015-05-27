package com.oa.common.dao;

import java.util.HashMap;

import com.oa.common.util.PageUtil;

public interface IFlowStepDao extends IBaseDao{
	public  HashMap<String,Object> queryMiniListByPage(PageUtil pageUtil,long id);
	
	public double queryMaxReq(Long wfId);
}
