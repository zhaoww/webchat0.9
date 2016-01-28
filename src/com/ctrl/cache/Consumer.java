/**
 * @author CTRL 
 * Classname : Consumer 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.cache;

public class Consumer implements Runnable {

	// run方法
	@Override
	public void run() {
	// 一直循环执行，当成功消费后，进行下一次消费
	while (true) {
			// 进行消费
			storage.sub();
		}
	}

	private Storage storage;

	public Consumer(Storage storage) {
		this.storage = storage;
	}
}
