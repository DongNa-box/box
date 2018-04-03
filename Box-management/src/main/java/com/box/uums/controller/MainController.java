
 /**
 * Project Name:PdoneCI-web
 * File Name:MainController.java
 * Package Name:com.pdone.uums.controller
 * Date:2017年5月10日下午1:05:08
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.uums.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName:MainController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月10日 下午1:05:08
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

@Controller
public class MainController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(HttpServletRequest request) {
        return "redirect:/login";
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

