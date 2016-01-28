/**
 * @author CTRL 
 * Classname : MessageService 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.service;

import java.util.List;

import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.vo.Message;

public interface MessageService {
	 // 修改记录
	public void update(Message message);
	
	// 通过模糊查询得到所有满足条件的Message
	public List<Message> fuzzyQuery(FuzzyQueryUtils fq);

	// 得到所有的Message对象
	public List<Message> getAll();

	// 插入记录后进行保存
	public void save(Message message);
	
	// 批量插入记录后进行保存
	public void saveWithBlog(Message[] message);

	// 通过参数id去获取Message对象
	public Message get(Integer id);

	// 通过id去删除Message对象
	public void delete(Integer id);

	// 获取与参数类型及参数值一致的Message对象的数量
	public long getCountWithParam(String param,String type);
	
	//根据参数值分页获取Message对象
	public List<Message> getByParams(String day,String content,int begin,int size);
	
	//获取消息总记录数
	public Long getMessagesCount();
	
	//根据日期以及文本内容获取消息记录数
	public long getCountWithDayAndCon(String day,String content);
	
}
