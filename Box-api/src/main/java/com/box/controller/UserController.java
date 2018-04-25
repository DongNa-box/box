package com.box.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.box.uums.service.UserService;

@Controller
@RequestMapping(value="/{platform}/user")
public class UserController {
	@Resource
	UserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
}
