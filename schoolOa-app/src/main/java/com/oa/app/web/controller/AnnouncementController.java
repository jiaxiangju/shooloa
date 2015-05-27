package com.oa.app.web.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oa.app.service.announcement.IAnnouncementService;
import com.oa.common.entity.Announcement;
import com.oa.common.util.JsonUtil;
import com.oa.common.util.PageUtil;

@Controller
public class AnnouncementController {

	@Resource(name = "announcementService")
	private IAnnouncementService announcementService;
	
	@RequestMapping(value = "/resource/announcement",method=RequestMethod.GET)
	public void list(PageUtil pageUtil,String keyWord,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map = announcementService.queryAnnouncementByPage(pageUtil, keyWord);
		String json = JsonUtil.getJSONString(map);
		response.getWriter().write(json);
		
	}
	
	@RequestMapping(value = "/resource/announcement/{id}",method=RequestMethod.GET)
	public void get(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Announcement announcement = announcementService.queryAnnouncementById(Long.valueOf(id));
		String json = JsonUtil.getJSONString(announcement);
		response.getWriter().write(json);
		
	}
	
	@RequestMapping(value = "/resource/announcement",method=RequestMethod.POST)
	public void add(@ModelAttribute("announcement") Announcement announcement,HttpServletRequest request, HttpServletResponse response) throws IOException{
		announcementService.addAnnouncement(announcement);
		String json = JsonUtil.getJSONString(announcement);
		response.getWriter().write(json);
	}
	
	@RequestMapping(value = "/resource/announcement",method=RequestMethod.PUT)
	public void update(Announcement announcement,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		announcement.setId(Long.valueOf(id));
		announcementService.updateAnnouncement(announcement);
		String json = JsonUtil.getJSONString(announcement);
		response.getWriter().write(json);
	}
	
	@RequestMapping(value = "/resource/announcement/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
		long aid = Long.parseLong(id);
		Announcement announcement = announcementService.queryAnnouncementById(aid);
		announcementService.deleteAnnouncement(announcement);
		response.getWriter().print("true");
	}
}
