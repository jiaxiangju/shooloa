package com.oa.common.dao;

import java.util.HashMap;

import com.oa.common.entity.User;

public interface IUserDao extends IBaseDao{
	
	/**
	 * 验证登录名密码
	 * @param user
	 * @return
	 */
	public User queryUserByUsrPwd(User user);
	/**
	 * 根据登录名查询用户
	 * @param loginName
	 * @return
	 */
	public User queryUserByLoginName(String loginName);
	/**
	 * 分页查询所有用户
	 * @param sortOrder
	 * @param sortField
	 * @param pageSize
	 * @param pageIndex
	 * @param params
	 * @return
	 */
	public HashMap<String, Object> queryUsersByPage(String sortOrder,
			String sortField, int pageSize, int pageIndex, Object[] params);

}
