package com.oa.common.dao;

import java.util.HashMap;

import com.oa.common.util.PageUtil;


public interface IWorkFlowDao extends IBaseDao{
	public  HashMap<String,Object> queryMiniListByPage(PageUtil pageUtil,String keyWord);
}
