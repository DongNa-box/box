
 /**
 * Project Name:PdoneCI-framework
 * File Name:BaseService.java
 * Package Name:com.pdone.framework.base.service
 * Date:2017年5月4日下午3:42:31
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.base.service;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:BaseService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月4日 下午3:42:31
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public abstract interface BaseService<T,Obj extends Serializable> {
		
		boolean save(T record);

		boolean update(T record);
		
		boolean delete(Obj id);
		
		boolean batchDeleteById(List<Obj> records);
		
		boolean delete(T record);
		
		T get(Obj id);
		
		T get(T record);
		
		List<T> getList(T record);
		
		List<T> getAllList();
		
}

