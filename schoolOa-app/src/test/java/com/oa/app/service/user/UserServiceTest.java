package com.oa.app.service.user;

import org.junit.Before;
import org.junit.Test;
import com.oa.common.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class UserServiceTest{
	private ApplicationContext ctx; 
	
	private IUserService userService ;
	@Before 
	public void setUp() { 
	        String[] configLocations = new String[] {"classpath:spring-servlet.xml"}; 
	        ctx = new ClassPathXmlApplicationContext(configLocations); 
	        userService  = (UserServiceImpl) ctx.getBean("userService");
	} 
	
	@Test
	public void test1(){
		User user = new User();
		user.setLoginName("liuxin");
		user.setPassword("888");
		userService.addUser(user);
	}
	
	@Test
	public void testUpdateUser(){
		User user = new User();
		user.setLoginName("liuxin");
		user.setPassword("888");
		User user1 = userService.queryUserByUsrPwd(user);
		user1.setPassword("888888");
		userService.updateUser(user1);
	}
	
	@Test
	public void testDeleteUser(){
		User user = new User();
		user.setLoginName("liuxin");
		user.setPassword("888");
		User user1 = userService.queryUserByUsrPwd(user);
		userService.deleteUser(user1);
	}
}
