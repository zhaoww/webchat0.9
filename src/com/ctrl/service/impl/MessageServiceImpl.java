/**
 * @author CTRL 
 * Classname : MessageServiceImpl 
 * Version information : modified 
 * Date : 2016-01-27 
 * Copyright notice : CTRL
 */
package com.ctrl.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ctrl.dao.MessageDao;
import com.ctrl.dao.impl.MessageDaoImpl;
import com.ctrl.dao.proxy.MessageDaoLoggingProxy;
import com.ctrl.service.MessageService;
import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.Message;

public class MessageServiceImpl implements MessageService {

	MessageDao target = new MessageDaoImpl();
	//日志代理
	MessageDao messageDao =new MessageDaoLoggingProxy(target).getLoggingProxy();

	@Override
	public void update(Message message) {

		messageDao.update(message);
	}

	@Override
	public List<Message> fuzzyQuery(FuzzyQueryUtils fq) {

		return messageDao.fuzzyQuery(fq);
	}

	@Override
	public List<Message> getAll() {

		return messageDao.getAll();
	}

	@Override
	public void save(Message message) {
		
		messageDao.save(message);
	}
	
	@Override
	public void saveWithBlog(Message[] message) {

		messageDao.savaWithBlog(message);
	}

	@Override
	public Message get(Integer id) {

		return messageDao.get(id);
	}

	@Override
	public void delete(Integer id) {

		messageDao.delete(id);
	}

	@Override
	public long getCountWithParam(String param, String type) {

		long count = 0;
		if ("code".equals(type)) {
			count = messageDao.getCountWithCode(param);
		} else {
			count = messageDao.getCountWithPwd(param);
		}
		return count;
	}

	@Override
	public List<Message> getByParams(String day, String content, int begin,
			int size) {

		List<Message> messages = new ArrayList<Message>();
		if ("".equals(day) && "".equals(content)) {
			messages = messageDao.getLimitMessages(begin, size);
		} else if ("".equals(day) && !"".equals(content)) {
			messages = messageDao.getByContent(content, begin, size);
		} else if (!"".equals(day) && "".equals(content)) {
			messages = messageDao.getByDay(day, begin, size);
		} else {
			messages = messageDao.getByParams(day, content, begin, size);
		}
		return messages;
	}

	@Override
	public Long getMessagesCount() {

		return messageDao.getMessagesCount();

	}

	@Override
	public long getCountWithDayAndCon(String day, String content) {

		long count=0;
		if ("".equals(day) && "".equals(content)) {
			count=messageDao.getMessagesCount();
		} else if ("".equals(day) && !"".equals(content)) {
			count=messageDao.getCountWithCon(content);
		} else if (!"".equals(day) && "".equals(content)) {
			count=messageDao.getCountWithDay(day);
		} else {
			count=messageDao.getCountWithDayAndCon(day, content);
		}

		return count;
	}

}
