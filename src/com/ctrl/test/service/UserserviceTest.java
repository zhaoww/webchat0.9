/**
 * @author CTRL 
 * Classname : UserserviceTest 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.test.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import com.ctrl.service.UserService;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.vo.User;

public class UserserviceTest {
	
	UserService userService = null;
	@Before
	public void beforeTest() {
		// TODO Auto-generated method stub
		userService = new UserServiceImpl();
	}

	@Test
	public void testUpdate() {
		User user = new User(29, "刘小可", "6027020836", "202cb962ac59075b964b07152d234b70", "T", "T");
		userService.update(user);
	}

	@Test
	public void testGetLimitUsers(){
		userService.getLimitUsers(0, 10);
	}
	
	@Test
	public void testGetUsersCount(){
		long count = userService.getUsersCount();
		assertEquals(count, 36);
		
	}
	
	@Test
	public void testGetAll(){
		userService.getAll();
	}
	
	@Test
	public void testGet(){
		userService.get(1);
	}
	
	@Test
	public void testDelete(){
		userService.delete(36);
	}
	
	@Test
	public void testGetbyCode(){
		userService.getByCode("6027020845");
	}
	
	@Test
	public void testGetCountWithCode(){
		long count = userService.getCountWithCode("123");
		assertEquals(1, count);
	}
	
	@Test
	public void testGetCountWithPwd(){
		long count = userService.getCountWithPwd("6027020836","202cb962ac59075b964b07152d234b70");
		assertEquals(1, count);
	}
	
	@Test
	public void testGetByName(){
		userService.getByName("root");
	}
	
}
