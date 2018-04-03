/**
 *Title:RoleService.java
 *@date 2018年4月2日下午1:25:47
 */
package com.box.uums.service;

import java.util.List;
import java.util.Set;

import com.box.framework.base.service.BaseService;
import com.box.uums.model.Role;
import com.box.uums.model.RoleFunction;

/**
 * CalssName:RoleService.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:25:47
 * @version
 * @since:
 * @see
 */
public interface RoleService extends BaseService<Role, String> {
	Set<String> getRoleCodeSet(String loginName);

	Role getRoleByType(Integer type);

	boolean authRole(List<RoleFunction> auth);
	
	void removeAuth(String roleId);

}
