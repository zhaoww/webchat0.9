package com.ctrl.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.ctrl.utils.LoggerWebChat;
import com.ctrl.utils.SessionDataUtils;

/**
 * 保存用户上传文件到uploadFiles下
 * @author CTRL
 *
 */
@WebServlet(name = "upload", urlPatterns = { "/upload" })
public class UploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8;");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String savePath = this.getServletConfig().getServletContext().getRealPath("") + "/uploadFiles";
		File file = new File(savePath);
		String fileFullPath = "";
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		try {
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("UTF-8");
			// 获取多个上传文件
			List fileList = upload.parseRequest(request);
			// 遍历上传文件写入磁盘
			Iterator it = fileList.iterator();
			while (it.hasNext()) {
				Object obit = it.next();
				if (obit instanceof DiskFileItem) {
					DiskFileItem item = (DiskFileItem) obit;
					// 如果item是文件上传表单域
					// 获得文件名及路径
					String fileName = item.getName();
					if (fileName != null) {
						fileFullPath = savePath + "/" + fileName;
						BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流
						BufferedOutputStream outStream = new BufferedOutputStream(
								new FileOutputStream(new File(fileFullPath)));// 获得文件输出流
						Streams.copy(in, outStream, true);
						String filePath;
						int end = fileName.lastIndexOf(".");
						String endStr = fileName.substring(end);
						if (endStr.equalsIgnoreCase(".jpg") || endStr.equalsIgnoreCase(".jpeg")
								|| endStr.equalsIgnoreCase(".bmp") || endStr.equalsIgnoreCase(".png")
								|| endStr.equalsIgnoreCase(".gif")) {
							filePath = "<img  src=uploadFiles/" + fileName + " alt=''/>";
							SessionDataUtils.imgList.add(filePath);
						} else {
							filePath = "<a href=uploadFiles/" + fileName + " target=_blank >" + fileName + "</a>";
							SessionDataUtils.fileList.add(filePath);
						}
						response.getWriter().write(filePath);
					}
				}
			}
		} catch (org.apache.commons.fileupload.FileUploadException ex) {
			ex.printStackTrace();
			LoggerWebChat.logger.info("用户停止上传");
			return;
		}
		out.flush();
		out.close();
	}
}
