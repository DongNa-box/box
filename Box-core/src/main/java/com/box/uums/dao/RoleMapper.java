package com.box.uums.dao;

import java.util.List;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.Role;

public interface RoleMapper extends BaseMapper<Role, String>{
	List<Role> getRoleCode(String loginName);
	
	Role getRoleByType(Integer type);
}