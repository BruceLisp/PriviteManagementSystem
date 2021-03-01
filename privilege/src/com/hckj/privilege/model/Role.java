package com.hckj.privilege.model;

import java.util.List;

//角色的javaBean描述信息
public class Role {
	/** id=> 角色id*/
	private int id;
	/** id=> 角色名称*/
	private String name;
	/** id=> 角色的职责描述信息*/
	private String description;
	/** users=> 一个角色对象对用多个用户信息*/
	private List<User> users;
	/** privileges=> 一个角色可以分配多个权限信息*/
	private List<Privilege> privileges;
	/** checked=> 代表角色的选中状态*/
	private boolean checked;
	public List<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
