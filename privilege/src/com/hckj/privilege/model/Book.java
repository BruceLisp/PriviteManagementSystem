package com.hckj.privilege.model;

//图书的javaBean描述信息
public class Book {
	//图书id
	private int id;
	//图书名字
	private String name;
	//图书出版社
	private String productor;
	//图书作者
	private String author;
	//图书的购入时间
	private String indate;
	//图书状态id
	private int status;
	//图书的价格
	private float price;
	//图书类别id
	private int type;
	//图书状态
	private String statusStr;
	//图书类别
	private String typeStr;
	//数量
	private int num;
	//书籍借阅时长
	private int period;
	
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
	public Book() {
	}
	public Book(String name, int id, String productor, String author, int type) {
		super();
		this.name = name;
		this.id = id;
		this.productor = productor;
		this.author = author;
		this.type = type;
	}
	//通过图书的类别id找到对应的类别
	public String queryTypeByTid(int id) {
		
		return null;
	}
	//通过图书的状态id找到对应的图书状态
	public String queryStatusBySid(int id) {
		
		return null;
	}
	
	
}
