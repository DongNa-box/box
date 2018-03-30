package com.box.uums.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.box.uums.dao.RoleMapper;
import com.box.uums.model.Role;
import com.box.uums.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public boolean save(Role record) {
	    int row=roleMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public boolean update(Role record) {
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
	public boolean delete(Role record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role get(Role record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getList(Role record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getRoleCodeSet(String loginName) {
		List roleList = roleMapper.selectRoleCode(loginName);
        Set<String> roleSet=new HashSet<String>();
        roleSet.addAll(roleList);
		return roleSet;
	}

}
