package com.hckj.privilege.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hckj.privilege.model.User;
import com.hckj.privilege.service.api.UserService;
import com.hckj.privilege.service.impl.UserServiceImpl;
import com.hckj.privilege.utils.ServiceProxyFactory;

@WebServlet("/personAction.action")
public class PersonAction extends BasicServlet{
	private UserService userService = ServiceProxyFactory.getService(new UserServiceImpl());
	//1.跳转至个人信息查看页面
	public void personMessageUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//转发至个人查看也卖弄
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/person/person_mes.jsp").forward(req, resp);
	}
	//2.保存个人信息的照片路径
	public void personMessageSave(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获取登录的用户信息
		User user = (User)req.getSession().getAttribute("loginUser");
		System.out.println(user);
		int userId = user.getId();
		//获取图片的访问路径
		String url = req.getParameter("url");
		//修改会话中user对象的图片路径
		user.setPic(url);
		//修改数据库中的图片路径
		userService.updatePicUrlByUserId(url,userId);
		resp.sendRedirect(req.getContextPath()+"/personAction.action?method=personMessageUI");
	}
	//3.跳转至密码修改页面
	public void personUpdatePasswordUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/person/update_password.jsp").forward(req, resp);
	}
	//4.异步请求查看密码是否正确
	public void queryPwdIsTrue(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		//获得密码和用户名
		String pwd = req.getParameter("pwd");
		User serssionUser = (User)req.getSession().getAttribute("loginUser");
		String name = serssionUser.getName();
		User user = userService.loginRelatedHasPrivileges(name, pwd);
		if(user!=null) {
			resp.getWriter().print(1);
		} else {
			resp.getWriter().print(0);
		}
	}
	//5.修改个人密码
	public void updateloginUserPwd(HttpServletRequest req,HttpServletResponse resp)throws Exception{
		String new_pwd =req.getParameter("new_pwd");
		User serssionUser = (User)req.getSession().getAttribute("loginUser");
		int userId = serssionUser.getId();
		userService.updatePwdByUserId(userId,new_pwd);
		resp.sendRedirect(req.getContextPath()+"/personAction.action?method=personMessageUI");
	}
}
