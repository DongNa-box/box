package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.box.boxmanage.model.BoxType;
import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.ShoppingRateService;

public interface ShoppingRateMapper extends BaseMapper<ShoppingRate, String>{
	 
	int updateByPrimaryKeySelective(ShoppingRate record);
	 
	List<Map<String, Object>> getRateList(Map<String, Object> map);
	 
	ShoppingRate getByType(@Param(value="type") int type);

	List<ShoppingRate> getAllList();

  
}