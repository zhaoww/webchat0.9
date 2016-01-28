/**
 * @author CTRL 
 * Classname : MessageDao 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.dao;

import java.util.List;

import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.Message;

public interface MessageDao {
	
	// 根据日期 查找消息，返回list 集合
	public List<Message> getByDay(String day, int begin, int size);

	// 根据内容模糊查询，返回list 集合
	public List<Message> getByContent(String content, int begin, int size);

	// 获取最近几天的消息记录，返回list 集合
	public List<Message> getForLatestDays(int days);

	// 更新消息
	public void update(Message message);

	// 根据条件对消息进行模糊查询
	public List<Message> fuzzyQuery(FuzzyQueryUtils fq);

	// 获取所有的消息记录
	public List<Message> getAll();

	// 保存单条消息
	public void save(Message message);

	// 保存多条消息
	public void savaWithBlog(Message[] message);

	// 根据消息id 获取该消息
	public Message get(Integer id);

	// 根据消息id 删除该消息
	public void delete(Integer id);

	// 根据消息code 获取该code用户发送了多少条消息
	public long getCountWithCode(String code);

	// 未用
	public long getCountWithPwd(String pwd);

	// 通过日期和文本内容查询消息
	public List<Message> getByParams(String day, String content, int begin, int size);

	// 从某个位置查询一定数量的消息
	public List<Message> getLimitMessages(int begin, int size);

	// 获取所有的消息数量
	public Long getMessagesCount();

	// 根据日期和内容获取消息数量
	public long getCountWithDayAndCon(String day, String content);

	// 根据日期获取消息数量
	public long getCountWithDay(String day);

	// // 根据内容获取消息数量
	public long getCountWithCon(String content);
}
