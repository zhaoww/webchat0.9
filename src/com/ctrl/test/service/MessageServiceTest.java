/**
 * @author CTRL 
 * Classname : MessageServiceTest 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.test.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ctrl.service.MessageService;
import com.ctrl.service.impl.MessageServiceImpl;
import com.ctrl.vo.Message;

public class MessageServiceTest {
	
	MessageService messageService = null;
	
	@Before
	public void beforeTest(){
		// TODO Auto-generated method stub
		messageService = new MessageServiceImpl();
	}
	
	@Test
	public void testUpdate() {
		Message message = new Message("123", 37, new Date());
		messageService.update(message);
	}
	
	@Test
	public void testGetAll(){
		messageService.getAll();
	}

	@Test
	public void testSave(){
		Message message = new Message("123", 37, new Date());
		messageService.save(message);
	}
	
	@Test
	public void testGetCountWithParam(){
		long count = messageService.getCountWithParam("123", "code");
		assertEquals(0, count);
	}
	
	@Test
	public void	testGetByParams(){
		messageService.getByParams("2016-01-25", "123", 1, 10);
	}
	
	@Test
	public void testGetMessagesCount(){
		long count = messageService.getMessagesCount();
		assertEquals(449, count);
	}
	
	@Test
	public void testGetCountWithDayAndCon(){
		long count = messageService.getCountWithDayAndCon("2016-01-19", "123");
		assertEquals(12, count);
	}
	
	
	
}
