package com.hckj.privilege.model;

import java.util.List;

//用户的javaBean描述
public class User {
	/** id=> 用户id  */
	private int id;
	/** name=> 账户名  */
	private String name;
	/** pwd=> 密码  */
	private String pwd;
	/** phone=> 手机号  */
	private String phone;
	/** pic=> 用户照片  */
	private String pic;
	/** status=> 用户状态 0代表启用 1代表禁用  */
	private int status;
	/** roles=> 一个用户对应多个角色信息*/
	private List<Role> roles;
	
	//根据权限名称判断用户是否拥有此权限
	public boolean hasPrivilegeByName(String name) {
		//如果是超级管理员登录直接拥有所有权限不用控制
		if("admin".equals(this.name)) {
			return true;
		} else {
			//遍历当前用户的所有角色，在遍历角色的所有权限
			boolean hasPrivilege = false;
			if(roles!=null) {
				for(Role role : roles) {
					List<Privilege> privileges = role.getPrivileges();
					for(Privilege privilege : privileges) {
						if(privilege.getName().equals(name)) {
							hasPrivilege = true;
						}
					}
				}
			}else {
				return hasPrivilege;
			}
			return hasPrivilege;
		}
		
	}
	private List<String> urls;
	
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	public boolean hasPrivilegeByUrl(String url) {
		url= url.replace("UI", "");
		//如果登录的账户名是admin直接拥有所有的权限路径
		if(name.equals("admin")) {
			return true;
		}else {
			//如果url不在权限控制路径的集合中，证明当前路径不受权限约束，可以访问
			if(!urls.contains(url)) {
				return true;
			} else {
				// url路径受权限路径控制，查看当前用户的所有角色的所有权限的路径是否拥有
				boolean hasPrivilege = false;
				if(roles!=null) {
					for(Role role : roles) {
						List<Privilege> privileges = role.getPrivileges();
						for(Privilege privilege : privileges) {
							if(privilege!=null && privilege.getUrl()!=null) {
								if(privilege.getUrl().equals(url)) {
									hasPrivilege = true;
								}
							}
						}
					}
				}
				return hasPrivilege;
			}
		}
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", phone=" + phone + ", pic=" + pic + ", status="
				+ status + "]";
	}
	
}
