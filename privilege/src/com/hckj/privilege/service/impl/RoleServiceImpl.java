package com.hckj.privilege.service.impl;

import java.util.List;

import com.hckj.privilege.dao.RoleDao;
import com.hckj.privilege.model.Role;
import com.hckj.privilege.service.api.RoleService;

//角色业务处理
public class RoleServiceImpl implements RoleService{
	private RoleDao roleDao = new RoleDao();
	@Override //查询所有角色内容
	public List<Role> findAll() throws Exception{
		return roleDao.findAll();
	}
	@Override //添加角色内容
	public void save(Role role) throws Exception {
		roleDao.add(role);
	}
	@Override //根据id删除角色信息
	public void delById(int id) throws Exception {
		roleDao.delById(id);
	}
	@Override //根据id查询角色信息
	public Role findRoleById(int id) throws Exception {
		return roleDao.findRoleById(id);
	}
	@Override //修改角色信息
	public void update(Role role) throws Exception {
		roleDao.update(role);
	}
	@Override//查询所有角色信息；并设置当前用户所拥有的角色的checked属性
	public List<Role> queryRoleListAndCheckedUserHasRole(int id) throws Exception {
		List<Role> roles = roleDao.findAll();
		for(Role role : roles) {
			//查询用户所拥护的角色信息
			List<Role> userHasRoles = roleDao.queryRoleListByUserId(id);
			//如果userHasRoles的集合中只要有一个对象的角色id值和role.id值，就代表当前用户拥有该角色
			for(Role userRole: userHasRoles) {
				if(role.getId()==userRole.getId()) {
					role.setChecked(true);
				}
			}
		}
		return roles;
	}

}
