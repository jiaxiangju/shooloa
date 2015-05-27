package com.oa.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.oa.common.dao.BaseDao;
import com.oa.common.dao.IUserDao;
import com.oa.common.entity.User;
import com.oa.common.util.StringUtil;

import org.springframework.stereotype.Repository;

@Repository(value="userDao")
public class UserDaoImpl extends BaseDao implements IUserDao{

	/**
	 * 验证登录名密码
	 */
	public User queryUserByUsrPwd(User user) {
		String hql = " from  User where loginName=? and password=? ";
		Object[] params = new String[2];
    	params[0] = user.getLoginName();
		params[1] = user.getPassword();
		List<User> list = queryListByParam(hql,params);
		if(list.size()>0){
			return list.get(0);
		}
		return null;	
	}

	public User queryUserByLoginName(String loginName) {
		String hql = " from  User where loginName= ?";
		Object[] params = new String[1];
    	params[0] = loginName;
		List<User> list = queryListByParam(hql,params);
		if(list.size()>0){
			return list.get(0);
		}
		return null;	
	}

	public HashMap<String, Object> queryUsersByPage(String sortOrder,
			String sortField, int pageSize, int pageIndex, Object[] params) {
		String hql = "from User where 1=1";
		if(!StringUtil.isNullOrEmpty(params))
		{
			if(!StringUtil.isNullOrEmpty(params[0])){
				hql += " and (loginName like '%"+params[0]+"%' or userName like '%"+params[0]+"%')";
			}
		}
		// 排序
		String sortHql = " order by createDate desc";// 默认创建时间排序
		if(!StringUtil.isNullOrEmpty(sortField)){
			sortHql = " order by " + sortField;
			if(!StringUtil.isNullOrEmpty(sortOrder)){
				sortHql += " " + sortOrder;
			}
		}
		hql += sortHql;
//		HashMap<String, Object> map = this.queryMiniListByPage(User.class, hql, pageSize, pageIndex, params);
		HashMap<String, Object> map = this.queryMiniListByPage(hql, pageSize, pageIndex, new Object[0]);
		return map;
	}
	

}
