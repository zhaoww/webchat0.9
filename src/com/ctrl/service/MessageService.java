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
	 // �޸ļ�¼
	public void update(Message message);
	
	// ͨ��ģ����ѯ�õ���������������Message
	public List<Message> fuzzyQuery(FuzzyQueryUtils fq);

	// �õ����е�Message����
	public List<Message> getAll();

	// �����¼����б���
	public void save(Message message);
	
	// ���������¼����б���
	public void saveWithBlog(Message[] message);

	// ͨ������idȥ��ȡMessage����
	public Message get(Integer id);

	// ͨ��idȥɾ��Message����
	public void delete(Integer id);

	// ��ȡ��������ͼ�����ֵһ�µ�Message���������
	public long getCountWithParam(String param,String type);
	
	//���ݲ���ֵ��ҳ��ȡMessage����
	public List<Message> getByParams(String day,String content,int begin,int size);
	
	//��ȡ��Ϣ�ܼ�¼��
	public Long getMessagesCount();
	
	//���������Լ��ı����ݻ�ȡ��Ϣ��¼��
	public long getCountWithDayAndCon(String day,String content);
	
}
