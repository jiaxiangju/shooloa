package com.oa.app.service.role;

import java.util.HashMap;
import java.util.List;
import com.oa.common.entity.Role;

import com.oa.common.entity.Menu;
import com.oa.common.util.PageUtil;
public interface IRoleService {

	/**
	 * 查询所有角色信息
	 * @return List<Role>
	 */
	public List<Role> queryAllRoles();
	/**
	 * 分页查询所有角色信息
	 * @return List<Role>
	 */
	public HashMap<String,Object> queryAllRolesByPage(Role role,String sord,String column,int rowNum,int page,String[] params);
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	public void addRole(Role role);
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public void updateRole(Role role);
	/**
	 * 删除角色
	 * @param role
	 * @return
	 */
	public void deleteRole(Role role);
	/**
	 * 根据ID查询角色
	 * @param id
	 * @return role
	 */
	public Role queryRoleById(long id);
	
	/**
	 * 分页查询所工作流列表
	 * @return
	 */
	public HashMap<String,Object> queryRolesByPage(PageUtil pageUtil,String keyWord);
	
	/**
	 * 查询角色的菜单权限
	 * @param roleid -1 全部 ；       其他
	 * @return
	 */
	public List<Menu> queryMenus(long roleid);
}
