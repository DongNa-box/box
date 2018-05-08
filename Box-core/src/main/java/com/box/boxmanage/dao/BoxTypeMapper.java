package com.box.boxmanage.dao;

import java.util.List;
import java.util.Map;

import com.box.boxmanage.model.BoxType;
import com.box.framework.base.dao.BaseMapper;

public interface BoxTypeMapper extends BaseMapper<BoxType,String>{

	BoxType checkBoxTypeNameExists(String name);

	List<Map<String,Object>> getAllBoxTypeList();

	List<Map<String, Object>> boxTypeSearchList(Map<String, Object> map);

	List<Map<String, Object>> getWebBoxTypeList(Map<String, Object> map);

	Map<String,Object> getAllById(String boxId);
  
}