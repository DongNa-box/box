package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.LayoutDetail;

public interface LayoutDetailMapper extends BaseMapper<LayoutDetail, String>{
	 
	List<Map<String, Object>> getLayoutDetailList(Map<String, Object> map);

	int updateByPrimaryKeySelective(LayoutDetail record);

	Map<String,String> getMaxId();

	int updateImageByid(LayoutDetail d);
  
}