package com.oa.common.dao;

import java.util.HashMap;

import com.oa.common.entity.Role;
import com.oa.common.util.PageUtil;


public interface IRoleDao extends IBaseDao{

	/**
	 * 查询所有
	 * @return
	 */
	public List<Role> queryAllRoles();
	/**
	 * 分页查询所有角色
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> findRolesByPage(Role role,String sord,String column,int rowNum,int page,String[] params);
}
