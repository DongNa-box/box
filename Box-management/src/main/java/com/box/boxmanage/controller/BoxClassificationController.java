
 /**
 * Project Name:Box-management
 * File Name:BoxClassificationController.java
 * Package Name:com.box.boxmanage.controller
 * Date:2018年4月4日上午11:09:55
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxClassification;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.Sequence;

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
	 protected String boxClassification() {
	     return "boxmanage/boxClassification";
     }
	   
	 /**
	  * 查找纸盒类型列表
	  * boxClassificationList:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	    @RequestMapping(method = RequestMethod.GET, value = "/boxClassificationList")
	   	@ResponseBody
	   	protected List<BoxClassification> boxClassificationList(){
			List<BoxClassification> list = boxClassificationService.getAllList();
	   		return list;
	   	}	
	
       /**
        * 对纸盒类型进行新增和修改操作
        * editBoxClassification:(这里用一句话描述这个方法的作用).
        *
        * @author cheng
        * @param params
        * @return
        * @since JDK 1.8
        */
	    @RequestMapping(method = RequestMethod.POST, value = "/editBoxClassification")
	    @ResponseBody
	    private Result editBoxClassification(@Param(value = "params") String params) {
	    	BoxClassification box  = JSON.parseObject(params,BoxClassification.class);
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	    	String flag = jsonObj.getString("flag");
	    	boolean result;
	    	switch(flag){
	    	//盒型新增
	    		case "1":
	    			box.setId(Sequence.nextId());
	    			box.setCreateby(SecurityUtil.getUser().getId());
	    			box.setCreatetime(new Date());
			    	result = boxClassificationService.save(box);
	    	    	return result ? new Result(true,"新增成功") : new Result(false,"新增失败");
           //盒型修改
	    		case "2":
	    	    	result = boxClassificationService.update(box);
	    	    	return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
	    			
	    	}
	    	return new Result(false,"操作失败");
	    	
	    }
	    
        /**
         * 删除纸盒类型
         * deleteBoxClassification:(这里用一句话描述这个方法的作用).
         *
         * @author cheng
         * @param boxCids
         * @return
         * @since JDK 1.8
         */
		 @RequestMapping(method = RequestMethod.POST, value = "deleteBoxClassification")
		 @ResponseBody
		 private Result deleteBoxClassification(@Param(value = "boxCids") String boxCids) {
			  List<String> list = JSON.parseArray(boxCids, String.class);
			  boolean result=boxClassificationService.batchDeleteById(list);
			    if(result){
			    	return new Result(true,"删除成功！");
		    	   }else{
			    		return new Result(false,"删除失败！");
		   	       } 	
		  
		  }
		 
	     /**
	      * 查找包装盒类型依据名称的模糊查询
	      * boxClassificationSearchList:(这里用一句话描述这个方法的作用).
	      *
	      * @author cheng
	      * @param name
	      * @return
	      * @since JDK 1.8
	      */
		 @RequestMapping(method = RequestMethod.GET, value = "boxClassificationSearchList")
		 @ResponseBody
		 private List<BoxClassification> boxClassificationSearchList(@RequestParam String params) {
		     JSONObject jsonObj = JSONObject.parseObject(params);
		     String name= jsonObj.getString("search-name");
			 List<BoxClassification> list= boxClassificationService.boxClassificationSearchList(name);
			 return list;
		  
		  }
		 
		/**
		 * 获取不同级别的列表
		 * getBoxClassLevelList:(这里用一句话描述这个方法的作用).
		 *
		 * @author cheng
		 * @param params
		 * @return
		 * @since JDK 1.8
		 */
		 @RequestMapping(method = RequestMethod.POST, value = "/getBoxClassLevelList")
		 @ResponseBody
		 private Object getBoxClassLevelList(@Param(value = "params") String params) {
		     JSONObject jsonObj = JSONObject.parseObject(params);
			 String level=jsonObj.getString("level");
		     List<BoxClassification> boxClassification=new ArrayList<BoxClassification>();
		     if(level.equals("0")){
		    	 boxClassification = boxClassificationService.getBoxClassificaionByLevel(level);
		     }else{
		       	 Map<String,Object> map=new HashMap<String,Object>();	
		         if(jsonObj.getString("groupid")!=null){
			    	   String groupid= jsonObj.getString("groupid");
			    	   map.put("groupid", groupid);
			     }
		    	 map.put("level", level);
		    	 boxClassification = boxClassificationService.getBoxClassificaionByGroupLevel(map);
		     }
		     return boxClassification;
		 }
		
		 /**
		  * 通过groupId获取纸盒类型
		  * getBoxClassifByGroupid:(这里用一句话描述这个方法的作用).
		  *
		  * @author cheng
		  * @param params
		  * @return
		  * @since JDK 1.8
		  */
		 @RequestMapping(method = RequestMethod.POST, value = "/getBoxClassifByGroupid")
		 @ResponseBody
		 private Object  getBoxClassifByGroupid(@Param(value = "params") String params) {
		     String groupid=params.replaceAll("\"","").replace("[", "").replace("]", "");
		     BoxClassification boxClassification= boxClassificationService.getBoxClassificaionByGroupid(groupid);
		     return boxClassification;
		 }
}

