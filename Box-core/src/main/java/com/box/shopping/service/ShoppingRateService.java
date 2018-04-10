
 /**
 * Project Name:Box-core
 * File Name:ShoppingRateService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月4日下午5:55:00
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.shopping.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.shopping.model.ShoppingRate;

/**
 * ClassName:ShoppingRateService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 下午5:55:00
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface ShoppingRateService extends BaseService<ShoppingRate, String> {
	List<Map<String,Object>> getRateList(Map<String, Object> map);

}

