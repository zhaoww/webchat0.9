/**
 * @author CTRL 
 * Classname : TmpStorage 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.cache;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.ctrl.vo.Message;

public class TmpStorage implements Runnable {

	public static final ConcurrentLinkedQueue<Message> tmp_queue = new ConcurrentLinkedQueue<Message>();
	private Storage storage;

	@Override
	public void run() {
		while (true) {
			produce();
		}
	}

	public TmpStorage(Storage storage) {
		this.storage = storage;
	}

	public void produce() {
		if (tmp_queue.size() > 0) {
			Message message = tmp_queue.poll();
			System.out.println("可以进行输出");
			storage.add(message);
		} else {
			return;
		}
	}

}
