package com.box.uums.dao;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.Login;

public interface LoginMapper extends BaseMapper<Login, String>{
	 
	int updateByPrimaryKeySelective(Login record);
   
}