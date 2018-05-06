
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
import java.util.UUID;

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
import com.box.framework.utils.MailUtils;
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
		User user = userService.getUserByAccount(map);//这个里面需要去查询验证码是否通过
		if(user == null){
			return new Result(false,RspCode.R10000);
		}else if(!user.getPassword().equals(password)){
			return new Result(false,RspCode.R10001);
		}else if(user.getStatus()== 0){
			return new Result(false,RspCode.R10002);
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
				Map<String,String> m=new HashMap<String,String>();
				m.put("userId", user.getId());
				return new Result(true,m);
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
		String email = jsonObj.getString("email");
		String password = jsonObj.getString("password");
		User appUser = new User();
		appUser.setId(Sequence.nextId());
		appUser.setLoginName(account);
		appUser.setPassword(password);
		appUser.setCreateTime(new Date());
		appUser.setEmail(email);
		appUser.setLoginCount(0);
		appUser.setType(3);//APP用户
		appUser.setStatus(0);//初始注册未激活，需要登录邮箱进行激活
		appUser.setCode(UUID.randomUUID().toString());//添加验证码
		boolean saveResult1 = userService.save(appUser);
		if(saveResult1){
			// 发送邮件:
			//("发送邮件"+vo.getEmail());
			//("发送邮件"+code);
			try {
			  MailUtils.sendMail(appUser.getEmail(), appUser.getCode());
			} catch (Exception e) {
			  e.printStackTrace();
			}
			return new Result(true);
		}else{
			return new Result(false,RspCode.R00000);
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

	
}

