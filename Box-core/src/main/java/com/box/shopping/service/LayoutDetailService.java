
 /**
 * Project Name:Box-core
 * File Name:LayoutDetailService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月4日下午5:19:24
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

/**
 * Project Name:Box-core
 * File Name:LayoutDetailService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月4日下午5:19:24
 * Copyright (c) 2018,Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
 */
 

package com.box.shopping.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.shopping.model.LayoutDetail;

/**
 * ClassName: LayoutDetailService
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2018年4月4日 下午5:19:24
 *
 * @author luowen
 * @version 
 * @since JDK 1.8
 */

public interface LayoutDetailService extends BaseService<LayoutDetail, String> {
	 
	List<Map<String, Object>> getLayoutDetailList(Map<String, Object> map);

	String getMaxId();

	boolean updateImageByid(LayoutDetail d);

}

