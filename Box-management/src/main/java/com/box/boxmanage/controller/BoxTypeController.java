
 /**
 * Project Name:Box-management
 * File Name:BoxTypeController.java
 * Package Name:com.box.boxmanage.controller
 * Date:2018年4月4日上午11:03:12
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.EncryptUtil;
import com.box.framework.utils.Sequence;
import com.box.uums.controller.UserContoller;
import com.box.uums.model.Role;
import com.box.uums.model.User;
import com.box.uums.model.UserRole;
import com.box.uums.service.UserService;

/**
 * ClassName:BoxTypeController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午11:03:12
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/boxmanage")
public class BoxTypeController {
	   private static final Logger LOGGER = LoggerFactory.getLogger(BoxTypeController.class);
	  
	   @Resource
	   BoxTypeService boxTypeService;
	 
	   @RequestMapping(method = RequestMethod.GET, value = "/boxType")
	   protected String user() {
	     return "boxmanage/boxType";
       }
	 /**
	  * 查找盒型列表
	  * boxTypeList:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	    @RequestMapping(method = RequestMethod.GET, value = "/boxTypeList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> boxTypeList(@Param(value = "params") String searchparams){
	    	JSONObject jsonObj = JSONObject.parseObject(searchparams);
			Map<String,String> map = new HashMap<String,String>();
			if(jsonObj != null){
		    	map.put("prRid", jsonObj.getString("province"));
		    	map.put("ciRid", jsonObj.getString("city"));
		    	map.put("arRid", jsonObj.getString("area"));
		    	map.put("streetId", jsonObj.getString("town"));
		    	map.put("communityName", jsonObj.getString("communityName"));
		    	map.put("communityId", jsonObj.getString("communityId"));
		    	map.put("status", jsonObj.getString("status"));
			}
			List<Map<String,Object>> list = boxTypeService.getBoxTypelist(map);
	   		return list;
	   	}	

	    @RequestMapping(method = RequestMethod.POST, value = "/editBoxType")
	    @ResponseBody
	    private Result editUser(@Param(value = "params") String params) {
	    	BoxType box  = JSON.parseObject(params,BoxType.class);
	    	Map<String,Object> paramsMap = new HashMap<String,Object>();
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	    	String flag = jsonObj.getString("flag");
	    	boolean result;
	    	switch(flag){
	    	//盒型新增
	    		case "1":

			    	result = boxTypeService.save(box);
	    	    	return result ? new Result(true,"新增成功") : new Result(false,"新增失败");
	    		
	    	    		
	    		case "2":

	    	    	result = boxTypeService.update(box);
	    	    	return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
	    			
	    	}
	    	return new Result(false,"操作失败");
	    	
	    }
}

