/**
 * @author CTRL 
 * Classname : MessageDaoImpl 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.dao.impl;

import java.util.List;

import com.ctrl.dao.MessageDao;
import com.ctrl.utils.DAOUtil;
import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.Message;

public class MessageDaoImpl extends DAOUtil<Message> implements MessageDao {

	@Override
	public void update(Message message) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Message> fuzzyQuery(FuzzyQueryUtils fq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from message order by created_dt desc limit 10 ";
		return getForList(sql);
	}

	@Override
	public void save(Message message) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO message(content,staff_id,created_dt) VALUES(?,?,?)";
		update(sql, message.getContent(), message.getStaff_id(),
				message.getCreated_dt());

	}

	@Override
	public void savaWithBlog(Message[] message) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO message(content,staff_id,created_dt) VALUES(?,?,?)";
		updateWithBlog(sql, message);
	}

	@Override
	public Message get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getCountWithCode(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCountWithPwd(String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getForLatestDays(int days) {
		String sql = "select * from message where datediff(now(),created_dt)<=?;";
		return getForList(sql, days);
	}

	@Override
	public List<Message> getByDay(String day, int begin, int size) {
		String sql = "select * from message where date(created_dt)=? limit ?, ?";
		return getForList(sql, day, begin, size);
	}

	@Override
	public List<Message> getByContent(String content, int begin, int size) {
		content = "%" + content + "%";
		String sql = "select * from message where content like ?  limit ?, ?";
		return getForList(sql, content, begin, size);
	}

	@Override
	public List<Message> getByParams(String day, String content, int begin,
			int size) {
		// TODO Auto-generated method stub
		content = "%" + content + "%";
		String sql = "select * from message where date(created_dt)= ? AND content like ? limit ? , ? ";
		return getForList(sql, day, content, begin, size);
	}

	@Override
	public List<Message> getLimitMessages(int begin, int size) {
		String sql = "select * from message order by created_dt desc limit ?,?";
		return getForList(sql, begin, size);
	}

	@Override
	public Long getMessagesCount() {
		String sql = "select count(*) from message";
		return getForValue(sql);
	}

	@Override
	public long getCountWithDayAndCon(String day, String content) {
		content = "%" + content + "%";
		String sql = "select count(*) from message where date(created_dt)= ? AND content like ?";
		return getForValue(sql, day, content);
	}

	@Override
	public long getCountWithDay(String day) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from message where date(created_dt)= ?";
		return getForValue(sql, day);
	}

	@Override
	public long getCountWithCon(String content) {
		content = "%" + content + "%";
		String sql = "select count(*) from message where content like ?";
		return getForValue(sql, content);
	}
}
