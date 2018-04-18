
 /**
 * Project Name:Box-management
 * File Name:TechnologyPriceController.java
 * Package Name:com.box.technology.controller
 * Date:2018年4月12日下午7:41:03
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.technology.controller;

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
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.Sequence;
import com.box.technology.model.TechnologyPrice;
import com.box.technology.service.TechnologyPriceService;

/**
 * ClassName:TechnologyPriceController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 下午7:41:03
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/technology")
public class TechnologyPriceController {
	  private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyPriceController.class);
	  
	   @Resource
	   TechnologyPriceService technologyPriceService;
	 
	   @RequestMapping(method = RequestMethod.GET, value = "/technologyPrice")
	   protected String user() {
	     return "technology/technologyPrice";
      }
	   
	 /**
	  * 获取全部工艺价格列表
	  * technologyPriceList:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @return
	  * @since JDK 1.8
	  */
	    @RequestMapping(method = RequestMethod.GET, value = "/technologyPriceList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> technologyPriceList(){
	    	List<Map<String,Object>> list = technologyPriceService.getAllPriceList();
	   		return list;
	   	}
	    
     /**
      * 工艺价格的新增与修改
      * editTechnologyPrice:(这里用一句话描述这个方法的作用).
      *
      * @author cheng
      * @param params
      * @return
      * @since JDK 1.8
      */
	    @RequestMapping(method = RequestMethod.POST, value = "/editTechnologyPrice")
	    @ResponseBody
	    private Result editTechnologyPrice(@Param(value = "params") String params) {
	    	TechnologyPrice technologyPrice = JSON.parseObject(params,TechnologyPrice.class);
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	    	String flag = jsonObj.getString("flag");
	    	boolean result;
				switch(flag){
				   //盒型新增
				   case "1": 
					     technologyPrice.setId(Sequence.nextId());
					     technologyPrice.setCreatetime(new Date());
					     technologyPrice.setCreateby(SecurityUtil.getUser().getId());
						 result = technologyPriceService.save(technologyPrice);
				    	 return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
				    	    		
				   case "2":
				    	 result = technologyPriceService.update(technologyPrice);
				    	 return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
				    			
				 }				
				return new Result(false,"操作失败");
	    }
	    
	    /**
        * 删除工艺价格
        * deleteTechnologyPrice:(这里用一句话描述这个方法的作用).
        *
        * @author cheng
        * @param techIds
        * @return
        * @since JDK 1.8
        */
		 @RequestMapping(method = RequestMethod.POST, value = "deleteTechnologyPrice")
		 @ResponseBody
		 private Result deleteTechnologyPrice(@Param(value = "techIds") String techIds) {
			  List<String> list = JSON.parseArray(techIds, String.class);
			  boolean result=technologyPriceService.batchDeleteById(list);
			    if(result){
			    	return new Result(true,"删除成功！");
		    	   }else{
			    		return new Result(false,"删除失败！");
		   	       } 	
		  
		  }
		  
		 
	     /**
	      * 查找工艺价格依据名称的模糊查询
	      * boxClassificationSearchList:(这里用一句话描述这个方法的作用).
	      *
	      * @author cheng
	      * @param name
	      * @return
	      * @since JDK 1.8
	      */
		 @RequestMapping(method = RequestMethod.GET, value = "/technologyPriceSearchList")
		 @ResponseBody
		 private List<Map<String,Object>> technologyPriceSearchList(@RequestParam String params) {
		     JSONObject jsonObj = JSONObject.parseObject(params);
		     String name= jsonObj.getString("search-technologyName");
		     String parentId=jsonObj.getString("search-type");
		     String cname=jsonObj.getString("search-cname");
		     Map<String,Object> map=new HashMap<String,Object>();
		     map.put("name", name);
		     map.put("parentId",parentId);
		     map.put("cname",cname);
		     List<Map<String,Object>> list= technologyPriceService.technologyPriceSearchList(map);
			 return list;
		  
		  }

}

