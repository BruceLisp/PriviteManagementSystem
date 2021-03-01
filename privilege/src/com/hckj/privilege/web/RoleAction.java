package com.hckj.privilege.web;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.hckj.privilege.model.Privilege;
import com.hckj.privilege.model.Role;
import com.hckj.privilege.service.api.PrivilegeService;
import com.hckj.privilege.service.api.RoleService;
import com.hckj.privilege.service.impl.PrivilegeServiceImpl;
import com.hckj.privilege.service.impl.RoleServiceImpl;
import com.hckj.privilege.utils.ServiceProxyFactory;

@WebServlet("/roleAction.action")
public class RoleAction extends BasicServlet{
	private RoleService roleService = ServiceProxyFactory.getService(new RoleServiceImpl());
	private PrivilegeService privilegeService = ServiceProxyFactory.getService(new PrivilegeServiceImpl());
	//1.查询所有数据转发至查询页面
	public void listUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//查询所有角色数据
		List<Role> list = roleService.findAll();
		//将数据存储到req对象中
		req.setAttribute("list", list);
		//将数据请求转发到页面
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/role/table.jsp").forward(req, resp);
	}
	//2.跳转至添加页面
	public void addUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/role/add.jsp").forward(req, resp);
	}
	//3.保存角色数据
	public void add(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得所有请求数据
		Map<String, String[]> maps = req.getParameterMap();
		//将所有数据映射到javabean中（反射的加强知识点，apache公司提供了一个加强内省bean包）
		Role role = new Role();
		BeanUtils.populate(role, maps);
		//将数据保存到数据
		roleService.save(role);
		//重定向到查询页面 （请求转发绝度路径不加功成名 ，重定向绝对路径加工程名 req.getContextPath获取工程名）
		resp.sendRedirect(req.getContextPath()+"/roleAction.action");
	}
	//4.删除角色信息
	public void del(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得要删除的角色id值
		int id = Integer.parseInt(req.getParameter("id"));
		//根据id删除角色信息
		roleService.delById(id);
		//重定向到查询页面 （请求转发绝度路径不加功成名 ，重定向绝对路径加工程名 req.getContextPath获取工程名）
		resp.sendRedirect(req.getContextPath()+"/roleAction.action");
	}
	//5.跳转至修改页面
	public void editUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得要删除的角色id值
		int id = Integer.parseInt(req.getParameter("id"));
		//根据id查询角色信息
		Role role = roleService.findRoleById(id);
		//将信息保存到req对象中
		req.setAttribute("role", role);
		//请求转发到修改页面
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/role/modify.jsp").forward(req, resp);
	}
	//6.修改角色信息
	public void edit(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得所有请求数据
		Map<String, String[]> maps = req.getParameterMap();
		//将所有数据映射到javabean中（反射的加强知识点，apache公司提供了一个加强内省bean包）
		Role role = new Role();
		BeanUtils.populate(role, maps);
		//将角色信息修改到数据库
		roleService.update(role);
		//重定向到查询页面 （请求转发绝度路径不加功成名 ，重定向绝对路径加工程名 req.getContextPath获取工程名）
		resp.sendRedirect(req.getContextPath()+"/roleAction.action");
	}
	//7.角色的权限维护
	public void privilegeUI(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得角色id
		int id = Integer.parseInt(req.getParameter("id"));
		//根据id查询角色信息
		Role role = roleService.findRoleById(id);
		//将角色信息保存到req中
		req.setAttribute("role", role);
		//请求转发到角色页面
		req.getRequestDispatcher("/WEB-INF/jsp/page/page/role/privilege.jsp").forward(req, resp);
	}
	//8.显示权限数据
	public void privilegeShow(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得角色id
		int roleId = Integer.parseInt(req.getParameter("id"));
		//查询所有权限数据并选中角色所拥有的的权限
		List<Privilege> list = privilegeService.queryPrivilegeAllAndCheckedRoleHasPrivileges(roleId);
		//设置响应格式:响应json编码utf-8
		resp.setContentType("application/json;charset=utf-8");
		//将集合数据转json数据
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//响应json数据
		resp.getWriter().print(json);
	}
	//9.保存角色所拥有的权限数据
	public void privilegeSave(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//获得角色id
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		//获得权限ids
		String privilegeIds = req.getParameter("privilegeIds");
		//关联角色所拥有的权限
		privilegeService.relatedRoleHasPrivileges(roleId,privilegeIds);
		//重定向到查询页面
		resp.sendRedirect(req.getContextPath()+"/roleAction.action");
	}
	
	
}
