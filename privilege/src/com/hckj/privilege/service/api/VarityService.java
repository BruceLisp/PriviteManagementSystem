package com.hckj.privilege.service.api;

import java.util.List;

import com.hckj.privilege.model.Varity;

//图书分类业务处理接口
public interface VarityService {

	List<Varity> queryAllVarity() throws Exception;

	List<Varity> queryAllVarities() throws Exception;
	
}
