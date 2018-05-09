package com.box.boxmanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.box.boxmanage.model.BoxClassification;
import com.box.framework.base.dao.BaseMapper;

public interface BoxClassificationMapper extends BaseMapper<BoxClassification,String> {

	List<BoxClassification> getBoxClassificaionByLevel(String level);

	List<BoxClassification> getBoxClassificaionByGroupLevel(@Param("map") Map<String, Object> map);

	BoxClassification getBoxClassificaionByGroupid(String groupid);

	List<BoxClassification> boxClassificationSearchList(String name);

	List<Map<String, Object>> getAllDetailList();
 
}