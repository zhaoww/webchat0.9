package com.ctrl.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 点击表情后servlet
 * 
 * @author CTRL
 *
 */
@WebServlet("/expressServlet")
public class ExpressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pMessage = request.getParameter("method");
		// 利用反射，得到showAllUsers(...)方法，并调用
		try {
			Method method = this.getClass().getDeclaredMethod(pMessage, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			response.sendRedirect("error.jsp");
		}
	}

	public void doShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		String message = request.getParameter("message");
		String mark = request.getParameter("mark");
		result = message + "[/表情" + mark + "]";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
	}
}
