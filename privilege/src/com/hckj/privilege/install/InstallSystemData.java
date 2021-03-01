package com.hckj.privilege.install;

import com.hckj.privilege.model.Privilege;
import com.hckj.privilege.model.User;
import com.hckj.privilege.service.api.PrivilegeService;
import com.hckj.privilege.service.api.UserService;
import com.hckj.privilege.service.impl.PrivilegeServiceImpl;
import com.hckj.privilege.service.impl.UserServiceImpl;
import com.hckj.privilege.utils.MD5Util;
import com.hckj.privilege.utils.ServiceProxyFactory;

//安装系统的基础设施数据
public class InstallSystemData {
	private static UserService userService = ServiceProxyFactory.getService(new UserServiceImpl());
	private static PrivilegeService privilegeService = ServiceProxyFactory.getService(new PrivilegeServiceImpl());
	public static void main(String[] args) throws Exception{
		//1.添加超级管理员
		/*User user = new User();
		user.setName("admin");
		user.setPwd("123456");
		userService.addUserRelatedRole(user, null);*/
		//2.初始化权限数据
		//2.1初始化顶级权限数据
		Privilege privilege = new Privilege(1, null, "系统管理", null);
		privilegeService.addPrivilegeData(privilege);
		//2.2初始化二级权限数据
		Privilege twoPrivilege = new Privilege(2, "/userAction.action", "用户管理", 1);
		Privilege twoPrivilege2 = new Privilege(3, "/roleAction.action", "角色管理", 1);
		privilegeService.addPrivilegeData(twoPrivilege);
		privilegeService.addPrivilegeData(twoPrivilege2);
		//2.3初始化三级权限数据
		Privilege threePrivilege = new Privilege(4,"/userAction.action?method=list", "用户查询", 2);
		Privilege threePrivilege2 = new Privilege(5,"/userAction.action?method=add", "用户添加", 2);
		Privilege threePrivilege3 = new Privilege(6,"/userAction.action?method=edit", "用户修改", 2);
		Privilege threePrivilege4 = new Privilege(7,"/userAction.action?method=del", "用户删除", 2);
		Privilege threePrivilege5 = new Privilege(8,"/userAction.action?method=resetPwd", "重置密码", 2);
		Privilege threePrivilege6 = new Privilege(9,"/roleAction.action?method=list", "角色查询", 3);
		Privilege threePrivilege7 = new Privilege(10,"/roleAction.action?method=add", "角色添加", 3);
		Privilege threePrivilege8 = new Privilege(11,"/roleAction.action?method=edit", "角色修改", 3);
		Privilege threePrivilege9 = new Privilege(12,"/roleAction.action?method=del", "角色删除", 3);
		Privilege threePrivilege10 = new Privilege(13,"/roleAction.action?method=privilege", "权限管理", 3);
		privilegeService.addPrivilegeData(threePrivilege);
		privilegeService.addPrivilegeData(threePrivilege2);
		privilegeService.addPrivilegeData(threePrivilege3);
		privilegeService.addPrivilegeData(threePrivilege4);
		privilegeService.addPrivilegeData(threePrivilege5);
		privilegeService.addPrivilegeData(threePrivilege6);
		privilegeService.addPrivilegeData(threePrivilege7);
		privilegeService.addPrivilegeData(threePrivilege8);
		privilegeService.addPrivilegeData(threePrivilege9);
		privilegeService.addPrivilegeData(threePrivilege10);
	}
}
