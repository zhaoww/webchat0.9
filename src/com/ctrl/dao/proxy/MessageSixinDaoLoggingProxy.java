/**
 * @author CTRL 
 * Classname : MessageSixinDaoLoggingProxy 
 * Version information : modified 
 * Date : 2016-01-25
 * Copyright notice : CTRL
 */
package com.ctrl.dao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.ctrl.dao.MessageSixinDao;
import com.ctrl.dao.UserDao;

public class MessageSixinDaoLoggingProxy {
	private static Logger logger = Logger
			.getLogger(MessageSixinDaoLoggingProxy.class);
	//日志
	private MessageSixinDao target;

	public MessageSixinDaoLoggingProxy(MessageSixinDao target) {
		super();
		this.target = target;
	}

	public MessageSixinDao getLoggingProxy() {
		MessageSixinDao proxy = null;
		// 代理对象由哪个类加载器加载
		ClassLoader loader = target.getClass().getClassLoader();
		// 代理对象类型，即包含哪些方法
		Class<?>[] interfaces = new Class[] { MessageSixinDao.class };
		// 调用方法时执行的方法
		InvocationHandler h = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				String methodName = method.getName();

				if (args == null) {
					logger.info("Method: " + methodName + " || Params: null");
				} else {
					logger.info("Method: " + methodName + " || Params: "
							+ Arrays.asList(args));
				}

				Object result = method.invoke(target, args);

				logger.info("Method: " + methodName + " || Results: " + result);
				return result;
			}
		};
		
		proxy = (MessageSixinDao) Proxy.newProxyInstance(loader, interfaces, h);

		return proxy;
	}

}
