/**
 * @author CTRL 
 * Classname : LoggerWebChat 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

import org.apache.log4j.Logger;

public class LoggerWebChat {
	// 初始化 日志对象
	public static final Logger logger = Logger.getLogger(LoggerWebChat.class);

	static {
		System.out.println("LoggerWebChat加载.....................");
	}

	public static void main(String[] args) {
		logger.debug("This is debug message.");
		logger.info("This is info message.");
		logger.error("This is error message.");
		logger.warn("warn");
	}

}
