/**
 * @author CTRL 
 * Classname : MessageSixinDaoImpl 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.dao.impl;

import java.util.Date;
import java.util.List;

import com.ctrl.dao.MessageSixinDao;
import com.ctrl.utils.DAOUtil;
import com.ctrl.vo.MessageSixin;

public class MessageSixinDaoImpl extends DAOUtil<MessageSixin> implements
		MessageSixinDao {

	@Override
	public void save(MessageSixin messageSixin) {
		String sql = "insert into sixin (fromCode,toCode,content,created_dt) values(?,?,?,?);";
		update(sql, messageSixin.getFromCode(), messageSixin.getToCode(),
				messageSixin.getContent(), messageSixin.getCreated_dt());
	}

	@Override
	public List<MessageSixin> getByCode(String code) {
		String sql = "select * from sixin where fromCode=? or toCode=?";
		return getForList(sql, code, code);
	}

	@Override
	public List<MessageSixin> getByCodeDay(String code, Date date) {
		String sql = "select * from sixin where (fromCode=? or toCode=?) and to_days(created_dt)=to_days(?)";

		return getForList(sql, code, code, date);
	}

}
