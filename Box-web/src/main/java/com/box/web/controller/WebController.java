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
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.TreeNode;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.Sequence;
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.service.ShoppingDeatilService;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;

/**
 * Project Name:Box-web
 * File Name:WebController.java
 * Package Name:
 * Date:2018年4月12日上午10:27:20
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

/**
 * ClassName:WebController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午10:27:20
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/web")
public class WebController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
	
	 @Resource
	 BoxClassficationService boxClassficationService;
	 @Resource
	 BoxTypeService boxTypeService;
	 @Resource
	 TechnologyDetailService technologyDetailService;
	 @Resource 
	 ShoppingDeatilService shoppingDeatilService;
	 @RequestMapping(method = RequestMethod.GET, value = "/picture")
	    private String picture() {
	        return "web/picture";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/xiadan")
	    private String xiadan() {
		 
	        return "web/xiadan";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/layout")
	    private String layout() {
	        return "web/layout";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/xiadan2")
	    private String xiadan2() {
	        return "web/xiadan2";
	    }
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
		int page=jsonObj.getInteger("page");
		page=(page-1)*12;
		map.put("page", page);
		list = boxTypeService.getWebBoxTypeList(map);
		return list;
	}
	/**
	 * 查询该盒型相关信息
	 * xiadanBox:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/xiadanBox")
    @ResponseBody
    public BoxType xiadanBox(@Param(value = "params") String params){
		JSONObject jsonObj = JSONObject.parseObject(params);
		List<Map<String, Object>> list = new ArrayList<>();
		String boxId=jsonObj.getString("boxId");
		BoxType boxType = boxTypeService.get(boxId);
		return boxType;
		
	}
	/**
	 * 查询所有工艺
	 * technology:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/technology")
    @ResponseBody
    public List<TreeNode> technology(){
		List<TreeNode> technologymenu = technologyDetailService.getTechnologyTree();
		return technologymenu;
	}
	/**
	 * 生成订单，排版
	 * createShoppingandLayout:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingandLayout")
    @ResponseBody
    public String createShoppingandLayout(@Param(value = "params") String params){
		JSONObject jsonObj = JSONObject.parseObject(params);
		LayoutDetail layoutDetail = new LayoutDetail();
		layoutDetail.setId(Sequence.nextId());
		layoutDetail.setBoxId(jsonObj.getString("boxId"));
		layoutDetail.setBoxLength(Double.valueOf(jsonObj.getString("boxLength")));
		layoutDetail.setBoxWidth(Double.valueOf(jsonObj.getString("boxWidth")));
		layoutDetail.setBoxWidth(Double.valueOf(jsonObj.getString("boxHighth")));
		layoutDetail.setBoxUnit(Integer.valueOf(jsonObj.getString("boxUnit")));
		layoutDetail.setCreateby(SecurityUtil.getUser().getId());
		layoutDetail.setCreatetime(DateUtil.getCurrDate());
		
		ShoppingDetail shoppingDetail = new ShoppingDetail();
		shoppingDetail.setShoppingId(Sequence.nextId());
		shoppingDetail.setBoxId(jsonObj.getString("boxId"));
		shoppingDetail.setLayoutId(layoutDetail.getId());
		shoppingDetail.setUserId(SecurityUtil.getUser().getId());
		shoppingDetail.setPrintPaperId(jsonObj.getString("printPaperId"));
		shoppingDetail.setPaperGramsId(jsonObj.getString("paperGramsId"));
		shoppingDetail.setPrintColorId(jsonObj.getString("printColorId"));
		//0单色 1多色
		if (jsonObj.getString("colorFlag").equals("0")&jsonObj.getString("colorFlag")!="") {
			shoppingDetail.setPantoneId(jsonObj.getString("pantoneId"));
		}
		shoppingDetail.setSurfaceTreatmentId(jsonObj.getString("surfaceTreatmentId"));
		if (jsonObj.containsKey("isBronzing")) {
			shoppingDetail.setIsBronzing(1);
			shoppingDetail.setBronzingLength(Double.valueOf(jsonObj.getString("bronzingLength")));
			shoppingDetail.setBronzingWidth(Double.valueOf(jsonObj.getString("bronzingWidth")));
			shoppingDetail.setBronzingUnit(Integer.valueOf(jsonObj.getString("bronzingUnit")));
		}else {
			shoppingDetail.setIsBronzing(0);
		}
			
		if (jsonObj.containsKey("isConvex")) {
			shoppingDetail.setIsConvex(1);
			shoppingDetail.setConvexLength(Double.valueOf(jsonObj.getString("convexLength")));
			shoppingDetail.setConvexWidth(Double.valueOf(jsonObj.getString("convexWidth")));
			shoppingDetail.setConvexUnit(Integer.valueOf(jsonObj.getString("convexUnit")));
		}else{
			shoppingDetail.setIsConvex(0);
		} if (jsonObj.containsKey("isPvc")) {
			shoppingDetail.setIsPvc(1);
			shoppingDetail.setPvcLength(Double.valueOf(jsonObj.getString("pvcLength")));
			shoppingDetail.setPvcWidth(Double.valueOf(jsonObj.getString("pvcWidth")));
			shoppingDetail.setPvcUnit(Integer.valueOf(jsonObj.getString("pvcUnit")));
		}else{
			shoppingDetail.setIsPvc(0);
		} if (jsonObj.containsKey("isUv")) {
			shoppingDetail.setIsUv(1);
			shoppingDetail.setUvLength(Double.valueOf(jsonObj.getString("uvLength")));
			shoppingDetail.setUvWidth(Double.valueOf(jsonObj.getString("uvWidth")));
			shoppingDetail.setUvUnit(Integer.valueOf(jsonObj.getString("uvUnit")));
		}else {
			shoppingDetail.setIsUv(0);
		}
		shoppingDetail.setReceiveAreaId(jsonObj.getString("receiveAreaId"));
		shoppingDetail.setPrintNumber(Integer.valueOf(jsonObj.getString("printNumber")));
		Map<String, Object> map=new HashMap<>();
		map.put("layoutDetail", layoutDetail);
		map.put("shoppingDetail", shoppingDetail);
		boolean result=shoppingDeatilService.createLayoutAndShopping(map);
		if (result) {
			return shoppingDetail.getShoppingId();
		}else {
			return null;
		}
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
		ShoppingDetail shoppingDetail = shoppingDeatilService.get(jsonObj.getString("shoppingId"));
		return shoppingDetail;
	}
}

