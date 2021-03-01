package com.hckj.privilege.service.impl;

import java.util.List;

import com.hckj.privilege.dao.PrivilegeDao;
import com.hckj.privilege.model.Privilege;
import com.hckj.privilege.service.api.PrivilegeService;

//处理权限数据业务类
public class PrivilegeServiceImpl implements PrivilegeService {
	private PrivilegeDao privilegeDao = new PrivilegeDao();
	@Override//1.添加权限数据
	public void addPrivilegeData(Privilege privilege) throws Exception {
		privilegeDao.add(privilege);
	}
	@Override //2.查询所有权限数据并选中角色所拥有的的权限
	public List<Privilege> queryPrivilegeAllAndCheckedRoleHasPrivileges(int roleId) throws Exception {
		//查询所有权限
		List<Privilege> list = privilegeDao.queryPrivilegeAll();
		//查看当前角色所拥有的权限
		List<Privilege> roleHasPrivileges = privilegeDao.queryPrivilegesByRoleId(roleId);
		//遍历所有权限，把当前角色所拥有的的权限的checked属性值设置为true
		for(Privilege privilege : list) {
			for(Privilege roleHasPrivilege: roleHasPrivileges) {
				if(privilege.getId()==roleHasPrivilege.getId()) {
					privilege.setChecked(true);
				}
			}
		}
		return list;
	}
	@Override //3.关联角色所拥有的权限
	public void relatedRoleHasPrivileges(int roleId, String privilegeIds) throws Exception {
		//清空当前角色之前在数据库所拥有的的权限
		privilegeDao.clearRoleRelatedPrivilegeByRole(roleId);
		//新增最终角色所拥有的权限
		if(privilegeIds!=null && !privilegeIds.equals("") && !privilegeIds.equals("null")) {
			String[] priIds = privilegeIds.split(",");
			for(String str : priIds) {
				int privilegeId = Integer.parseInt(str);
				privilegeDao.addRoleRelatedPrivilegeByRoleIdAndPrivilegeId(roleId,privilegeId);
			}
		}
	}
	@Override //加载顶级权限数据并关联二级权限数据
	public List<Privilege> queryTopPrivilegeRelatedTwoPrivilege() throws Exception {
		//查询顶级权限数据
		List<Privilege> topPrivileges = privilegeDao.queryTopPrivilegeAll();
		//遍历顶级权限数据，给顶级权限数据关联二级权限数据
		for(Privilege privilege : topPrivileges) {
			int topId = privilege.getId();
			//根据顶级权限id查询二级权限
			List<Privilege> children = privilegeDao.queryTwoPrivilegeByTopId(topId);
			privilege.setChildren(children);
		}
		return topPrivileges;
	}
	@Override//查询有访问路径的权限路径
	public List<String> queryHasUrlsPrivilege() throws Exception {
		return privilegeDao.queryHasUrlsPrivilege();
	}

}
