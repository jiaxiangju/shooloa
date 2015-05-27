package com.oa.common.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.common.dao.BaseDao;
import com.oa.common.dao.IRoleDao;
import com.oa.common.entity.Role;
import com.oa.common.util.StringUtil;

@Repository(value="roleDao")
public class RoleDaoImpl extends BaseDao implements IRoleDao{

	public List<Role> queryAllRoles() {
		String hql = "from Role";
		return this.queryListByParam(hql);
	}

	public HashMap<String, Object> findRolesByPage(Role role, String sord,
			String column, int rowNum, int page, String[] params) {
		Object[] temp = null;
		String hql = " from Role";
		if(  params.length>0 && params[0]!= null){
			hql += " where roleName like '%" + params[0] + "%'";
			hql += " or roleCode like '%" + params[0] + "%'";
		}
		// 默认 角色编码升序排列
		String sortHql = " order by roleCode";
		if(!StringUtil.isNullOrEmpty(column)){
			sortHql = " order by " + column;
			if(!StringUtil.isNullOrEmpty(sord)){
				sortHql += " " + sord;
			}
		}
		hql += sortHql;
		return this.queryMiniListByPage(hql, rowNum, page, temp);
	}

}
