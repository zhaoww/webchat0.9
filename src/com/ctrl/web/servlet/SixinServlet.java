package com.ctrl.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctrl.service.MessageSixinService;
import com.ctrl.service.UserService;
import com.ctrl.service.impl.MessageSixinServiceimpl;
import com.ctrl.service.impl.UserServiceImpl;
import com.ctrl.utils.LoggerWebChat;
import com.ctrl.vo.MessageSixin;
import com.ctrl.vo.MessageSixinDto;
import com.ctrl.vo.User;

/**
 * 私信记录的展示
 * 
 * @author CTRL
 *
 */
@SuppressWarnings("serial")
@WebServlet("/sixinServlet")
public class SixinServlet extends HttpServlet {
	private MessageSixinService messageSixinService = new MessageSixinServiceimpl();
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Date date = null;
		String day = req.getParameter("day");
		if (null == day) {
			date = new Date();
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(day);
			} catch (Exception e) {
				req.setAttribute("error", "您的输入" + day + "日期错误");
				LoggerWebChat.logger.info(e);
				req.getRequestDispatcher("sixin.jsp").forward(req, resp);
				return;
			}
		}
		User user = (User) req.getSession().getAttribute("user");
		List<MessageSixin> list = messageSixinService.getByCodeDay(user.getCode(), date);
		List<MessageSixinDto> listDto = new ArrayList<MessageSixinDto>();
		for (MessageSixin sixin : list) {
			User fromUser = userService.getByCode(sixin.getFromCode());
			User toUser = userService.getByCode(sixin.getToCode());
			MessageSixinDto messageSixinDto = new MessageSixinDto(fromUser.getName(), toUser.getName(),
					sixin.getCreated_dt(), sixin.getContent());
			listDto.add(messageSixinDto);
		}
		LoggerWebChat.logger.info(listDto.size() + "........");
		req.setAttribute("day", date);
		req.setAttribute("listDto", listDto);
		req.getRequestDispatcher("sixin.jsp").forward(req, resp);
	}

}
