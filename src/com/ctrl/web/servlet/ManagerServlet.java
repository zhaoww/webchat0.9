package com.ctrl.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ctrl.service.UserService;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.utils.FuzzyQueryUtils;
import com.ctrl.utils.MD5Utils;
import com.ctrl.utils.SessionDataUtils;
import com.ctrl.utils.UserStringUtils;
import com.ctrl.vo.Page;
import com.ctrl.vo.User;

/**
 * 用户管理与修改密码模块
 * 
 * @author CTRL
 *
 */
@WebServlet("/managerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MD5Utils getMD5 = new MD5Utils();
	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pMessage = request.getParameter("method");
		System.out.println(pMessage + "...");
		// 利用反射，得到showAllUsers(...)方法，并调用
		try {
			Method method = this.getClass().getDeclaredMethod(pMessage, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}
	}

	public void showAllUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("query").trim();
		// 注意获取参数时要进行编码的转化！
		option = new String(option.getBytes("ISO-8859-1"), "UTF-8");
		option = option != null ? option : "";
		// only null
		if ("".equals(option)) {
			showLimitUsers(request, response);
		} else {
			// 进行模糊查询
			userService = new UserServiceImpl();
			FuzzyQueryUtils fq = new FuzzyQueryUtils(option, option);
			List<User> users = userService.fuzzyQuery(fq);
			HttpSession session = request.getSession();
			session.setAttribute("users", users);
			// <a>标签是否显示
			session.setAttribute("flag", "F");
			response.sendRedirect(request.getContextPath() + "/showUsers.jsp");
		}
	}

	public void showUser(HttpServletRequest request, HttpServletResponse response, Integer id)
			throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		HttpSession session = request.getSession();
		List<User> list = new ArrayList<User>();
		list.add(userService.get(id));
		session.setAttribute("users", list);
		response.sendRedirect(request.getContextPath() + "/showUsers.jsp");
	}

	public void showFirstUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userService = new UserServiceImpl();
		FuzzyQueryUtils fq = new FuzzyQueryUtils("", "");
		List<User> users = userService.fuzzyQuery(fq);
		HttpSession session = request.getSession();
		session.setAttribute("users", users);
		response.sendRedirect(request.getContextPath() + "/showUsers.jsp");
	}

	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("up_user");
		String state = "";
		String flag = "";
		try {
			state = UserStringUtils.encodingString(request.getParameter("state"));
			flag = UserStringUtils.encodingString(request.getParameter("flag"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setState(state);
		user.setFlag(flag);
		session.setAttribute("up_user", user);
		// <a>标签是否显示
		session.setAttribute("flag", "F");
		UserService userService = new UserServiceImpl();
		userService.update(user);
		// 只显示修改的那一条数据
		this.showUser(request, response, user.getId());
		// response.sendRedirect(request.getContextPath() + "/showUsers.jsp");
	}

	/**
	 * 分页查询
	 * 
	 */
	public void showLimitUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page page = new Page();
		UserService userService = new UserServiceImpl();
		long totalRecord = userService.getUsersCount() - 1;
		page.setTotalRecord((int) totalRecord);
		page.setTotalPage(page.getTotalRecord(), page.getPageSize());
		String type = request.getParameter("type");
		if (request.getParameter("currentRecord") != null && !request.getParameter("currentRecord").equals("")) {
			int currentRecord = Integer.parseInt(request.getParameter("currentRecord"));
			page.setCurrentRecord(currentRecord);
			page.setCurrentPage(page.getCurrentRecord(), page.getPageSize());
			if ("next".equals(type) && page.getCurrentPage() < (page.getTotalPage() - 1)) {
				currentRecord = currentRecord + 10;
				page.setCurrentRecord(currentRecord);
			} else if ("prev".equals(type) && page.getCurrentPage() > 0) {
				currentRecord = currentRecord - 10;
				page.setCurrentRecord(currentRecord);
			}
		} else {
			page.setCurrentRecord(0);
		}
		List<User> users = userService.getLimitUsers(page.getCurrentRecord(), page.getPageSize());
		page.setCurrentPage(page.getCurrentRecord(), page.getPageSize());

		HttpSession session = request.getSession();
		// <a>标签是否显示
		session.setAttribute("flag", "T");
		session.setAttribute("users", users);
		session.setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/showUsers.jsp");

	}

	@SuppressWarnings("static-access")
	public void UpdatePwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user.getCode());
		String oldPwd = "";
		String newPwd1 = "";
		String newPwd2 = "";
		try {
			oldPwd = UserStringUtils.encodingString(request.getParameter("oldPwd"));
			newPwd1 = UserStringUtils.encodingString(request.getParameter("newPwd1"));
			newPwd2 = UserStringUtils.encodingString(request.getParameter("newPwd2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String password = user.getPwd();
		// 判断用户输入的旧密码是否和数据库中的一致
		if (password.equals(getMD5.GetMD5Code(oldPwd))) {
			// 若一致，则不显示错误信息
			session.setAttribute("show_1", "");
			// 若输入的新密码不为空
			if (!newPwd1.trim().equals("") && !newPwd2.trim().equals("")) {
				session.setAttribute("show_2", "");
				// 输入的两次新密码一致
				if (newPwd1.equals(newPwd2)) {
					// 改变该user 的pwd 属性
					user.setPwd(getMD5.GetMD5Code(newPwd1));
					// 写入数据库
					userService.update(user);
					// 修改过密码，应该让其用新密码重新验证登录
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				} else {
					session.setAttribute("show_2", "两次输入的密码不一致！");
					response.sendRedirect(request.getContextPath() + "/updatepwd.jsp");
					return;
				}
			} else {
				session.setAttribute("show_2", "输入的密码不能为空！");
				response.sendRedirect(request.getContextPath() + "/updatepwd.jsp");
				return;
			}
			Hashtable<String, String> hashtable = SessionDataUtils.USER_SESSION;
			String userCode = (String) request.getSession().getAttribute("userCode");
			hashtable.remove(userCode);
		} else {
			session.setAttribute("show_1", "输入的旧密码不正确！");
			session.setAttribute("show_2", "");
			response.sendRedirect(request.getContextPath() + "/updatepwd.jsp");
			return;
		}
	}

	public void LogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		SessionDataUtils.USER_SESSION.remove(user.getCode());
		SessionDataUtils.USERNAME_IPAddr.remove(user.getCode());
		SessionDataUtils.USERCODE_ONLINE.remove(user.getCode());
		SessionDataUtils.USERNAME_ONLINE.remove(user.getName());
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
