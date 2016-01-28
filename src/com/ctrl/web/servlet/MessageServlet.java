package com.ctrl.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctrl.service.MessageService;
import com.ctrl.service.UserService;
import com.ctrl.service.impl.MessageServiceImpl;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.vo.Message;
import com.ctrl.vo.MessageDto;
import com.ctrl.vo.Page;
import com.ctrl.vo.User;

/**
 * 群聊记录的查询
 * 
 * @author CTRL
 *
 */
@SuppressWarnings("serial")
@WebServlet("/messageServlet")
public class MessageServlet extends HttpServlet {
	private MessageService messageService = new MessageServiceImpl();
	private UserService userservice = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String pMessage = req.getParameter("method");
		System.out.println(pMessage + "...");
		// 利用反射，得到showAllMessages(...)方法，并调用
		try {
			Method method = this.getClass().getDeclaredMethod(pMessage, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			resp.sendRedirect("error.jsp");
		}
	}

	// 初始
	public void showAllMessages(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Page page = new Page();
		page.setCurrentRecord(0);
		page.setCurrentPage(page.getCurrentRecord(), page.getPageSize());
		String day = req.getParameter("day");
		String content = req.getParameter("content");
		day = (null == day ? "" : day);
		content = (null == content ? "" : content);
		Pattern p = Pattern.compile("2\\d{3}-\\d{2}-\\d{2}");
		Matcher ma = p.matcher(day);
		if (!"".equals(day) && !ma.matches()) {
			req.setAttribute("error", "您的输入" + day + "格式错误");
		} else {
			List<Message> messages = messageService.getByParams(day, content, page.getCurrentRecord(),
					page.getPageSize());
			System.out.println(messages);
			List<MessageDto> messageDtos = new ArrayList<MessageDto>();
			for (Message m : messages) {
				User user = userservice.get(m.getStaff_id());
				messageDtos.add(new MessageDto(m, user.getName()));
			}
			req.setAttribute("messageDtos", messageDtos);
		}
		req.setAttribute("page", page);
		req.getSession().setAttribute("day", day);
		req.getSession().setAttribute("content", content);
		req.setAttribute("yourInput", "您的输入:[day: " + day + " ,[content: " + content + " ]");
		req.getRequestDispatcher("message.jsp").forward(req, resp);
	}

	public void showLimitMessages(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String day = (String) req.getSession().getAttribute("day");
		String content = (String) req.getSession().getAttribute("content");
		Page page = new Page();
		long totalRecord = messageService.getCountWithDayAndCon(day, content);
		page.setTotalRecord((int) totalRecord);
		page.setTotalPage(page.getTotalRecord(), page.getPageSize());
		String type = req.getParameter("type");
		if (req.getParameter("currentRecord") != null && !req.getParameter("currentRecord").equals("")) {
			int currentRecord = Integer.parseInt(req.getParameter("currentRecord"));
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
		page.setCurrentPage(page.getCurrentRecord(), page.getPageSize());
		req.setAttribute("page", page);
		List<Message> messages = messageService.getByParams(day, content, page.getCurrentRecord(), page.getPageSize());
		List<MessageDto> messageDtos = new ArrayList<MessageDto>();
		for (Message m : messages) {
			User user = userservice.get(m.getStaff_id());
			messageDtos.add(new MessageDto(m, user.getName()));
		}
		req.setAttribute("messageDtos", messageDtos);
		req.getRequestDispatcher("message.jsp").forward(req, resp);
	}
}