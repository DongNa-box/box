package com.box.uums.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.RoleFunction;

public interface RoleFunctionMapper extends BaseMapper<RoleFunction, String>{
	
	int batchInsert(@Param(value = "roleFunctions") List<RoleFunction> roleFunction);
	
	List<RoleFunction> selectByRoleId(String roleId);
   
}