package com.box.technology.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.box.framework.base.dao.BaseMapper;
import com.box.technology.model.TechnologyPrice;

public interface TechnologyPriceMapper extends BaseMapper<TechnologyPrice,String>{

	List<Map<String, Object>> getAllPriceList();
	
	List<Map<String, Object>> technologyPriceSearchList(@Param("map") Map<String, Object> map);

}