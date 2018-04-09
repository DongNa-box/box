
 /**
 * Project Name:Box-core
 * File Name:ShoppingPantoneService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月8日上午10:16:24
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.shopping.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.shopping.model.ShoppingPantone;

/**
 * ClassName:ShoppingPantoneService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月8日 上午10:16:24
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface ShoppingPantoneService extends BaseService<ShoppingPantone, String> {

	boolean batchDeletePantone(List<String> list);

	List<Map<String, Object>> getPantoneList(Map<String, Object> map);

}

