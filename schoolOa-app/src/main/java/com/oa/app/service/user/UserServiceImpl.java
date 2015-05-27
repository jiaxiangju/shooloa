package com.oa.app.service.user;


import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import com.oa.common.dao.IUserDao;
import com.oa.common.entity.User;

import org.springframework.stereotype.Service;
@Service(value="userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userDao")
	private IUserDao userDao;

	public void addUser(User user) {
		userDao.add(user);
	}
	@Override
	public void updateUser(User user) {
		userDao.update(user);;
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public User queryUserById(Long id) {
		return userDao.queryObjectById(User.class, id);
	}

	@Override
	public User queryUserByUsrPwd(User user) {
		return userDao.queryUserByUsrPwd(user);
	}
	@Override
	public User queryUserByLoginName(String loginName) {
		return userDao.queryUserByLoginName(loginName);
	}
	@Override
	public HashMap<String, Object> queryUsersByPage(String sortOrder,
			String sortField, int pageSize, int pageIndex, Object[] params) {
		return userDao.queryUsersByPage(sortOrder,sortField, pageSize, pageIndex, params);
	}

	

	

}
