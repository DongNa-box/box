/**
 *Title:UserServiceImpl.java
 *@date 2018年4月2日下午12:01:26
 */
package com.box.uums.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.box.framework.base.dao.BaseMapper;
import com.box.uums.dao.UserMapper;
import com.box.uums.dao.UserRoleMapper;
import com.box.uums.model.User;
import com.box.uums.model.UserRole;
import com.box.uums.service.UserService;



/**
 * CalssName:UserServiceImpl.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午12:01:26
 * @version
 * @since:
 * @see
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource
	UserMapper userMapper;
	@Resource
	UserRoleMapper userRoleMapper;

	@Override
	public boolean save(User record) {
		int result = userMapper.insert(record);
		return result>0?true:false;
	}

	@Override
	public boolean update(User record) {
		int result = userMapper.updateByPrimaryKeySelective(record);
		return result>0?true:false;
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
		return userMapper.selectByPrimaryKey(id);
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
	public User getUserForLogin(Map<String,Object> map) {
		
		return userMapper.getUserForLogin(map);
	}

	@Override
	public List<Map<String, Object>> getAdminSearchlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.getAdminSearchlist(map);
	}

	@Override
	public boolean updateStatusById(Map<String, Object> map) {
		int result = userMapper.updateStatusById(map);
		return result>0?true:false;
	}

	@Override
	public boolean checkLoginNameExists(String loginName) {
		User user = userMapper.getUserByLoginName(loginName);
		return user == null?false : true;
	}

	@Override
	public boolean checkPhoneExists(String phone) {
		User user = userMapper.getUserByPhone(phone);
		return user == null?false : true;
	}

	@Override
	public boolean batchSaveUser(Map<String, Object> paramsMap) {
		try {
			User user = (User) paramsMap.get("user");
			UserRole userRole = (UserRole) paramsMap.get("userRole");
			int userRow = userMapper.insert(user);
			int userRoleRow = userRoleMapper.insert(userRole);
			return (userRow + userRoleRow)>1?true:false;
		} catch (Exception ex) {
			logger.error(UserServiceImpl.class.getName() + ":错误信息："+ex.getMessage().toString());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}

	@Override
	public boolean batchDeleteAdmin(List<String> list) {
		try {
			int userRow = userMapper.batchDeleteByIds(list);
			return userRow>0?true:false;
		} catch (Exception ex) {
			logger.error(UserServiceImpl.class.getName() + ":错误信息："+ex.getMessage().toString());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;			
		}
	}

	@Override
	public List<User> getSearchUser(User user) {
		
		return userMapper.getSearchUser(user);
	}

	@Override
	public User getUserByAccount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userMapper.getUserByAccount(map);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
