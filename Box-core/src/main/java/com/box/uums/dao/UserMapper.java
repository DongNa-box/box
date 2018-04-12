package com.box.uums.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.model.User;

public interface UserMapper extends BaseMapper<User, String> {
	User getUserForLogin(Map<String,Object> map);
	
	List<Map<String,Object>> getAdminSearchlist(Map<String,Object> map);
	
	int updateStatusById(Map<String, Object> map);
	
	boolean checkLoginNameExists(String loginName);
	
	boolean checkPhoneExists(String phone);

	User getUserByLoginName(String loginName);

	User getUserByPhone(String phone);
	
	List<User> getSearchUser(User user);
	 
	int updateByPrimaryKeySelective(User record);

}