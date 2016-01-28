/**
 * @author CTRL 
 * Classname : Storage 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.ctrl.service.MessageService;
import com.ctrl.service.impl.MessageServiceImpl;
import com.ctrl.vo.Message;

public class Storage {
	
	private static int MAX_SIZE; // 仓库最大存储量
	private static int EVERY_NUM; // 每次批处理的数量
	private static int i = 0;  // 批处理积累的sql条数
	private static Message[] mssage = new Message[10];
	MessageService messageService = new MessageServiceImpl();
	// 静态块，加载配置文件
	static {
		InputStream in = Storage.class.getClassLoader().getResourceAsStream(
				"config");
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String tmp = properties.getProperty("MAX_SIZE");
		MAX_SIZE = Integer.parseInt(tmp);
		EVERY_NUM = Integer.parseInt(properties.getProperty("EVERY_NUM"));
	}

	// 仓库存储的载体
	public static final ConcurrentLinkedQueue<Message> queue = new ConcurrentLinkedQueue<Message>();

	// 记录加入缓存
	public void add(Message message) {
		synchronized (queue) {
			// 如果仓库存储量不足
			while (queue.size() >= MAX_SIZE) {
				System.out.println("/t【库存量】:" + queue.size() + "/t暂时不能执行生产任务!");
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("加入到queue中...");
			queue.add(message);
			queue.notifyAll();
		}
	}

	// 向数据库中插入
	public void sub() {
		synchronized (queue) {
			// 如果仓库存储量不足
			while (queue.size() <= MAX_SIZE/2) {
				System.out.println("/t【库存量】:" + queue.size() + "/t暂时不能执行消费任务!");
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Message message = queue.poll();
			if (i == EVERY_NUM) {
				messageService.saveWithBlog(mssage);
				i = 0;
				mssage[i] = message;
				i++;
			} else {
				mssage[i] = message;
				i++;
			}
			System.out.println("消费...");
			queue.notifyAll();
		}
	}

	public static Message[] getMessage() {
		Message[] mage = new Message[i];
		for (int j = 0; j < i; j++) {
			mage[j] = mssage[j];
		}
		return mage;
	}

}
