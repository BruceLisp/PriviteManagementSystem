package com.hckj.privilege.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理service层对象，生成具有事务的service层对象
public class ServiceProxyFactory {
	/**
	 * 
	 * @param target 目标类 指的是没有事务的service对象
	 * @return 代理类  返回的是代理后有事务的service对象
	 */
	public static <T> T  getService(T target) {
		return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),new MyHandler(target));
	}
	static class MyHandler implements InvocationHandler{
		private Object obj;
		
		public MyHandler(Object obj) {
			this.obj = obj;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			//1.开启事务
			DbUtils.start();
			Object result = null;
			try {
				result = method.invoke(obj, args);
				//2.提交事务
				DbUtils.commit();
			} catch (Exception e) {
				//3.回滚事务
				DbUtils.rollback();
				e.printStackTrace();
			}
			return result;
		}
	}
}
