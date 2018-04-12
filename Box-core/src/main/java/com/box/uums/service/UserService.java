/**
 *Title:UserService.java
 *@date 2018年4月2日下午12:00:27
 */
package com.box.uums.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.uums.model.User;

/**
 * CalssName:UserService.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午12:00:27
 * @version
 * @since:
 * @see
 */
public interface UserService extends BaseService<User, String> {
	User getUserForLogin(Map<String,Object> map);
	
	List<Map<String,Object>> getAdminSearchlist(Map<String,Object> map);
	
	boolean updateStatusById(Map<String, Object> map);
	
	boolean checkLoginNameExists(String loginName);
	
	boolean checkPhoneExists(String phone);

	boolean batchSaveUser(Map<String, Object> paramsMap);

	boolean batchDeleteAdmin(List<String> list);
	
	List<User> getSearchUser(User user);
}
