package com.oa.app.service.announcement;

import java.util.HashMap;

import com.oa.common.entity.Announcement;
import com.oa.common.util.PageUtil;

public interface IAnnouncementService {
	
	public HashMap<String,Object> queryAnnouncementByPage(PageUtil pageUtil,String keyWord);
	
	public void addAnnouncement(Announcement announcement);
	
	public void updateAnnouncement(Announcement announcement);
	
	public void deleteAnnouncement(Announcement announcement);
	
	public Announcement queryAnnouncementById(Long id);

}
