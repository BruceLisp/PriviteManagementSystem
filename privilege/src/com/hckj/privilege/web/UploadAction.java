package com.hckj.privilege.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hckj.privilege.utils.UploadUtils;
@WebServlet("/uploadAction.action")
public class UploadAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.上传图片并返回服务器的访问路径
		String data = UploadUtils.uploadFile(req, "/temp", "/upload");
		resp.getWriter().print(data);
	}
}
