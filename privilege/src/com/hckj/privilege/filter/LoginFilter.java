package com.hckj.privilege.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hckj.privilege.model.User;
@WebFilter("/*")
public class LoginFilter implements Filter{

	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//转换对象
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse resp = (HttpServletResponse)arg1;
		req.setCharacterEncoding("utf-8");
		//获得资源标识符
		String uri = req.getRequestURI();
		//将uri的路径替换为满足判断是否是权限路径的路径
		String priUrl = uri.replace(req.getContextPath(), "");
		String method = req.getParameter("method");
		if(method!=null && !method.equals("")) {
			priUrl = priUrl+"?method="+method;
		}
		priUrl = priUrl.replace("UI", "");
		//获得权限集合控制的urls
		ServletContext servletContext = req.getServletContext();
		List<String> urls = (List<String>)servletContext.getAttribute("urls");
		//判断priUrl是否受权限控制
		if(urls.contains(priUrl) || priUrl.startsWith("/mainAction")) {
			//从会话中取出用户数据，判断是否登录
			Object obj = req.getSession().getAttribute("loginUser");
			//没有登录 直接拦截到登录页面
			if(obj==null) {
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
			} else {
				//登录后看看是否拥有路径的访问权限，有放行，没有踢到权限不足页面
				User user = (User)obj;
				if(user.hasPrivilegeByUrl(priUrl)) {
					arg2.doFilter(req, resp);
				} else {
					resp.sendRedirect(req.getContextPath()+"/noPrivilege.jsp");
				}
			}
		} else {
			//放行
			arg2.doFilter(req, resp);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	@Override
	public void destroy() {
		
	}


}
