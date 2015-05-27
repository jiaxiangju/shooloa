package com.oa.common.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.oa.common.dao.BaseDao;
import com.oa.common.dao.IAnnouncementDao;
import com.oa.common.entity.Announcement;
import com.oa.common.util.PageUtil;
import com.oa.common.util.StringUtil;

@Repository(value="announcementDao")
public class AnnouncementDaoImpl extends BaseDao implements IAnnouncementDao {

	public HashMap<String, Object> queryMiniListByPage(PageUtil pageUtil,
			String keyWord) {
		String where = "";
		Object[] param = new String[2];
		if(!StringUtil.isNull(keyWord)){
			where = " creater =? or title = ?";
			param[0] = keyWord;
			param[1] = keyWord;
		}
		
		return queryMiniListByPage(Announcement.class,where, pageUtil.getPageIndex(), pageUtil.getPageSize(), param);
	}

}
