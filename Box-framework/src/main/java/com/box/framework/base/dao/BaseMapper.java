
 /**
 * Project Name:PdoneCI-framework
 * File Name:BaseDao.java
 * Package Name:com.pdone.framework.base.dao
 * Date:2017年5月4日上午11:46:06
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.base.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * ClassName:BaseDao
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月4日 上午11:46:06
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface BaseMapper<T,PK extends Serializable> {
	
    int deleteByPrimaryKey(String id);

    int deleteByCustomParameters(Map<String,Object> Parameters);
    
    int batchDeleteByIds(@Param(value = "ids")List<String> ids);
    
    int insert(T record);

    T selectByPrimaryKey(PK primaryKey);

    int updateByPrimaryKey(T record);

	List<T> selectAll();
	
	List<T> selectAllByEntity(T record);

	List<T> selectByCustomParameters(Map<String,Object> Parameters);
}

