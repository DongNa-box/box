package com.box.uums.dao;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole, String>{

	UserRole selectUserRoleByUserId(String userId);
   
}