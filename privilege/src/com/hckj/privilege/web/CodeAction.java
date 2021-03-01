package com.hckj.privilege.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hckj.privilege.utils.CodeUtils;

@WebServlet("/codeAction.action")
public class CodeAction  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CodeUtils.getWriteCode(req, resp);
	}
}
