package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.ShoppingOrder;

public interface ShoppingOrderMapper extends BaseMapper<ShoppingOrder, String>{
	int updateByPrimaryKeySelective(ShoppingOrder record);
	 
	List<Map<String, Object>> getPantoneList(Map<String, Object> map);
   
}