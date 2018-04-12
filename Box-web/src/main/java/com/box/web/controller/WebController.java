package com.box.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.box.boxmanage.service.BoxClassficationService;
import com.box.framework.pojo.TreeNode;

/**
 * Project Name:Box-web
 * File Name:WebController.java
 * Package Name:
 * Date:2018年4月12日上午10:27:20
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

/**
 * ClassName:WebController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午10:27:20
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/web")
public class WebController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
	 @Resource
	 BoxClassficationService boxClassficationService;
	 @RequestMapping(method = RequestMethod.POST, value = "/boxTree")
	    @ResponseBody
	    public List<TreeNode> getMenuTree() {
	    	List<TreeNode> webmenu = boxClassficationService.getWebTree();
	        return webmenu;
	    }
}

