package com.hckj.privilege.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mainAction.action")
public class MainAction extends BasicServlet{
	public void mainUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/main/main.jsp").forward(req, resp);
	}
	public void leftUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/main/left.jsp").forward(req, resp);
	}
	public void rightUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/main/right.jsp").forward(req, resp);
	}
	public void topUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/main/top.jsp").forward(req, resp);
	}
	public void rightTopUI(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/WEB-INF/jsp/page/main/right_top.jsp").forward(req, resp);
	}
}
