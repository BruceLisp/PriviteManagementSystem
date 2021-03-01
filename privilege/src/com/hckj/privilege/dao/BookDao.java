package com.hckj.privilege.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hckj.privilege.model.Book;
import com.hckj.privilege.model.Record;
import com.hckj.privilege.model.Status;
import com.hckj.privilege.utils.DbUtils;

public class BookDao {
	private QueryRunner queryRunner = new QueryRunner();
	//从数据库获得所有图书信息
	public List<Book> queryAllBooks() throws SQLException {
		String sql = "select * from Book";
		List<Book> result = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Book>(Book.class));
		return result;
	}
	//添加书籍数据
	public void add(Book book) throws SQLException{
		String sql = "insert into book (name, author, productor, type, indate, price) values( ?, ?, ?, ?, ?, ?)";
		queryRunner.update(DbUtils.getConn(),sql,book.getName(),book.getAuthor(),
				book.getProductor(),book.getType(),book.getIndate(),book.getPrice());
	}
	//删除书籍数据
	public void delBookById(int id) throws SQLException {
		String sql = "delete from book where id = ?";
		queryRunner.update(DbUtils.getConn(), sql, id);
	}
	//查询通过id查询书籍
	public Book queryBookById(int id) throws SQLException {
		String sql = "select * from book where id = ?";
		Book book = queryRunner.query(DbUtils.getConn(), sql, new BeanHandler<Book>(Book.class), id);
		return book;
	}
	//修改书籍信息
	public void modifyBookMess(Book book) throws SQLException {
		String sql = "update book set name=?, author=?, productor=?, type=?, price=?, status=? where id = ?";
		queryRunner.update(DbUtils.getConn(),sql,book.getName(),book.getAuthor(),
				book.getProductor(),book.getType(),book.getPrice(),book.getStatus(),book.getId());
	}
	//通过书籍信息模糊查询书籍
	public List<Book> queryBookByMess(String mess) throws SQLException {
		String sql = "select * from book where id like ? or author like ? or name like ? or productor like ?";
		List<Book> books = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Book>(Book.class), mess, mess, mess, mess);
		return books;
	}
	//通过类别id查询书籍
	public List<Book> queryBookByVid(int vid) throws SQLException {
		String sql;
		String str;
		if(vid<100){
			str = vid+"%";
			sql = "select * from book where type like ?";
		}else{
			str = vid+"";
			sql = "select * from book where type = ?";
		}
		List<Book> books = queryRunner.query(DbUtils.getConn(), sql, new BeanListHandler<Book>(Book.class), str);
		return books;
	}
	//通过书籍状态id查询状态
	public String queryStatusBySid(int status) throws SQLException {
		String sql = "select * from status where id = ?";
		Status result = queryRunner.query(DbUtils.getConn(), sql, new BeanHandler<Status>(Status.class), status);
		return result.getStatusName();
	}
	//将图书状态设置为被预借状态
	public void setBookStatusByBid(int bid, int status) throws SQLException {
		String sql = "update book set status = ? where id = ?";
		queryRunner.update(DbUtils.getConn(), sql, status, bid);
	}
	//修改record的图书的状态为取消预借
	public void modifyRecordStatusByRid(int rid, int sid) throws SQLException {
		String sql = "update record set status = ? where id = ?";
		queryRunner.update(DbUtils.getConn(), sql, sid, rid);
	}
	//根据图书借阅记录查询图书的状态码，再查询状态信息
	public String queryStatusByRecordId(int id) throws SQLException {
		String sql = "select * from status where id in (select status from record where id = ?)";
		Status status = queryRunner.query(DbUtils.getConn(),sql,new BeanHandler<Status>(Status.class),id);
		return status.getStatusName();
	}
	//查询数据库status表中所有信息
	public List<Status> queryAllStatus() throws SQLException{
		String sql = "select * from status";
		List<Status> status = queryRunner.query(DbUtils.getConn(),sql,new BeanListHandler<Status>(Status.class));
		return status;
	}
	
}
