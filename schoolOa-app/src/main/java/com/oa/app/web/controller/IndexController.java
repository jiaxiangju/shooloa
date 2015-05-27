package com.oa.app.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oa.app.service.role.IRoleService;
import com.oa.common.entity.Menu;
import com.oa.common.entity.User;
import com.oa.common.util.JsonDateValueProcessor;

@Controller
public class IndexController {

	@Resource(name = "roleService")
	private IRoleService roleService;

	@RequestMapping(value = "/resource/indexMenu", method = RequestMethod.GET)
	public void getMenus(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		User user = (User) session.getAttribute("user");
		List<Menu> list = roleService.queryMenus(-1);

		String json = getJSONString(list);
		response.getWriter().write(json);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 对象转换为json
	 * 
	 * @param object
	 * @return
	 */
	public static String getJSONString(Object object) {
		String jsonString = null;
		// 日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if (arg1.equals("roles")) { // 要过滤的areas ，Map对象中的
					return true;
				} else {
					return false;
				}
			}
		});

		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new JsonDateValueProcessor());
		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {
				jsonString = JSONArray.fromObject(object, jsonConfig)
						.toString();
			} else {
				jsonString = JSONObject.fromObject(object, jsonConfig)
						.toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}
}
