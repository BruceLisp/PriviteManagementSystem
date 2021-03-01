package com.hckj.privilege.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hckj.privilege.model.User;
import com.hckj.privilege.utils.DbUtils;

public class UserDao {
	private QueryRunner queryRunner = new QueryRunner();
	public List<User> queryUserListLikeUserName(String name) throws Exception{
		String sql = "select * from user where name like ?";
		List<User> result = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<User>(User.class),name);
		return result;
	}
	public int addUserReturnId(User user) throws Exception{
		String sql ="insert into user(name,phone,pwd) values(?,?,?)";
		queryRunner.update(DbUtils.getConn(), sql, user.getName(),user.getPhone(),user.getPwd());
		sql = "select LAST_INSERT_ID()";
		Long result = queryRunner.query(DbUtils.getConn(), sql, new ScalarHandler<Long>());
		return result.intValue();
	}
	public void addUserAndRoleRelationship(int userId, int roleId) throws Exception{
		String sql = "insert into user_role(user_id,role_id) values(?,?)";
		queryRunner.update(DbUtils.getConn(), sql, userId,roleId);
	}
	public void delById(int id) throws Exception{
		String sql = "delete from user where id=?";
		queryRunner.update(DbUtils.getConn(), sql, id);
	}
	public User queryUserById(int id) throws Exception{
		String sql = "select * from user where id =?";
		return queryRunner.query(DbUtils.getConn(), sql, new BeanHandler<User>(User.class), id);
	}
	public void edit(User user) throws Exception{
		String sql = "update user set name=?,phone=? where id=?";
		queryRunner.update(DbUtils.getConn(), sql, user.getName(),user.getPhone(),user.getId());
	}
	public void delOldUserRelationshipRole(int userId) throws Exception {
		String sql = "delete from user_role where user_id=?";
		queryRunner.update(DbUtils.getConn(),sql,userId);
	}
	public void resetPwdByUserId(int id, String pwd) throws Exception {
		String sql = "update user set pwd=? where id=?";
		queryRunner.update(DbUtils.getConn(), sql, pwd,id);
	}
	public User queryUserByNameAndPwd(String name, String pwd)throws Exception {
		String sql = "select * from user where name =? and pwd = ? ";
		return queryRunner.query(DbUtils.getConn(), sql, new BeanHandler<User>(User.class), name,pwd);
	}
	public void updatePicUrlByUserId(String url, int userId) throws Exception{
		String sql = "update user set pic=? where id=?";
		queryRunner.update(DbUtils.getConn(), sql, url,userId);
	}
	public void updatePwdByUserId(int userId, String new_pwd) throws Exception{
		String sql = "update user set pwd=? where id=?";
		queryRunner.update(DbUtils.getConn(), sql, new_pwd,userId);
	}
}
