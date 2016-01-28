package com.ctrl.web.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.ctrl.cache.Consumer;
import com.ctrl.cache.Storage;
import com.ctrl.cache.TmpStorage;
import com.ctrl.service.MessageSixinService;
import com.ctrl.service.UserService;
import com.ctrl.service.impl.MessageSixinServiceimpl;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.utils.LoggerWebChat;
import com.ctrl.utils.SessionDataUtils;
import com.ctrl.vo.Message;
import com.ctrl.vo.MessageSixin;
import com.ctrl.vo.User;
import com.ctrl.web.servlet.GetQueueServlet;

import net.sf.json.JSONObject;

/**
 * websocket通信
 * 
 * @author CTRL
 *
 */
@ServerEndpoint(value = "/chatSocket", configurator = GetHttpSessionConfigurator.class)
public class ChatSocket {
	private MessageSixinService messageSixinService = new MessageSixinServiceimpl();
	private UserService userService = new UserServiceImpl();
	// 上条消息时间
	private static long messageTime = 0;
	// 距上个时间为止的消息记录数
	private static int messageNum = 0;
	// 超过多久广播时间
	private static final int timeInterval = 60000;
	// 超过条数广播时间
	private static final int messageInterval = 30;
	String userCode = null;

	static {
		TmpStorage tmpStorage = new TmpStorage(new Storage());
		Consumer consumer = new Consumer(new Storage());
		Thread t1 = new Thread(tmpStorage);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();
	}

	private Logger logger = Logger.getLogger(this.getClass().getName());
	HttpSession httpSession;
	static Map<String, Session> sessionMap = new Hashtable<String, Session>();

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		userCode = (String) httpSession.getAttribute("userCode");
		sessionMap.put(userCode, session);
		User user = userService.getByCode(userCode);
		synchronized (OnOpen.class) {
			if (SessionDataUtils.USERNAME_ONLINE.contains(user.getName())) {
				SessionDataUtils.USERNAME_ONLINE.remove(user.getName());
			}
			SessionDataUtils.USERNAME_ONLINE.add(user.getName());
			if (SessionDataUtils.USERCODE_ONLINE.contains(user.getCode())) {
				SessionDataUtils.USERCODE_ONLINE.remove(user.getCode());
			}
			SessionDataUtils.USERCODE_ONLINE.add(user.getCode());
		}
		String info = user.getName() + "加入群聊";
		JSONObject joOpen = new JSONObject();
		joOpen.put("info", info);
		joOpen.put("userNameOnline", SessionDataUtils.USERNAME_ONLINE);
		joOpen.put("userCodeOnline", SessionDataUtils.USERCODE_ONLINE);
		broadcastAll(joOpen.toString());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws UnsupportedEncodingException {
		LoggerWebChat.logger.info(message);
		Pattern pattern = Pattern.compile("\\(私信-.*\\)<br>.*");
		Matcher matcher = pattern.matcher(message);
		if (matcher.matches()) {
			String toName = message.substring(4, message.indexOf(")<br>"));
			User toUser = userService.getByName(toName);
			// 私信的user在线则私信，否则广播
			if (null != toUser) {
				sixin(message, toName, toUser);
				return;
			}
		}
		User user = (User) httpSession.getAttribute("user");
		String flag = userService.getByCode(user.getCode()).getFlag();
		JSONObject jo = new JSONObject();
		// 禁言
		if (flag.equals("T")) {
			Message mess = new Message(message, user.getId(), new Date());
			// 缓存队列更新
			if (GetQueueServlet.queue.size() > GetQueueServlet.cacheSize) {
				GetQueueServlet.queue.poll();
			}
			// 是否广播时间
			synchronized (ChatSocket.class) {
				long newtime = new Date().getTime();
				if ((newtime - messageTime) >= timeInterval || ++messageNum >= messageInterval) {
					messageNum = 0;
					jo.put("time", new Date());
					System.out.println(jo);
					messageTime = newtime;
				}
			}
			GetQueueServlet.queue.add(mess);
			logger.info(GetQueueServlet.queue.size() + "");
			jo.put("name", user.getName());
			jo.put("message", mess);
			jo.put("code", user.getCode());
			broadcastAll(jo.toString());
			TmpStorage.tmp_queue.add(mess);
		}
	}

	private void sixin(String message, String toName, User toUser) {
		LoggerWebChat.logger.info(toName);
		User fromUser = (User) httpSession.getAttribute("user");
		MessageSixin messageSixin = new MessageSixin(fromUser.getCode(), toUser.getCode(), message, new Date());
		Session session = sessionMap.get(toUser.getCode());
		Session session2 = sessionMap.get(fromUser.getCode());
		JSONObject jo = new JSONObject();
		jo.put("name", fromUser.getName());
		jo.put("code", fromUser.getCode());
		Message message2 = new Message(message, fromUser.getId(), new Date());
		jo.put("message", message2);
		try {
			session.getBasicRemote().sendText("{'text':" + "'" + jo.toString() + "'}");
			session2.getBasicRemote().sendText("{'text':" + "'" + jo.toString() + "'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageSixinService.save(messageSixin);
	}

	public static void broadcastAll(String message) {
		Set<Map.Entry<String, Session>> set = sessionMap.entrySet();
		for (Map.Entry<String, Session> i : set) {
			try {
				i.getValue().getBasicRemote().sendText("{'text':" + "'" + message + "'}");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		User user = (User) httpSession.getAttribute("user");
		SessionDataUtils.USER_SESSION.remove(user.getCode());
		SessionDataUtils.USERNAME_ONLINE.remove(user.getName());
		SessionDataUtils.USERCODE_ONLINE.remove(user.getCode());
		JSONObject joClose = new JSONObject();
		joClose.put("userNameOnline", SessionDataUtils.USERNAME_ONLINE);
		joClose.put("userCodeOnline", SessionDataUtils.USERCODE_ONLINE);
		broadcastAll(joClose.toString());
		SessionDataUtils.USERNAME_IPAddr.remove(userCode);
		sessionMap.remove(user.getCode());
		logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
	}

	@OnError
	public void error(Session session, java.lang.Throwable throwable) {
		sessionMap.remove(userCode);
		System.err.println("session " + userCode + " error:" + throwable);
	}
}