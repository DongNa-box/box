
 /**
 * Project Name:PdoneCI-api
 * File Name:AuthController.java
 * Package Name:com.pdone.api.controller
 * Date:2017年5月19日下午3:27:08
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.box.framework.pojo.Result;
import com.box.framework.pojo.RspCode;
import com.box.framework.utils.Sequence;
import com.box.token.JwtUtil;
import com.box.uums.model.User;
import com.box.uums.service.UserService;


/**
 * ClassName:AuthController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月19日 下午3:27:08
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value="/auth")
public class AuthController {
	
	@Resource
	UserService userService;
	
	
	@Autowired
	JwtUtil jwt;
	
	private static final Logger logger = Logger.getLogger(AuthController.class);
	
	/**
	 * 
	 * login:用户登录.
	 *
	 * @author Jay
	 * @param token
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	String account = jsonObj.getString("account");
    	String password = jsonObj.getString("password");
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("loginName",account);
    	map.put("password",password);
		User user = userService.getUserByAccount(map);
		if(user == null){
			return new Result(false,RspCode.R10000);
		}else if(!user.getPassword().equals(password)){
			return new Result(false,RspCode.R10001);
		}else if(user.getStatus()== 0){
			return new Result(false,RspCode.R10000);
		}else{
			user.setLastLoginTime(new Date());
			if(user.getLoginCount()==null){
				user.setLoginCount(1);
			}
			else{
				user.setLoginCount(user.getLoginCount() + 1);
			}
			
			boolean success = userService.update(user);
			if(success){
				return new Result(true);
			}else{
				return new Result(false,RspCode.R00000);
			}
		}
	}
	
	/**
	 * 
	 * register:用户注册.
	 *
	 * @author Jay
	 * @param token
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody 
	public Result register(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String account=jsonObj.getString("account");
		String phoneNumber = jsonObj.getString("mobile");
		String email = jsonObj.getString("email");
		String password = jsonObj.getString("password");
		User user = userService.getUserByEmail(email);
		if(user == null){
			User appUser = new User();
			appUser.setId(Sequence.nextId());
			appUser.setLoginName(account);
			appUser.setPassword(password);
			appUser.setCreateTime(new Date());
			appUser.setPhone(phoneNumber);
			appUser.setEmail(email);
			appUser.setLoginCount(0);
			appUser.setType(1);
			appUser.setStatus(1);
			boolean saveResult1 = userService.save(appUser);
			if(saveResult1){
				return new Result(true);
			}else{
				return new Result(false,RspCode.R00000);
			}
		}else{
			return new Result(false,RspCode.R10003);
		}
	}
	
	/**
	 * 
	 * logout:用户注销.
	 *
	 * @author Jay
	 * @param headers
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.POST)
	@ResponseBody
	public Result logout(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String userId = jsonObj.getString("userId");
		//??后续处理
		return new Result(true);
	}

	/**
	 * 
	 * changePassword:修改密码.
	 *
	 * @author Jay
	 * @param headers
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/changePassword",method = RequestMethod.POST)
	@ResponseBody
	public Result changePassword(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String userId = jsonObj.getString("userId");		
		String oldPassword = jsonObj.getString("oldPassword");
		String newPassword = jsonObj.getString("newPassword");
		User user = userService.get(userId);
		/**
	    if("".equals(userId)||userId==null){
			return new Result(false,RspCode.R00000);
		}
		else if("".equals(oldPassword)||oldPassword==null){
			return new Result(false,RspCode.R00000);
		}
		else if("".equals(newPassword)||newPassword==null){
			return new Result(false,RspCode.R00000);
		}*/
						
		if(!user.getPassword().equals(oldPassword)){
			return new Result(false,RspCode.R10001);
		}else{
			user.setPassword(newPassword);
			boolean updateResult = userService.update(user);
			if(updateResult){
				return new Result(true);
			}else{
				return new Result(false,RspCode.R00002);
			}
		}
	}
	
	/**
	 * 
	 * forgetPassword:忘记密码.
	 *
	 * @author Jay
	 * @param headers
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
	@ResponseBody
	public Result forgetPassword(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String phoneNumber = jsonObj.getString("phoneNumber");
		String newPassword = jsonObj.getString("newPassword");
		User user = userService.getUserByEmail(phoneNumber);
		if(user!=null){
			user.setPassword(newPassword);
			boolean updateResult = userService.update(user);
			if(updateResult){
				return new Result(true);
			}else{
				return new Result(false,RspCode.R00000);
			}
		}else{
			return new Result(false,RspCode.R10000);
		}
	}
	
}

