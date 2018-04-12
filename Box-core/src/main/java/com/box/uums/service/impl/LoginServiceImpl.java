/**
 *Title:LoginServiceImpl.java
 *@date 2018年4月2日下午1:28:46
 */
package com.box.uums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.box.uums.dao.FunctionMapper;
import com.box.uums.dao.LoginMapper;
import com.box.uums.model.Login;
import com.box.uums.service.LoginService;

/**
 * CalssName:LoginServiceImpl.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:28:46
 * @version
 * @since:
 * @see
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	LoginMapper loginMapper; 
	@Override
	public boolean save(Login record) {
		int res = loginMapper.insert(record);
		return res>0?true:false;
	}

	@Override
	public boolean update(Login record) {
		int res = loginMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;
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
	public boolean delete(Login record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Login get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login get(Login record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> getList(Login record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

}
