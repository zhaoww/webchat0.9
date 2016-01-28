/**
 * @author CTRL 
 * Classname : ApplicationListener 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ctrl.cache.Storage;
import com.ctrl.dao.MessageDao;
import com.ctrl.dao.impl.MessageDaoImpl;
import com.ctrl.vo.Message;

public class ApplicationListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(ApplicationListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info(arg0.getServletContext().getContextPath()
				+ " has Destroyed...");
		int tmp = Storage.queue.size();
		int num = Storage.getMessage().length;
		Message message;
		MessageDao messageDao = new MessageDaoImpl();
		// num大于0将Message[]中数据存入数据库
		if (num > 0) {
			messageDao.savaWithBlog(Storage.getMessage());
			logger.info("Message[] 中的数据已存入数据库中...");
		}
		// tmp大于0强Storage中数据存入数据库
		if (tmp > 0) {
			for (int i = 0; i < tmp; i++) {
				message = Storage.queue.poll();
				messageDao.save(message);
			}
			logger.info("Storage 中的数据已存入数据库中...");
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info(arg0.getServletContext().getContextPath()
				+ " has initialized...");
	}

}
