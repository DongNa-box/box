package com.box.technology.dao;

import com.box.framework.base.dao.BaseMapper;
import com.box.technology.model.TechnologyDetail;

public interface TechnologyDetailMapper extends BaseMapper<TechnologyDetail,String>{

	TechnologyDetail checkTechnologyDetailNameExists(String name);
}