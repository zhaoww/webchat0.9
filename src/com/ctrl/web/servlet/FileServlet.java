package com.ctrl.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctrl.utils.LoggerWebChat;
import com.ctrl.utils.SessionDataUtils;

/**
 * 请求List中的文件或图像字符串，文件共享
 * 
 * @author CTRL
 *
 */
@SuppressWarnings("serial")
@WebServlet("/fileServlet")
public class FileServlet extends HttpServlet {
	// 系统重启时读取uploadFiles下的已上传文件
	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getServletContext().getRealPath("") + "/uploadFiles";
		File file = new File(path);
		for (File f : file.listFiles()) {
			String fileName = f.getName();
			String endName = fileName.substring(fileName.lastIndexOf("."));
			if (endName.equalsIgnoreCase(".jpeg") || endName.equalsIgnoreCase(".jpg")
					|| endName.equalsIgnoreCase(".bmp") || endName.equalsIgnoreCase(".png")
					|| endName.equalsIgnoreCase(".gif")) {
				SessionDataUtils.imgList.add("<img src='uploadFiles/" + fileName + "'/>");
			} else {
				SessionDataUtils.fileList.add("<a href=uploadFiles/" + fileName + ">" + fileName + "</a>");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	// 文件模糊查询
	public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> list = new ArrayList<String>();
		String name = req.getParameter("name");
		for (String s : SessionDataUtils.fileList) {
			if (s.substring(s.indexOf(">"), s.indexOf("</a>")).contains(name)) {
				list.add(s);
			}
		}
		LoggerWebChat.logger.info(list.size() + "......................");
		req.setAttribute("show", "file");
		req.setAttribute("list", list);
		req.setAttribute("page", 0);
		req.getRequestDispatcher("file.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		if (null != name && !"".equals(name)) {
			query(req, resp);
			return;
		}
		String show = req.getParameter("show");
		if (null == show || "".equals(show)) {
			show = "img";
		}
		int page;
		try {
			page = Integer.parseInt(req.getParameter("page"));
			if (page < 0) {
				page = 0;
			}
		} catch (NumberFormatException e1) {
			page = 0;
		}
		// page副本，为使page大小不变
		int page2 = page;
		List<String> list = new ArrayList<String>();
		// 判断是否取imglist中的字符串，若是取十个
		if ("img".equals(show)) {
			for (int i = 0; i < 10 && SessionDataUtils.imgList.size() > page2; ++i) {
				list.add(SessionDataUtils.imgList.get(page2++));
			}
		}
		// 判断是否取filelist中的字符串，若是取十个
		else if ("file".equals(show)) {
			for (int i = 0; i < 10 && SessionDataUtils.fileList.size() > page2; ++i) {
				list.add(SessionDataUtils.fileList.get(page2++));
			}
		}
		req.setAttribute("show", show);
		req.setAttribute("list", list);
		req.setAttribute("page", page);
		req.getRequestDispatcher("file.jsp").forward(req, resp);
	}
}
