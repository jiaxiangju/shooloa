package com.oa.common.dao;

import java.util.HashMap;

import com.oa.common.util.PageUtil;

public interface IAnnouncementDao extends IBaseDao {
	
	

	public  HashMap<String,Object> queryMiniListByPage(PageUtil pageUtil,String keyWord);
}
