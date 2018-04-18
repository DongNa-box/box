package com.box.technology.dao;

import java.util.List;
import java.util.Map;

import com.box.framework.base.dao.BaseMapper;
import com.box.technology.model.TechnologyPrice;

public interface TechnologyPriceMapper extends BaseMapper<TechnologyPrice,String>{

	List<Map<String, Object>> getAllPriceList();

}