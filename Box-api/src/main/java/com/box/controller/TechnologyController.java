
 /**
 * Project Name:PdoneCI-api
 * File Name:AuthController.java
 * Package Name:com.pdone.api.controller
 * Date:2017年5月19日下午3:27:08
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;
import com.box.token.JwtUtil;



/**
 * ClassName:TechnologyController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月19日 下午3:27:08
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value="/technology")
public class TechnologyController {
	
	@Resource
	TechnologyDetailService technologyDetailService;
	
	
	@Autowired
	JwtUtil jwt;
	
	private static final Logger logger = Logger.getLogger(TechnologyController.class);
	
	/**
	 * 
	 * 获取工艺信息
	 *
	 * @author Cheng
	 * @param token
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/getTech",method = RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	String cname = jsonObj.getString("cname");
    	String level = jsonObj.getString("level");
    	String description = jsonObj.getString("description");
    	String parentId = jsonObj.getString("parentId");
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("cname", cname);
    	map.put("level", level);
    	map.put("description", description);
    	map.put("parentId", parentId);
    	List<TechnologyDetail> result=technologyDetailService.getTechnologyByDetail(map);
    	
        return new Result(true,result);
	}
	
}

