package com.hckj.privilege.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hckj.privilege.model.Privilege;
import com.hckj.privilege.service.api.PrivilegeService;
import com.hckj.privilege.service.impl.PrivilegeServiceImpl;
import com.hckj.privilege.utils.ServiceProxyFactory;

//监听器ServletContext对象，在ServletContext创建时（服务器启动时创建）加载权限数据
public class PrivilegeListener implements ServletContextListener{	
	private PrivilegeService privilegeService = ServiceProxyFactory.getService(new PrivilegeServiceImpl());
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			//加载顶级权限数据并关联二级权限数据
			System.out.println("权限数据初始化中...............");
			List<Privilege> topPrivileges = privilegeService.queryTopPrivilegeRelatedTwoPrivilege();
			//加载有访问路径的权限
			List<String> urls = privilegeService.queryHasUrlsPrivilege();
			//将权限数据保存到上下文对象中
			ServletContext servletContext = arg0.getServletContext();
			servletContext.setAttribute("topPrivileges", topPrivileges);
			servletContext.setAttribute("urls", urls);
			System.out.println("权限数据初始化结束..............");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
