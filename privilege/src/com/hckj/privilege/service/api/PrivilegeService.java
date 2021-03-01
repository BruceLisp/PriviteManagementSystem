package com.hckj.privilege.service.api;

import java.util.List;

import com.hckj.privilege.model.Privilege;

//处理权限业务类
public interface PrivilegeService {
	//1.添加权限数据
	public void addPrivilegeData(Privilege privilege) throws Exception;
	//2.查询所有权限数据并选中角色所拥有的的权限
	public List<Privilege> queryPrivilegeAllAndCheckedRoleHasPrivileges(int roleId) throws Exception;
	//3.关联角色所拥有的权限
	public void relatedRoleHasPrivileges(int roleId, String privilegeIds) throws Exception;
	//4.加载顶级权限数据并关联二级权限数据
	public List<Privilege> queryTopPrivilegeRelatedTwoPrivilege() throws Exception;
	//5.查询有访问路径的权限路径
	public List<String> queryHasUrlsPrivilege() throws Exception;
} 
