
 /**
 * Project Name:Box-core
 * File Name:LayoutSizeService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月4日下午5:46:17
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

/**
 * Project Name:Box-core
 * File Name:LayoutSizeService.java
 * Package Name:com.box.shopping.service
 * Date:2018年4月4日下午5:46:17
 * Copyright (c) 2018,Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
 */
 

package com.box.shopping.service;


import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.shopping.model.LayoutSize;

/**
* ClassName: LayoutSizeService
* Function: TODO ADD FUNCTION.
* Reason: TODO ADD REASON(可选).
* date: 2018年4月4日 下午5:46:17
*
* @author luowen
* @version 
* @since JDK 1.8
*/

public interface LayoutSizeService extends BaseService<LayoutSize, String> {
	List<Map<String,Object>> getLayoutSizelist(Map<String, Object> map);

}

