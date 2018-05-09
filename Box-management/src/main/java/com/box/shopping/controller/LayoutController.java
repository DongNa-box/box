
 /**
 * Project Name:Box-management
 * File Name:LayoutController.java
 * Package Name:com.box.shopping.controller
 * Date:2018年4月6日下午2:11:35
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.shopping.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Layout;
import org.apache.shiro.SecurityUtils;
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
import com.box.shopping.model.LayoutSize;
import com.box.shopping.service.LayoutDetailService;
import com.box.shopping.service.LayoutSizeService;
import com.box.uums.controller.FunctionController;
import com.box.uums.model.Role;
import com.box.uums.model.User;
import com.box.uums.model.UserRole;
import com.box.uums.service.UserService;

/**
 * ClassName:LayoutController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月6日 下午2:11:35
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/layout")
public class LayoutController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(LayoutController.class);

	 @Resource
	 LayoutSizeService layoutSizeService;
	 @Resource
	 LayoutDetailService layoutDetailService;
	 @Resource
	 UserService userService;
	 @Resource
	 BoxTypeService boxTypeService;
	 @RequestMapping(method = RequestMethod.GET, value = "/detail")
	    private String detail() {
	        return "layout/detail";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/size")
	    private String size() {
	        return "layout/size";
	    }
	 /**
	  * 查询尺寸列表
	  * sizeList:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @param searchparams
	  * @return
	  * @since JDK 1.8
	  */
	 @RequestMapping(method = RequestMethod.GET, value = "/sizeList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> sizeList(@Param(value = "params") String params){
	    	JSONObject jsonObj = JSONObject.parseObject(params);
	   		Map<String,Object> map = new HashMap<String,Object>();
	   		List<Map<String,Object>> list = null;
	   		if(jsonObj!=null ){
	   			map.put("size", jsonObj.getString("search-size"));
		    	map.put("name", jsonObj.getString("search-name"));
		    	map.put("type", jsonObj.getString("search-type"));
			}
	   		list = layoutSizeService.getLayoutSizelist(map);
	   		for (int i = 0; i < list.size(); i++) {
	   			if (list.get(i).get("createby")!=null&list.get(i).get("createby")!="") {
	   				User user = userService.get(String.valueOf(list.get(i).get("createby")));
					if (user!=null) {
						list.get(i).put("createby", user.getLoginName());
					}
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
	 @RequestMapping(method = RequestMethod.POST, value = "/deleteSize")
	    @ResponseBody
	    private Result deleteUser(@Param(value = "ids")String ids) {
		 List<String> list = JSON.parseArray(ids, String.class);
	    	boolean result = layoutSizeService.batchDeleteById(list);
	    	return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
	    
	 }
	/**
	 * 新增修改size
	 * editSize:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	 @RequestMapping(method = RequestMethod.POST, value = "/editSize")
	@ResponseBody
	private Result editSize(@Param(value = "params") String params) {
		LayoutSize layoutSize  = JSON.parseObject(params,LayoutSize.class);
		
		JSONObject jsonObj = JSONObject.parseObject(params);
		String flag = jsonObj.getString("flag");
		boolean result;
		switch(flag){
		//新增
			case "1":
				layoutSize.setId(Sequence.nextId());
				layoutSize.setCreateby(SecurityUtil.getUser().getId());
				layoutSize.setCreatetime(DateUtil.getCurrDate());
				result = layoutSizeService.save(layoutSize);
				return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
			case "2":
				result = layoutSizeService.update(layoutSize);
				return result ? new Result(true,"修改成功") : new Result(false,"修改失败");	
		}
		return new Result(false,"操作失败");
		
}
	 /**
	  * 查询排版细节列表
	  * detailList:(这里用一句话描述这个方法的作用).
	  *
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
	   			map.put("boxName", jsonObj.getString("search-boxName"));
	   			map.put("boxLength", jsonObj.getString("search-boxLength"));
			   	map.put("boxWidth", jsonObj.getString("search-boxWidth"));
			    map.put("boxHeight", jsonObj.getString("search-boxHeight"));
		    	map.put("paperLength",jsonObj.getString("search-paperLength"));
		    	map.put("paperWidth", jsonObj.getString("search-paperWidth"));
				map.put("paperXValue", jsonObj.getString("search-paperXValue"));
	    		map.put("xnumber", jsonObj.getString("search-xnumber"));
				map.put("ynumber", jsonObj.getString("search-ynumber"));
	       	}
	   		list = layoutDetailService.getLayoutDetailList(map);
	   		for (int i = 0; i < list.size(); i++) {
	   			if (list.get(i).get("createby")!=null&list.get(i).get("createby")!="") {
	   				User user = userService.get(String.valueOf(list.get(i).get("createby")));
	   				if (user!=null) {
	   					list.get(i).put("createby", user.getLoginName());
						list.get(i).put("userId", user.getLoginName());
					}
				}
	   			
	   		}
	   		return list;
	 }
	    /**
	     * 名称去重
	     * checkNameExists:(这里用一句话描述这个方法的作用).
	     *
	     * @author Administrator
	     * @param loginName
	     * @return
	     * @since JDK 1.8
	     */
	   @RequestMapping(method = RequestMethod.POST, value = "/checkNameExists")
	    @ResponseBody
	    private JSONObject checkNameExists(@RequestParam String name){
		   boolean result = layoutSizeService.checkNameExists(name);
	    	JSONObject jsonObj = new JSONObject();
	    	jsonObj.put("valid", (!result));
			return jsonObj;
	    }
}