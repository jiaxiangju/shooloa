package com.oa.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.common.dao.BaseDao;
import com.oa.common.dao.IMenuDao;
import com.oa.common.entity.Menu;

@Repository(value="menuDao")
public class MenuDaoImpl extends BaseDao  implements IMenuDao {

	public List<Menu> queryAllMenus() {
		return this.queryListByParam(" from Menu order by req", new Object[0]);
	}
}
