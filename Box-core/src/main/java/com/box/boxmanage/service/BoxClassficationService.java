
 /**
 * Project Name:Box-core
 * File Name:BoxClassficationService.java
 * Package Name:com.box.boxmanage.service
 * Date:2018年4月4日上午10:59:59
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.service;

import java.util.List;

import com.box.boxmanage.model.BoxClassification;
import com.box.framework.base.service.BaseService;

/**
 * ClassName:BoxClassficationService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午10:59:59
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface BoxClassficationService extends BaseService<BoxClassification, String> {

	boolean checkBoxClassNameExists(String name);

	List<BoxClassification> getBoxClassificaionByLevel(String level);

	List<BoxClassification> getBoxClassificaionByGroupLevel(String groupid);

}

