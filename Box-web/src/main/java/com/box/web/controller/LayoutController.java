package com.box.web.controller;

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
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.model.ShoppingDetail;
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
public class LayoutController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(LayoutController.class);
	
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
	 @RequestMapping(method = RequestMethod.GET, value = "/layout")
	    private String layout() {
	        return "web/layout";
	    }
	/**
	 * 查询价格信息
	 * getShopping:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getShopping")
    @ResponseBody
    public ShoppingDetail getShopping(@Param(value = "params") String params){
		JSONObject jsonObj = JSONObject.parseObject(params);
		ShoppingDetail shoppingDetail = shoppingDetailService.get(jsonObj.getString("shoppingId"));
		return shoppingDetail;
	}
	/**
	 * 查询排版信息
	 * getLayout:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getLayout")
    @ResponseBody
    public LayoutDetail getLayout(@Param(value = "params") String params){
		JSONObject jsonObj = JSONObject.parseObject(params);
		LayoutDetail shoppingDetail = layoutDetailService.get(jsonObj.getString("layoutId"));
		return shoppingDetail;
	}
	
}

