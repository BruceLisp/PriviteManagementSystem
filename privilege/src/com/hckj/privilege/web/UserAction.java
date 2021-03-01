package com.hckj.privilege.web;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.hckj.privilege.model.Role;
import com.hckj.privilege.model.User;
import com.hckj.privilege.service.api.RoleService;
import com.hckj.privilege.service.api.UserService;
import com.hckj.privilege.service.impl.RoleServiceImpl;
import com.hckj.privilege.service.impl.UserServiceImpl;
import com.hckj.privilege.utils.ServiceProxyFactory;

import javax.servlet.http.HttpServletRequest;

@WebServlet("/userAction.action")
public class UserAction extends BasicServlet{
	private UserService userService = ServiceProxyFactory.getService(new UserServiceImpl());
	private RoleService roleService = ServiceProxyFactory.getService(new RoleServiceImpl());
	//1.跳转至查询页面
	public void listUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得查询条件
		String name = req.getParameter("name");
		if(name==null || name.equals("") || name.equals("null")) {
			name = "%%";
		} else {
			name="%"+name+"%";
		}
		//根据条件查询用户信息并关联用户所属的角色信息
		List<User> list = userService.queryUserRelatedRoleByLikeUserName(name);
		//将信息保存到req属性中
		req.setAttribute("list", list);
		//请求转发到查询页面
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/user/table.jsp").forward(req, resp);
	}
	//2.跳转至添加页面
	public void addUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//查询所有角色信息
		List<Role> list = roleService.findAll();
		//将数据存储到req属性中
		req.setAttribute("list", list);
		//请求转发到查询页面
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/user/add.jsp").forward(req, resp);
	}
	//3.保存用户信息并关联角色信息
	public void add(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得所有表单数据
		Map<String, String[]> maps = req.getParameterMap();
		//将表单数据映射到javabean中
		User user = new User();
		BeanUtils.populate(user, maps);
		//获得角色的id值
		String[] roleIds = req.getParameterValues("roleId");
		//将用户信息保存到数据库并维护用户与角色的外键关系
		userService.addUserRelatedRole(user,roleIds);
		//重定向到查询界面
		resp.sendRedirect(req.getContextPath()+"/userAction.action");
	}
	//4.根据用户id删除用户信息
	public void del(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得要删除的id值
		int id = Integer.parseInt(req.getParameter("id"));
		//根据id删除用户信息
		userService.delById(id);
		//重定向到查询界面
		resp.sendRedirect(req.getContextPath()+"/userAction.action");
	} 
	//5.跳转至用户修改界面
	public void editUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得要修改的用户id值
		int id = Integer.parseInt(req.getParameter("id"));
		//根据id查询用户信息
		User user = userService.queryUserById(id);
		//查询所有角色信息,并标记user用户所拥有的角色信息对象的checked属性为true
		List<Role> roles = roleService.queryRoleListAndCheckedUserHasRole(id);
		//存取用户信息和所有roles信息
		req.setAttribute("user", user);
		req.setAttribute("roles", roles);
		//携带数据请求转发到修改页面
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/user/modify.jsp").forward(req, resp);
	}
	//6.修改用户信息，并维护用户和角色的关系
	public void edit(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得所有表单数据
		Map<String, String[]> maps = req.getParameterMap();
		//将表单数据映射到javabean中
		User user = new User();
		BeanUtils.populate(user, maps);
		//获得角色的id值
		String[] roleIds = req.getParameterValues("roleId");
		//修改用户信息并关联用户与角色的关系
		userService.editUserRelatedRole(user,roleIds);
		//重定向到查询界面
		resp.sendRedirect(req.getContextPath()+"/userAction.action");
	}
	//7.重置用户密码
	public void resetPwd(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得用户id值
		int id = Integer.parseInt(req.getParameter("id"));
		//根据id重置密码1234
		userService.resetPwd(id);
		//重定向到查询界面
		resp.sendRedirect(req.getContextPath()+"/userAction.action");
	}
	//8.登录
	public void login(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得用户名和密码以及验证码
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String code = req.getParameter("code");
		//获得服务器随机的验证码
		String code2 = (String)req.getSession().getAttribute("code");
		//判断验证码输入是否正确(忽略大小写比较)
		if(code2.equalsIgnoreCase(code)) {
			//验证码输入正确继续判断用户密码是否输入正确,如果登录正确返回用户信息，并关联权限数据
			User user = userService.loginRelatedHasPrivileges(name,pwd);
			if(user!=null) {
				//登录成功将服务器上存储的权限控制的路径存储登录的用户属性中
				List<String> urls = (List<String>)req.getServletContext().getAttribute("urls");
				user.setUrls(urls);
				//登录成功,将用户信息保存到会话中
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", user);
				//重定向到后台首页
				resp.sendRedirect(req.getContextPath()+"/mainAction.action?method=mainUI");
			}else {
				req.setAttribute("error", "用户名或者密码输入有误");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("error", "验证码输入有误");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
	//9.安全退出
	public void exit(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		//让会话对象失效
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/login.jsp");
	}
	
}
