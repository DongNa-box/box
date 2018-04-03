/**
 *Title:UserRoleService.java
 *@date 2018年4月2日下午1:31:08
 */
package com.box.uums.service;

import com.box.framework.base.service.BaseService;
import com.box.uums.model.UserRole;

/**
 * CalssName:UserRoleService.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:31:08
 * @version
 * @since:
 * @see
 */
public interface UserRoleService extends BaseService<UserRole, String> {
	UserRole getUserRoleByUserId(String userId);
}
