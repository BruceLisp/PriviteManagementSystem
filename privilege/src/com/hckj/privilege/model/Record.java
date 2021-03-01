package com.hckj.privilege.model;


//借书记录
public class Record {
	//记录编号id
	private int id;
	//图书id
	private int bid;
	//借书用户id
	private int uid;
	//借书人name
	private String uname;
	//图书状态
	private int status;
	//借书日期
	private String lenddate;
	//借书时间
	private int period;
	//借书截止日期
	private String deadline;
	//书
	private Book book;
	
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLenddate() {
		return lenddate;
	}
	public void setLenddate(String lenddate) {
		this.lenddate = lenddate;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public Record(int id, int bid, int uid, int status, String lenddate, int period, String deadline) {
		this.id = id;
		this.bid = bid;
		this.uid = uid;
		this.status = status;
		this.lenddate = lenddate;
		this.period = period;
		this.deadline = deadline;
	}
	public Record() {
	}
	
	
}
