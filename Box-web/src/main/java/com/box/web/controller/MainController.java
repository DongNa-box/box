
 /**
 * Project Name:Box-web
 * File Name:MainController.java
 * Package Name:com.box.web.controller
 * Date:2018年4月11日下午8:13:51
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.boxmanage.service.BoxClassficationService;
import com.box.framework.pojo.TreeNode;


/**
 * ClassName:MainController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月11日 下午8:13:51
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
public class MainController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	
	 @RequestMapping(method = RequestMethod.GET, value = "/")
	    public String index(HttpServletRequest request) {
	        return "redirect:/login";
	    }
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/toRegister")
	    public String toRegister(HttpServletRequest request) {
	        return "register";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/toLogin")
	    public String toLogin(HttpServletRequest request) {
	        return "login";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/main")
	    public String main(HttpServletRequest request) {
	        return "main";
	    }
	    
	    @RequestMapping(method = RequestMethod.GET, value = "/homePage")
	    public String homePage(HttpServletRequest request) {
	        return "homePage";
	    }
	
}

