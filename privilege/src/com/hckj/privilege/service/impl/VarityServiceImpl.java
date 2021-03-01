package com.hckj.privilege.service.impl;

import java.util.List;

import com.hckj.privilege.dao.VarityDao;
import com.hckj.privilege.model.Varity;
import com.hckj.privilege.service.api.VarityService;

//图书分类相关业务处理类
public class VarityServiceImpl implements VarityService{
	private VarityDao varityDao = new VarityDao();
	//加载所有类别信息，按照父类别查找子类别，分层次。
	@Override
	public List<Varity> queryAllVarity() throws Exception {
		List<Varity> pVarities = varityDao.queryAllPVarity();
		for(Varity pVarity : pVarities){
			List<Varity> varities = varityDao.queryVarityByPVarityId(pVarity.getId());
			pVarity.setChildren(varities);
		}
		return pVarities;
	}
	//加载所有的类别信息，不分子类，父类
	@Override
	public List<Varity> queryAllVarities() throws Exception {
		List<Varity> pVarities = varityDao.queryAllPVarities();
		return pVarities;
	}
}
