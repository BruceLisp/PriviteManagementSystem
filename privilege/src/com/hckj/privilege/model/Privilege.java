package com.hckj.privilege.model;

import java.util.List;

//权限的javaBean描述信息
public class Privilege {
	/** id=>权限id*/
	private Integer id;
	/** url=>权限路径*/
	private String url;
	/** name=>权限名称*/
	private String name;
	/** pId=>权限的父id*/
	private Integer pId;
	/** parent=>当前权限对应一个父权限*/
	private Privilege parent;
	/** children=> 当前权限对应多个子权限*/
	private List<Privilege> children;
	
	
	private boolean open = true;
	private boolean checked;
	
	
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public List<Privilege> getChildren() {
		return children;
	}
	public void setChildren(List<Privilege> children) {
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	@Override
	public String toString() {
		return "Privilege [id=" + id + ", url=" + url + ", name=" + name + ", pId=" + pId + "]";
	}
	public Privilege(Integer id, String url, String name, Integer pId) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.pId = pId;
	}
	public Privilege() {
	}
	
}
