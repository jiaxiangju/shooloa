package com.oa.common.dao;

import java.util.List;

import com.oa.common.entity.Menu;


public interface IMenuDao extends IBaseDao{
	public List<Menu> queryAllMenus();
}
