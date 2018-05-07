package com.box.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.box.framework.pojo.Result;
import com.box.framework.pojo.RspCode;
import com.box.token.JwtUtil;
import com.box.uums.model.User;
import com.box.uums.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Resource
	UserService userService;
	
	@Autowired
	JwtUtil jwt;
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	/**
	 * 确定用户名和邮箱的匹配或者验证邮箱的唯一性或者验证用户名的唯一性
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/verifyMsg",method = RequestMethod.POST)
	@ResponseBody 
	public Result verifyMsg(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String email=jsonObj.getString("email");
		String account=jsonObj.getString("account");
		Map<String,String> map=new HashMap<String,String>();
		map.put("email", email);
		map.put("loginName", account);
	    User user=userService.getUserByMsg(map);
		if(user!=null){
			Map<String,String> m=new HashMap<String,String>();
			m.put("userId",user.getId());
			return new Result(true,m);
		}else{
			return new Result(false,RspCode.R00001);
		}
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
	 * 重置密码.
	 *
	 * @author Jay
	 * @param headers
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
	@ResponseBody
	public Result resetPassword(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String userId = jsonObj.getString("userId");
		String newPassword = jsonObj.getString("password");
		User user = userService.get(userId);
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
	/**
	 *邮箱激活
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/userActive",method = RequestMethod.GET)
	@ResponseBody 
	public Result userActive(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String code=jsonObj.getString("code");
	    boolean result=userService.updateUserByCode(code);
		if(result){
			return new Result(true);
		}else{
			return new Result(false,RspCode.R00002);
		}
	}
}
