package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import com.box.boxmanage.model.BoxType;
import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.ShoppingRateService;

public interface ShoppingRateMapper extends BaseMapper<ShoppingRate, String>{
	 
	int updateByPrimaryKeySelective(ShoppingRate record);
	 
	List<Map<String, Object>> getRateList(Map<String, Object> map);
	 
	ShoppingRate getByType(int type);

	List<ShoppingRate> getAllList();

  
}