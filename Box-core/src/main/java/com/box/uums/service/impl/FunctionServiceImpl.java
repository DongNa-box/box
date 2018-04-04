/**
 *Title:FunctionServiceImpl.java
 *@date 2018年4月2日下午1:31:45
 */
package com.box.uums.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.box.framework.pojo.TreeNode;
import com.box.framework.utils.TreeUtil;
import com.box.uums.dao.FunctionMapper;
import com.box.uums.dao.RoleFunctionMapper;
import com.box.uums.model.AuthTree;
import com.box.uums.model.Function;
import com.box.uums.model.RoleFunction;
import com.box.uums.service.FunctionService;

/**
 * CalssName:FunctionServiceImpl.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:31:45
 * @version
 * @since:
 * @see
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
	@Resource
	FunctionMapper functionMapper;
	@Resource
	RoleFunctionMapper roleFunctionMapper;
	@Override
	public boolean save(Function record) {
		int row = functionMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public boolean update(Function record) {
		int result = functionMapper.updateByPrimaryKeySelective(record);
		return result>0?true:false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean batchDeleteById(List<String> records) {
		int result = functionMapper.batchDeleteByIds(records);
		return result>0?true:false;
	}

	@Override
	public boolean delete(Function record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Function get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Function get(Function record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Function> getList(Function record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Function> getAllList() {
		return functionMapper.selectAll();
	}

	@Override
	public List<TreeNode> getMenuTree(String roleId) {
		List<Function> funcs = functionMapper.selectMenuTree(roleId);
        Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
        for (Function func : funcs) {
            TreeNode node = new TreeNode();
            node.setText(func.getName());
            node.setId(func.getId());
            node.setParentId(func.getParentId());
            node.setLevelCode(func.getType().toString());
            node.setIcon(func.getIcon());
            node.setUrl(func.getUrl());
            nodelist.put(node.getId(), node);
        }
        // 构造树形结构
        return TreeUtil.getNodeList(nodelist);
	}

	@Override
	public List<AuthTree> getAuthTree(String roleId) {
		List<Function> funcs = functionMapper.selectAuthTree();
		List<RoleFunction> roleFuctions = roleFunctionMapper.selectByRoleId(roleId);
		
		List<String> authList = new ArrayList<String>();
		for(RoleFunction roleFuction:roleFuctions){
			authList.add(roleFuction.getFunctionId());
		}
		List<AuthTree> authTrees = new ArrayList<AuthTree>();
		for (Function func : funcs) {
			AuthTree authTree = new AuthTree();
			authTree.setId(func.getId());
			authTree.setName(func.getName());
			authTree.setpId(func.getParentId());
			authTree.setOpen(true);
			if(authList.contains(func.getId())){
				authTree.setChecked(true);
			}else{
				authTree.setChecked(false);
			}
			authTrees.add(authTree);
		}
		return authTrees;
	}

	@Override
	public List<Function> getParentMenu() {
		return functionMapper.selectParentMenu();
	}

}
