package com.oa.app.service.announcement;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.common.dao.IAnnouncementDao;
import com.oa.common.entity.Announcement;
import com.oa.common.util.PageUtil;

@Service(value="announcementService")
public class AnnouncementServiceImpl implements IAnnouncementService {
	
	@Resource(name="announcementDao")
	private IAnnouncementDao announcementDao;

	@Override
	public HashMap<String, Object> queryAnnouncementByPage(PageUtil pageUtil,
			String keyWord) {
		return announcementDao.queryMiniListByPage(pageUtil,keyWord);
	}

	@Override
	public void addAnnouncement(Announcement announcement) {
		announcementDao.add(announcement);
	}

	@Override
	public void updateAnnouncement(Announcement announcement) {
		announcementDao.update(announcement);
	}

	@Override
	public void deleteAnnouncement(Announcement announcement) {

		announcementDao.delete(announcement);
	}

	@Override
	public Announcement queryAnnouncementById(Long id) {
		return announcementDao.queryObjectById(Announcement.class, id);
	}

}
