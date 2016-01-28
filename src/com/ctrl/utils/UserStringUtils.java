/**
 * @author CTRL 
 * Classname : UserStringUtils 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

import com.ctrl.vo.User;

public class UserStringUtils {

	// 解析url的user字符串，并将其映射为user对象
	public static User getUser(String user) {
		// User [id=29, name=��С��, code=6027020836,
		// pwd=202cb962ac59075b964b07152d234b70, state=T, flag=F]
		user = user.substring(5);
		user = user.substring(1, user.length() - 1);
		String str[] = user.split(",");
		String[] result = new String[6];
		for (int i = 0; i < 6; i++) {
			String tmp[] = str[i].split("=");
			result[i] = tmp[1];
		}
		User u = new User();
		u.setId(Integer.parseInt(result[0]));
		try {
			result[1] = encodingString(result[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		u.setName(result[1]);
		u.setCode(result[2]);
		u.setPwd(result[3]);
		u.setState(result[4]);
		u.setFlag(result[5]);
		return u;
	}

	// 进行转码，防止中文乱码
	public static String encodingString(String str) throws Exception {
		str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		return str;
	}
}
