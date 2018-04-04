
 /**
 * Project Name:Box-management
 * File Name:BoxClassificationController.java
 * Package Name:com.box.boxmanage.controller
 * Date:2018年4月4日上午11:09:55
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
import com.box.boxmanage.model.BoxClassification;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.framework.pojo.Result;

/**
 * ClassName:BoxClassificationController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午11:09:55
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/boxmanage")
public class BoxClassificationController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(BoxTypeController.class);
	  
	   @Resource
	   BoxClassficationService boxClassificationService;
	 
	   @RequestMapping(method = RequestMethod.GET, value = "/boxClassification")
	   protected String user() {
	     return "boxmanage/boxClassification";
     }
	 /**
	  * 查找纸盒类型列表
	  * boxTypeList:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	    @RequestMapping(method = RequestMethod.GET, value = "/boxClassficationList")
	   	@ResponseBody
	   	protected List<BoxClassification> boxTypeList(@Param(value = "params") String searchparams){
	    	JSONObject jsonObj = JSONObject.parseObject(searchparams);
			Map<String,String> map = new HashMap<String,String>();
			if(jsonObj != null){
		    	map.put("prRid", jsonObj.getString("province"));
		    	map.put("ciRid", jsonObj.getString("city"));
		    	map.put("arRid", jsonObj.getString("area"));
			}
			List<BoxClassification> list = boxClassificationService.getAllList();
	   		return list;
	   	}	
       /**
        * 对纸盒类型进行新增和修改操作
        * editUser:(这里用一句话描述这个方法的作用).
        *
        * @author cheng
        * @param params
        * @return
        * @since JDK 1.8
        */
	    @RequestMapping(method = RequestMethod.POST, value = "/editBoxClassification")
	    @ResponseBody
	    private Result editUser(@Param(value = "params") String params) {
	    	BoxClassification box  = JSON.parseObject(params,BoxClassification.class);
	    	Map<String,Object> paramsMap = new HashMap<String,Object>();
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	    	String flag = jsonObj.getString("flag");
	    	boolean result;
	    	switch(flag){
	    	//盒型新增
	    		case "1":

			    	result = boxClassificationService.save(box);
	    	    	return result ? new Result(true,"新增成功") : new Result(false,"新增失败");
	    		
	    	    		
	    		case "2":

	    	    	result = boxClassificationService.update(box);
	    	    	return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
	    			
	    	}
	    	return new Result(false,"操作失败");
	    	
	    }
}

