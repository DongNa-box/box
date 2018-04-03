/**
 *Title:RoleFunctionServiceImpl.java
 *@date 2018年4月2日下午1:32:30
 */
package com.box.uums.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.box.uums.dao.RoleFunctionMapper;
import com.box.uums.model.RoleFunction;
import com.box.uums.service.RoleFunctionService;

/**
 * CalssName:RoleFunctionServiceImpl.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:32:30
 * @version
 * @since:
 * @see
 */
@Service("roleFunctionService")
public class RoleFunctionServiceImpl implements RoleFunctionService {
	@Resource
	RoleFunctionMapper roleFunctionMapper;
	@Override
	public boolean save(RoleFunction record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RoleFunction record) {
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
	public boolean delete(RoleFunction record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RoleFunction get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleFunction get(RoleFunction record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleFunction> getList(RoleFunction record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleFunction> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

}
