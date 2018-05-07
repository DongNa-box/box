
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
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.Result;
import com.box.token.JwtUtil;



/**
 * ClassName:BoxController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月19日 下午3:27:08
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value="/box")
public class BoxTypeController {
	
	@Resource
	BoxTypeService boxTypeService;
	
	
	@Autowired
	JwtUtil jwt;
	
	private static final Logger logger = Logger.getLogger(BoxTypeController.class);
	
	/**
	 * 
	 * 获取纸盒信息
	 *
	 * @author Cheng
	 * @param token
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/getMsg",method = RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	String msg = jsonObj.getString("msg");
    	Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
	    Matcher m=p.matcher(msg); 
       
    	if(msg.equals("00")){
    		 //查询全部盒型
    		List<Map<String,Object>> result=boxTypeService.getAllBoxTypeList();
    		return new Result(true,result);
    	}else if(m.find()){
    		//模糊查询
    		Map<String, Object> map=new HashMap<String,Object>();
    		map.put("name", msg);
    		List<Map<String,Object>> result=boxTypeService.boxTypeSearchList(map);
    		return new Result(true,result);
    	}else{
    		//查询具体盒型
    		Map<String, Object> map=new HashMap<String,Object>();
    		map.put("boxId", msg);
    		List<Map<String,Object>> result=boxTypeService.boxTypeSearchList(map);
    		return new Result(true,result);
    	}
	

	}
	

}

