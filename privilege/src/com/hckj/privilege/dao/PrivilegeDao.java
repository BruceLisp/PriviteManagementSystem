package com.hckj.privilege.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.hckj.privilege.model.Privilege;
import com.hckj.privilege.utils.DbUtils;

public class PrivilegeDao {
	private QueryRunner queryRunner = new QueryRunner();
	public void add(Privilege privilege) throws Exception{
		String sql = "insert into privilege(id,url,name,pId) values(?,?,?,?)";
		queryRunner.update(DbUtils.getConn(), sql, privilege.getId(), privilege.getUrl(),privilege.getName(),privilege.getpId());
	}
	public List<Privilege> queryPrivilegeAll() throws Exception{
		String sql = "select * from privilege";
		List<Privilege> result = queryRunner.query(DbUtils.getConn(), sql,new BeanListHandler<Privilege>(Privilege.class));
		return result;
	}
	public List<Privilege> queryPrivilegesByRoleId(int roleId) throws Exception{
		String sql = "select p.* from role_privilege rp , privilege p where rp.privilege_id=p.id and rp.role_id=?";
		List<Privilege> result = queryRunner.query(DbUtils.getConn(), sql,new BeanListHandler<Privilege>(Privilege.class),roleId);
		return result;
	}
	public void clearRoleRelatedPrivilegeByRole(int roleId) throws Exception{
		String sql = "delete from role_privilege where role_id=?";
		queryRunner.update(DbUtils.getConn(),sql, roleId);
		
	}
	public void addRoleRelatedPrivilegeByRoleIdAndPrivilegeId(int roleId, int privilegeId) throws Exception{
		String sql = "insert into role_privilege(role_id,privilege_id) values(?,?)";
		queryRunner.update(DbUtils.getConn(),sql, roleId,privilegeId);
	}
	public List<Privilege> queryTopPrivilegeAll() throws Exception{
		String sql = "select * from privilege where pId is null";
		List<Privilege> result = queryRunner.query(DbUtils.getConn(), sql,new BeanListHandler<Privilege>(Privilege.class));
		return result;
	}
	public List<Privilege> queryTwoPrivilegeByTopId(int topId) throws Exception {
		String sql = "select * from privilege where pId = ?";
		List<Privilege> result = queryRunner.query(DbUtils.getConn(), sql,new BeanListHandler<Privilege>(Privilege.class),topId);
		return result;
	}
	public List<String> queryHasUrlsPrivilege() throws Exception {
		String sql = "select url from privilege where not url is null";
		List<String> result = queryRunner.query(DbUtils.getConn(), sql, new ColumnListHandler<String>());
		return result;
	}

}
