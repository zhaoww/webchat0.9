/**
 * @author CTRL 
 * Classname : CheckServlet 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ctrl.service.UserService;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.utils.MD5Utils;
import com.ctrl.utils.SessionDataUtils;
import com.ctrl.vo.User;

/**
 * 登录验证
 * @author CTRL
 */
@WebServlet("/checkServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private MD5Utils getMD5 = new MD5Utils();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pMessage = request.getParameter("method");
		try {
			Method method = this.getClass().getDeclaredMethod(pMessage, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object result = null;
		String pwd = request.getParameter("password");
		String name_1 = request.getParameter("name");
		String name = new String(name_1.getBytes("ISO-8859-1"), "UTF-8");
		String flag = request.getParameter("remember");
		int db_name = (int) userService.getCountWithCode(name);
		int db_password = (int) userService.getCountWithPwd(name, getMD5.GetMD5Code(pwd));
		HttpSession session = request.getSession();
		if (db_name > 0) {
			if (db_password > 0) {
				// 记住用户名、密码
				if (null != flag && !("".equals(flag)) && !("null".equals(flag))) {
					// 创建两个Cookie对象
					Cookie nameCookie = new Cookie("name", name);
					// 设置Cookie的有效期为3天
					nameCookie.setMaxAge(60 * 60 * 24 * 3);
					Cookie pwdCookie = new Cookie("password", pwd);
					pwdCookie.setMaxAge(60 * 60 * 24 * 3);
					response.addCookie(nameCookie);
					response.addCookie(pwdCookie);
					System.out.println("123");
				}
				User user = userService.getByCode(name);
				String userCode = user.getCode();
				String state = user.getState();
				if (state.equals("F")) {
					result = (Object) "当前用户不可用";
				} else {
					// 判断是否重复登录 start
					@SuppressWarnings("rawtypes")
					Hashtable hashtable = SessionDataUtils.USER_SESSION;
					for (Object key : hashtable.keySet()) {
						System.out.println(key + "||" + hashtable.get(key));
					}
					if (hashtable.get(userCode) != null) {
						result = (Object) "当前用户已登录";
					} else {
						// 是否是同一个IP登录
						String ipAddress = getIpAddr(request, response);
						if (SessionDataUtils.USERNAME_IPAddr.containsValue(ipAddress)) {
							result = (Object) "该IP已有用户登录";
						} else {
							session.setAttribute("user", user);
							session.setAttribute("userCode", userCode);
							SessionDataUtils.USERNAME_IPAddr.put(userCode, ipAddress);
							hashtable.put(userCode, request.getSession());
							SessionDataUtils.USER_SESSION = hashtable;
							result = (Object) "chat.jsp";
						}
					}
				}
			} else {
				result = (Object) "密码错误";
			}
		} else {
			result = (Object) "用户名有误";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
	}

	protected void checkUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = "webchat";
		String name = request.getParameter("name");
		if (name == null) {
			result = "用户名不能为空";
		} else {
			int db_name = (int) userService.getCountWithCode(name);
			if (db_name > 0) {
				result = "webchat";
			} else {
				result = "用户名不存在";
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
	}

	private String getIpAddr(HttpServletRequest request, HttpServletResponse response) {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

}
