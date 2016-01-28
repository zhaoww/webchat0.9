/**
 * @author CTRL 
 * Classname : MessageSixinServiceimpl 
 * Version information : modified 
 * Date : 2016-01-27 
 * Copyright notice : CTRL
 */
package com.ctrl.service.impl;

import java.util.Date;
import java.util.List;

import com.ctrl.dao.MessageSixinDao;
import com.ctrl.dao.impl.MessageSixinDaoImpl;
import com.ctrl.dao.proxy.MessageSixinDaoLoggingProxy;
import com.ctrl.service.MessageSixinService;
import com.ctrl.vo.MessageSixin;

public class MessageSixinServiceimpl implements MessageSixinService {
	private MessageSixinDao target=new MessageSixinDaoImpl();
	//日志代理
	private MessageSixinDao messageSixinDao =new MessageSixinDaoLoggingProxy(target).getLoggingProxy();
	@Override
	public void save(MessageSixin messageSixin) {
		messageSixinDao.save(messageSixin);
	}

	@Override
	public List<MessageSixin> getByCode(String code) {
		
		return messageSixinDao.getByCode(code);
	}

	@Override
	public List<MessageSixin> getByCodeDay(String code, Date date) {
		return messageSixinDao.getByCodeDay(code, date);
	}

}
