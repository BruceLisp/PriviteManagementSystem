package com.hckj.privilege.service.api;

import java.util.List;

import com.hckj.privilege.model.Role;

//角色业务处理
public interface RoleService {
	//查询角色所有数据
	List<Role> findAll() throws Exception;
	//添加角色信息
	void save(Role role) throws Exception;
	//根据id删除角色信息
	void delById(int id) throws Exception;
	//根据id查找角色信息
	Role findRoleById(int id) throws Exception;
	//修改角色信息
	void update(Role role) throws Exception;
	//查询所有角色信息；并设置当前用户所拥有的角色的checked属性
	List<Role> queryRoleListAndCheckedUserHasRole(int id)throws Exception;

}
