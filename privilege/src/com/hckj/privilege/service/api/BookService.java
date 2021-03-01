package com.hckj.privilege.service.api;

import java.util.List;

import com.hckj.privilege.model.Book;
import com.hckj.privilege.model.Record;
import com.hckj.privilege.model.Status;
import com.hckj.privilege.model.Varity;

//图书业务处理接口
public interface BookService {

	List<Book> queryAll()throws Exception;

	void add(Book book) throws Exception;

	void delBookById(int id) throws Exception;

	Book queryBookById(int id) throws Exception;

	void modifyBookMess(Book book) throws Exception;

	List<Book> queryBookByMess(String mess) throws Exception;

	List<Book> queryBookByVar(int vid) throws Exception;

	String queryVarityByVid(int type) throws Exception;

	String queryStatusBySid(int status) throws Exception;

	void addRecord(int bid, int uid, int period) throws Exception;

	void changeStatusByBid(int bid,int status) throws Exception;

	List<Record> queryBookByUidAndStatus(int id) throws Exception;

	void changeRecordStatusByRid(int rid, int sid) throws Exception;

	List<Record> queryRecordByUid(int id) throws Exception;

	void adjustRecordPeriod(int bid, int uid, int period) throws Exception;

	List<Record> queryReserve() throws Exception;

	List<Record> queryRecord() throws Exception;

	List<Record> queryReserveByMess(String mess) throws Exception;

	List<Status> queryAllStatus() throws Exception;

	void returnBook(int rid, int bid) throws Exception;
	
}
