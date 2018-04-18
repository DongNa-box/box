
 /**
 * Project Name:Box-core
 * File Name:TechnologyPriceService.java
 * Package Name:com.box.technology.service
 * Date:2018年4月12日上午9:56:14
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.technology.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.technology.model.TechnologyPrice;

/**
 * ClassName:TechnologyPriceService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午9:56:14
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface TechnologyPriceService extends BaseService<TechnologyPrice, String> {

	List<Map<String, Object>> technologyPriceSearchList(Map<String, Object> map);

	List<Map<String, Object>> getAllPriceList();

}

