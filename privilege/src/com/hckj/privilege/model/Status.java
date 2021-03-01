package com.hckj.privilege.model;


//状态的javaBean描述信息
public class Status {
	//状态id
	private int id;
	//状态名字
	private String statusName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public Status(int id, String statusName) {
		this.id = id;
		this.statusName = statusName;
	}
	
	public Status() {
	}
	
}
