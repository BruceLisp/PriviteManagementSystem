package com.hckj.privilege.model;

import java.util.List;

//分类的javaBean描述
public class Varity {
	//图书类别的id
	private int id;
	//图书类别的名字
	private String name;
	//图书类别的父类的id
	private int pid;
	//图书子类的集合
	private List<Varity> children;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<Varity> getChildren() {
		return children;
	}
	public void setChildren(List<Varity> chrildVarity) {
		this.children = chrildVarity;
	}
	
	public Varity() {
	}
	public Varity(int id, String name, int pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
}
