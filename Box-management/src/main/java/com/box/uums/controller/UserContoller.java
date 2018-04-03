/**
 *Title:UserContoller.java
 *@date 2018年4月2日下午1:43:02
 */
package com.box.uums.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.EncryptUtil;
import com.box.framework.utils.PropertiesUtil;
import com.box.framework.utils.Sequence;
import com.box.uums.model.Role;
import com.box.uums.model.User;
import com.box.uums.model.UserRole;
import com.box.uums.service.RoleService;
import com.box.uums.service.UserService;

import net.sf.ehcache.CacheManager;

/**
 * CalssName:UserContoller.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:43:02
 * @version
 * @since:
 * @see
 */
@Controller
@RequestMapping(value = "/uums")
public class UserContoller {
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserContoller.class);
		
	    private static final String APP_USER = PropertiesUtil.getValue("appUser");

	    private static final String SYSTEM_USER = PropertiesUtil.getValue("systemUser");
	    
	    private static final String DEFAULT_PASSWORD = PropertiesUtil.getValue("default.password");
	    
	    
		@Resource
		UserService userService;
		
		@Resource
		RoleService roleService;
		
		CacheManager manager =CacheManager.create(); 
		
	    @RequestMapping(method = RequestMethod.GET, value = "/{category}/user")
	    private String user(@PathVariable String category) {
	    	String url = "";
	    	if(SYSTEM_USER.equals(category)){
	    		url = "uums/sysUser";
	    	}else if(APP_USER.equals(category)){
	    		url = "uums/appUser";
	    	}
	        return url;
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/admin")
	    protected String admin() {
	        return "uums/admin";
		}
	   
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/webUser")
	    protected String schoolMgr() {
	        return "uums/webUser";
		}
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/appUser")
	    protected String companyMgr() {
	        return "uums/appUser";
		}
	   
	    @RequestMapping(method = RequestMethod.GET, value = "/userSetting")
	    protected String userSetting(ModelMap model) {
	    	User user = userService.get(SecurityUtil.getUser().getId());
	    	String birthday = DateUtil.format(user.getBirthday());
	    	model.addAttribute("birthday", birthday);
	    	model.addAttribute("userDtl", user);
	    	return "uums/userSetting";
		}
	    	    	        
	    /**
	     * 管理员用户查询（查询所有和条件查询）
	     * adminList:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param searchparams
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.GET, value = "/adminList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> adminUserList(@Param(value = "searchparams") String searchparams){
	   		JSONObject jsonObj = JSONObject.parseObject(searchparams);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
		    	map.put("name", jsonObj.getString("asearch-name"));
		    	map.put("phone", jsonObj.getString("asearch-phone"));
		    	map.put("status", jsonObj.getString("asearch-status"));
	       	}
	   		list = userService.getAdminSearchlist(map);
	   		return list;
	   	}
	    
	    /**
	     * 用户启用禁用
	     * updateStatus:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param params
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.POST, value = "/updateStatus")
	    @ResponseBody
	    private Result updateStatus(@Param(value = "params") String params) {
	    	Map<String, Object> paramsMap = new HashMap<String,Object>();
	    	paramsMap = JSON.parseObject(params);
	    	boolean result = userService.updateStatusById(paramsMap);
	    	return result ? new Result(true,"操作成功") : new Result(false,"操作失败");
	    }
	    
	    /**
	     * 修改或新增用户
	     * saveSchoolMgrMessage:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param saveparams
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.POST, value = "/editUser")
	    @ResponseBody
	    private Result editUser(@Param(value = "params") String params) {
	    	User user  = JSON.parseObject(params,User.class);
	    	UserRole userRole = new UserRole();
	    	Role role = new Role();
	    	Map<String,Object> paramsMap = new HashMap<String,Object>();
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	    	String flag = jsonObj.getString("flag");
	    	boolean result;
	    	switch(flag){
	    	//管理员修改
	    		case "01":
	    			boolean b= userService.checkLoginNameExists(user.getLoginName());
	    			boolean b1= userService.checkPhoneExists(user.getPhone());
	    			if(b){
	    				return new Result(false,"工号已存在");
	    			}
	    			if(b1){
	    				return new Result(false,"手机号已存在");
	    			}
	    			user.setId(Sequence.nextId());
	    	    	user.setPassword(EncryptUtil.encodeByMD5(DEFAULT_PASSWORD));
	    	    	paramsMap.put("user", user);
	    	    	role = roleService.getRoleByType(user.getType());
			    	//UserRole userRole = new UserRole();
			    	userRole.setId(Sequence.nextId());
			    	userRole.setRoleId(role.getId());
			    	userRole.setUserId(user.getId());
			    	userRole.setCreateby(SecurityUtil.getUser().getId());
			    	userRole.setCreatetime(DateUtil.getCurrDate());
	
			    	paramsMap.put("userRole", userRole);
			    	result = userService.batchSaveUser(paramsMap);
	    	    	return result ? new Result(true,"新增成功") : new Result(false,"新增失败");
	    		//webuser修改
	    		case "02":
	    			boolean b2= userService.checkPhoneExists(user.getPhone());
	    			if(b2){
	    				return new Result(false,"手机号已存在");
	    			}
	    			user.setId(Sequence.nextId());
	    	    	user.setPassword(EncryptUtil.encodeByMD5(DEFAULT_PASSWORD));
	    	    	paramsMap.put("user", user);
	    	    	role = roleService.getRoleByType(user.getType());
			    	userRole.setId(Sequence.nextId());
			    	userRole.setRoleId(role.getId());
			    	userRole.setUserId(user.getId());
			    	userRole.setCreateby(SecurityUtil.getUser().getId());
			    	userRole.setCreatetime(DateUtil.getCurrDate());
			    	paramsMap.put("userRole", userRole);
			    	result = userService.batchSaveUser(paramsMap);
	    	    	return result ? new Result(true,"新增成功") : new Result(false,"新增失败");
	    		//appuser修改
	    		case "03":
	    			boolean b3= userService.checkLoginNameExists(user.getLoginName());
	    			boolean b4= userService.checkPhoneExists(user.getPhone());
	    			if(b3){
	    				return new Result(false,"用户名已存在");
	    			}
	    			if(b4){
	    				return new Result(false,"手机号已存在");
	    			}
	    			user.setId(Sequence.nextId());
	    	    	user.setPassword(EncryptUtil.encodeByMD5(DEFAULT_PASSWORD));
	    	    	paramsMap.put("user", user);
	    	    	role = roleService.getRoleByType(user.getType());
			    	userRole.setId(Sequence.nextId());
			    	userRole.setRoleId(role.getId());
			    	userRole.setUserId(user.getId());
			    	userRole.setCreateby(SecurityUtil.getUser().getId());
			    	userRole.setCreatetime(DateUtil.getCurrDate());
			    	paramsMap.put("userRole", userRole);
			    	result = userService.batchSaveUser(paramsMap);
	    	    	return result ? new Result(true,"新增成功") : new Result(false,"新增失败");
	    	    		
	    		case "1":
	    	    	result = userService.update(user);
	    	    	return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
	    			
	    	}
	    	return new Result(false,"操作失败");
	    	
	    }
	    
	    
	    /**
	     * 工号去重
	     * checkLoginNameExists:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param loginName
	     * @return
	     * @since JDK 1.8
	     */
	   @RequestMapping(method = RequestMethod.POST, value = "/checkLoginNameExists")
	    @ResponseBody
	    private JSONObject checkLoginNameExists(@RequestParam String loginName){
		   boolean result = userService.checkLoginNameExists(loginName);
	    	JSONObject jsonObj = new JSONObject();
	    	jsonObj.put("valid", result);
			return jsonObj;
	    }
	    
	    /**
	     * 手机号去重
	     * checkPhoneExists:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param phone
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.POST, value = "/checkPhoneExists")
	    @ResponseBody
	    private JSONObject checkPhoneExists(@RequestParam String phone){
	    	boolean result = userService.checkPhoneExists(phone);
	    	JSONObject jsonObj = new JSONObject();
	    	jsonObj.put("valid", result);
			return jsonObj;
	    }
	    
	    /**
	     * 修改个人设置
	     * editUserSetting:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param params
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.POST, value = "/editUserSetting")
	    @ResponseBody
	    private Result editUserSetting(@Param(value = "params") String params){
	    	User user = JSON.parseObject(params, User.class);
	    	if("".equals(user.getPassword()) || user.getPassword() == null){	
			}else{
				user.setPassword(EncryptUtil.encodeByMD5(user.getPassword()));
			}
	    	boolean result = userService.update(user);
	    	return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
	    }
	    
	    /**
	     * 验证密码
	     * verifyPassword:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param password
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.POST, value = "/verifyPassword")
	    @ResponseBody
	    private JSONObject verifyPassword(@RequestParam String password){
	    	User user = SecurityUtil.getUser();
	    	boolean result = false;
	    	if(EncryptUtil.encodeByMD5(password).equals(user.getPassword())){
	    		result = true;
	    	}
	    	JSONObject jsonObj = new JSONObject();
	    	jsonObj.put("valid", result);
			return jsonObj;
	    }
	    
	    /**
	     * 删除后台用户
	     * deleteUser:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param uids
	     * @return
	     * @since JDK 1.8
	     */
	    @RequestMapping(method = RequestMethod.POST, value = "/deleteAdmin")
	    @ResponseBody
	    private Result deleteUser(@Param(value = "uids")String uids) {
	    	List<String> list = JSON.parseArray(uids, String.class);
	    	boolean result = userService.batchDeleteAdmin(list);
	    	return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
	    }

}
