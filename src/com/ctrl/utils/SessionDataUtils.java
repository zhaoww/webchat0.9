/**
 * @author CTRL 
 * Classname : SessionDataUtils 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SessionDataUtils {

	// 存放user 信息
	public static Hashtable<String, String> USER_SESSION = new Hashtable<String, String>();
	// 存放在线的user的code信息
	public static List<String> USERCODE_ONLINE = new ArrayList<String>();
	// 存放在线的user的name信息
	public static List<String> USERNAME_ONLINE = new ArrayList<String>();
	// 存放在线的user的IP信息
	public static Hashtable<String, String> USERNAME_IPAddr = new Hashtable<String, String>();
	// 存放文件列表信息
	public static List<String> fileList = new ArrayList<String>();
	// 存放图片列表信息
	public static List<String> imgList = new ArrayList<String>();
	
 }
