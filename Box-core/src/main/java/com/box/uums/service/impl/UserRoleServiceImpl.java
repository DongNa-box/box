/**
 *Title:UserRoleServiceImpl.java
 *@date 2018年4月2日下午1:33:04
 */
package com.box.uums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.box.uums.dao.UserRoleMapper;
import com.box.uums.model.UserRole;
import com.box.uums.service.UserRoleService;

/**
 * CalssName:UserRoleServiceImpl.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:33:04
 * @version
 * @since:
 * @see
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Resource
	UserRoleMapper userRoleMapper;
	@Override
	public boolean save(UserRole record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserRole record) {
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
	public boolean delete(UserRole record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserRole get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole get(UserRole record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> getList(UserRole record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole getUserRoleByUserId(String userId) {
		return userRoleMapper.selectUserRoleByUserId(userId);
	}



}
