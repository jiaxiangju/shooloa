package com.oa.app.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oa.common.entity.Role;
import com.oa.common.util.JsonDateValueProcessor;
import com.oa.common.util.PageUtil;
import com.oa.common.util.StringUtil;
import com.oa.app.service.role.IRoleService;




@Controller
@RequestMapping(value="/role/resource")
public class RoleController {
	
	@Resource(name = "roleService")
	private IRoleService roleService;
	
	/**
	 * 获取所有角色列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/RoleAll", method = RequestMethod.GET)
	public void listAll(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Role> list = roleService.queryAllRoles();
		String json = getJSONString(list);
		response.getWriter().write(json);
	}
	
	/**
	 * 分页查询(角色列表)
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void list(PageUtil pageUtil, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String keyword = StringUtil.isNullOrEmpty(request.getParameter("keyWord")) ? null : request.getParameter("keyWord");
		String[] params = new String[] { keyword };
		HashMap<String, Object> map = new HashMap<String, Object>();
		map = roleService.queryAllRolesByPage(null, pageUtil.getSortOrder(), pageUtil.getSortField(),pageUtil.getPageSize(), pageUtil.getPageIndex(), params);
		String json = getJSONString(map);
		response.getWriter().write(json);
	}
	
	/**
	 * 新增  post
	 * @param user
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void saveRole(Role role,HttpServletRequest request,HttpServletResponse response) throws IOException{
		roleService.addRole(role);
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
	@RequestMapping(method = RequestMethod.PUT)
	public void update(Role role,HttpServletRequest request,HttpServletResponse response) throws IOException{
		roleService.updateRole(role);
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
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void delFonds(@PathVariable("id")String id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		long uid = Long.parseLong(id);
		Role role = roleService.queryRoleById(uid);
		roleService.deleteRole(role);
		response.getWriter().print("true");
	}
	
	public  String getJSONString(Object object){
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
		        } else if(arg1.equals("roles")){
		        	return true;
		        } else{
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
