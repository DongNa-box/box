
 /**
 * Project Name:Box-management
 * File Name:TechnologyDetailController.java
 * Package Name:com.box.technology.controller
 * Date:2018年4月4日上午11:03:12
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.technology.controller;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxClassification;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.FileUtil;
import com.box.framework.utils.Sequence;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;


/**
 * ClassName:TechnologyDetailController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午10:03:12
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/technology")
public class TechnologyDetailController {
	   private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyDetailController.class);
	  
	   @Resource
	   TechnologyDetailService technologyDetailService;
	 
	   @RequestMapping(method = RequestMethod.GET, value = "/technologyDetail")
	   protected String user() {
	     return "technology/technologyDetail";
       }
	   
	 /**
	  * 获取全部工艺列表
	  * TechnologyDetailList:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @return
	  * @since JDK 1.8
	  */
	    @RequestMapping(method = RequestMethod.GET, value = "/technologyDetailList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> technologyDetailList(){
	    	List<Map<String,Object>> list = technologyDetailService.getAllTechnologyList();
	   		return list;
	   	}
	    
      /**
       * 工艺类型的新增与修改
       * editTechnologyDetail:(这里用一句话描述这个方法的作用).
       *
       * @author cheng
       * @param params
       * @return
       * @since JDK 1.8
       */
	    @RequestMapping(method = RequestMethod.POST, value = "/editTechnologyDetail")
	    @ResponseBody
	    private Result editTechnologyDetail(@Param(value = "params") String params) {
	    	TechnologyDetail technologyDetail = JSON.parseObject(params,TechnologyDetail.class);
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	    	String flag = jsonObj.getString("flag");
	    	boolean result;
				switch(flag){
				   //盒型新增
				   case "1": 
					     technologyDetail.setId(Sequence.nextId());
					     technologyDetail.setCreatetime(new Date());
					     technologyDetail.setCreateby(SecurityUtil.getUser().getId());
						 result = technologyDetailService.save(technologyDetail);
				    	 return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
				    	    		
				   case "2":
				    	 result = technologyDetailService.update(technologyDetail);
				    	 return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
				    			
				 }				
				return new Result(false,"操作失败");
	    }
	    
	    /**
         * 删除工艺类型
         * deleteTechnologyDetail:(这里用一句话描述这个方法的作用).
         *
         * @author cheng
         * @param techIds
         * @return
         * @since JDK 1.8
         */
		 @RequestMapping(method = RequestMethod.POST, value = "deleteTechnologyDetail")
		 @ResponseBody
		 private Result deleteTechnologyDetail(@Param(value = "techIds") String techIds) {
			  List<String> list = JSON.parseArray(techIds, String.class);
			  boolean result=technologyDetailService.batchDeleteById(list);
			    if(result){
			    	return new Result(true,"删除成功！");
		    	   }else{
			    		return new Result(false,"删除失败！");
		   	       } 	
		  
		  }
		 
		 /**
		  * 工艺名称的唯一性
		  * checkTechnologyDetailNameExists:(这里用一句话描述这个方法的作用).
		  *
		  * @author cheng
		  * @param name
		  * @return
		  * @since JDK 1.8
		  */
		 @RequestMapping(method = RequestMethod.POST, value = "/checkTechnologyDetailNameExists")
		 @ResponseBody
		 private JSONObject checkTechnologyDetailNameExists(@RequestParam String name){
		    	boolean result = technologyDetailService.checkTechnologyDetailNameExists(name);
		    	JSONObject jsonObj = new JSONObject();
		    	jsonObj.put("valid", (!result));
				return jsonObj;
		 }	 
		 
	     /**
	      * 查找工艺类型依据名称的模糊查询
	      * boxClassificationSearchList:(这里用一句话描述这个方法的作用).
	      *
	      * @author cheng
	      * @param name
	      * @return
	      * @since JDK 1.8
	      */
		 @RequestMapping(method = RequestMethod.GET, value = "/technologyDetailSearchList")
		 @ResponseBody
		 private List<Map<String,Object>> TechnologyDetailSearchList(@RequestParam String params) {
		     JSONObject jsonObj = JSONObject.parseObject(params);
		     String name= jsonObj.getString("search-technologyName");
		     String parentId=jsonObj.getString("search-type");
		     String cname=jsonObj.getString("search-cname");
		     Map<String,Object> map=new HashMap<String,Object>();
		     map.put("name", name);
		     map.put("parentId",parentId);
		     map.put("cname",cname);
		     List<Map<String,Object>> list= technologyDetailService.technologyDetailSearchList(map);
			 return list;
		  
		  }

		/**
		 * 获取不同级别的列表
		 * getTechonolgyLevelList:(这里用一句话描述这个方法的作用).
		 *
		 * @author cheng
		 * @param params
		 * @return
		 * @since JDK 1.8
		 */
		 @RequestMapping(method = RequestMethod.POST, value = "/getTechonolgyLevelList")
		 @ResponseBody
		 private Object getTechonolgyLevelList(@Param(value = "params") String params) {
		     JSONObject jsonObj = JSONObject.parseObject(params);
			 String level=jsonObj.getString("level");
		     List<TechnologyDetail> technologyDetail=new ArrayList<TechnologyDetail>();
		     if(level.equals("0")){
		    	 technologyDetail = technologyDetailService.getTechnologyByLevel(level);
		     }else{
		       	 Map<String,Object> map=new HashMap<String,Object>();	
		         if(jsonObj.getString("groupid")!=null){
			    	   String groupid= jsonObj.getString("groupid");
			    	   map.put("groupid", groupid);
			     }
		    	 map.put("level", level);
		    	 technologyDetail = technologyDetailService.getTechnologyByParentLevel(map);
		     }
		     return technologyDetail;
		 }
		
		 /**
		  * 通过Parentid获取纸盒类型
		  * getTechnologyByParentid:(这里用一句话描述这个方法的作用).
		  *
		  * @author cheng
		  * @param params
		  * @return
		  * @since JDK 1.8
		  */
		 @RequestMapping(method = RequestMethod.POST, value = "/getTechnologyByParentid")
		 @ResponseBody
		 private Object  getTechnologyByParentid(@Param(value = "params") String params) {
		     String groupid=params.replaceAll("\"","").replace("[", "").replace("]", "");
		     TechnologyDetail boxClassification= technologyDetailService.getTechnologyByParentid(groupid);
		     return boxClassification;
		 }
	

		 
}

