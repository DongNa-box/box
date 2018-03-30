package com.box.uums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.uums.dao.UserMapper;
import com.box.uums.model.User;
import com.box.uums.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public boolean save(User record) {
		int row=userMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public boolean update(User record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchDeleteById(List<String> records) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(User record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getList(User record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserForLogin(String loginName) {
		return userMapper.selectUserForLogin(loginName);
	}

}
