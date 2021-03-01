package com.hckj.privilege.service.impl;

import java.util.List;

import com.hckj.privilege.dao.PrivilegeDao;
import com.hckj.privilege.dao.RoleDao;
import com.hckj.privilege.dao.UserDao;
import com.hckj.privilege.model.Privilege;
import com.hckj.privilege.model.Role;
import com.hckj.privilege.model.User;
import com.hckj.privilege.service.api.UserService;
import com.hckj.privilege.utils.MD5Util;

//用户业务处理类
public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDao();
	private RoleDao roleDao = new RoleDao();
	private PrivilegeDao privilegeDao = new PrivilegeDao();
	@Override //根据用户名字模糊查询用户信息并关联角色信息
	public List<User> queryUserRelatedRoleByLikeUserName(String name) throws Exception {
		//根据用户名模糊查询用户信息
		List<User> users = userDao.queryUserListLikeUserName(name);
		//遍历用户信息 并映射用户对应的角色信息
		for(User user : users) {
			int userId = user.getId();
			//根据用户Id查询角色信息
			List<Role> roles = roleDao.queryRoleListByUserId(userId);
			//将角色信息映射到user对象中
			user.setRoles(roles);
		}
		return users;
	}
	@Override//添加用户信息并关联用户所拥有角色关系
	public void addUserRelatedRole(User user, String[] roleIds) throws Exception {
		//给用户的密码进行加密操作
		user.setPwd(MD5Util.getMD5Str(user.getPwd()));
		//添加用户信息返回自增长的id值
		int userId = userDao.addUserReturnId(user);
		//遍历角色id值并维护关系
		if(roleIds!=null) {
			for(String str : roleIds) {
				int roleId = Integer.parseInt(str);
				//将用户与角色的关系添加中间表
				userDao.addUserAndRoleRelationship(userId,roleId);
			}
		}	
	}
	@Override//根据id删除用户信息
	public void delById(int id) throws Exception {
		userDao.delById(id);
	}
	@Override//根据id查找用户信息
	public User queryUserById(int id) throws Exception {
		User user = userDao.queryUserById(id);
		return user;
	}
	@Override//修改用户信息并关联用户所拥有角色关系
	public void editUserRelatedRole(User user, String[] roleIds) throws Exception {
		//修改用户信息
		userDao.edit(user);
		//删除原来中间表中用户与角色的关系
		userDao.delOldUserRelationshipRole(user.getId());
		//添加最新的用户与角色关系到中间表
		if(roleIds!=null) {
			for(String str : roleIds) {
				int roleId = Integer.parseInt(str);
				//将用户与角色的关系添加中间表
				userDao.addUserAndRoleRelationship(user.getId(),roleId);
			}
		}
	}
	@Override//根据用户id将密码重置为1234
	public void resetPwd(int id) throws Exception {
		String pwd = MD5Util.getMD5Str("1234");
		userDao.resetPwdByUserId(id,pwd);
	}
	@Override//根据用户名密码判断用户登录是否成功,如果成功返回用户信息并关联所拥有的权限数据
	public User loginRelatedHasPrivileges(String name, String pwd) throws Exception {
		//根据用户名密码查询用户信息
		pwd = MD5Util.getMD5Str(pwd);
		User user = userDao.queryUserByNameAndPwd(name,pwd);
		//如果用户名密码正确关联权限数据
		if(user!=null) {
			//根据用户id查询该用户对应的所有角色信息
			int userId = user.getId();
			List<Role> roles = roleDao.queryRoleListByUserId(userId);
			//遍历角色信息查询角色信息对应的权限数据
			for(Role role :roles) {
				int roleId = role.getId();
				List<Privilege> roleHasPrivileges = privilegeDao.queryPrivilegesByRoleId(roleId);
				//将权限数据关联到角色数据中
				role.setPrivileges(roleHasPrivileges);
			}
			//将角色数据关联到用户数据中
			user.setRoles(roles);
		}
		return user;
	}
	@Override
	public void updatePicUrlByUserId(String url,int userId) throws Exception {
		userDao.updatePicUrlByUserId(url,userId);
	}
	@Override
	public void updatePwdByUserId(int userId, String new_pwd) throws Exception {
		new_pwd = MD5Util.getMD5Str(new_pwd);
		userDao.updatePwdByUserId(userId,new_pwd);
	}
}
