
 /**
 * Project Name:Box-core
 * File Name:TechnologyDetailServiceImpl.java
 * Package Name:com.box.technology.service.impl
 * Date:2018年4月12日上午9:47:32
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.technology.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.boxmanage.model.BoxType;
import com.box.technology.dao.TechnologyDetailMapper;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;

/**
 * ClassName:TechnologyDetailServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午9:47:32
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("technologyDetailService")
public class TechnologyDetailServiceImpl implements TechnologyDetailService {
	
	private static final Logger logger = LoggerFactory.getLogger(TechnologyDetailServiceImpl.class);
	@Resource
	TechnologyDetailMapper technologyDetailMapper;
	
	@Override
	public boolean save(TechnologyDetail record) {

		int row=technologyDetailMapper.insert(record);
		return row>0?true:false;

	}

	@Override
	public boolean update(TechnologyDetail record) {

		int row=technologyDetailMapper.updateByPrimaryKey(record);
		return row>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean delete(TechnologyDetail record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public TechnologyDetail get(String id) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public TechnologyDetail get(TechnologyDetail record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<TechnologyDetail> getList(TechnologyDetail record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<TechnologyDetail> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public boolean checkTechnologyDetailNameExists(String name) {
		
		TechnologyDetail b=technologyDetailMapper.checkTechnologyDetailNameExists(name);
		return b==null?false:true;
		
	}

	@Override
	public List<Map<String, Object>> TechnologyDetailSearchList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<TechnologyDetail> getTechnologyByLevel(String level) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<TechnologyDetail> getTechnologyByParentLevel(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public TechnologyDetail getTechnologyByParentid(String groupid) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

}

