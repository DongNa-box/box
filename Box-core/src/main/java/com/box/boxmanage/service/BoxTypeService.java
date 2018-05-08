
 /**
 * Project Name:Box-core
 * File Name:BoxTypeService.java
 * Package Name:com.box.boxmanage.service
 * Date:2018年4月4日上午10:59:05
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.service;

import java.util.List;
import java.util.Map;


import com.box.boxmanage.model.BoxType;
import com.box.framework.base.service.BaseService;

/**
 * ClassName:BoxTypeService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午10:59:05
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface BoxTypeService extends BaseService<BoxType, String> {

	boolean checkBoxTypeNameExists(String name);

	List<Map<String, Object>> boxTypeSearchList(Map<String, Object> map);

	List<Map<String,Object>> getAllBoxTypeList();

	List<Map<String, Object>> getWebBoxTypeList(Map<String, Object> map);

	Map<String,Object> getAllById(String boxId);

}

