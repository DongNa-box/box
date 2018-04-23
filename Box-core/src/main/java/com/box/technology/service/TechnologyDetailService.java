
 /**
 * Project Name:Box-core
 * File Name:TechnologyDetailService.java
 * Package Name:com.box.technology.service
 * Date:2018年4月12日上午9:46:38
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.technology.service;

import java.util.List;
import java.util.Map;

import com.box.framework.base.service.BaseService;
import com.box.framework.pojo.TreeNode;
import com.box.technology.model.TechnologyDetail;

/**
 * ClassName:TechnologyDetailService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午9:46:38
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface TechnologyDetailService extends BaseService<TechnologyDetail, String> {

	boolean checkTechnologyDetailNameExists(String name);

	List<Map<String, Object>> technologyDetailSearchList(Map<String, Object> map);

	List<TechnologyDetail> getTechnologyByParentLevel(Map<String, Object> map);

	List<Map<String, Object>> getAllTechnologyList();
	
	List<TreeNode> getTechnologyTree();

}

