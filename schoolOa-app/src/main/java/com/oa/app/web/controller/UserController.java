package com.oa.app.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oa.app.service.user.UserServiceImpl;
import com.oa.common.entity.User;
import com.oa.common.util.PageUtil;
import com.oa.common.util.StringUtil;
import com.oa.common.util.JsonDateValueProcessor;

/**
 * 用户控制器
 * @author jia
 *
 */
@Controller
public class UserController {

	@Resource(name = "userService")
	private UserServiceImpl userService;
	
	/**
	 * 分页查询用户
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/user/resource",method = RequestMethod.GET)
	public void list(PageUtil pageUtil, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String keyword = StringUtil.isNullOrEmpty(request.getParameter("keyWord")) ? null : request.getParameter("keyWord");
		String[] params = new String[] { keyword };
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = userService.queryUsersByPage( pageUtil.getSortOrder(), pageUtil.getSortField(), pageUtil.getPageSize(),pageUtil.getPageIndex(), params);
		String json =  getJSONString(map);
		response.getWriter().write(json);
		
	}
	
	/**
	 * 新增  post
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/user/resource",method = RequestMethod.POST)
	public void saveUser(@ModelAttribute("user") User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		userService.addUser(user);
		/////////////set role
		response.getWriter().print("true");
	}


	/**
	 * PUT 更新
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/user/resource",method = RequestMethod.PUT)
	public void update(User user,HttpServletRequest request,HttpServletResponse response) throws IOException{
		userService.updateUser(user);
		////update role
		response.getWriter().print("true");
	}
	
	/**
	 * DELETE 删除
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/user/resource/{id}",method = RequestMethod.DELETE)
	public void delFonds(@PathVariable("id")String id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//判断是否有其他业务相关  未实现
		long uid = Long.parseLong(id);
		User user = userService.queryUserById(uid);
		userService.deleteUser(user);
		response.getWriter().print("true");
	}
	
	
	
	/**
	 * 对象转换为json
	 * @param object
	 * @return
	 */
	public static String getJSONString(Object object){
		String jsonString = null;
		//日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("role")){
		        	return true;
		        }else if(arg1.equals("users")){
		        	return true;
		        }else if(arg1.equals("user")){
		            return true;
		        }else if(arg1.equals("functionPurviews")){
		        	return true;
		        }else{
		        	return false;
		        }
			}
		});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
		if(object != null){
			if(object instanceof Collection || object instanceof Object[]){
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			}else{
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}
}
