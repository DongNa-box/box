package com.box.boxmanage.dao;

import java.util.List;

import com.box.boxmanage.model.BoxClassification;
import com.box.framework.base.dao.BaseMapper;

public interface BoxClassificationMapper extends BaseMapper<BoxClassification,String> {

	BoxClassification checkBoxClassNameExists(String name);

	List<BoxClassification> getBoxClassificaionByLevel(String level);

	List<BoxClassification> getBoxClassificaionByGroupLevel(String groupID);
 
}