package com.box.technology.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.box.framework.base.dao.BaseMapper;
import com.box.framework.pojo.TreeNode;
import com.box.technology.model.TechnologyDetail;

public interface TechnologyDetailMapper extends BaseMapper<TechnologyDetail,String>{

	TechnologyDetail checkTechnologyDetailNameExists(String name);

	List<TechnologyDetail> getTechnologyByParentLevel(Map<String, Object> map);

	List<Map<String,Object>> getAllTechnologyList();

	List<Map<String, Object>> technologyDetailSearchList(@Param("map") Map<String, Object> map);
	 
	List<TreeNode> getTechnologyTree();

	List<TechnologyDetail> getTechnologyByDetail(Map<String, Object> map);
}