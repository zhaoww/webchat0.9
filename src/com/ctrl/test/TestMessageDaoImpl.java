//package com.ctrl.test;
//
//import java.util.List;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.ctrl.dao.impl.MessageDaoImpl;
//import com.ctrl.vo.Message;
//
//public class TestMessageDaoImpl {
//private MessageDaoImpl mdi=new MessageDaoImpl();
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Test
//	public void testGetByContent() {
//	List<Message> list=	mdi.getByContent("weq");
//	System.out.println(list.size());
//	}
//	
//	@Test
//	public void testGetByDay(){
//		List<Message> list=mdi.getByDay("2016-1-20");
//		System.out.println(list.size());
//	}
//
//}
