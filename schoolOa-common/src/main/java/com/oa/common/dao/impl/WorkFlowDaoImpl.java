package com.oa.common.dao.impl;

import java.util.HashMap;

import com.oa.common.dao.BaseDao;
import com.oa.common.dao.IWorkFlowDao;
import com.oa.common.entity.WorkFlow;
import com.oa.common.util.PageUtil;
import com.oa.common.util.StringUtil;

import org.springframework.stereotype.Repository;

@Repository(value="workFlowDao")
public class WorkFlowDaoImpl extends BaseDao implements IWorkFlowDao{

	public HashMap<String, Object> queryMiniListByPage(PageUtil pageUtil,
			String keyWord) {
		String where = "";
		Object[] param = new String[2];
		if(!StringUtil.isNull(keyWord)){
			where = " and flowName like ? or flowCode like ?";
			param[0] = "%"+keyWord+"%";
			param[1] = "%"+keyWord+"%";
		}
		return queryMiniListByPage(WorkFlow.class,where, pageUtil.getPageIndex(), pageUtil.getPageSize(), param);
	}


}
