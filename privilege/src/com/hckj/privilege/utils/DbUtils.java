package com.hckj.privilege.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

//连接池与事务相关操作的封装
public class DbUtils {
	//1.定义数据源的属性
	private static DataSource dataSource;
	//2.加载配置文件
	private static Properties properties = new Properties();
	//3.将连接与本地线程绑定
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	//4.在加载类的时候加载数据源
	static {
		try {
			properties.load(DbUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	//5.提供获取数据源的方法
	public static DataSource getDataSource() {
		return dataSource;
	}
	//6.获得连接的方法
	public static Connection getConn() {
		//6.1从本地线程中取出连接
		Connection connection = threadLocal.get();
		try {
			if(connection==null) {
				//6.2.当本地线程没有绑定连接时，从数据源取出一个连接返回，并将当前连接绑定到本地线程
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	//7.封装开启事务
	public static void start() throws SQLException {
		Connection conn = getConn();
		conn.setAutoCommit(false);
	}
	//8.提交事务
	public static void commit() throws Exception{
		Connection conn = getConn();
		conn.commit();
	}
	//9.回滚事务
	public static void rollback() throws Exception{
		Connection conn = getConn();
		conn.rollback();
	}
}

