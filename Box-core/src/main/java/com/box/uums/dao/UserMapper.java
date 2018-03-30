package com.box.uums.dao;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.User;

public interface UserMapper extends BaseMapper<User,String>{

	User selectUserForLogin(String loginName);
    
}