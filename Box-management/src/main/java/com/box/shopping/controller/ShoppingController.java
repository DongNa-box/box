
 /**
 * Project Name:Box-management
 * File Name:ShoppingController.java
 * Package Name:com.box.shopping.controller
 * Date:2018年4月6日下午2:11:51
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.shopping.controller;

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
import com.box.framework.pojo.Result;
import com.box.framework.utils.Sequence;
import com.box.shopping.model.LayoutSize;
import com.box.shopping.model.ShoppingPantone;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.ShoppingDeatilService;
import com.box.shopping.service.ShoppingPantoneService;
import com.box.shopping.service.ShoppingRateService;
import com.box.uums.controller.FunctionController;

/**
 * ClassName:ShoppingController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月6日 下午2:11:51
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/shopping")
public class ShoppingController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingController.class);

	 @Resource
	 ShoppingRateService shoppingRateService;
	 @Resource
	 ShoppingDeatilService shoppingDeatilService;
	 @Resource
	 ShoppingPantoneService shoppingPantoneService;
	 @RequestMapping(method = RequestMethod.GET, value = "/detail")
	    private String detail() {
	        return "shopping/detail";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/rate")
	    private String rate() {
	        return "shopping/rate";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/pantone")
	    private String pantone() {
	        return "shopping/pantone";
	    }
	 //利率操作
	 /**
	  * 查询尺寸列表
	  * rateList:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.GET, value = "/rateList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> rateList(@Param(value = "params") String searchparams){
	    	JSONObject jsonObj = JSONObject.parseObject(searchparams);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("value", jsonObj.getString("search-value"));
		    	map.put("type", jsonObj.getString("search-type"));
		    	
	       	}
	   		list = shoppingRateService.getRatelist(map);
	   		return list;
	   	}
	 /**
	  * 删除列表
	  * deleteUser:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param ids
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteRate")
	    @ResponseBody
	    private Result deleteRate(@Param(value = "ids")String ids) {
		 List<String> list = JSON.parseArray(ids, String.class);
	    	boolean result = shoppingRateService.batchDeleteRate(list);
	    	return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
	    
	 }
	/**
	 * 新增修改rate
	 * editRate:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	 @RequestMapping(method = RequestMethod.POST, value = "/editRate")
	@ResponseBody
	private Result editRate(@Param(value = "params") String params) {
		ShoppingRate shoppingRate  = JSON.parseObject(params,ShoppingRate.class);
		
		JSONObject jsonObj = JSONObject.parseObject(params);
		String flag = jsonObj.getString("flag");
		boolean result;
		switch(flag){
		//新增
			case "1":
				shoppingRate.setId(Sequence.nextId());
				result = shoppingRateService.save(shoppingRate);
				return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
			case "2":
				result = shoppingRateService.update(shoppingRate);
				return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
		}
		return new Result(false,"操作失败");
		
}
	 //Pantone色号操作
	 /**
	  * 查询尺寸列表
	  * pantoneList:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.GET, value = "/pantoneList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> pantoneList(@Param(value = "params") String searchparams){
	    	JSONObject jsonObj = JSONObject.parseObject(searchparams);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("tid", jsonObj.getString("search-tid"));
		    	map.put("colorNum", jsonObj.getString("search-colorNum"));
		    	map.put("attr1", jsonObj.getString("search-attr1"));
		    	map.put("attr2", jsonObj.getString("search-attr2"));
		    	map.put("attr3", jsonObj.getString("search-attr3"));
	       	}
	   		list = shoppingPantoneService.getPantoneList(map);
	   		return list;
	   	}
	 /**
	  * 删除列表
	  * deletePantone:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param ids
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.POST, value = "/deletePantone")
	    @ResponseBody
	    private Result deleteUser(@Param(value = "ids")String ids) {
		 List<String> list = JSON.parseArray(ids, String.class);
	    	boolean result = shoppingPantoneService.batchDeletePantone(list);
	    	return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
	    
	 }
	/**
	 * 新增修改pantone
	 * editpantone:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	 @RequestMapping(method = RequestMethod.POST, value = "/editPantone")
	@ResponseBody
	private Result editPantone(@Param(value = "params") String params) {
		ShoppingPantone shoppingPantone  = JSON.parseObject(params,ShoppingPantone.class);
		
		JSONObject jsonObj = JSONObject.parseObject(params);
		String flag = jsonObj.getString("flag");
		boolean result;
		switch(flag){
		//新增
			case "1":
				shoppingPantone.setId(Sequence.nextId());
				result = shoppingPantoneService.save(shoppingPantone);
				return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
			case "2":
				result = shoppingPantoneService.update(shoppingPantone);
				return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
		}
		return new Result(false,"操作失败");
		
}
	 @RequestMapping(method = RequestMethod.GET, value = "/detailList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> detailList(@Param(value = "params") String searchparams){
		 JSONObject jsonObj = JSONObject.parseObject(searchparams);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("boxId", jsonObj.getString("search-boxId"));
		    	map.put("userId", jsonObj.getString("search-userId"));
		    	map.put("layoutId", jsonObj.getString("search-layoutId"));

	       	}
	   		list = shoppingDeatilService.getShoppingDetailList(map);
	   		return list;
	 }
}

