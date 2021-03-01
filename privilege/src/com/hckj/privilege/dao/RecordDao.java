package com.hckj.privilege.dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.hckj.privilege.model.Record;
import com.hckj.privilege.utils.DbUtils;

public class RecordDao {
	private QueryRunner queryRunner = new QueryRunner();
	//向数据库添加借阅记录
	public void add(int bid, int uid, String date, int period) throws SQLException {
		String sql = "insert into record(uid, bid, lenddate, status, period) values(?, ?, ?, 1, ?)";
		queryRunner.update(DbUtils.getConn(), sql, uid, bid, date, period);
	}
	//通过用户的id查询借阅记录
	public List<Record> queryAllRecordVyUid(int id) throws SQLException {
		String sql = "select * from record where uid = ?";
		List<Record> records = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Record>(Record.class), id);
		return records;
	}
	//根据用户id和图书status查询该用户的预借书籍记录。
	public List<Record> queryRecordByUidAndStatus(int uid) throws SQLException {
		String sql = "select * from record where uid = ? and (status = ? or status = ?)";
		List<Record> records = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Record>(Record.class), uid, 1, 6);		
		return records;
	}
	public void adjustPeriod(int bid, int uid, int period) throws SQLException {
		String sql = "update record set period = ? where bid = ? and uid = ? and status = 1";
		queryRunner.update(DbUtils.getConn(),sql,period,bid,uid);
	}
	//查询所有借阅记录
	public List<Record> queryAllReserve() throws SQLException {
		String sql = "select * from record where status = 1";
		List<Record> records = queryRunner.query(DbUtils.getConn(),sql,new BeanListHandler<Record>(Record.class));
		return records;
	}
	//查询所有记录
	public List<Record> queryAllRecord() throws SQLException{
		String sql = "select * from record where status = 4 or status = 2 or status = 3";
		List<Record> records = queryRunner.query(DbUtils.getConn(),sql,new BeanListHandler<Record>(Record.class));
		return records;
	}
	//模糊查询预借记录
	public List<Record> queryReserveByMess(String mess) throws SQLException {
		String sql = "select * from record where status = 2 and (id=? or bid=? or uid=? or (bid in (select id from book where name like ? )))";
		List<Record> records = queryRunner.query(DbUtils.getConn(), sql, 
				new BeanListHandler<Record>(Record.class), mess, mess, mess,"%"+mess+"%");
		System.out.println(records.size());
		return records;
	}
	//模糊查询预借记录
	public List<Record> queryRecordByMess(String mess) throws SQLException {
		String sql = "select * from record where (id=? or bid=? or uid=? or (bid in (select id from book where name like ? ))) and status = 1";
		List<Record> records = queryRunner.query(DbUtils.getConn(), sql, 
				new BeanListHandler<Record>(Record.class), mess, mess, mess,"%"+mess+"%");
		System.out.println(records.size());
		return records;
	}
}
