
 /**
 * Project Name:Box-web
 * File Name:RegisterContoller.java
 * Package Name:com.box.web.controller
 * Date:2018年4月11日上午11:24:43
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.box.framework.pojo.Result;
import com.box.framework.pojo.ResultCode;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.EncryptUtil;
import com.box.framework.utils.Sequence;
import com.box.uums.model.User;
import com.box.uums.service.UserService;



/**
 * ClassName:RegisterContoller
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月11日 上午11:24:43
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
public class RegisterContoller {
	 private static final Logger LOGGER = LoggerFactory.getLogger(RegisterContoller.class);

	 private final static String LOGIN_PAGE = "login";
	 private final static String REGISTER_PAGE = "register";
	 @Resource
	 UserService userService;
	 
	 @RequestMapping(value = "/toLogin")
	    protected String toLogin() {
	        return "/login";
		}
	 @RequestMapping(value = "/register")
	    private String doRegister(HttpServletRequest request, Model model) {
		 String userName = request.getParameter("userName");
		 String phone = request.getParameter("phone");
		 String password = request.getParameter("password");
		 String msg;
		 String shiroLoginFailure = (String)request.getAttribute("shiroLoginFailure");
     	if("jCaptcha.error".equals(shiroLoginFailure)){
             msg = "验证码错误！";
             model.addAttribute("message", new ResultCode("4", msg));
             LOGGER.error(msg);
             return REGISTER_PAGE;
     	}else if (userService.checkLoginNameExists(userName)) {
     		 msg = "用户名已注册！";
             model.addAttribute("message", new ResultCode("5", msg));
             LOGGER.error(msg);
             return REGISTER_PAGE;
		}else if (userService.checkPhoneExists(phone)) {
			msg = "手机号已注册！";
            model.addAttribute("message", new ResultCode("6", msg));
            LOGGER.error(msg);
            return REGISTER_PAGE;
		}else {
			User user = new User();
			user.setId(Sequence.nextId());
			user.setLoginName(userName);
			user.setPassword(EncryptUtil.encodeByMD5(password));
			user.setPhone(phone);
			user.setStatus(1);
			user.setType(2);
			user.setLoginCount(0);
			user.setCreateTime(DateUtil.getCurrDate());
			if (userService.save(user)) {
				return LOGIN_PAGE;
			}else {
				return REGISTER_PAGE;
			}
		}
         
         
	 }
}

