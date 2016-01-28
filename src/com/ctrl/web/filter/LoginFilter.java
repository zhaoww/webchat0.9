/**
 * @author CTRL 
 * Classname : LoginFilter 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ctrl.utils.LoggerWebChat;
import com.ctrl.vo.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = { "/chat.jsp", "/avatar.html", "/message.jsp",
		"/showUsers.jsp", "/update.jsp", "/updatepwd.jsp" })
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		// 判断session中user是否为空，为空进行拦截
		if (null != session.getAttribute("user")) {
			User user = (User) session.getAttribute("user");
			LoggerWebChat.logger.info(user);
			filterChain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

}
