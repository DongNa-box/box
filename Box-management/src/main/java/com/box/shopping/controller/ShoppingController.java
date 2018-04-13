
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
import org.apache.ibatis.annotations.Select;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.Sequence;
import com.box.framework.utils.StrUtil;
import com.box.shopping.model.LayoutSize;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.model.ShoppingPantone;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.ShoppingDeatilService;
import com.box.shopping.service.ShoppingOrderService;
import com.box.shopping.service.ShoppingPantoneService;
import com.box.shopping.service.ShoppingRateService;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;
import com.box.uums.controller.FunctionController;
import com.box.uums.model.User;
import com.box.uums.service.UserService;

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
	 @Resource
	 UserService userService;
	 @Resource
	 BoxTypeService boxTypeService;
	 @Resource
	 TechnologyDetailService technologyDetailService;
	 @Resource
	 ShoppingOrderService shoppingOrderService;
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
	   	protected List<Map<String,Object>> rateList(@Param(value = "params") String params){
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("value", jsonObj.getString("search-value"));
		    	map.put("type", Integer.valueOf(jsonObj.getString("search-type")));
		    	
	       	}
	   		list = shoppingRateService.getRateList(map);
	   		
	   		for (int i = 0; i < list.size(); i++) {
	   			if (list.get(i).get("createby")!=null&list.get(i).get("createby")!="") {
	   				User user = userService.get(String.valueOf(list.get(i).get("createby")));
					list.get(i).put("createby", user.getLoginName());
				}
				
			}
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
	    	boolean result = shoppingRateService.batchDeleteById(list);
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
				shoppingRate.setCreateby(SecurityUtil.getUser().getId());
				shoppingRate.setCreatetime(DateUtil.getCurrDate());
				result = shoppingRateService.save(shoppingRate);
				return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
			case "2":
				result = shoppingRateService.update(shoppingRate);
				return result ? new Result(true,"修改成功") : new Result(false,"修改失败");	
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
	   	protected List<Map<String,Object>> pantoneList(@Param(value = "params") String params){
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("tid", jsonObj.getString("search-tid"));
	   			map.put("colorNum", jsonObj.getString("search-colorNum"));
		    	map.put("attr1", jsonObj.getString("search-attr1"));
	       	}
	   		list = shoppingPantoneService.getPantoneList(map);
	   		for (int i = 0; i < list.size(); i++) {
	   			if (list.get(i).get("createby")!=null&list.get(i).get("createby")!="") {
	   				User user = userService.get(String.valueOf(list.get(i).get("createby")));
					list.get(i).put("createby", user.getLoginName());
				}
				
			}
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
	    	boolean result = shoppingPantoneService.batchDeleteById(list);
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
	 /**
	  * 购物车查询表
	  * detailList:(这里用一句话描述这个方法的作用).
	  *@Param(value = "params") String searchparams
	  * @author luowen
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.GET, value = "/detailList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> detailList(@Param(value = "params") String params){
		 JSONObject jsonObj = JSONObject.parseObject(params);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("boxId", jsonObj.getString("search-boxId"));
		    	map.put("userId", jsonObj.getString("search-userId"));
		    	map.put("layoutId", jsonObj.getString("search-layoutId"));
		    	map.put("pricePaperId", jsonObj.getString("search-pricePaperId"));
		    	map.put("paperGramsId", jsonObj.getString("search-paperGramsId"));
		    	map.put("printColorId", jsonObj.getString("search-printColorId"));
		    	map.put("surfaceTreatmentId", jsonObj.getString("search-surfaceTreatmentId"));
		    	map.put("receiveAreaId", jsonObj.getString("search-receiveAreaId"));
			    map.put("isBronzing", jsonObj.getString("search-isBronzing"));
		    	map.put("isConvex", jsonObj.getString("search-isConvex"));
		    	map.put("isUv", jsonObj.getString("search-isUv"));
		    	map.put("isPvc",jsonObj.getString("search-isPvc"));
				
	   		}
	   		list = shoppingDeatilService.getShoppingDetailList(map);
	   		for (int i = 0; i < list.size(); i++) {
	   			if (list.get(i).get("createby")!=null&list.get(i).get("createby")!="") {
	   				User user = userService.get(String.valueOf(list.get(i).get("createby")));
					list.get(i).put("createby", user.getLoginName());
					list.get(i).put("userId", user.getLoginName());
				}
	   			if (list.get(i).get("boxId")!=null&list.get(i).get("boxId")!="") {
	   				BoxType boxType = boxTypeService.get(String.valueOf(list.get(i).get("boxId")));
					list.get(i).put("boxId", boxType.getName());
				}
	   			if (list.get(i).get("pricePaperId")!=null&list.get(i).get("pricePaperId")!="") {
	   				TechnologyDetail te = technologyDetailService.get(String.valueOf(list.get(i).get("pricePaperId")));
					list.get(i).put("pricePaperId", te.getName());
				}
	   			if (list.get(i).get("paperGramsId")!=null&list.get(i).get("paperGramsId")!="") {
	   				TechnologyDetail te = technologyDetailService.get(String.valueOf(list.get(i).get("paperGramsId")));
					list.get(i).put("paperGramsId", te.getName());
				}
	   			if (list.get(i).get("printColorId")!=null&list.get(i).get("printColorId")!="") {
	   				TechnologyDetail te = technologyDetailService.get(String.valueOf(list.get(i).get("printColorId")));
					list.get(i).put("printColorId", te.getName());
				}
	   			if (list.get(i).get("surfaceTreatmentId")!=null&list.get(i).get("surfaceTreatmentId")!="") {
	   				TechnologyDetail te = technologyDetailService.get(String.valueOf(list.get(i).get("surfaceTreatmentId")));
					list.get(i).put("surfaceTreatmentId", te.getName());
				}
	   			if (list.get(i).get("receiveAreaId")!=null&list.get(i).get("receiveAreaId")!="") {
	   				TechnologyDetail te = technologyDetailService.get(String.valueOf(list.get(i).get("receiveAreaId")));
					list.get(i).put("receiveAreaId", te.getName());
				}
				
			}
	   		return list;
	 }
	 /**
	  * 查询订单列表
	  * orderList:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param params
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.GET, value = "/orderList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> orderList(@Param(value = "params") String params){
		 JSONObject jsonObj = JSONObject.parseObject(params);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("shoppingId", jsonObj.getString("search-shoppingId"));
		    	map.put("orderStatus", jsonObj.getString("search-orderStatus"));
	   		}
	   		list = shoppingOrderService.getShoppingOrderList(map);
	   		return list;
	 }
	 /**
	  * 删除订单
	  * deleteOrder:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param ids
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteOrder")
	    @ResponseBody
	    private Result deleteOrder(@Param(value = "ids")String ids) {
		 List<String> list = JSON.parseArray(ids, String.class);
	    	boolean result = shoppingOrderService.batchDeleteById(list);
	    	return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
	    
	 }
}

