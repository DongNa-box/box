package com.box.uums.dao;

import java.util.List;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.Function;

public interface FunctionMapper extends BaseMapper<Function, String>{
	List<Function> selectMenuTree(String roleId);
	
	List<Function> selectAuthTree();
	
	List<Function> selectParentMenu();

	int updateByPrimaryKeySelective(Function record);
	
}