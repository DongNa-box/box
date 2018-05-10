
 /**
 * Project Name:Box-core
 * File Name:ShoppingDeatilService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月4日下午5:53:47
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.shopping.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.shopping.model.ShoppingDetail;

/**
 * ClassName:ShoppingDeatilService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 下午5:53:47
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface ShoppingDetailService extends BaseService<ShoppingDetail, String>{
 
	boolean createLayoutAndShopping(Map<String, Object> map);
	 
	List<Map<String, Object>> getShoppingDetailList(Map<String, Object> map);

	List<Map<String, Object>> getInfoByUserIdandShoppingId(Map<String, Object> map);

	boolean updateByEnabled(List<String> list);
}

