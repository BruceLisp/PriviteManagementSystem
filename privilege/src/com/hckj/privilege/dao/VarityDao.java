package com.hckj.privilege.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.hckj.privilege.model.Varity;
import com.hckj.privilege.utils.DbUtils;

public class VarityDao {
	
	QueryRunner queryRunner = new QueryRunner();
	//查找所有一级分类
	public List<Varity> queryAllPVarity() throws SQLException {
		String sql = "select * from varity where id < 100";
		List<Varity> varities = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Varity>(Varity.class));
		return varities;
	}
	//根据一级分类的id,查找二级分类
	public List<Varity> queryVarityByPVarityId(int id) throws SQLException {
		String sql = "select * from varity where pid = ?";
		List<Varity> varities = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Varity>(Varity.class), id);
		return varities;
	}
	//通过书籍类别id查询类别
	public String queryVarityByVid(int type) throws SQLException {
		String sql = "select * from varity where id = ?";
		Varity varity = queryRunner.query(DbUtils.getConn(), sql, new BeanHandler<Varity>(Varity.class), type);
		return varity.getName();
	}
	//查找所有类别信息。
	public List<Varity> queryAllPVarities() throws SQLException {
		String sql = "select * from varity";
		List<Varity> varities = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Varity>(Varity.class));
		return varities;
	}
	
}
