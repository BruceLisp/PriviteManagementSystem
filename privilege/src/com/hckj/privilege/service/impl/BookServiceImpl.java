package com.hckj.privilege.service.impl;

import java.util.List;

import com.hckj.privilege.dao.BookDao;
import com.hckj.privilege.dao.RecordDao;
import com.hckj.privilege.dao.UserDao;
import com.hckj.privilege.dao.VarityDao;
import com.hckj.privilege.model.Book;
import com.hckj.privilege.model.Record;
import com.hckj.privilege.model.Status;
import com.hckj.privilege.model.User;
import com.hckj.privilege.service.api.BookService;
import com.hckj.privilege.utils.CalculateDate;

//图书相关业务处理类
public class BookServiceImpl implements BookService{
	private BookDao bookDao = new BookDao();
	private VarityDao varityDao = new VarityDao();
	private RecordDao recordDao = new RecordDao();
	private UserDao userDao = new UserDao();
	@Override
	public List<Book> queryAll() throws Exception {
		return bookDao.queryAllBooks();
	}
	@Override
	public void add(Book book) throws Exception {
		int num = book.getNum();
		for(int i=1; i<=num; i++){
			bookDao.add(book);
		}
	}
	@Override
	public void delBookById(int id) throws Exception {
		bookDao.delBookById(id);
	}
	@Override
	public Book queryBookById(int id) throws Exception {
		return bookDao.queryBookById(id);
	}
	@Override
	public void modifyBookMess(Book book) throws Exception {
		bookDao.modifyBookMess(book);
	}
	@Override
	public List<Book> queryBookByMess(String mess) throws Exception {
		return bookDao.queryBookByMess("%"+mess+"%");
	}
	@Override
	public List<Book> queryBookByVar(int vid) throws Exception {
		return bookDao.queryBookByVid(vid);
	}
	@Override
	public String queryVarityByVid(int type) throws Exception {
		return varityDao.queryVarityByVid(type);
	}
	@Override
	public String queryStatusBySid(int status) throws Exception {
		return bookDao.queryStatusBySid(status);
	}
	@Override
	public void addRecord(int bid, int uid, int period) throws Exception {
		recordDao.add(bid,uid,CalculateDate.getCurrentDate(),period);
	}
	@Override
	public void changeStatusByBid(int bid,int status) throws Exception {
		bookDao.setBookStatusByBid(bid,status);
	}
	@Override
	public List<Record> queryBookByUidAndStatus(int id) throws Exception {
		List<Record> records = recordDao.queryRecordByUidAndStatus(id);
		for(Record record : records){
			Book book = bookDao.queryBookById(record.getBid());
			record.setBook(book);
			String statusName = bookDao.queryStatusByRecordId(record.getId());
			record.getBook().setStatusStr(statusName);
			int days = record.getPeriod();
			record.getBook().setPeriod(days);
		}
		return records;
	}
	//修改图书的状态
	@Override
	public void changeRecordStatusByRid(int rid, int sid) throws Exception {
		bookDao.modifyRecordStatusByRid(rid,sid);
	}
	//查询所有的借书记录，根据用户的id
	@Override
	public List<Record> queryRecordByUid(int id) throws Exception {
		List<Record> records = recordDao.queryAllRecordVyUid(id);
		for(Record record : records){
			Book book = bookDao.queryBookById(record.getBid());
			record.setBook(book);
			String statusName = bookDao.queryStatusByRecordId(record.getId());
			record.getBook().setStatusStr(statusName);
			int days = record.getPeriod();
			record.getBook().setPeriod(days);
		}
		return records;
	}
	@Override
	public void adjustRecordPeriod(int bid, int uid, int period) throws Exception {
		recordDao.adjustPeriod(bid,uid,period);
	}
	//查询数据库record表中处于预借状态的书
	@Override
	public List<Record> queryReserve() throws Exception {
		List<Record> records = recordDao.queryAllReserve();
		for(Record record : records){
			Book book = bookDao.queryBookById(record.getBid());
			record.setBook(book);
			String statusName = bookDao.queryStatusByRecordId(record.getId());
			record.getBook().setStatusStr(statusName);
			int days = record.getPeriod();
			record.getBook().setPeriod(days);
		}
		return records;
	}
	//查询所有借书记录
	@Override
	public List<Record> queryRecord() throws Exception {
		List<Record> records = recordDao.queryAllRecord();
		for(Record record : records){
			Book book = bookDao.queryBookById(record.getBid());
			record.setBook(book);
			String statusName = bookDao.queryStatusByRecordId(record.getId());
			record.getBook().setStatusStr(statusName);
			int days = record.getPeriod();
			record.getBook().setPeriod(days);
			String deadline = CalculateDate.calculate(record.getLenddate(), record.getPeriod());
			record.setDeadline(deadline);
			User user = userDao.queryUserById(record.getUid());
			record.setUname(user.getName());
		}
		return records;
	}
	//根据相关信息查询借阅记录
	@Override
	public List<Record> queryReserveByMess(String mess) throws Exception {
		List<Record> records = recordDao.queryReserveByMess(mess);
		for(Record record : records){
			Book book = bookDao.queryBookById(record.getBid());
			record.setBook(book);
			String statusName = bookDao.queryStatusByRecordId(record.getId());
			record.getBook().setStatusStr(statusName);
			int days = record.getPeriod();
			record.getBook().setPeriod(days);
		}
		return records;
	}
	//查询所有的类别信息
	@Override
	public List<Status> queryAllStatus() throws Exception {
		List<Status> status = bookDao.queryAllStatus();
		return status;
	}
	//还书服务
	@Override
	public void returnBook(int rid, int bid) throws Exception {
		//改变图书状态为 0.可借阅
		bookDao.setBookStatusByBid(bid, 0);
		//改变图书借阅记录的状态为，8.已还书
		bookDao.modifyRecordStatusByRid(rid, 8);
	}
}
