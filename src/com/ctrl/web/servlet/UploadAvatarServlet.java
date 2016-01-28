package com.ctrl.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctrl.utils.LoggerWebChat;
import com.ctrl.vo.User;

import sun.misc.BASE64Decoder;

/**
 * 保存用户上传头像到avatar文件夹下
 * 
 * @author CTRL
 *
 */
@SuppressWarnings("serial")
@WebServlet("/uploadAvatar")
public class UploadAvatarServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession(true).getAttribute("user");
		String avatar = req.getParameter("avatar");
		avatar = avatar.substring(avatar.indexOf(",") + 1);
		generateImage(avatar, user, req);
	}

	public static boolean generateImage(String imgStr, final User user, HttpServletRequest req) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			String imgFilePath = user.getCode() + ".png";
			OutputStream out = new FileOutputStream(
					req.getSession().getServletContext().getRealPath("") + "/avatar/" + imgFilePath);
			LoggerWebChat.logger.info("头像路径 " + req.getSession().getServletContext().getRealPath(""));
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			LoggerWebChat.logger.info(e);
			return false;
		}
	}
}
