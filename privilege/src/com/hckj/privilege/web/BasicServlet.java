package com.hckj.privilege.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//1.获得提交参数method：指的是本次访问的方法
		String methodName = req.getParameter("method");
		if(methodName==null) {
			methodName="listUI";
		}
		//2.获得当前正在运行的servlet的字节码
		Class<? extends BasicServlet> clazz = this.getClass();	
		try {
			//3.获得正在运行类的指定方法
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//4.对象调用方法并传入请求与相应参数
			method.invoke(this, req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

