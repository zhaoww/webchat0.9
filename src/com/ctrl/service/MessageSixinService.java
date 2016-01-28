/**
 * @author CTRL 
 * Classname : MessageSixinService 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.service;

import java.util.Date;
import java.util.List;

import com.ctrl.vo.MessageSixin;

public interface MessageSixinService {
	// 保存单条私信
	public void save(MessageSixin messageSixin);

	// 根据用户 code 获取私信记录
	public List<MessageSixin> getByCode(String code);

	// 根据用户 code 和日期获取私信记录
	public List<MessageSixin> getByCodeDay(String code, Date date);

}
