/**
 * @author CTRL 
 * Classname : MessageSixinServiceTest 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.test.service;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ctrl.service.MessageSixinService;
import com.ctrl.service.impl.MessageSixinServiceimpl;
import com.ctrl.vo.MessageSixin;

public class MessageSixinServiceTest {
	
	MessageSixinService messageSixinService = null;
	
	@Before
	public void beforeTest(){
		// TODO Auto-generated method stub
		messageSixinService = new MessageSixinServiceimpl();
	}
	
	@Test
	public void testSave() {
		MessageSixin messageSixin = new MessageSixin("123", "6027020836", "test", new Date());
		messageSixinService.save(messageSixin);
	}
	
	@Test
	public void testGetByCode(){
		messageSixinService.getByCode("123");
	}
	
	@Test
	public void testgetByCodeDay(){
		@SuppressWarnings("deprecation")
		Date date = new Date(2016, 01, 20);
		messageSixinService.getByCodeDay("123", date);
	}
	
}
