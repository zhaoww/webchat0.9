package com.ctrl.web.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ctrl.service.UserService;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.vo.Message;
import com.ctrl.vo.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 从队列中读取缓存消息
 * 
 * @author CTRL
 *
 */
@SuppressWarnings("serial")
@WebServlet("/getQueueServlet")
public class GetQueueServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(GetQueueServlet.class);
	private UserService userService = new UserServiceImpl();
	// 缓存队列
	public static final ConcurrentLinkedQueue<Message> queue = new ConcurrentLinkedQueue<Message>();
	// 缓存大小
	public static final int cacheSize = 100;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("begin");
		Iterator<Message> iterator = queue.iterator();
		JSONArray jsons = new JSONArray();
		while (iterator.hasNext()) {
			Message message = iterator.next();
			User user = userService.get(message.getStaff_id());
			JSONObject jo = new JSONObject();
			jo.put("name", user.getName());
			jo.put("code", user.getCode());
			jo.put("message", message);
			jsons.add(jo);
		}
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(jsons.toString());
	}
}
