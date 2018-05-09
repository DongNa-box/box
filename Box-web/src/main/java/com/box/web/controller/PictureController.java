package com.box.web.controller;

import java.util.ArrayList;
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
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.TreeNode;
import com.box.shopping.service.LayoutDetailService;
import com.box.shopping.service.ShoppingDetailService;
import com.box.shopping.service.ShoppingRateService;
import com.box.technology.service.TechnologyDetailService;

/**
 * Project Name:Box-web
 * File Name:PictureController.java
 * Package Name:
 * Date:2018年4月12日上午10:27:20
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

@Controller
@RequestMapping(value = "/web")
public class PictureController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);
	
	 @Resource
	 BoxClassficationService boxClassficationService;
	 @Resource
	 BoxTypeService boxTypeService;
	 @Resource
	 TechnologyDetailService technologyDetailService;
	 @Resource 
	 ShoppingDetailService shoppingDetailService;
	 @Resource
	 LayoutDetailService layoutDetailService;
	 @Resource
	 ShoppingRateService shoppingRateService;
	 @RequestMapping(method = RequestMethod.GET, value = "/picture")
	    private String picture() {
	        return "web/picture";
	    }
	 /**
	  * 查询下拉框
	  * getMenuTree:(这里用一句话描述这个方法的作用).
	  *
	  * @author luowen
	  * @return
	  * @since JDK 1.8
	  */
	@RequestMapping(method = RequestMethod.POST, value = "/boxTree")
    @ResponseBody
    public List<TreeNode> getMenuTree() {
    	List<TreeNode> webmenu = boxClassficationService.getWebTree();
        return webmenu;
    }
	/**
	 * 查询盒型(页数)
	 * pictureList:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @return
	 * @since JDK 1.8
	 */
	
	@RequestMapping(method = RequestMethod.POST, value = "/pictureList")
    @ResponseBody
    public List<Map<String, Object>> pictureList(@Param(value = "params") String params){
		JSONObject jsonObj = JSONObject.parseObject(params);
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String,Object> map = new HashMap<String,Object>();
		if(jsonObj!=null ){
   			map.put("classid", jsonObj.getString("search-box"));
	    	map.put("detail1", jsonObj.getString("search-detail0"));
	    	map.put("detail2", jsonObj.getString("search-detail1"));
	    	map.put("detail3", jsonObj.getString("search-detail2"));
	    	
		}
		/*int page=jsonObj.getInteger("page");
		page=(page-1)*12;
		map.put("page", page);*/
		list = boxTypeService.getWebBoxTypeList(map);
		return list;
	}
}
