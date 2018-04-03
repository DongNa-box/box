/**
 *Title:FunctionService.java
 *@date 2018年4月2日下午1:30:04
 */
package com.box.uums.service;

import java.util.List;

import com.box.framework.base.service.BaseService;
import com.box.framework.pojo.TreeNode;
import com.box.uums.model.AuthTree;
import com.box.uums.model.Function;


/**
 * CalssName:FunctionService.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:30:04
 * @version
 * @since:
 * @see
 */
public interface FunctionService extends BaseService<Function, String> {
	List<TreeNode> getMenuTree(String roleId);
	
	List<AuthTree> getAuthTree(String roleId);
	
	List<Function> getParentMenu();
}
