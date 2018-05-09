package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.ShoppingDetail;

public interface ShoppingDetailMapper extends BaseMapper<ShoppingDetail, String>{
	List<Map<String, Object>> getShoppingDetailList(Map<String, Object> map);
	 
	int updateByPrimaryKeySelective(ShoppingDetail record);
	 
	List<Map<String, Object>> getInfoByUserIdandShoppingId(Map<String, Object> map);
	 
	int updateByEnabled(Map<String, Object> map);

}