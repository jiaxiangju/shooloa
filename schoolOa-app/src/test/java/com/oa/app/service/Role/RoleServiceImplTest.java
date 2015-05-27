package com.oa.app.service.Role;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oa.common.entity.Role;

public class RoleServiceImplTest {

	private ApplicationContext ctx; 
	
	private IRoleService roleService ;
//	@Before
//	public void setUp() throws Exception {
//		 String[] configLocations = new String[] {"classpath:spring-servlet.xml"}; 
//	        ctx = new ClassPathXmlApplicationContext(configLocations); 
//	        roleService  = (RoleServiceImpl) ctx.getBean("roleService");
//	}

//	@Test
//	public void testQueryAllRoles() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testQueryAllRolesByPage() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testAddRole() {
//		Role role = new Role();
//		role.setRoleCode("Q1W2");
//		role.setRoleName("老师");
//		roleService.addRole(role);
//	}

//	@Test
//	public void testUpdateRole() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testDeleteRole() {
//		fail("Not yet implemented");
//	}

}
