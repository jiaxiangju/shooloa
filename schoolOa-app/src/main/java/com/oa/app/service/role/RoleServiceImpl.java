package com.oa.app.service.Role;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.common.dao.IMenuDao;
import com.oa.common.dao.IRoleDao;
import com.oa.common.entity.Menu;
import com.oa.common.entity.Role;
import com.oa.common.util.PageUtil;
import com.oa.common.util.StringUtil;

@Service(value="roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource(name="roleDao")
	private IRoleDao roleDao;
	@Resource(name="menuDao")
	private IMenuDao menuDao;
	
	@Override
	public List<Role> queryAllRoles() {
		return roleDao.queryAllRoles();
	}

	@Override
	public HashMap<String, Object> queryAllRolesByPage(Role role, String sord,
			String column, int rowNum, int page, String[] params) {
		return roleDao.findRolesByPage(role, sord, column, rowNum, page, params);
	}

	@Override
	public void addRole(Role role) {
		roleDao.add(role);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.update(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);
	}

	@Override
	public Role queryRoleById(long id) {
		return roleDao.queryObjectById(Role.class, id);
	}
	
		@Override
	public HashMap<String, Object> queryRolesByPage(PageUtil pageUtil,
			String keyWord) {
		return roleDao.queryMiniListByPage(pageUtil, keyWord);
	}
	
	
		@SuppressWarnings("unchecked")
	@Override
	public List<Menu> queryMenus(long roleid) {
		if(roleid  == -1){
			return menuDao.queryAllMenus();
		}else{
			Role role = roleDao.queryObjectById(Role.class, roleid);
			if(StringUtil.isNull(role)){
				return (List<Menu>) role.getMenus();
			}
		}
		return null;
	}

}
