package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.ShoppingPantone;

public interface ShoppingPantoneMapper extends BaseMapper<ShoppingPantone, String>{
	 
	int updateByPrimaryKeySelective(ShoppingPantone record);

	List<Map<String, Object>> getPantoneList(Map<String, Object> map);
    
}