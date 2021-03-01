package com.hckj.privilege.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hckj.privilege.model.Role;
import com.hckj.privilege.utils.DbUtils;

public class RoleDao {
	private QueryRunner queryRunner = new QueryRunner();
	public List<Role> findAll() throws SQLException {
		String sql = "select * from role";
		List<Role> result = queryRunner.query(DbUtils.getConn(), sql ,new BeanListHandler<Role>(Role.class));
		return result;
	}
	public void add(Role role) throws Exception {
		String sql = "insert into role(name,description) values(?,?)";
		queryRunner.update(DbUtils.getConn(), sql, role.getName(),role.getDescription());
	}
	public void delById(int id) throws Exception {
		String sql = "delete from role where id=?";
		queryRunner.update(DbUtils.getConn(), sql, id);
	}
	public Role findRoleById(int id) throws SQLException {
		String sql = "select * from role where id=?";
		Role role = queryRunner.query(DbUtils.getConn(), sql,new BeanHandler<Role>(Role.class), id);
		return role;
	}
	public void update(Role role) throws Exception {
		String sql = "update role set name=?,description=? where id=?";
		queryRunner.update(DbUtils.getConn(), sql, role.getName(),role.getDescription(),role.getId());
	}
	public List<Role> queryRoleListByUserId(int userId)throws Exception {
		String sql = "select r.* from user_role ur , role r where ur.role_id=r.id and ur.user_id=?";
		List<Role> result = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Role>(Role.class), userId);
		return result;
	}

}
