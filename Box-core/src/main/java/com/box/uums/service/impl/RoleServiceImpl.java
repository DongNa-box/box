/**
 *Title:RoleServiceImpl.java
 *@date 2018年4月2日下午1:26:19
 */
package com.box.uums.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.box.uums.dao.RoleFunctionMapper;
import com.box.uums.dao.RoleMapper;
import com.box.uums.model.Role;
import com.box.uums.model.RoleFunction;
import com.box.uums.service.RoleService;


/**
 * CalssName:RoleServiceImpl.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:26:19
 * @version
 * @since:
 * @see
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	RoleMapper roleMapper;
	@Resource
	RoleFunctionMapper roleFunctionMapper;

	@Override
	public boolean save(Role record) {
		int row = roleMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public boolean update(Role record) {
		int row = roleMapper.updateByPrimaryKeySelective(record);
		return row>0?true:false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchDeleteById(List<String> records) {
		int result = roleMapper.batchDeleteByIds(records);
		return result>0?true:false;
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
		return roleMapper.selectAll();
	}

	@Override
	public Set<String> getRoleCodeSet(String loginName) {
		List roleList = roleMapper.getRoleCode(loginName);
        Set<String> roleSet=new HashSet<String>();
        roleSet.addAll(roleList);
		return roleSet;
	}

	@Override
	public Role getRoleByType(Integer type) {
		
		return roleMapper.getRoleByType(type);
	}

	@Override
	public boolean authRole(List<RoleFunction> auth) {
		int row = roleFunctionMapper.batchInsert(auth);
		return row>0?true:false;
	}

	@Override
	public void removeAuth(String roleId) {
		roleFunctionMapper.deleteByPrimaryKey(roleId);
		
	}

}
