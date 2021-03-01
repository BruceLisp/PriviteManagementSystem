package com.hckj.privilege.service.api;

import java.util.List;

import com.hckj.privilege.model.User;

//用户的业务处理接口
public interface UserService {
	//根据用户名字模糊查询用户信息并关联角色信息
	public List<User> queryUserRelatedRoleByLikeUserName(String name) throws Exception;
	//添加用户信息并关联用户所拥有角色关系
	public void addUserRelatedRole(User user, String[] roleIds) throws Exception;
	//根据id删除用户信息
	public void delById(int id) throws Exception;
	//根据id查找用户信息
	public User queryUserById(int id) throws Exception;
	//修改用户信息并关联用户所拥有角色关系
	public void editUserRelatedRole(User user, String[] roleIds) throws Exception;
	//根据用户id将密码重置为1234
	public void resetPwd(int id) throws Exception;
	//根据用户名密码判断用户登录是否成功,如果成功返回用户信息并关联所拥有的权限数据
	public User loginRelatedHasPrivileges(String name, String pwd) throws Exception;
	//修改个人图片的访问路径
	public void updatePicUrlByUserId(String url,int userId) throws Exception;
	//根据用户id修改密码
	public void updatePwdByUserId(int userId, String new_pwd) throws Exception;
}
