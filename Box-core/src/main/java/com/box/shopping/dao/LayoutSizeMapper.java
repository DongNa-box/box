package com.box.shopping.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.shopping.model.LayoutSize;

public interface LayoutSizeMapper extends BaseMapper<LayoutSize, String>{
	List<Map<String,Object>> getLayoutSizelist(Map<String, Object> map);
	
	int updateByPrimaryKeySelective(LayoutSize layoutSize);

	LayoutSize getSizeByName(String name);

	List<Integer> getSizeByType();

	LayoutSize checkNameExists(Map<String,Object> map);

}